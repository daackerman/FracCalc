import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Tests for Fraction.java
 */
public class TestFraction {
    private static int[][] first;
    private static int[][] second;

    @BeforeClass
    public static void init() {
        try {
            Scanner scan = new Scanner(new File("random_fractions.txt"));
            if (first == null) {
                first = new int[1000][2];
                second = new int[1000][2];
                Scanner line = new Scanner(scan.nextLine());
                line.next();
                int i = 0;
                while (line.hasNext()) {
                    first[i] = parseFraction(line.next());
                    i++;
                }
                line = new Scanner(scan.nextLine());
                line.next();
                i = 0;
                while (line.hasNext()) {
                    second[i] = parseFraction(line.next());
                    i++;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found");
        }
    }

    @AfterClass
    public static void TearDown() {
        first = null;
        second = null;
    }

    @Test
    public void testConstructorTwoParam() {
        Fraction f = new Fraction(3,2);
        Assert.assertEquals(3, f.getNumerator());
        Assert.assertEquals(2, f.getDenominator());
    }

    @Test
    public void testConstructorTwoParamNegD() {
        Fraction f = new Fraction(5,-3);
        Assert.assertEquals(-5, f.getNumerator());
        Assert.assertEquals(3, f.getDenominator());
    }

    @Test
    public void testConstructorZero() {
        Fraction f = new Fraction(0, 4);
        Assert.assertEquals(0, f.getNumerator());
        Assert.assertEquals(1, f.getDenominator());
    }

    @Test
    public void testConstructorOneParam() {
        Fraction f = new Fraction(5);
        Assert.assertEquals(5, f.getNumerator());
        Assert.assertEquals(1, f.getDenominator());
    }

    @Test
    public void testConstructorNoParam() {
        Fraction f = new Fraction();
        Assert.assertEquals(0, f.getNumerator());
        Assert.assertEquals(1, f.getDenominator());
    }

    @Test
    public void testConstructorException() {
        boolean threwException = false;
        try {
            new Fraction(3,0);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }
        Assert.assertTrue(threwException);
    }

    @Test
    public void testGetNumerator() {
        Assert.assertEquals(3, new Fraction(3,5).getNumerator());
    }

    @Test
    public void testGetDenominator() {
        Assert.assertEquals(5, new Fraction(3,5).getDenominator());
    }

    //May or may not use this Test depending on if gcd is public/private
    @Test
    public void testGCD() throws FileNotFoundException{
        Scanner scan = new Scanner(new File("random_fractions.txt"));
        while (scan.hasNextLine()) {
            Scanner line = new Scanner(scan.nextLine());
            if (line.next().equals("gcd")) {
                for(int[] f: first) {
                    Assert.assertEquals(line.nextInt(), Fraction.gcd(f[0], f[1]));
                }
            }
        }
    }

    @Test
    public void testToLowestTermsEasy() {
        Fraction f = new Fraction(2,4);
        f.toLowestTerms();
        Assert.assertEquals(f.getNumerator(), 1);
        Assert.assertEquals(f.getDenominator(), 2);
    }

    @Test
    public void testToLowestTermsMedium() {
        Fraction f = new Fraction(24, -72);
        f.toLowestTerms();
        Assert.assertEquals(f.getNumerator(), -1);
        Assert.assertEquals(f.getDenominator(), 3);
    }

    @Test
    public void testToLowestTermsZero() {
        Fraction f = new Fraction(0, -10);
        f.toLowestTerms();
        Assert.assertEquals(f.getNumerator(), 0);
        Assert.assertEquals(f.getDenominator(), 1);
    }

    @Test
    public void testToStringFrac() {
        Fraction f = new Fraction(4,5);
        Assert.assertEquals(f.toString(), "4/5");
    }

    @Test
    public void testToStringInt() {
        Fraction f = new Fraction(-3, 1);
        Assert.assertEquals("-3", f.toString());
    }

    @Test
    public void testEqualsEasy() {
        Fraction fOne = new Fraction(3, 4);
        Fraction fTwo = new Fraction(3, 4);
        Assert.assertEquals(fOne, fTwo);
    }

    @Test
    public void testEqualsMedium() {
        Fraction fOne = new Fraction(-2, 6);
        Fraction fTwo = new Fraction(1, -3);
        Assert.assertTrue(fOne.equals(fTwo));
    }

    @Test
    public void testNotEquals() {
        Fraction fOne = new Fraction(1, 2);
        Fraction fTwo = new Fraction(1, 3);
        Assert.assertFalse(fOne.equals(fTwo));
        Fraction fThree = new Fraction(3, 6);
        Fraction fFour = new Fraction(1, -2);
        Assert.assertFalse(fThree.equals(fFour));
        Fraction fFive = new Fraction(3, 2);
        Fraction fSix = new Fraction(2, 3);
        Assert.assertFalse(fFive.equals(fSix));
    }

    @Test
    public void testAddEasy() {
        Fraction sum = new Fraction(1,3).add(new Fraction(4,3));
        Assert.assertEquals(5, sum.getNumerator());
        Assert.assertEquals(3, sum.getDenominator());
    }

    @Test
    public void testAddMedium() {
        Fraction sum = new Fraction(-9,5).add(new Fraction(13,10));
        Assert.assertEquals(-1, sum.getNumerator());
        Assert.assertEquals(2, sum.getDenominator());
    }

    @Test
    public void testAddInt() {
        Fraction sum = new Fraction(3).add(new Fraction(-4));
        Assert.assertEquals(-1, sum.getNumerator());
        Assert.assertEquals(1, sum.getDenominator());
    }

    @Test
    public void testAddHard() throws FileNotFoundException{
        Scanner scan = new Scanner(new File("random_fractions.txt"));
        while (scan.hasNextLine()) {
            Scanner line = new Scanner(scan.nextLine());
            if (line.next().equals("add")) {
                for(int i = 0; i < 1000; i++) {
                    Fraction fOne = new Fraction(first[i][0], first[i][1]);
                    Fraction fTwo = new Fraction(second[i][0], second[i][1]);
                    Fraction sum = fOne.add(fTwo);
                    int[] correct = parseFraction(line.next());

                    Assert.assertEquals(correct[0], sum.getNumerator());
                    Assert.assertEquals(correct[1], sum.getDenominator());
                }
            }
        }
    }

    @Test
    public void testSubEasy() {
        Fraction diff = new Fraction(3,4).subtract(new Fraction(2,4));
        Assert.assertEquals(1, diff.getNumerator());
        Assert.assertEquals(4, diff.getDenominator());
    }

    @Test
    public void testSubMedium() {
        Fraction diff = new Fraction(6,20).subtract(new Fraction(11,10));
        Assert.assertEquals(-4, diff.getNumerator());
        Assert.assertEquals(5, diff.getDenominator());
    }

    @Test
    public void testSubInt() {
        Fraction diff = new Fraction(5).subtract(new Fraction(3));
        Assert.assertEquals(2, diff.getNumerator());
        Assert.assertEquals(1, diff.getDenominator());
    }

    @Test
    public void testSubHard() throws FileNotFoundException{
        Scanner scan = new Scanner(new File("random_fractions.txt"));
        while (scan.hasNextLine()) {
            Scanner line = new Scanner(scan.nextLine());
            if (line.next().equals("sub")) {
                for(int i = 0; i < 1000; i++) {
                    Fraction fOne = new Fraction(first[i][0], first[i][1]);
                    Fraction fTwo = new Fraction(second[i][0], second[i][1]);
                    Fraction diff = fOne.subtract(fTwo);
                    int[] correct = parseFraction(line.next());

                    Assert.assertEquals(correct[0], diff.getNumerator());
                    Assert.assertEquals(correct[1], diff.getDenominator());
                }
            }
        }
    }

    @Test
    public void testMulEasy() {
        Fraction prod = new Fraction(1,3).multiply(new Fraction(2,5));
        Assert.assertEquals(2, prod.getNumerator());
        Assert.assertEquals(15, prod.getDenominator());
    }

    @Test
    public void testMulMedium() {
        Fraction prod = new Fraction(3,4).multiply(new Fraction(2,-6));
        Assert.assertEquals(-1, prod.getNumerator());
        Assert.assertEquals(4, prod.getDenominator());
    }

    @Test
    public void testMulInt() {
        Fraction prod = new Fraction(3).multiply(new Fraction(-2));
        Assert.assertEquals(-6, prod.getNumerator());
        Assert.assertEquals(1, prod.getDenominator());
    }

    @Test
    public void testMulHard() throws FileNotFoundException{
        Scanner scan = new Scanner(new File("random_fractions.txt"));
        while (scan.hasNextLine()) {
            Scanner line = new Scanner(scan.nextLine());
            if (line.next().equals("mul")) {
                for(int i = 0; i < 1000; i++) {
                    Fraction fOne = new Fraction(first[i][0], first[i][1]);
                    Fraction fTwo = new Fraction(second[i][0], second[i][1]);
                    Fraction prod = fOne.multiply(fTwo);
                    int[] correct = parseFraction(line.next());

                    Assert.assertEquals(correct[0], prod.getNumerator());
                    Assert.assertEquals(correct[1], prod.getDenominator());
                }
            }
        }
    }

    @Test
    public void testDivEasy() {
        Fraction quotient = new Fraction(1,4).divide(new Fraction(3,5));
        Assert.assertEquals(5, quotient.getNumerator());
        Assert.assertEquals(12, quotient.getDenominator());
    }

    @Test
    public void testDivMedium() {
        Fraction quotient = new Fraction(-11,4).divide(new Fraction(3,-8));
        Assert.assertEquals(22, quotient.getNumerator());
        Assert.assertEquals(3, quotient.getDenominator());
    }

    @Test
    public void testDivInt() {
        Fraction quotient = new Fraction(-5).divide(new Fraction(-10));
        Assert.assertEquals(1, quotient.getNumerator());
        Assert.assertEquals(2, quotient.getDenominator());
    }

    @Test
    public void testDivException() {
        boolean threwException = false;
        try {
            new Fraction(1,2).divide(new Fraction());
        } catch (IllegalArgumentException e) {
            threwException = true;
        }
        Assert.assertTrue(threwException);
    }

    @Test
    public void testDivHard() throws FileNotFoundException{
        Scanner scan = new Scanner(new File("random_fractions.txt"));
        while (scan.hasNextLine()) {
            Scanner line = new Scanner(scan.nextLine());
            if (line.next().equals("div")) {
                for(int i = 0; i < 1000; i++) {
                    Fraction fOne = new Fraction(first[i][0], first[i][1]);
                    Fraction fTwo = new Fraction(second[i][0], second[i][1]);
                    Fraction quot = fOne.divide(fTwo);
                    int[] correct = parseFraction(line.next());

                    Assert.assertEquals(correct[0], quot.getNumerator());
                    Assert.assertEquals(correct[1], quot.getDenominator());
                }
            }
        }
    }

    @Test
    public void testToDoubleEasy() {
        Assert.assertEquals(0.5, new Fraction(1, 2).toDouble(), 0.001);
    }

    @Test
    public void testToDoubleHard() throws FileNotFoundException{
        Scanner scan = new Scanner(new File("random_fractions.txt"));
        while (scan.hasNextLine()) {
            Scanner line = new Scanner(scan.nextLine());
            if (line.next().equals("doubles")) {
                for(int i = 0; i < 1000; i++) {
                    Fraction fOne = new Fraction(first[i][0], first[i][1]);

                    Assert.assertEquals(line.nextDouble(), fOne.toDouble(), 0.001);
                }
            }
        }
    }

    private static int[] parseFraction(String frac) {
        int correctNum;
        int correctDenom;
        if (frac.contains("/")) {
            correctNum = Integer.parseInt(frac.substring(0, frac.indexOf("/")));
            correctDenom = Integer.parseInt(frac.substring(frac.indexOf("/") + 1));
        } else {
            correctNum = Integer.parseInt(frac);
            correctDenom = 1;
        }
        return new int[]{correctNum,correctDenom};
    }
}
