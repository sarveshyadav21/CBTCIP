import java.io.*;
import java.util.*;

class IndiaBank implements Serializable {
    String name, AccountNumber, Account_Type;
    long balance;

    IndiaBank(String name, String AccountNumber, String Account_Type, long balance) {
        this.name = name;
        this.AccountNumber = AccountNumber;
        this.Account_Type = Account_Type;
        this.balance = balance;
    }

boolean search(String AccountNumber) {
if (this.AccountNumber.equals(AccountNumber)) {
    return true;
        } else {
            return false;  }
    }

    void showAccount() {
        System.out.println("Account No: " + AccountNumber);
        System.out.println("Account Type: " + Account_Type);
        System.out.println("Balance: " + balance);
    }

    void deposit(long amount) {
        balance += amount;
        System.out.println("Deposit Successful. New Balance: " + balance);
    }

    void withdraw(long amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal Successful. New Balance: " + balance);
        } else {
            System.out.println("Insufficient Balance");
        }
    }
}

public class BankingSystem {
    public static void main(String args[]) {
        ArrayList<IndiaBank> C = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        // Load accounts from file
        C = loadAccountsFromFile();

        int ch;
        do {
            System.out.println("\n #Banking System Application#");
            System.out.println(" 1.Create an account \n2.Display all account details\n 3. Search by Account number\n 4. Deposit the amount \n 5. Withdraw the amount \n 6. Remove account\n 7.Exit ");
            System.out.println("Enter your choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    IndiaBank cus = createAccount(C);
                    if (cus != null) {
                        C.add(cus);
                        saveAccountsToFile(C);
                    }
                    break;
                case 2:
                    if (C.isEmpty()) {
                        System.out.println("No accounts found");
                    } else {
                        for (int i = 0; i < C.size(); i++) {
                            C.get(i).showAccount();
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter account no. you want to search: ");
                    String ac_no = sc.next();
                    boolean found = false;
                    for (int i = 0; i < C.size(); i++) {
                        found = C.get(i).search(ac_no);
                        if (found) {
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist..!!");
                    }
                    break;
                case 4:
                    System.out.print("Enter Account no. : ");
                    ac_no = sc.next();
                    found = false;
                    for (int i = 0; i < C.size(); i++) {
                        found = C.get(i).search(ac_no);
                        if (found) {
                            System.out.println("Enter the amount to deposit");
                            long amount = sc.nextLong();
                            C.get(i).deposit(amount);
                            saveAccountsToFile(C);
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist..!!");
                    }
                    break;
                case 5:
                    System.out.print("Enter Account No : ");
                    ac_no = sc.next();
                    found = false;
                    for (int i = 0; i < C.size(); i++) {
                        found = C.get(i).search(ac_no);
                        if (found) {
                            System.out.println("Enter the amount to withdraw");
                            long amount = sc.nextLong();
                            C.get(i).withdraw(amount);
                            saveAccountsToFile(C);
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist.! ");
                    }
                    break;
                case 6:
                    System.out.println("Enter your acc no: ");
                    ac_no = sc.next();
                    found = false;
                    for (int i = 0; i < C.size(); i++) {
                        found = C.get(i).search(ac_no);
                        if (found) {
                            C.remove(i);
                            saveAccountsToFile(C);
                            System.out.println("Account deleted successfully");
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Search failed! Account doesn't exist.! ");
                    }
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Enter the correct Choice");
                    break;
            }
        } while (ch != 7);
    }

    public static IndiaBank createAccount(ArrayList<IndiaBank> C) {
       Scanner sc = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter account number: ");
        String AccountNumber = sc.nextLine();
        System.out.print("Enter account type: ");
        String Account_Type = sc.nextLine();
        System.out.print("Enter initial balance: ");
        long balance = sc.nextLong();
        IndiaBank cus = new IndiaBank(name, AccountNumber, Account_Type, balance);
        return cus;
    }
    

    public static ArrayList<IndiaBank> loadAccountsFromFile() {
        ArrayList<IndiaBank> accounts = new ArrayList<>();
        try {
            FileInputStream FIS = new FileInputStream("accounts.txt");
            ObjectInputStream OS = new ObjectInputStream(FIS);
            accounts = (ArrayList<IndiaBank>) OS.readObject();
            OS.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading accounts from file: " + e.getMessage());
        }
        return accounts;
    }
    

    public static void saveAccountsToFile(ArrayList<IndiaBank> C) {
        try {
            FileOutputStream fos = new FileOutputStream("accounts.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(C);
            oos.close();
        } catch (IOException e) {
            System.out.println("Error saving accounts to file: " + e.getMessage());
            e.printStackTrace(); // Print the exception stack trace
        }
    }
}
