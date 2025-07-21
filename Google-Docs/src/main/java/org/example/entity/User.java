package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String userId;
    private final String name;
    private final List<String> documentIds;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.documentIds = new ArrayList<>();
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public List<String> getDocumentIds() { return documentIds; }

    public void addDocument(String docId) {
        documentIds.add(docId);
    }

    public void removeDocument(String docId) {
        documentIds.remove(docId);
    }
}
