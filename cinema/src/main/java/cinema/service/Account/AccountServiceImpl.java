package cinema.service.Account;

import cinema.modal.entity.Account;
import cinema.modal.entity.constant.StatusAccount;
import cinema.modal.request.AccountRequest;
import cinema.repository.AccountRepository;
import cinema.service.MailSender.IMailSenderService;
import cinema.service.MailSender.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private IMailSenderService mailSenderService;
    @Override
    public List<Account> findAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(int id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Account createAccount(AccountRequest request) throws Exception {
        Account account = request.asAccount();
        accountRepository.save(account);
        mailSenderService.generateVerificationCode(request.getEmail(),generateToken(request.getEmail()));
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
            try {
                List<StatusAccount> accounts = List.of(StatusAccount.values());
                StatusAccount statusEnum = StatusAccount.valueOf(newStatus);
                if (accounts.contains(statusEnum)){
                    account.setStatus(statusEnum);
                    accountRepository.save(account);
                    return account;

                }

            } catch (IllegalArgumentException e) {
                System.out.println("Giá trị newStatus không hợp lệ: " + newStatus);
            }
        }
        return null;
    }
    public Account confirmAccount(String email, String checkCode) throws Exception {
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            throw new Exception("Account not found for email: " + email);
        }

        if (checkCode.equals(account.getVerificationCode())) {
            account.setStatus(StatusAccount.ACTIVE);
            account.setVerificationCode(null);
            accountRepository.save(account);
            return account;
        }

        throw new Exception("Invalid verification code.");
    }

    @Override
    public Account changePassword(String email, String oldPassword, String newPassword) throws Exception {
        Account account = accountRepository.findByEmail(email);

        if (Objects.nonNull(account)) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(oldPassword, account.getPassword())) {
                account.setPassword(passwordEncoder.encode(newPassword));
                return accountRepository.save(account);
            } else {
                throw new Exception("Mật khẩu cũ không đúng!");
            }
        } else {
            throw new Exception("Tài khoản không tồn tại!");
        }
    }

    @Override
    public Account fogotPassword(String email) throws Exception {
        Account account = accountRepository.findByEmail(email);
        if (account != null){
        }
        return null;
    }

    private String generateToken(String email) throws Exception {
        String token = UUID.randomUUID().toString();
        Account account = accountRepository.findByEmail(email);
        if (account != null) {
            account.setVerificationCode(token);
            accountRepository.save(account); // Lưu mã xác thực vào DB
            mailSenderService.generateVerificationCode(email, token); // Gửi mã qua email
            return token;
        }
        return null;
    }
}
