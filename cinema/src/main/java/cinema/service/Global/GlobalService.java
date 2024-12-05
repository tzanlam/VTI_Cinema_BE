package cinema.service.Global;

import cinema.modal.entity.Account;
import cinema.modal.request.AccountRequest;
import cinema.modal.request.LoginRequest;
import cinema.modal.response.AuthResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface GlobalService {
    AuthResponse login(LoginRequest request);
    AuthResponse loginByEmail(LoginRequest request);
    Account register(AccountRequest request);
    String upload(MultipartFile file) throws IOException;
    String createPaymentUrl(Map<String, String> params) throws Exception;
    String hashWithHmacSHA512(String data, String secret) throws Exception;
}
