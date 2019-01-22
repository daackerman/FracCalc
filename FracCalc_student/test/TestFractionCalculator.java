import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

/**
 * Tests for FractionCalculator.java
 */
public class TestFractionCalculator {
    @Test
    public void testGetOperationValid() {
        String charToTest = "+-/*=qQ";
        for (int i = 0; i < charToTest.length(); i++) {
            String op = charToTest.substring(i,i+1);
            Scanner in = new Scanner(op);
            Assert.assertEquals(op, FractionCalculator.getOperation(in));
        }
    }

    @Test
    public void testGetOperationInvalid() {
        Scanner in = new Scanner("this\ndoesn't work\n+-\n1/2\n- *\n/");
        Assert.assertEquals("/", FractionCalculator.getOperation(in));
    }

    @Test
    public void testValidFractionPos() {
        Assert.assertTrue(FractionCalculator.validFraction("2/4"));
    }

    @Test
    public void testValidFractionNeg() {
        Assert.assertTrue(FractionCalculator.validFraction("-17/3"));
    }

    @Test
    public void testValidFractionInt() {
        Assert.assertTrue(FractionCalculator.validFraction("-1"));
        Assert.assertTrue(FractionCalculator.validFraction("0"));
        Assert.assertTrue(FractionCalculator.validFraction("1"));
    }

    @Test
    public void testValidFractionFalseEasy() {
        Assert.assertFalse(FractionCalculator.validFraction("three"));
        Assert.assertFalse(FractionCalculator.validFraction("0.5/2.2"));
        Assert.assertFalse(FractionCalculator.validFraction("one/two"));
    }

    @Test
    public void testValidFractionFalseWeird() {
        Assert.assertFalse(FractionCalculator.validFraction("2/"));
        Assert.assertFalse(FractionCalculator.validFraction("/1"));
        Assert.assertFalse(FractionCalculator.validFraction("1 / 2"));
        Assert.assertFalse(FractionCalculator.validFraction("1/1/2"));
        Assert.assertFalse(FractionCalculator.validFraction("2//1"));
    }


    @Test
    public void testGetFractionValidPos() {
        Scanner in = new Scanner("5/2");
        Fraction f = FractionCalculator.getFraction(in);
        Assert.assertEquals(5, f.getNumerator());
        Assert.assertEquals(2, f.getDenominator());
    }

    @Test
    public void testGetFractionValidNeg() {
        Scanner in = new Scanner("-3/4");
        Fraction f = FractionCalculator.getFraction(in);
        Assert.assertEquals(-3, f.getNumerator());
        Assert.assertEquals(4, f.getDenominator());
    }

    @Test
    public void testGetFractionValidIntPos() {
        Scanner in = new Scanner("27");
        Fraction f = FractionCalculator.getFraction(in);
        Assert.assertEquals(27, f.getNumerator());
        Assert.assertEquals(1, f.getDenominator());
    }

    @Test
    public void testGetFractionValidIntNeg() {
        Scanner in = new Scanner("-2");
        Fraction f = FractionCalculator.getFraction(in);
        Assert.assertEquals(-2, f.getNumerator());
        Assert.assertEquals(1, f.getDenominator());
    }

    @Test
    public void testGetFractionInvalid() {
        Scanner in = new Scanner("3/-4\n0.5\nfoo\nnot a fraction\n1 / 2\nthree/1\n0/10");
        Fraction f = FractionCalculator.getFraction(in);
        Assert.assertEquals(0, f.getNumerator());
        Assert.assertEquals(1, f.getDenominator());
    }
}
