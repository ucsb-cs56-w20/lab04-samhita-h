/** A class to represent a rational number
    with a numerator and denominator

    @author P. Conrad for CS56 F16

    */
package edu.ucsb.cs56.ratcalc.model;

public class Rational {

    private int num;
    private int denom;

    /** 
	greatest common divisor of a and b
	@param a first number
	@param b second number
	@return gcd of a and b
    */
    public static int gcd(int a, int b) {
	if (a==0)
	    return b;
	else if (b==0)
	    return a;
	else
	    return gcd(b%a, a);
    }
    
    public Rational() {
	this.num = 1;
	this.denom = 1;
    }

    public Rational(int num, int denom) {
	if (denom== 0) {
	    throw new IllegalArgumentException("denominator may not be zero");
	}
	this.num = num;
	this.denom = denom;
	if (num != 0) {
	    int gcd = Rational.gcd(num,denom);
	    this.num /= gcd;
	    this.denom /= gcd;
	}
	// Flip the negative sign to the numerator instead of the denom if necessary (For formatting issues)
	if(this.getDenominator() < 0 && this.getNumerator() > 0)//(r1.getDenominator() < 0) 
	{
		this.num = this.num * -1;
		this.denom = this.denom * -1;
	}
    }

    public String toString() {
	if (denom == 1 || num == 0)
	    return "" + num;
	return num + "/" + denom;
    }

    public int getNumerator() { return this.num; }
    public int getDenominator() { return this.denom; }

    public Rational times(Rational r) {
	return new Rational(this.num * r.num,
			    this.denom * r.denom);
    }

    public static Rational product(Rational a, Rational b) {
	return new Rational(a.num * b.num,
			    a.denom * b.denom);
    }

    public static int lcm(int a, int b) {
    	int x = Math.abs(a * b);
	int lcm = Math.abs(x / gcd(a,b));

	return lcm;
    }

    public Rational plus(Rational r) {
    	int lcm = lcm(this.getDenominator(), r.getDenominator());
    	int thisNumerator = this.getNumerator() * (lcm / this.getDenominator()); 
	int rNumerator = r.getNumerator() * (lcm / r.getDenominator()); 

	Rational r1 = new Rational(thisNumerator + rNumerator, lcm);

	

	return r1; 
    }

    public static Rational sum(Rational a, Rational b) {
    	return a.plus(b);
    }

    public Rational minus(Rational r)
    {
	Rational r1 = new Rational(r.getNumerator() * -1, r.getDenominator());
	return this.plus(r1);
    }

    public static Rational difference(Rational a, Rational b)
    {
	return a.minus(b);
    }

    public Rational reciprocalOf() //throws java.lang.ArithmeticExpression 
    {
    	if(this.getNumerator() == 0) throw new java.lang.ArithmeticException();
    	return new Rational(this.getDenominator(), this.getNumerator());
    }

    public Rational dividedBy(Rational r)
    {
    	Rational r1 = r.reciprocalOf();
	return product(this, r1);
    }

    public static Rational quotient(Rational a, Rational b)
    {
    	return a.dividedBy(b);
    }

    
    /** 
	For testing getters.  
	@param args unused
     */

    public static void main (String [] args) {
	Rational r = new Rational(5,7);
	System.out.println("r.getNumerator()=" + r.getNumerator());
	System.out.println("r.getDenominator()=" + r.getDenominator());
    }

    
}
