package cinema.service.Global;

import cinema.modal.request.LoginRequest;
import cinema.modal.response.AuthResponse;

public interface GlobalService {
    AuthResponse login(LoginRequest request);
}
