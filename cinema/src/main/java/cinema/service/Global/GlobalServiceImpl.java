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
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
import java.util.Map;
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

    @Autowired
    private Cloudinary cloudinary;

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
        request.RegisterAccount(account);
        accountRepository.save(account);
        return account;
    }

    @Override
    public String upload(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder", "/vti_cinema"));
        return uploadResult.get("URL").toString();
    }
}
