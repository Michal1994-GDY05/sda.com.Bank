package Projekt_praktyczny.bank;

import ProjektPraktyczny.bank.model.BankAccount;
import ProjektPraktyczny.bank.repository.InMemoryBankAccountRepository;
import ProjektPraktyczny.bank.repository.MySQLBankAcountRepository;
import ProjektPraktyczny.bank.request.CreateBankAccountRequest;
import ProjektPraktyczny.bank.service.BankAccountService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String THREE = "3";

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        BankAccountService service = new BankAccountService(InMemoryBankAccountRepository.getInstance());
        MySQLBankAcountRepository mySQLBankAcountRepository = new MySQLBankAcountRepository();

//        mySQLBankAcountRepository.withdrawMoney("355364563245" , BigDecimal.valueOf(500));

        BankAccount account = new BankAccount.BankAccountBuilder()
                .buildNumberAccount("111121212111111")
                .buildPesel("39210391203912")
                .build();

        System.out.println(account);


        printMainMenu();
        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.next();
        scanner.nextLine();
        while (userChoice.equals(ONE) || userChoice.equals(TWO) || userChoice.equals(THREE)) {
            if (userChoice.equals(ONE)) {
                System.out.println("Podaj PESEL");
                String scanerPesel = scanner.nextLine();
                mySQLBankAcountRepository.create(new CreateBankAccountRequest(scanerPesel, BigDecimal.ZERO));
                printMainMenu();
                userChoice = scanner.next();

            } else if (userChoice.equals(TWO)) {
                mySQLBankAcountRepository.findAll();
                printMainMenu();
                userChoice = scanner.next();
            } else {
                System.out.println("Podaj PESEL by usunąć konto");
                String peselUserDeleteAccount = scanner.next();
                mySQLBankAcountRepository.delete(peselUserDeleteAccount);
                printMainMenu();
                userChoice = scanner.next();
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("Wyświetl menu \n" +
                "1. Stwórz konto bankowe \n" +
                "2. Pokaż wszystkie konta \n" +
                "3. Usuwanie konta");


    }

    private static void printAllBankAccount(BankAccountService service) {
        List<BankAccount> bankAccounts = service.findAll();

    }
}
