package Com.dnb.jdbcdemo.service;

import Com.dnb.jdbcdemo.dto.Account;
import Com.dnb.jdbcdemo.repo.AccountRepository;
import Com.dnb.jdbcdemo.repo.AccountRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService{

    private AccountServiceImpl(){

    }

    private static AccountService accountService = null;

    public static AccountService getInstance(){

        synchronized (AccountServiceImpl.class){
            if(accountService == null) {
                accountService = new AccountServiceImpl();
            }
        }

        return accountService;
    }

    @Override
    public Account createAccount(Account account) {
        AccountRepository accountRepository = AccountRepositoryImpl.getInstance();
        return accountRepository.createAccount(account);
    }

    @Override
    public Optional<Account> getAccountById(String accountId) {
        AccountRepository accountRepository = AccountRepositoryImpl.getInstance();
        return accountRepository.getAccountById(accountId);
    }

    @Override
    public boolean deleteAccountById(String accountId) {
        AccountRepository accountRepository = AccountRepositoryImpl.getInstance();
        return accountRepository.deleteAccountById(accountId);
    }

    public List<Account> getAllAccounts(){
        AccountRepository accountRepository = AccountRepositoryImpl.getInstance();
        return accountRepository.getAllAccounts();
    }
}
