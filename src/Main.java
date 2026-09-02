import exception.InsufficientBalanceException;
import model.Account;
import service.BankService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BankService bankService = new BankService();

        int choice;

        do {

            System.out.println("\n=================================");
            System.out.println("   BANKING MANAGEMENT SYSTEM");
            System.out.println("=================================");

            System.out.println("1. Create Account");
            System.out.println("2. Search Account");
            System.out.println("3. Display All Accounts");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Transaction History");
            System.out.println("7. Update Account");
            System.out.println("8. Delete Account");
            System.out.println("9. Exit");

            System.out.print("\nEnter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    System.out.println("\n===== Create Account =====");

                    System.out.print("Enter Account Number: ");
                    long accountNumber = scanner.nextLong();

                    if (accountNumber <= 0) {
                        System.out.println("Invalid Account Number!");
                        break;
                    }

                    scanner.nextLine();

                    System.out.print("Enter Account Holder Name: ");
                    String name = scanner.nextLine();

                    if (name.trim().isEmpty()) {
                        System.out.println("Account Holder Name cannot be empty!");
                        break;
                    }

                    System.out.print("Enter Phone Number: ");
                    String phone = scanner.nextLine();

                    if (!phone.matches("\\d{10}")) {
                        System.out.println("Phone Number must be exactly 10 digits!");
                        break;
                    }

                    System.out.print("Enter PIN: ");
                    int pin = scanner.nextInt();

                    if (pin < 1000 || pin > 9999) {
                        System.out.println("PIN must be exactly 4 digits!");
                        break;
                    }

                    System.out.print("Enter Initial Balance: ");
                    double balance = scanner.nextDouble();

                    if (balance < 0) {
                        System.out.println("Initial Balance cannot be negative!");
                        break;
                    }

                    Account account = new Account(
                            accountNumber,
                            name,
                            phone,
                            pin,
                            balance
                    );

                    bankService.createAccount(account);

                    break;


                case 2:

                    System.out.println("\n===== Search Account =====");

                    System.out.print("Enter Account Number: ");
                    long searchNumber = scanner.nextLong();

                    Account foundAccount =
                            bankService.searchAccount(searchNumber);

                    if (foundAccount != null) {

                        System.out.println("Account Found!");

                        System.out.println("Account Number : "
                                + foundAccount.getAccountNumber());

                        System.out.println("Account Holder : "
                                + foundAccount.getAccountHolderName());

                        System.out.println("Phone Number   : "
                                + foundAccount.getPhoneNumber());

                        System.out.println("Balance        : ₹"
                                + foundAccount.getBalance());

                    } else {

                        System.out.println("Account not found.");
                    }

                    break;


                case 3:

                    System.out.println("\n===== All Accounts =====");

                    bankService.displayAllAccounts();

                    break;

                    case 4:

                    System.out.println("\n===== Deposit Money =====");

                    System.out.print("Enter Account Number: ");
                    long depositAccountNumber = scanner.nextLong();

                    Account depositAccount =
                            bankService.searchAccount(depositAccountNumber);

                    if (depositAccount == null) {

                        System.out.println("Account not found!");
                        break;
                    }

                    System.out.print("Enter PIN: ");
                    int depositPin = scanner.nextInt();

                    if (depositAccount.getPin() != depositPin) {

                        System.out.println("Invalid PIN!");
                        System.out.println("Transaction cancelled.");
                        break;
                    }

                    System.out.print("Enter Deposit Amount: ");
                    double depositAmount = scanner.nextDouble();

                    bankService.deposit(
                            depositAccountNumber,
                            depositAmount
                    );

                    break;
                case 5:

                    System.out.println("\n===== Withdraw Money =====");

                    System.out.print("Enter Account Number: ");
                    long withdrawAccountNumber = scanner.nextLong();

                    Account withdrawAccount =
                            bankService.searchAccount(withdrawAccountNumber);

                    if (withdrawAccount == null) {

                        System.out.println("Account not found!");

                        break;
                    }

                    System.out.print("Enter PIN: ");
                    int withdrawPin = scanner.nextInt();

                    if (withdrawPin != withdrawAccount.getPin()) {

                        System.out.println("Invalid PIN!");
                        System.out.println("Transaction cancelled.");

                        break;
                    }

                    System.out.print("Enter Withdrawal Amount: ");
                    double withdrawAmount = scanner.nextDouble();

                    try {

                        bankService.withdraw(
                                withdrawAccountNumber,
                                withdrawAmount
                        );

                    } catch (InsufficientBalanceException e) {

                        System.out.println(e.getMessage());
                    }

                    break;


                case 6:

                    System.out.println("\n===== Transaction History =====");

                    bankService.displayTransactionHistory();

                    break;

                case 7:

                    System.out.println("\n===== Update Account =====");

                    System.out.print("Enter Account Number: ");
                    long updateAccountNumber = scanner.nextLong();

                    Account updateAccount =
                            bankService.searchAccount(updateAccountNumber);

                    if (updateAccount == null) {

                        System.out.println("Account not found!");
                        break;
                    }

                    System.out.print("Enter Current PIN: ");
                    int updatePin = scanner.nextInt();

                    if (updateAccount.getPin() != updatePin) {

                        System.out.println("Invalid PIN!");
                        System.out.println("Update cancelled.");
                        break;
                    }

                    scanner.nextLine();

                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();

                    if (newName.trim().isEmpty()) {

                        System.out.println("Name cannot be empty!");
                        break;
                    }

                    System.out.print("Enter New Phone Number: ");
                    String newPhone = scanner.nextLine();

                    if (!newPhone.matches("\\d{10}")) {

                        System.out.println(
                                "Phone Number must be exactly 10 digits!"
                        );
                        break;
                    }

                    System.out.print("Enter New PIN: ");
                    int newPin = scanner.nextInt();

                    if (newPin < 1000 || newPin > 9999) {

                        System.out.println("PIN must be exactly 4 digits!");
                        break;
                    }

                    bankService.updateAccount(
                            updateAccountNumber,
                            newName,
                            newPhone,
                            newPin
                    );

                    break;

                case 8:

                    System.out.println("\n===== Delete Account =====");

                    System.out.print("Enter Account Number: ");
                    long deleteAccountNumber = scanner.nextLong();

                    Account deleteAccount =
                            bankService.searchAccount(deleteAccountNumber);

                    if (deleteAccount == null) {

                        System.out.println("Account not found!");
                        break;
                    }

                    System.out.print("Enter PIN: ");
                    int deletePin = scanner.nextInt();

                    if (deleteAccount.getPin() != deletePin) {

                        System.out.println("Invalid PIN!");
                        System.out.println("Deletion cancelled.");
                        break;
                    }

                    bankService.deleteAccount(deleteAccountNumber);

                    break;
                case 9:

                    System.out.println(
                            "\nThank you for using Banking Management System!"
                    );

                    break;


                default:

                    System.out.println(
                            "\nInvalid choice! Please try again."
                    );
            }

        } while (choice != 9);

        scanner.close();
    }
}