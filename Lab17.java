import javax.lang.model.util.ElementScanner6;
import java.lang.Math;
// Main Class
class Lab17 {
  public static void main(String[] args) {
    Fraction f = new Fraction(26,3);
    Fraction g = new Fraction(4,0);
    Fraction h = new Fraction(3,-7);
    System.out.println("8 2/3:\t" + f.toMixedNumber());
    System.out.println("8:\t" + f.integerPart());
    System.out.println("2:\t" + f.remainder());
    System.out.println("true:\t" + g.isError());
    System.out.println("false:\t" + h.isPositive());
    System.out.println("26/3:\t" + Fraction.max(f,h).toString());

  }
}

class Fraction {
  // Constants (Final Static Variables)
  // Static/Class Variables
  private static int objectCount=0; // Number of Fractions created
  private static int methodCount=0; // Number of times methods have been called.
  // Nonstatic/Instance Variables
  private int numerator;
	private int denominator;
  // Constructors
  public Fraction() {
    numerator = 1;
    denominator = 1;
  }
  public Fraction(int n, int d){
    numerator = n;
    denominator = d;
  }
  public Fraction(Fraction f){
     setNumerator(f.numerator);
     setDenominator(f.denominator);
  }
  // Getters/Setters
  public int getObjectCount(){
    return objectCount;
  }
  public int getMethodCount(){
    return methodCount;
  }
  public int getNumerator(){
    return numerator;
  }
  public int getDenominator(){
    return denominator;
  }
  public void setNumerator(int numer){
    this.numerator = numer;
  }
  public void setDenominator(int denom){
    this.denominator = denom;
  }
  // Static Methods
  public static int integerPart(Fraction f){
    return f.numerator / f.denominator;
  }
  public static int abs(int a){
    return (int)Math.abs(a);
  }
  public static Fraction min(Fraction a, Fraction b){
    double aDouble = a.numerator / a.denominator;
    double bDouble = b.numerator / b.denominator;
    if (aDouble > bDouble){
      Fraction f = new Fraction(b);
      return f;
    }
    else {
      Fraction f = new Fraction(a);
      return f;
    }
  }
  public static Fraction max(Fraction a, Fraction b){
    double aDouble = a.numerator / a.denominator;
    double bDouble = b.numerator / b.denominator;
    if (aDouble > bDouble){
      Fraction f = new Fraction(a);
      return f;
    }
    else {
      Fraction f = new Fraction(b);
      return f;
    }
  }
  public static Fraction copy(Fraction a){
    Fraction rv = new Fraction(a.getNumerator(), a.getDenominator());
    return rv;
  }
  public static Fraction[] quadraticFormula(Fraction a, Fraction b, Fraction c){
    a.setDenominator(a.getNumerator()*4);
    Fraction expression = copy((b.product(b)).difference(a.product(c)));
    double expressionDouble = expression.getNumerator() / expression.getDenominator();
    Fraction[] rv = new Fraction[2];
    if (expressionDouble == 0){
      rv[0].setNumerator(-1*b.getNumerator()*a.getDenominator());
      rv[0].setDenominator(4*a.getNumerator());
      return rv;
    }
    if (expressionDouble < 0){
      return null;
    }
    else{
      rv[0] = new Fraction()
    }
  }
  // Nonstatic Methods
  public String toString(){
    methodCount++;
    return numerator + "/" + denominator;
  }
  public String toMixedNumber(){
    methodCount++;
    int whole = numerator/denominator;
    if (numerator == 0)
      return whole + "";
    else
      return whole + " " + (numerator - (denominator*whole)) + "/" + denominator;
  }
  public int integerPart(){
    return numerator/denominator;
  }
  public int remainder(){
    return numerator - (denominator*(numerator/denominator));
  }
  public boolean isError(){
    if (denominator == 0){
      return true;
    }
    return false;
  }
  public boolean isPositive(){
    if ((numerator >=  0 && denominator > 0) || (numerator <= 0 && denominator < 0)){
      return true;
     }
    return false;
  }
  public boolean isSimplified(){
    for (int i = 2; i<=denominator; i++){
      if (numerator%i == 0){
        return false;
      }
    }
    if (numerator < 0 && denominator < 0){
     return false; 
    }
    return true;
  }
  public void simplify(){
    if (isSimplified()){
      for (int i = 2; i<=denominator; i++){
        if (numerator%i == 0){
          numerator /= i;
          denominator /= i;
        }
      }
    }
  }
  public Fraction simplified(){
    Fraction f = new Fraction(numerator, denominator);
    f.simplify();
    return f;
  }
  public Fraction sum(Fraction a){
    Fraction sum = new Fraction();
    if (a.denominator == denominator){
      sum.setNumerator(numerator + a.numerator);
      sum.setDenominator(denominator);
      return sum;
    }
    else {
      sum.setNumerator(numerator*a.denominator + a.numerator*denominator);
      sum.setDenominator(denominator*a.denominator);
      sum.simplify();
      return sum;
    }
  }
  public Fraction sum(Fraction[] a){
    Fraction sums = new Fraction();
    for (int i = 0; i<a.length-1; i++){
      sums = a[i].sum(a[i+1]);
    }
    sums.simplify();
    return sums;
  }
  public Fraction difference(Fraction a){
    Fraction sum = new Fraction();
    if (a.denominator == denominator){
      sum.setNumerator(numerator - a.numerator);
      sum.setDenominator(denominator);
      sum.simplify();
      return sum;
    }
    else {
      sum.setNumerator(numerator*a.denominator - a.numerator*denominator);
      sum.setDenominator(denominator*a.denominator);
      sum.simplify();
      return sum;
    }
  }
  public Fraction product(Fraction a){
    Fraction prod = new Fraction();
    prod.setNumerator(numerator*a.numerator);
    prod.setDenominator(denominator*a.denominator);
    prod.simplify();
    return prod;
  }  
  public Fraction quotient(Fraction a){
    Fraction quot = new Fraction();
    quot.setNumerator(numerator * a.denominator);
    quot.setDenominator(denominator*a.numerator);
    quot.simplify();
    return quot;
  }
  public Fraction sqrt(){
    Fraction f = new Fraction(numerator, denominator);
    for (int i = 1; i*i<=numerator; i++){
      f.numerator = i;
    }
    for (int k = 1; k*k<=numerator; k++){
      f.denominator = k;
    }
    f.simplify();
    return f;
  }
}
