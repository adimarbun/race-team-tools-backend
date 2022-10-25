package com.raceteam.racetools.service.serviceImpl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.raceteam.racetools.dto.RaceHistoryModel;
import com.raceteam.racetools.service.IFireStoreService;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;

@Service
public class FireStoreService implements IFireStoreService {

    private static final String COLLECTION_NAME ="status_car";

    /*
    Save race history to colection on firestore
     */
    public String saveHistory(RaceHistoryModel raceHistory) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.
                collection(COLLECTION_NAME).document(raceHistory.getId().toString()).set(raceHistory);
        return collectionApiFuture.get().getUpdateTime().toString();
    }
}
