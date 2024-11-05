package cinema.service.Account;

import cinema.modal.entity.Account;
import cinema.modal.request.AccountRequest;
import org.springframework.data.domain.Page;

public interface AccountService {
    Page<Account> findAccounts(int page);
    Account findById(int id);
    Account createAccount(AccountRequest request);
    Account updateAccount(int id, AccountRequest request);
    Account changeStatus(int id, String status);
}
