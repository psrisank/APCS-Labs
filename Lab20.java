import 
class Account {
   // INSTANCE VARIABLES

   private String name = "";
   private static int accountNumber = 10000000 - 1;
   private double balance = 0.0;
   private String[] transactions = null;

   // CONSTRUCTORS

   public Account(){ accountNumber++; } // Incrementing here allows more code efficiency in subclasses

   public Account(String name, double balance){
       accountNumber++;
       this.name = name;
       this.balance = balance;
   }

   // GETTERS

   public int getAccountNumber() {
       return accountNumber;
   }

   public double getBalance() {
       return this.balance;
   }

   public String getName() {
       return this.name;
   }

   public String[] getTransactions() {
       return this.transactions;
   }

   // SETTERS

   public void setName(String name) {
       this.name = name;
   }

   // METHODS

   public String toString(){ // Use as a generic template which is added on in subclasses
       return "Name: " + this.name +", Acct: " + accountNumber + ", Balance: $" + this.balance;
   }

   public void deposit(double amount){
       balance += amount;

       // Updating string array of transactions
       String[] copy = new String[1];
       // Make a copy array that appends the new transaction and then make this.transactions point to the copy
       if(this.transactions == null){
           copy[0] = "Deposit: " + amount;
           this.transactions = copy;
           return;
       }
       copy = new String[this.transactions.length + 1];
       for(int i = 0; i < this.transactions.length; i++)
           copy[i] = this.transactions[i];
       copy[this.transactions.length] = "Deposit: " + amount;
       this.transactions = copy;

   }

   public double withdraw(double amount){
       balance -= amount;

       // Same as
       String[] copy = new String[1];
       if(this.transactions == null){
           copy[0] = "Withdraw: " + amount;
           this.transactions = copy;
           return amount;
       }

       copy = new String[this.transactions.length + 1];
       for(int i = 0; i < this.transactions.length; i++)
           copy[i] = this.transactions[i];
       copy[this.transactions.length] = "Withdraw: " + amount;
       this.transactions = copy;
       return amount;
   }

   public double addTransactions(String type, double a){
       if(type.substring(0, 1).compareToIgnoreCase("W") == 0){
           return withdraw(a);
       }
       else if(type.substring(0, 1).compareToIgnoreCase("D") == 0){
           deposit(a);
           return 0.0;
       }
       else return 0;
   }
}
class SavingsAccount extends Account{
   // INSTANCE VARIABLES

   private double rate;

   // CONSTRUCTORS

   public SavingsAccount(){ super(); } // calling super to increment account number

   public SavingsAccount(String name, double balance, double rate){
       super(name, balance); // inherits attributes from Account as well as increments account number
       this.rate = rate;
   }

   // GETTER

   public double getRate() {
       return this.rate;
   }

   // SETTER

   public void setRate(double rate) {
       this.rate = rate;
   }

   // METHODS

   
   public String toString() {
       return super.toString() + ", Interest: " + this.rate + "%";
   } // Adds on interest rate

   public void applyInterest(){
       deposit((this.rate / 100) / 12); // Because the rate is a percent, divide by 100 as well
   }
}
class CreditCardAccount extends Account {
   // INSTANCE VARIABLES

   private double rate;
   private String type;

   // CONSTRUCTORS

   public CreditCardAccount(){ super(); }

   public CreditCardAccount(String name, double balance, double rate, String type){
       super(name, balance);
       this.rate = rate;
       this.type = type;
   }

   // GETTERS

   public double getRate() {
       return rate;
   }

   public String getType() {
       return type;
   }

   // SETTERS

   public void setRate(double rate) {
       this.rate = rate;
   }

   public void setType(String type) {
       this.type = type;
   }

   // METHODS


   public String toString() {
       return "Name: " + this.getName() + ", " + this.type + " Acct: " + this.getAccountNumber() + ", Balance: $" + this.getBalance() + ", Interest: " + this.rate + "%";
   }

   public void chargeInterest(){
       withdraw(((this.rate / 100) / 12));
   } // Same as applyInterest

   public void charge(double amount){ withdraw(amount); }

   public void pay(double amount){ deposit(amount); }
}
class CheckingAccount extends Account{
   // CONSTRUCTORS

