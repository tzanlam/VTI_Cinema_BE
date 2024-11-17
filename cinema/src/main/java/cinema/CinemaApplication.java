package cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CinemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }
// viet dto room, seatroom, seat => fix ben controller. kiemtra code ben service
// tiep account dk dnhap voi mail;
// dto voucher service => fix controller
// sua lai ben booking if status success => set seatroom booked; => send details mail
}
