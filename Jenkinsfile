#!/usr/bin/groovy
@Library('github.com/SprintHive/sprinthive-pipeline-library')

def componentName = 'test-project'
def versionTag = ''
def resourcesDir = 'config/kubernetes'
def dockerImage

gradleNode(label: 'gradle-and-docker') {

    def namespace = ''
    def deployStage = ''

    stage('Compile source') {
        def scmInfo = checkout scm
        if (scmInfo == null || scmInfo.GIT_BRANCH == null) {
            currentBuild.result = 'ABORTED'
            error('Git branch is null…')
        } else if (scmInfo.GIT_BRANCH == 'origin/master') {
            namespace = 'pre-prod'
            deployStage = 'pre-production'
        } else {
            namespace = scmInfo.GIT_BRANCH.startsWith('origin/') ? scmInfo.GIT_BRANCH - 'origin/' : scmInfo.GIT_BRANCH
            deployStage = namespace
        }

        versionTag = getNewVersion {}
        dockerImage = "${componentName}:${versionTag}"

        container(name: 'gradle') {
            sh "gradle bootJar"
        }
    }

    stage('Publish docker image') {
        container('docker') {
            sh "docker build -t ${dockerImage} ."
        }
    }

    stage("Rollout to ${namespace}") {
        def kubeResources = kubeResourcesFromTemplates {
            templates = [
                readFile(resourcesDir + '/deployment.yaml'),
                readFile(resourcesDir + '/service.yaml')
            ]
            stage = deployStage
            version = versionTag
            image = dockerImage
            name = componentName
        }

        for (String kubeResource : kubeResources) {
            kubernetesApply(file: kubeResource, environment: namespace)
        }
    }
}
