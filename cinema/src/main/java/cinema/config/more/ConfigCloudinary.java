package cinema.config.more;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigCloudinary {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dfs6uboxp",
                "api_key", "419544445816388",
                "api_secret", "iRihAykP0rW-3Ke9GFS2nsAmRCA",
                "secure", true
        ));
    }
}
