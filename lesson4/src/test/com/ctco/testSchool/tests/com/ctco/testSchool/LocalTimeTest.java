package com.ctco.testSchool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;

public class LocalTimeTest {

    @Test
    public void test1(){
        String expected = "Hello world!";
        assertEquals("current time is 17, greeting is incorrect", Team.getHelloWorldText(), expected);
    }
/**
    @Test
    public void test2(){ ..// ask for code
        int localHour = LocalDateTime.now().getHour();
        String A = "Hello world!";
        String B = "Good morning world!";
        String C =  "Good night world!";
        String D = "Good day world!";
        String actualResult = Team.getHelloWorldText();


       // if (localHour == )
    }
*/




}
