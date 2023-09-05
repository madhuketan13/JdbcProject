package Com.dnb.jdbcdemo.repo;

import Com.dnb.jdbcdemo.dto.Account;

import java.util.List;
import java.util.Optional;



public interface AccountRepository {
    public Account createAccount(Account account);

    public Optional<Account> getAccountById(String accountId);

    public boolean deleteAccountById(String accountId);

    public List<Account> getAllAccounts();
}
