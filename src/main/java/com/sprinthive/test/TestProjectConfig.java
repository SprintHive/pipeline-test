package com.sprinthive.test;

import com.sprinthive.test.domain.TestProject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestProjectConfig {

    @Bean
    TestProject testProject() {
        return new TestProject();
    }
}
