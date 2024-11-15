package cinema.service.Account;

import cinema.modal.entity.Account;
import cinema.modal.request.AccountRequest;

import java.util.List;

public interface AccountService {
    List<Account> findAccounts();
    Account findById(int id);
    Account createAccount(AccountRequest request);
    Account updateAccount(int id, AccountRequest request);
    Account changeStatus(int id, String status);
}