   public CheckingAccount(){ super(); }

   public CheckingAccount(String name, double balance){ // No extra attributes
       super(name, balance);
   }

   // METHODS


   public String toString() {
       return super.toString();
   }


}
class Lab20 {

   public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
       Account[] user = new Account[100]; // Storing all accounts with an array
       int i = 0;
       int accountType = 0;

       System.out.println("Welcome to my online banking system.");
       while(true){
           System.out.println("Enter the corresponding number for a desired action:\n");

           // Menu options for display
           System.out.println("\t1. Create new account");
           System.out.println("\t2. Deposit / Pay");
           System.out.println("\t3. Withdraw / Charge");
           System.out.println("\t4. Print report of all accounts");
           System.out.println("\t5. Print all transactions");
           System.out.println("\t6. Advance time for an account");
           System.out.println("\t7. Automatic deposit");
           System.out.println("\t9. Exit\n");

           int selection = input.nextInt(); // Based on corresponding number of desired action

           switch(selection){
               case 1: // Prompt for the type of new account
                   System.out.println("Enter the type of account:");
                   System.out.println("\t1. Savings Account");
                   System.out.println("\t2. Checking Account");
                   System.out.println("\t3. Credit Card Account\n");

                   accountType = input.nextInt(); // Based on number input, create that type of account

                   switch (accountType){
                       case 1:
                           user[i] = new SavingsAccount(); i++;
                           break;
                       case 2:
                           user[i] = new CheckingAccount(); i++;
                           break;
                       case 3:
                           user[i] = new CreditCardAccount(); i++;
                           break;
                       default:
                           System.out.println("Invalid entry");
                           break;
                   }

                   // Prompt for name and balance of new account
                   System.out.println("Enter the account name: ");
                   String name = new Scanner(System.in).nextLine();
                   System.out.print("Enter the current account balance: ");
                   user[i-1].setName(name);

                   if(accountType == 3){
                       System.out.print("Enter your card name (Visa, MasterCard, etc.): ");
                       ((CreditCardAccount) user[i-1]).setType(new Scanner(System.in).nextLine());
                   }

                   System.out.println("\nAccount information registered");
                   break;

               case 2: // Deposit option
                   System.out.print("Enter the deposit/payment amount: ");
                   user[i - 1].deposit(input.nextInt());
                   System.out.println("\nDeposited into" + user[i - 1].getAccountNumber());
                   break;

               case 3: // Withdraw Option
                   System.out.print("Enter the withdrawal/charge amount: ");
                   user[i-1].withdraw(input.nextInt());
                   System.out.println("\nWithdrawn");
                   break;

               case 4: // Prints out all objects with their toString methods
                   System.out.println("Below are all of your created accounts:\n");
                   for(Account j : user){
                       if(j == null)
                           break;
                       System.out.println(j.toString());
                   }
                   break;

               case 5: // Uses a nested for loop and transactions string array to print out all transactions of every account
                   System.out.println("Below are all transactions from every created account:\n");
                   for(Account j : user){
                       if(j == null)
                           break;
                       System.out.println("Account: " + j.getAccountNumber());
                       for(String t : j.getTransactions())
                           System.out.println("\t" + t);
                   }
                   break;

               case 6: // Charges interest in advance of a certain number of months
                   System.out.print("Enter the number of months you want to advance in interest: ");
                   int months = input.nextInt(); // Prompt for number of months
                   if(accountType == 1) // Based on account type, use the corresponding interest method
                       for(int j = 0; j < months; j++)
                           ((SavingsAccount) user[i-1]).applyInterest();

                   else if(accountType == 3)
                       for(int j = 0; j < months; j++)
                           ((CreditCardAccount) user[i-1]).chargeInterest();
                   break;

               case 7: // Inputs in every existing account as $1, $2, ..., $numAccounts
                   System.out.println("An automatic deposit has been made to all of your created accounts");
                   double a = 0;
                   for(Account j : user){
                       if(j == null)
                           break;
                       a++;
                       j.deposit(a);
                   }
                   break;

               case 9: // Force quit out of the program
                   System.out.println("You have exited the online banking system");
                   System.exit(0);
           }
       }
   }
}
