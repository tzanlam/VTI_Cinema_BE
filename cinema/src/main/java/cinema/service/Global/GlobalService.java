package cinema.service.Global;

import cinema.modal.entity.Account;
import cinema.modal.request.AccountRequest;
import cinema.modal.request.LoginRequest;
import cinema.modal.response.AuthResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GlobalService {
    AuthResponse login(LoginRequest request);
    AuthResponse loginByEmail(LoginRequest request);
    Account register(AccountRequest request);
}
