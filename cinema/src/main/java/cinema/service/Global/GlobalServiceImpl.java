package cinema.service.Global;

import cinema.config.jwt.JwtTokenUtil;
import cinema.modal.entity.Account;
import cinema.modal.entity.Booking;
import cinema.modal.entity.Payment;
import cinema.modal.entity.constant.StatusAccount;
import cinema.modal.entity.constant.StatusBooking;
import cinema.modal.request.AccountRequest;
import cinema.modal.request.LoginRequest;
import cinema.modal.response.AuthResponse;
import cinema.repository.AccountRepository;
import cinema.repository.BookingRepository;
import cinema.service.MailSender.MailSenderService;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class GlobalServiceImpl implements GlobalService{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtTokenUtil.generateToken(userDetails);
        return new AuthResponse(token, request.getUsername(), userDetails.getAuthorities());
    }

    @Override
    public AuthResponse loginByEmail(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtTokenUtil.generateToken(userDetails);
        return new AuthResponse(token, request.getEmail(), userDetails.getAuthorities());
    }

    @Override
    public Account register(AccountRequest request) {
        Account account = new Account();
        request.ResgisterAccount(account);
        accountRepository.save(account);
        return account;
    }

    @Override
    public String firebaseStorage(MultipartFile file) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket();
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        Blob blob = bucket.create(fileName, file.getBytes(), file.getContentType());
        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                bucket.getName(),
                blob.getName());
    }

//    @Override
//    @Scheduled(fixedRate = 5000)
//    // sau register
//    public Account confirmAccount(String username, String checkCode) {
//        Account account = accountRepository.findByUsername(username);
//        String email = account.getEmail();
//        String code  = mailSenderService.createCode(email);
//        int i;
//        for (i = 0 ; i < 3; i++) {
//            if (checkCode.equals(code)) {
//                account.setStatus(StatusAccount.ACTIVE);
//                accountRepository.save(account);
//            }
//            else {
//                System.out.println("error");
//            }
//        }
//        return account;
//    }


}
