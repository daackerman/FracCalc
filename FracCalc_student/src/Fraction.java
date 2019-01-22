/**
 * Name:
 * Date Due:
 * Description:
 */
public class Fraction {
    //instance variables here

    /**
     * Constructs a typical Fraction from a numerator and denominator.
     * @param n the numerator,
     * @param d the denominator.
     */
    public Fraction(int n, int d) {
        //your code here
    }

    /**
     * Constructs a Fraction equal to an integer. For example, new Fraction(2) would be equal to 2/1.
     * @param n the integer.
     */
    public Fraction(int n) {
        //your code here
    }

    /**
     * Constructs an instance of a Fraction equal to zero.
     */
    public Fraction() {
        //your code here
    }

    /**
     * Gets the numerator for this Fraction.
     * @return the numerator.
     */
    public int getNumerator() {
        //your code here
        return Integer.MAX_VALUE;
    }

    /**
     * Gets the denominator for this Fraction.
     * @return the denominator.
     */
    public int getDenominator() {
        //your code here
        return Integer.MAX_VALUE;
    }

    /**
     * Computes the greatest common divisor between a and b.
     * For example, gcd(14, 21) returns 7.
     * Precondition: a and b are positive integers.
     * @param a a positive integer,
     * @param b a positive integer.
     * @return the greatest common divisor of a and b.
     */
    public static int gcd(int a, int b) {
        //your code here
        return Integer.MAX_VALUE;
    }

    /**
     * Reduces this Fraction to lowest terms.
     */
    public void toLowestTerms() {
        //your code here
    }

    /**
     * Converts this Fraction into a String.
     * @return a String representation of this Fraction.
     */
    @Override
    public String toString() {
        //your code here
        return null;
    }

    /**
     * Converts this Fraction into a double.
     * @return the double that is roughly equivalent to this Fraction.
     */
    public double toDouble() {
        //your code here
        return Double.MAX_VALUE;
    }

    /**
     * Adds this Fraction with another Fraction.
     * @param other the other Fraction.
     * @return the sum of this and other.
     */
    public Fraction add(Fraction other) {
        //your code here
        return null;
    }

    /**
     * Subtracts the other Fraction from this Fraction (other - this).
     * @param other the other Fraction.
     * @return the difference of this and other.
     */
    public Fraction subtract(Fraction other) {
        //your code here
        return null;
    }

    /**
     * Multiplies this Fraction with another Fraction.
     * @param other the other Fraction.
     * @return the product of this and other.
     */
    public Fraction multiply(Fraction other) {
        //your code here
        return null;
    }

    /**
     * Divides this Fraction by another Fraction (other / this).
     * Throws an IllegalArgumentException if other is equal to the zero fraction.
     * @param other the other Fraction.
     * @return the quotient of this and other.
     */
    public Fraction divide(Fraction other) {
        //your code here
        return null;
    }

    /**
     * Compares this Fraction with another Object for equality.
     * Two Fractions are equal if and only if their difference is zero.
     * Note, this method should not modify either Fraction.
     * @param o the other Fraction.
     * @return true if the Fractions are equal,
     *         false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Fraction)) {
            return false;
        }
        Fraction other = (Fraction) o;
        //your code here
        return false;
    }
}
