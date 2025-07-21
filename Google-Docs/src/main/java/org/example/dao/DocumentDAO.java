package org.example.dao;
import org.example.entity.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DocumentDAO {
    private final Map<String, Document> documents = new HashMap<>();
    private final Map<String, List<String>> userToDocIds = new HashMap<>();

    public void saveDocument(Document document) {
        documents.put(document.getId(), document);
    }

    public Document getDocumentById(String docId) {
        if (!documents.containsKey(docId)) {
            System.out.println("Document not found: " + docId);
            return null;
        }
        else{
            return documents.get(docId);
        }
    }

    public List<Document> getDocumentsByUserId(String userId) {
        List<Document> result = new ArrayList<>();
        for (Document doc : documents.values()) {
            if (doc.getUserId().equals(userId)) {
                result.add(doc);
            }
        }
        return result;
    }

    public void deleteDocument(String docId) {
        Document doc = documents.get(docId);
        if (doc != null) {
            documents.remove(docId);
            userToDocIds.getOrDefault(doc.getUserId(), new ArrayList<>()).remove(docId);
        }
    }
}

