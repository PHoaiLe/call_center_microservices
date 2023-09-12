package org.com.BookingService.Firebase.Config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseAdminConfig
{
    private static final String firebase_path = "../../../../../transportation-app-297c0-firebase-adminsdk-o8thy-621518a5af.json";
    public FirebaseAdminConfig() throws IOException
    {
        InputStream inputStream = getClass().getResourceAsStream(firebase_path);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .setDatabaseUrl("https://transportation-app-297c0-default-rtdb.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
}
