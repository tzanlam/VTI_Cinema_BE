package cinema.service.Global;

import cinema.config.jwt.JwtTokenUtil;
import cinema.modal.entity.Account;
import cinema.modal.entity.constant.StatusAccount;
import cinema.modal.request.AccountRequest;
import cinema.modal.request.LoginRequest;
import cinema.modal.response.AuthResponse;
import cinema.repository.AccountRepository;
import cinema.service.MailSender.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class GlobalServiceImpl implements GlobalService{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MailSenderService mailSenderService;

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
    public Account register(AccountRequest request) {
        Account account = new Account();
        request.ResgisterAccount(account);
        accountRepository.save(account);
        return account;
    }

    @Override
    @Scheduled(fixedRate = 5000)
    // sau register
    public Account confirmAccount(String username, String checkCode) {
        Account account = accountRepository.findByUsername(username);
        String email = account.getEmail();
        String code  = mailSenderService.createCode(email);
        int i;
        for (i = 0 ; i < 3; i++) {
            if (checkCode.equals(code)) {
                account.setStatus(StatusAccount.ACTIVE);
                accountRepository.save(account);
            }
            else {
                System.out.println("error");
            }
        }
        return account;
    }
}
