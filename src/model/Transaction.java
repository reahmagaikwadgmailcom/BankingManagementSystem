package model;

import java.time.LocalDateTime;

public class Transaction {

    private int transactionId;
    private String type;
    private double amount;
    private LocalDateTime dateTime;


    // Constructor for new transaction
    public Transaction(
            int transactionId,
            String type,
            double amount) {

        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }


    // Constructor for loading transaction from file
    public Transaction(
            int transactionId,
            String type,
            double amount,
            LocalDateTime dateTime) {

        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
    }


    public int getTransactionId() {
        return transactionId;
    }


    public String getType() {
        return type;
    }


    public double getAmount() {
        return amount;
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }


    public void displayTransaction() {

        System.out.println(
                "Transaction ID : " + transactionId
        );

        System.out.println(
                "Type           : " + type
        );

        System.out.println(
                "Amount         : ₹" + amount
        );

        System.out.println(
                "Date & Time    : " + dateTime
        );
    }
}