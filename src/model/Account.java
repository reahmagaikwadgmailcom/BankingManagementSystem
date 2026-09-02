package model;

public class Account {

    private long accountNumber;
    private String accountHolderName;
    private String phoneNumber;
    private int pin;
    private double balance;

    // Constructor
    public Account(long accountNumber, String accountHolderName,
                   String phoneNumber, int pin, double balance) {

        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.phoneNumber = phoneNumber;
        this.pin = pin;
        this.balance = balance;
    }

    // Getters
    public long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    // Setters
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}