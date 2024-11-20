package cinema.config.more;

import cinema.modal.entity.Account;
import cinema.modal.entity.constant.Role;
import cinema.modal.entity.constant.StatusAccount;
import cinema.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initData(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Account account1 = new Account("user1", "John Doe", LocalDate.of(1990, 1, 1), "user1@gmail.com",
                    passwordEncoder.encode("password1"), "0123456789", Role.USER, StatusAccount.ACTIVE);
            Account account2 = new Account("user2", "Jane Smith", LocalDate.of(1992, 2, 2), "user2@gmail.com",
                    passwordEncoder.encode("password2"), "0987654321", Role.USER, StatusAccount.INACTIVE);
            Account account3 = new Account("admin1", "Admin One", LocalDate.of(1985, 3, 3), "admin1@gmail.com",
                    passwordEncoder.encode("adminpass1"), "0112233445", Role.ADMIN, StatusAccount.ACTIVE);
            Account account4 = new Account("user3", "Alice Johnson", LocalDate.of(1995, 4, 4), "user3@gmail.com",
                    passwordEncoder.encode("password3"), "0223344556", Role.USER, StatusAccount.ACTIVE);
            Account account5 = new Account("admin2", "Admin Two", LocalDate.of(1980, 5, 5), "admin2@gmail.com",
                    passwordEncoder.encode("adminpass2"), "0334455667", Role.ADMIN, StatusAccount.INACTIVE);
            Account account6 = new Account("user4", "Bob Brown", LocalDate.of(1997, 6, 6), "user4@gmail.com",
                    passwordEncoder.encode("password4"), "0445566778", Role.USER, StatusAccount.ACTIVE);
            Account account7 = new Account("user5", "Charlie White", LocalDate.of(1991, 7, 7), "user5@gmail.com",
                    passwordEncoder.encode("password5"), "0556677889", Role.USER, StatusAccount.ACTIVE);
            Account account8 = new Account("user6", "Diana Black", LocalDate.of(1993, 8, 8), "user6@gmail.com",
                    passwordEncoder.encode("password6"), "0667788990", Role.USER, StatusAccount.INACTIVE);
            Account account9 = new Account("admin3", "Admin Three", LocalDate.of(1983, 9, 9), "admin3@gmail.com",
                    passwordEncoder.encode("adminpass3"), "0778899001", Role.ADMIN, StatusAccount.ACTIVE);
            Account account10 = new Account("user7", "Eve Green", LocalDate.of(1999, 10, 10), "user7@gmail.com",
                    passwordEncoder.encode("password7"), "0889900112", Role.USER, StatusAccount.ACTIVE);

            accountRepository.saveAll(Arrays.asList(account1, account2, account3, account4, account5,
                    account6, account7, account8, account9, account10));

            System.out.println("Dummy data added successfully!");
        };
    }
}
