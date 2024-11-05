package cinema.modal.request;

import cinema.modal.entity.Account;
import lombok.Data;

import static cinema.util.ConvertDateTime.convertToLocalDate;

@Data
public class AccountRequest {
    private String username;
    private String fullname;
    private String birthDate;
    private String email;
    private String password;
    private String phoneNumber;

    public Account asAccount() {
        Account account = new Account();
        account.setUsername(username);
        account.setFullname(fullname);
        account.setBirthDate(convertToLocalDate(birthDate));
        account.setEmail(email);
        account.setPassword(password);
        account.setPhoneNumber(phoneNumber);
        return account;
    }

    public  Account updateAccount(Account account) {
        account.setFullname(fullname);
        account.setBirthDate(convertToLocalDate(birthDate));
        account.setEmail(email);
        account.setPassword(password);
        account.setPhoneNumber(phoneNumber);
        return account;
    }
}
