package org.com.DriverPartitionService.Firebase.Config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseAdminConfig
{
    public FirebaseAdminConfig() throws IOException
    {
        InputStream inputStream = getClass().getResourceAsStream("../../../../../transportation-app-297c0-firebase-adminsdk-o8thy-126e20b06d.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .setDatabaseUrl("https://transportation-app-297c0-default-rtdb.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
}
