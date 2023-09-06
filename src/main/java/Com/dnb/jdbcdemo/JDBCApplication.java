package Com.dnb.jdbcdemo;

import Com.dnb.jdbcdemo.config.Config;
import Com.dnb.jdbcdemo.dto.Account;
import Com.dnb.jdbcdemo.service.AccountService;
import Com.dnb.jdbcdemo.service.AccountServiceImpl;
import Com.dnb.jdbcdemo.utils.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class JDBCApplication {

    @Autowired
    private static AccountService accountService = null;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        accountService = applicationContext.getBean(AccountService.class);

        int choice = 0;

        do {
            System.out.println("1. Create an Account");
            System.out.println("2. Get Account Details");
            System.out.println("3. Delete Account");
            System.out.println("4. Get All Accounts");
            System.out.println("5. Exit");

            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    Account account = new Account();
                    account.setAccountId(sc.next());
                    account.setAccountHolderName(sc.next());
                    account.setAccountType(sc.next());
                    account.setBalance(sc.nextFloat());
                    account.setContactNumber(sc.next());
                    account.setAddress(sc.next());
                    String date = sc.next();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate localDate = LocalDate.parse(date, formatter);
                    account.setDob(localDate);
                    account.setAccountStatus(sc.nextBoolean());
                    accountService.createAccount(account);
                }
                case 2 -> {
                    String accountId = sc.next();
                    Optional<Account> result = accountService.getAccountById(accountId);
                    System.out.println(result);
                }
                case 3 -> deleteAccount();
                case 4 -> {
                    List<Account> allAccounts = accountService.getAllAccounts();
                    System.out.println(allAccounts);
                }
            }

        } while (choice != 5);
    }

    public static void deleteAccount() {
        Scanner sc = new Scanner(System.in);
        String accountId = sc.next();
        Boolean result = accountService.deleteAccountById(accountId);

        if (result) {
            System.out.println("Account deleted Successfully");
        }
    }
}

