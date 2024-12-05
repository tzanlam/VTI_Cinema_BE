package cinema.config.more;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class VNPayConfig {
    @Value("${vnp.tmn-code}")
    private String tnmCode;

    @Value("${vnp.hash-secret}")
    private String hashSecret;

    @Value("${vnp.url}")
    private String url;

    @Value("${vnp.return-url}")
    private String returnUrl;

}
