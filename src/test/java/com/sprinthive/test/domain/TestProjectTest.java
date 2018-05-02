package com.sprinthive.test.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestProjectTest {

    private final TestProject testProject = new TestProject();

    @Test
    public void testGreetingGivesCorrectGreeting() {
        assertEquals("Hello Mr. Boaty McBoatface!", testProject.greeting("Mr. Boaty McBoatface"));
        assertEquals("Hello Coffee!", testProject.greeting("Coffee"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGreetingWithNullNameCausesExpectedException() {
        testProject.greeting(null);
    }
}
