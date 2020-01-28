package com.ctco.testSchool;


import org.junit.Assert;
import org.junit.Test;

public class RainCheckTest {

    @Test
    public void firstTaskHapyPath(){
        String result = FirstTask.concatenate(2, 1, "test");

        //Assert.assertThat(result, "4test");
    }



    @Test
    public void secondTaskHapyPath(){
        Assert.assertTrue(SecondTask.isEven(2));
    }

    @Test
    public void secondTaskNegativePath(){
        Assert.assertFalse(SecondTask.isEven(5));
    }

    @Test
    public void secondTaskZeroDevide(){
        Assert.assertTrue(SecondTask.isEven(0));
    }


    @Test
    public void forthTaskHappyPath(){
        FourthTask forthTask = new FourthTask( 2, 3);
        double result = forthTask.getPerimeter();
        double expected = 10;
        Assert.assertEquals(expected, result, 0.01);
    }


    @Test
    public void fourthTaskWithNegatives(){
        FourthTask forthTask = new FourthTask( -2, 3);
        double result = forthTask.getPerimeter();
        double expected = 10;
        Assert.assertEquals("Perimeter not equal to 10", expected, result, 0.01);
    }

    @Test
    public void fourthTaskZero(){
        FourthTask forthTask = new FourthTask( 0, 3);
        double result = forthTask.getPerimeter();
        double expected = 3;
        Assert.assertEquals("Perimeter not equal to 3", expected, result, 0.01);
    }


    @Test
    public void sixTaskPositiveNumber(){
        Assert.assertEquals("Positive was expected", "positive", SixthTask.isNumberPositive(2));
    }

    @Test
    public void sixTaskNegativeNumber(){
        Assert.assertEquals("Negative was expected", "negative", SixthTask.isNumberPositive(-2));
    }

    @Test
    public void sixTaskPositiveZeroNumber(){
        String expected = "negative";
       String actual = SixthTask.isNumberPositive(0);
        Assert.assertEquals("Negative was expected", expected, actual); // positive will be thrown
    }


    @Test
    public void thirdTaskPositiveZeroNumber(){
        ThirdTask thirdTask = new ThirdTask();
        String[] words = {"Hello, there"};

        thirdTask.getElementPosition(words, "me");
    }

}
