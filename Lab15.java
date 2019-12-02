class Lab15{
    public static void main(String[] args){
        Stack s = new Stack();
        for(int i=1; i<=100; i++)
            s.push(i);
        // Now, the Stack is full. If you push 1 more element, it would be out of bounds.
        // But, instead of letting the program crash, make a new 200 element array, copy over
        // the old 100 values, then add in the 101st element.
        s.push(101);
        System.out.println(s.toString());
        System.out.println(s.pop());

    }
}

class Stack{
    private int[] storage;
    private int numberOfElements;

    public Stack(){
        storage = new int[100];
        numberOfElements = 0;
    }

    public int[] getStorage(){
        return copyArray(storage);
    }

    public int getNumberOfElements(){
        return numberOfElements;
    }

    public void push(int number){
        
        if (numberOfElements+1 > storage.length){
            int[] newArray = new int[storage.length + 100];
            for (int i = 0; i < storage.length; i++){
                newArray[i] = storage[i];
            }
            storage = newArray;
        }
        storage[numberOfElements] = number;
        numberOfElements++;
    }

    public int pop(){
        if (numberOfElements-1 < 0){
        }
        else {
            numberOfElements--;
        }
        
        return storage[numberOfElements];
        
    }

   public String toString(){
        String s = "[" + storage[0];
        for (int i = 1; i < numberOfElements; i++){
            s += ", " + storage[i];
        }
        return s + "]";
    } 

    public static int[] copyArray(int[] a){
        int[] copyOfArray = new int[a.length];
        for (int i = 0; i<a.length; i++){
            copyOfArray[i] = a[i];
        }
        return copyOfArray;
    }

    public static int[] append(int[] a, int[] b){
        int[] appendedArray = new int[a.length + b.length];
        for (int i = 0; i<a.length; i++){
            appendedArray[i] = a[i];
        }
        for (int j = a.length; j<b.length; b++){
            appendedArray[j] = b[j-a.length];
        }
        return appendedArray;
    }
}