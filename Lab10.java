class Money {
    public int dollars, cents;
}

class ComplexNumber {
    public double a, b;
}

class DateClass{
    public int month, day, year;
}

class Fraction {
    public int numberator, denominator;
}

class Point {
    public double x, y;
}

class CreditCardAccount {
    public String firstName, lastName, bankName;
    public int accountNumber, dollars, cents, cvc, expirationMonth, expirationYear, type; // 0 =  visa, 1 = mastercard
}

public class Lab10{ 
    public static void main(String[] args){
        Money test = new Money();
        test.dollars=15; test.cents=20;
        System.out.println(moneyToString(payWith20(test)));
    }
    

    public static String moneyToString (Money m){
        return "$" + m.dollars + "." + m.cents;    
    }

    public static Money add(Money m1, Money m2){
        Money rv = new Money();
        rv.dollars = m1.dollars + m2.dollars;
        rv.cents = rv.cents + rv.cents;
        return rv;
    }


    public static Money payWith20(Money m){
        Money rv = new Money();
        int cents = m.dollars*100 + m.cents;
        cents = 2000 - cents;
        rv.dollars = cents/100;
        cents %= 100;
        rv.cents = cents;

        return rv;
    }

    public static Money applyInterest(Money m, double interestRate){
        Money rv = new Money();

        return rv;
    }

    public static void testCodeMoney(){
        Money[] objects = new Money[100];
        for (int i = 0; i< objects.length; i++){
            
        }
    }
    
}

