package util;

import model.Account;
import model.Transaction;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtil {

    private static final String FILE_NAME = "accounts.txt";

    private static final String TRANSACTION_FILE_NAME =
            "transactions.txt";


    // Save Accounts
    public static void saveAccounts(ArrayList<Account> accounts) {

        try (FileWriter writer = new FileWriter(FILE_NAME)) {

            for (Account account : accounts) {

                writer.write(
                        account.getAccountNumber() + "," +
                                account.getAccountHolderName() + "," +
                                account.getPhoneNumber() + "," +
                                account.getPin() + "," +
                                account.getBalance() +
                                "\n"
                );
            }

            System.out.println("Accounts saved successfully!");

        } catch (Exception e) {

            System.out.println("Error while saving accounts.");
        }
    }


    // Load Accounts
    public static ArrayList<Account> loadAccounts() {

        ArrayList<Account> accounts = new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return accounts;
        }

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                long accountNumber =
                        Long.parseLong(data[0]);

                String name = data[1];

                String phone = data[2];

                int pin =
                        Integer.parseInt(data[3]);

                double balance =
                        Double.parseDouble(data[4]);

                Account account = new Account(
                        accountNumber,
                        name,
                        phone,
                        pin,
                        balance
                );

                accounts.add(account);
            }

            System.out.println("Accounts loaded successfully!");

        } catch (Exception e) {

            System.out.println("Error while loading accounts.");
        }

        return accounts;
    }


    // Save Transactions
    public static void saveTransactions(
            ArrayList<Transaction> transactions) {

        try (FileWriter writer =
                     new FileWriter(TRANSACTION_FILE_NAME)) {

            for (Transaction transaction : transactions) {

                writer.write(
                        transaction.getTransactionId() + "," +
                                transaction.getType() + "," +
                                transaction.getAmount() + "," +
                                transaction.getDateTime() +
                                "\n"
                );
            }

            System.out.println(
                    "Transactions saved successfully!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Error while saving transactions."
            );
        }
    }


    // Load Transactions
    public static ArrayList<Transaction> loadTransactions() {

        ArrayList<Transaction> transactions =
                new ArrayList<>();

        File file =
                new File(TRANSACTION_FILE_NAME);

        if (!file.exists()) {
            return transactions;
        }

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                int transactionId =
                        Integer.parseInt(data[0]);

                String type = data[1];

                double amount =
                        Double.parseDouble(data[2]);

                LocalDateTime dateTime =
                        LocalDateTime.parse(data[3]);

                Transaction transaction =
                        new Transaction(
                                transactionId,
                                type,
                                amount,
                                dateTime
                        );

                transactions.add(transaction);
            }

            System.out.println(
                    "Transactions loaded successfully!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Error while loading transactions."
            );
        }

        return transactions;
    }
}