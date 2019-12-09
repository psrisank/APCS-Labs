class MathClass{
    // Constants (Final Static Variables)
    public static final double E = 2.72;
    public static final double PI = 3.1416;
    // Static/Class Variables
    private static int count = 0;
    // Nonstatic/Instance Variables
    // Constructors
    public MathClass(){}
    // Getters/Setters
    public static int getCount(){
        return count;
    }
    // Static Methods
    public static String abs(double value){
        if (value < 0){
            return value*-1 + "";
        }
        count++;
        return value + "";
        
    }
    public static String abs(int value){
        if (value < 0){
            return value*-1 + "";
        }
        count++;
        return value + "";
        
    }
    // Nonstatic Methods
    public String absNS(double value){
        count++;
        return abs(value);
    }
    public String absNS(int value){
        count++;
        return abs(value);
    }
    public int sqrtNS(int value){
        int number = Integer.parseInt(abs(value));
        int rv = 0;
        for (int i = 0; i*i <= number; i++){
            if (i*i <= number){
                rv = i;
            }
        }
        count++;
        return rv;
    }
}

// Main Class
public class Lab16{
    public static void main(String[] args){
        System.out.println("Math.E = " + Math.E);
        System.out.println("MathClass.E = " + MathClass.E);
        System.out.println("Math.PI = " + Math.PI);
        System.out.println("MathClass.PI = " + MathClass.PI);
        MathClass m = new MathClass();
        // You can access static variables through objects and class.
        // But you cannot access nonstatic variables through the class.
        System.out.println("m.E = " + m.E);
        System.out.println("m.PI = " + m.PI);

        System.out.println("Math.abs(4.0) = " + Math.abs(4.0));
        System.out.println("MathClass.abs(4.0) = " + MathClass.abs(4.0));
        System.out.println("Math.abs(4) = " + Math.abs(4));
        System.out.println("MathClass.abs(4) = " + MathClass.abs(4));

        MathClass m2 = new MathClass();
        System.out.println("m2.absNS(4.0) = " + m2.absNS(4.0));
        System.out.println("m2.abs(4.0) = " + m2.abs(4.0));
        System.out.println("m2.absNS(4) = " + m2.absNS(4));
        System.out.println("m2.abs(4) = " + m2.abs(4));

        System.out.println("m2.sqrt(10) = " + m2.sqrtNS(10));

        MathClass m3 = new MathClass();
        for(int i=0; i<10; i++)
            System.out.println("m3.sqrtNS(" + i + ") = " + m3.sqrtNS(i));
        System.out.println("MathClass.getCount() = " + MathClass.getCount());
        System.out.println("m3.getCount() = " + m3.getCount());
        

    }
}