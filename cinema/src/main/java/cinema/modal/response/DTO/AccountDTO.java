package cinema.modal.response.DTO;

import cinema.modal.entity.Account;
import lombok.Data;

@Data
public class AccountDTO {
    private String account_id;
    private String user_name;
    private String full_name;
    private String birth_date;
    private String email;
    private String phone_number;
    private String account_role;
    private String passport;
    private String gender;
    private String city;
    private String district;
    private String address;
    private String account_status;

    public AccountDTO(Account account) {
        this.account_id = String.valueOf(account.getId());
        this.user_name = account.getUsername();
        this.full_name = account.getFullName();
        this.birth_date = String.valueOf(account.getBirthDate());
        this.email = account.getEmail();
        this.phone_number = account.getPhoneNumber();
        this.account_role = String.valueOf(account.getRole());
        this.passport = account.getPassport();
        this.gender = String.valueOf(account.getGender());
        this.city = String.valueOf(account.getCity());
        this.district = String.valueOf(account.getDistrict());
        this.address = String.valueOf(account.getAddress());
        this.account_status = String.valueOf(account.getStatus());
    }
}
