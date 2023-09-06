package Com.dnb.jdbcdemo.repo;

import Com.dnb.jdbcdemo.dto.Account;
import Com.dnb.jdbcdemo.utils.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    private DBUtils dbUtils;
    @Override
    public Account createAccount(Account account) {

        Optional<Connection> connection = dbUtils.getConnection();
        String createAccount = "insert into account"
                + "(accountId ,accountHolderName ,accountType ,balance ,contactNumber ,address ,accountCreatedDate ,dob, accountStatus)"
                + " values(?,?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = null;

        if(connection.isPresent()){
            try {
                preparedStatement = connection.get().prepareStatement(createAccount);

                preparedStatement.setString(1,account.getAccountId());
                preparedStatement.setString(2,account.getAccountHolderName());
                preparedStatement.setString(3,account.getAccountType());
                preparedStatement.setFloat(4,account.getBalance());
                preparedStatement.setString(5,account.getContactNumber());
                preparedStatement.setString(6,account.getAddress());
                preparedStatement.setDate(7, java.sql.Date.valueOf(account.getAccountCreatedDate()));
                preparedStatement.setDate(8, java.sql.Date.valueOf(account.getDob()));
                preparedStatement.setBoolean(9, account.isAccountStatus());

                int result = preparedStatement.executeUpdate();

                if(result > 0){
                    return account;
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
            finally {
                if(connection.isPresent()){
                    dbUtils.closeConnection(connection.get());
                }
            }
        }

        return null;
    }

    @Override
    public Optional<Account> getAccountById(String accountId) {

        Optional<Connection> connection = dbUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        String query = "select * from account where accountId = ?";

        if(connection.isPresent()){
            try {
                preparedStatement = connection.get().prepareStatement(query);
                preparedStatement.setString(1,accountId);

                resultset = preparedStatement.executeQuery();

                if(resultset.next()){
                    Account account = new Account();
                    account.setAccountId(resultset.getString("accountId"));
                    account.setAccountHolderName(resultset.getString("accountHolderName"));
                    account.setAccountType(resultset.getString("accountType"));
                    account.setBalance(resultset.getFloat("balance"));
                    account.setContactNumber(resultset.getString("contactNumber"));
                    account.setAddress(resultset.getString("address"));
                    account.setAddress(resultset.getString("address"));
                    account.setAccountCreatedDate(resultset.getDate("accountCreatedDate").toLocalDate());
                    account.setDob(resultset.getDate("dob").toLocalDate());
                    account.setAccountStatus(resultset.getBoolean("accountStatus"));

                    return Optional.of(account);
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
            finally {
                if(connection.isPresent()){
                    dbUtils.closeConnection(connection.get());
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteAccountById(String accountId) {

        Optional<Connection> connection = dbUtils.getConnection();
        PreparedStatement preparedStatement = null;

        String delete = "DELETE FROM account WHERE accountId = ? ";
        try {

            preparedStatement = connection.get().prepareStatement(delete);
            preparedStatement.setString(1,accountId);

            int result = preparedStatement.executeUpdate();

            if(result > 0){
                return true;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connection.ifPresent(dbUtils::closeConnection);
        }
        return false;
    }

    @Override
    public List<Account> getAllAccounts() {

        Optional<Connection> connection = dbUtils.getConnection();
        ResultSet resultSet = null;

        List<Account> accounts = new ArrayList<>();
        String getAll = "select * from account";

        if(connection.isPresent()){
            try {
                PreparedStatement preparedStatement = connection.get().prepareStatement(getAll);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    Account account = new Account();
                    account.setAccountId(resultSet.getString("accountId"));
                    account.setAccountHolderName(resultSet.getString("accountHolderName"));
                    account.setAccountType(resultSet.getString("accountType"));
                    account.setBalance(resultSet.getFloat("balance"));
                    account.setContactNumber(resultSet.getString("contactNumber"));
                    account.setAddress(resultSet.getString("address"));
                    account.setAddress(resultSet.getString("address"));
                    account.setAccountCreatedDate(resultSet.getDate("accountCreatedDate").toLocalDate());
                    account.setDob(resultSet.getDate("dob").toLocalDate());
                    account.setAccountStatus(resultSet.getBoolean("accountStatus"));

                    accounts.add(account);
                }
                return accounts;
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
            finally {
                connection.ifPresent(dbUtils::closeConnection);
            }
        }
        return null;
    }
}
