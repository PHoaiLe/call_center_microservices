package org.com.VerificationService.FirebaseAdmin.Config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseAdminConfig
{
    private FirebaseAuth firebaseAuth;
//    @Value("${spring.datasource.service-account}")
//    private static String service_account_path;

    public FirebaseAdminConfig() throws IOException
    {
//        ClassPathResource classPathResource = new ClassPathResource("transportation-app-297c0-firebase-adminsdk-o8thy-8f59680cf5.json");
//        FileInputStream serviceAccount = new FileInputStream(classPathResource.getFile().getPath());
//        FileInputStream serviceAccount = new FileInputStream();

        //since we package the service, we cannot read the service_account file from a specific location
        //The resource files are packaged inside the JAR and so we need a different way of accessing them.
        //instead, we use this way (use the structure of JAR file)

        InputStream inputStream = getClass().getResourceAsStream("../../../../../transportation-app-297c0-firebase-adminsdk-o8thy-8f59680cf5.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .setDatabaseUrl("https://transportation-app-297c0-default-rtdb.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

        firebaseAuth = FirebaseAuth.getInstance();
    }
}
