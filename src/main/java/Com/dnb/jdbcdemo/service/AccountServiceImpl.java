package Com.dnb.jdbcdemo.service;

import Com.dnb.jdbcdemo.dto.Account;
import Com.dnb.jdbcdemo.repo.AccountRepository;
import Com.dnb.jdbcdemo.repo.AccountRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.createAccount(account);
    }

    @Override
    public Optional<Account> getAccountById(String accountId) {
        return accountRepository.getAccountById(accountId);
    }

    @Override
    public boolean deleteAccountById(String accountId) {
        return accountRepository.deleteAccountById(accountId);
    }

    public List<Account> getAllAccounts(){
        return accountRepository.getAllAccounts();
    }
}
