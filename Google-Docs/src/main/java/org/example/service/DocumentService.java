package org.example.service;


import org.example.dao.DocumentDAO;
import org.example.entity.Document;
import org.example.dao.UserDAO;

import java.util.List;
import java.util.UUID;

public class DocumentService {
    private final DocumentDAO documentDAO;
    private final UserDAO userDAO;

    public DocumentService(DocumentDAO documentDAO, UserDAO userDAO) {
        this.documentDAO = documentDAO;
        this.userDAO = userDAO;
    }

    public Document createDocument(String userId, String name) {
        String docId = UUID.randomUUID().toString();
        Document doc = new Document(docId, name, userId);
        documentDAO.saveDocument(doc);
        userDAO.getUserById(userId).addDocument(docId);
        return doc;
    }

    public List<Document> getUserDocuments(String userId) {
        return documentDAO.getDocumentsByUserId(userId);
    }

    public void deleteDocument(String docId) {
        Document doc = documentDAO.getDocumentById(docId);
        if (doc != null) {
            userDAO.removeDocumentFromUser(doc.getUserId(), docId);
            documentDAO.deleteDocument(docId);
        }
    }
}