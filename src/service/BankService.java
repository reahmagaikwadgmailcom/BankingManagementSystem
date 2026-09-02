package service;

import exception.InsufficientBalanceException;
import model.Account;
import model.Transaction;
import util.FileUtil;

import java.util.ArrayList;

public class BankService {

    private ArrayList<Account> accounts = new ArrayList<>();

    private ArrayList<Transaction> transactions = new ArrayList<>();

    private int transactionCounter = 1;

    // Constructor

    public BankService() {

        accounts = FileUtil.loadAccounts();

        transactions = FileUtil.loadTransactions();

        if (!transactions.isEmpty()) {

            transactionCounter =
                    transactions.get(transactions.size() - 1)
                            .getTransactionId() + 1;
        }
    }


    // Create Account
    public void createAccount(Account account) {

        // Check duplicate account number
        if (searchAccount(account.getAccountNumber()) != null) {

            System.out.println("Account already exists!");

            return;
        }

        accounts.add(account);

        FileUtil.saveAccounts(accounts);

        System.out.println("Account created successfully!");
    }


    // Search Account
    public Account searchAccount(long accountNumber) {

        for (Account account : accounts) {

            if (account.getAccountNumber() == accountNumber) {

                return account;
            }
        }

        return null;
    }


    // Display All Accounts
    public void displayAllAccounts() {

        if (accounts.isEmpty()) {

            System.out.println("No accounts found.");

            return;
        }

        for (Account account : accounts) {

            System.out.println("-------------------------");

            System.out.println("Account Number : "
                    + account.getAccountNumber());

            System.out.println("Account Holder : "
                    + account.getAccountHolderName());

            System.out.println("Phone Number   : "
                    + account.getPhoneNumber());

            System.out.println("Balance        : ₹"
                    + account.getBalance());
        }
    }


    // Deposit Money
    public void deposit(long accountNumber, double amount) {

        Account account = searchAccount(accountNumber);

        if (account == null) {

            System.out.println("Account not found.");

            return;
        }

        if (amount <= 0) {

            System.out.println("Invalid deposit amount.");

            return;
        }

        double newBalance =
                account.getBalance() + amount;

        account.setBalance(newBalance);

        FileUtil.saveAccounts(accounts);


        Transaction transaction =
                new Transaction(
                        transactionCounter++,
                        "Deposit",
                        amount
                );

        transactions.add(transaction);
        FileUtil.saveTransactions(transactions);


        System.out.println("Deposit successful!");

        System.out.println("Deposited Amount : ₹"
                + amount);

        System.out.println("New Balance      : ₹"
                + account.getBalance());
    }


    // Withdraw Money
    public void withdraw(long accountNumber, double amount)
            throws InsufficientBalanceException {

        Account account = searchAccount(accountNumber);

        if (account == null) {

            System.out.println("Account not found.");

            return;
        }

        if (amount <= 0) {

            System.out.println("Invalid withdrawal amount.");

            return;
        }

        if (amount > account.getBalance()) {

            throw new InsufficientBalanceException(
                    "Insufficient Balance! Available balance: ₹"
                            + account.getBalance()
            );
        }


        double newBalance =
                account.getBalance() - amount;

        account.setBalance(newBalance);

        FileUtil.saveAccounts(accounts);


        Transaction transaction =
                new Transaction(
                        transactionCounter++,
                        "Withdraw",
                        amount
                );

        transactions.add(transaction);
        FileUtil.saveTransactions(transactions);


        System.out.println("Withdrawal successful!");

        System.out.println("Withdrawn Amount : ₹"
                + amount);

        System.out.println("New Balance      : ₹"
                + account.getBalance());
    }


    // Transaction History
    public void displayTransactionHistory() {

        if (transactions.isEmpty()) {

            System.out.println("No transactions found.");

            return;
        }

        for (Transaction transaction : transactions) {

            System.out.println("-------------------------");

            transaction.displayTransaction();
        }
    }


    // Update Account
    public void updateAccount(
            long accountNumber,
            String newName,
            String newPhoneNumber,
            int newPin) {

        Account account = searchAccount(accountNumber);

        if (account == null) {

            System.out.println("Account not found.");

            return;
        }

        account.setAccountHolderName(newName);

        account.setPhoneNumber(newPhoneNumber);

        account.setPin(newPin);

        FileUtil.saveAccounts(accounts);

        System.out.println("Account updated successfully!");
    }


    // Delete Account
    public void deleteAccount(long accountNumber) {

        Account account = searchAccount(accountNumber);

        if (account == null) {

            System.out.println("Account not found.");

            return;
        }

        accounts.remove(account);

        FileUtil.saveAccounts(accounts);

        System.out.println("Account deleted successfully!");
    }
}