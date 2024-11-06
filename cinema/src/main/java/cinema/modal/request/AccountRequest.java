package cinema.modal.request;

import cinema.modal.entity.Account;
import cinema.modal.entity.constant.Role;
import cinema.modal.entity.constant.StatusAccount;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static cinema.util.ConvertDateTime.convertToLocalDate;

@Data
public class AccountRequest {
    private String username;
    private String fullname;
    private String birthDate;
    private String email;
    private String password;
    private String phoneNumber;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Account asAccount() {
        Account account = new Account();
        account.setUsername(username);
        account.setFullname(fullname);
        account.setBirthDate(convertToLocalDate(birthDate));
        account.setEmail(email);
        account.setPassword(passwordEncoder.encode(password));
        account.setPhoneNumber(phoneNumber);
        account.setStatus(StatusAccount.ACTIVE);
        account.setRole(Role.USER);
        return account;
    }

    public Account updateAccount(Account account) {
        account.setFullname(fullname);
        account.setBirthDate(convertToLocalDate(birthDate));
        account.setEmail(email);
        account.setPassword(passwordEncoder.encode(password));
        account.setPhoneNumber(phoneNumber);
        return account;
    }
}
