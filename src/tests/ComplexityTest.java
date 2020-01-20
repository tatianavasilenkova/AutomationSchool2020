package tests;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

import com.ctco.testSchool.Complexity;
import com.ctco.testSchool.Step;
import org.junit.Test;

public class ComplexityTest extends Complexity {

    // test cases for complexity


    @Test
    @Step("Test word 'margarīns'")
    public void testWordComplexityAllInLowerCase() {
        double expected = 3.5;
        String word = "margarīns";
        assertEquals(Complexity.getWordComplexity("margarīns"), expected);
    }

    @Test
    @Step("Test word 'Mar-ga-rīns'")
    public void testWordComplexityFirstCapital() {
        double expected = 3.5;
        String word = "Margarīns";
        assertEquals(Complexity.getWordComplexity(word), expected);
    }

    @Test
    @Step("Test word with no 'R' letter in it")
    public void testWordComplexityNoRInWord() {
        double expected = 0.5;
        String word = "Ass";
        assertEquals(Complexity.getWordComplexity(word), expected);
    }

    @Test
    @Step("Test word with two 'R' letter in it")
    public void testWordComplexityWith2R() {
        double expected = 4;
        String word = "Korrespondents";
        assertEquals(Complexity.getWordComplexity(word), expected);
    }

    @Test
    @Step("Test word that contains number and spaces")
    public void testWordComplexityWithNumberAndSpace() {
        double expected = 2.5;
        String word = "1_Birznieks ";
        assertEquals(Complexity.getWordComplexity(word), expected);
    }

    @Test
    @Step("Test string that contains punctuation")
    public void testWordComplexityWithPunctuation() {
        double expected = 14;
        String string = "latviešu valodā, šaurs darbības vārdu nenoteiksmē... vai nē!?";
        assertEquals(Complexity.getWordComplexity(string), expected);                             // issue with ','. This symbol counts as 0,5
    }

    @Test
    @Step("Test string that contains soft consonant")
    public void testWordComplexityWithSoftConsonant() {
        double expected = 1;
        String string = "pūķis";
        assertEquals(Complexity.getWordComplexity(string), expected);
    }
}