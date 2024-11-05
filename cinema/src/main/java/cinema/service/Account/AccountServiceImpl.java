package cinema.service.Account;

import cinema.modal.entity.Account;
import cinema.modal.entity.constant.StatusAccount;
import cinema.modal.request.AccountRequest;
import cinema.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Page<Account> findAccounts(int page) {
        return accountRepository.findAll(PageRequest.of(page-1, 10));
    }

    @Override
    public Account findById(int id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public Account createAccount(AccountRequest request) {
        Account account = request.asAccount();
        accountRepository.save(account);
        return account;
    }

    @Override
    public Account updateAccount(int id, AccountRequest request) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            Account a = request.updateAccount(account);
            accountRepository.save(a);
            return a;
        }
        return null;
    }

    @Override
    public Account changeStatus(int id, String newStatus) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            List<StatusAccount> validStatuses = Arrays.asList(StatusAccount.ACTIVE, StatusAccount.INACTIVE, StatusAccount.BLOCK);

            if (validStatuses.contains(newStatus)) {
                account.setStatus(StatusAccount.valueOf(newStatus));
                accountRepository.save(account);
                return account;
            } else {
                throw new IllegalArgumentException("Trạng thái không hợp lệ");
            }
        } else {
            System.out.println("Không tìm thấy tài khoản với ID: " + id);
        }
        return null;
    }
}
