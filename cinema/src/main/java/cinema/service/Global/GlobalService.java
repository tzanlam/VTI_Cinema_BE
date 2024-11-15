package cinema.service.Global;

import cinema.modal.request.LoginRequest;
import cinema.modal.response.AuthResponse;

public interface GlobalService {
    AuthResponse login(LoginRequest request);
    //boolean sendMail(); viết ở đây nhé sau đó ông viết logic ở cái globalserviceimpl rồi ông tạo controller global rồi viết service vào đó
}
