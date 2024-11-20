package cinema.config.more;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
public class FireBaseConfig {
        public FireBaseConfig() {
            try {
                // Lâm
                var serviceAccount = Path.of("src/main/resources/firebase-service.json");
                //Hoàng
//                var serviceAccount = Path.of("D:\\VTI_Cinema_BE\\cinema\\src\\main\\resources\\firebase-service.json");
                if (!Files.exists(serviceAccount)) {
                    throw new RuntimeException("Firebase service account file not found at: " + serviceAccount);
                }

                try (var serviceAccountStream = Files.newInputStream(serviceAccount)) {
                    var options = FirebaseOptions.builder()
                            .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                            .setStorageBucket("vti-cinema.appspot.com") // Thay bằng bucket của bạn
                            .build();

                    // Chỉ khởi tạo nếu Firebase chưa được khởi tạo
                    if (FirebaseApp.getApps().isEmpty()) {
                        FirebaseApp.initializeApp(options);
                        System.out.println("Firebase has been initialized successfully.");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Error initializing Firebase: " + e.getMessage(), e);
            }
        }
    }
