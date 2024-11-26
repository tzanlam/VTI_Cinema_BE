package cinema.service.Account;

import cinema.modal.entity.Account;
import cinema.modal.request.AccountRequest;

import java.util.List;

public interface AccountService {
    List<Account> findAccounts();
    Account findById(int id);
    Account findByEmail(String email);
    Account createAccount(AccountRequest request) throws Exception;
    Account updateAccount(int id, AccountRequest request);
    Account changeStatus(int id, String status);
    Account confirmAccount(String email, String checkCode) throws Exception;
    Account changePassword(String email, String oldPassword, String newPassword) throws Exception;
    Account forgotPassword(String email) throws Exception;
}
