package com.ctco.testSchool;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;

public class GetPrimeNumberTest {

    @Test
    public void test1() {
        assertEquals(7, Team.getPrimeNumberClosesTo(3), "");
    }

    @Test
    public void test2() {
        assertEquals(7, Team.getPrimeNumberClosesTo(8), "");
    }

    @Test
    public void test3() {
        int result = Team.getPrimeNumberClosesTo(9);
     //   if((result==7) || (result==11)) {
     //       assertTrue(true);
     //   } else {
     //       fail("Returned " + result + " expected was 7 OR 11");
     //   }
        assertTrue("Returned " + result + " expected was 7 OR 11", ((result==7) || (result==11)));
    }

}
