import java.util.*;

class User {
    private String userID;
    private int pin;

    public User(String userID, int pin) {
        this.userID = userID;
        this.pin = pin;
    }

    public String getUserID() {
        return userID;
    }

    public int getPin() {
        return pin;
    }
}

class Transaction {
    private String type;
    private double amount;
    private double balance;

    public Transaction(String type, double amount, double balance) {
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return type + ": Rs" + amount + ", Balance: Rs" + balance;
    }
}

class BankAccount {
    private double balance;
    private List<Transaction> transactionHistory;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount, balance));
            System.out.println("Deposit successful. New balance: Rs" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdraw", amount, balance));
            System.out.println("Withdraw successful. New balance: Rs" + balance);
        } else {
            System.out.println("Invalid Withdrawal amount or insufficient balance");
        }
    }
}

class ATM {
    private BankAccount userAccount;
    private User currentUser;

    public ATM(BankAccount account, User user) {
         this.userAccount = account;
        this.currentUser = user;
    }

    public void displayMenu() {
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Transactions History");
        System.out.println("5. Transfer");
        System.out.println("6. Quit");
    }

    public boolean authenticateUser(String userID, int pin) {
        return userID.equals(currentUser.getUserID()) && pin == currentUser.getPin();
    }

    public void processOption(int option) {
        Scanner scanner = new Scanner(System.in);
        double amount;

        switch (option) {
            case 1:
                System.out.println("Enter withdrawal amount: Rs");
                amount = scanner.nextDouble();
                userAccount.withdraw(amount);
                break;

            case 2:
                System.out.println("Enter Deposit amount: Rs");
                amount = scanner.nextDouble();
                userAccount.deposit(amount);
                break;

            case 3:
                System.out.println("Current Balance: Rs" + userAccount.getBalance());
                break;

            case 4:
                System.out.println("Transaction History:");
                for (Transaction transaction : userAccount.getTransactionHistory()) {
                    System.out.println(transaction);
                }
                break;

            case 5:
                // Implement transfer functionality
                break;

            case 6:
                System.out.println("Exiting ATM. Thank you!");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}

public class Main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user ID: ");
        String userID = scanner.next();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();

        // Assuming user ID and PIN are predefined
        User user = new User(userID, pin);
        BankAccount userAccount = new BankAccount(1000.0); // Initial balance
        ATM atmMachine = new ATM(userAccount, user); // Change as needed
        if (atmMachine.authenticateUser(userID, pin)) {
            while (true) {
                atmMachine.displayMenu();
                System.out.print("Enter option (1-6): ");
                int option = scanner.nextInt();
                atmMachine.processOption(option);
            }
        } else {
            System.out.println("Invalid user ID or PIN. Exiting.");
        }
    scanner.close();
}
}