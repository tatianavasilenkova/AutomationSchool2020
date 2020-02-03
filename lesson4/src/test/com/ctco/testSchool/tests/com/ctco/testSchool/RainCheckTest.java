package com.ctco.testSchool;


import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class RainCheckTest {

    @Test
    public void testFirstTaskHapyPath(){
          assertEquals("CheckThis", FirstTask.concatenate(5, 6, null), "6null6");
    }


    @Test
    @DisplayName("Do not touch")
    public void testSecondTask(){
        assertEquals("CheckThis", new SecondTask().isEven(4), "true");
    }

    @Test
    public void testThirdTask(){
        ThirdTask thirdTask = new ThirdTask();
        String[] myArray = {"abv", "ABV", "ABVABV"};
        assertEquals(2, thirdTask.getElementPosition(myArray, "aBV"));
    }

    @Test
    public void testFourthTask(){
       FourthTask fourthTask = new FourthTask(0, 0);
        assertEquals(5, fourthTask.getPerimeter(), 0.01);
    }


    @Test
    public void testFifthTask(){
        FourthTask fourthTask = new FourthTask(0, 0);
        assertEquals("true", new FifthTask().isRectangle(fourthTask));
    }

    @Test
    public void testSixTask(){
        assertEquals("positive", SixthTask.isNumberPositive(5));
    }


    @Test
    public void testSeventhTask(){
        SeventhTask.Dog dog = new SeventhTask.Dog("Bublique");
        assertEquals("null", SeventhTask.getDogBreed(dog));   // sobaka bez porodi
    }


    @Test
    @DisplayName("Test with object = null")
    public void testSeventhTaskNull(){
        SeventhTask.Dog dog = new SeventhTask.Dog("Bublique");
        assertEquals("null", SeventhTask.getDogBreed(null));   // sobaka bez porodi
    }


    @Test
    public void testEightTask(){
       // SeventhTask.Dog dog = new SeventhTask.Dog("Bublique");


        //assertEquals("null", SeventhTask.getDogBreed(dog));   // sobaka bez porodi
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
        assertEquals(expected, result, 0.01);
    }


    @Test
    public void fourthTaskWithNegatives(){
        FourthTask forthTask = new FourthTask( -2, 3);
        double result = forthTask.getPerimeter();
        double expected = 10;
        assertEquals("Perimeter not equal to 10", expected, result, 0.01);
    }

    @Test
    public void fourthTaskZero(){
        FourthTask forthTask = new FourthTask( 0, 3);
        double result = forthTask.getPerimeter();
        double expected = 3;
        assertEquals("Perimeter not equal to 3", expected, result, 0.01);
    }


    @Test
    public void sixTaskPositiveNumber(){
        assertEquals("Positive was expected", "positive", SixthTask.isNumberPositive(2));
    }

    @Test
    public void sixTaskNegativeNumber(){
        assertEquals("Negative was expected", "negative", SixthTask.isNumberPositive(-2));
    }

    @Test
    public void sixTaskPositiveZeroNumber(){
        String expected = "negative";
       String actual = SixthTask.isNumberPositive(0);
        assertEquals("Negative was expected", expected, actual); // positive will be thrown
    }


    @Test
    public void thirdTaskPositiveZeroNumber(){
        ThirdTask thirdTask = new ThirdTask();
        String[] words = {"Hello, there"};

        thirdTask.getElementPosition(words, "me");
    }

}
