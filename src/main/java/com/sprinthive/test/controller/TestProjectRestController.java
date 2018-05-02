package com.sprinthive.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sprinthive.test.domain.TestProject;
import com.sprinthive.test.controller.model.GreetingRequestV1;
import com.sprinthive.test.controller.model.GreetingResponseV1;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1")
public class TestProjectRestController {

    @Autowired
    TestProject testProject;

    @RequestMapping(method = RequestMethod.POST, value = "/greeting")
    public GreetingResponseV1 greeting(@Valid @RequestBody GreetingRequestV1 request) {
        log.debug("Received a GreetingRequest");

        String greeting = testProject.greeting(request.getName());

        return GreetingResponseV1.builder()
                .greeting(greeting)
                .build();
    }
}
