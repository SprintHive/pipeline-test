package com.sprinthive.test.controller.model;

import lombok.Value;
import lombok.Builder;

@Value
@Builder
public class GreetingResponseV1 {
    private String greeting;
}
