package org.example;

import org.example.Enums.Direction;
import org.example.dao.DocumentDAO;
import org.example.dao.UserDAO;
import org.example.entity.Document;
import org.example.entity.User;
import org.example.service.DocumentService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Setup DAOs and service
        UserDAO userDAO = new UserDAO();
        DocumentDAO documentDAO = new DocumentDAO();
        DocumentService documentService = new DocumentService(documentDAO, userDAO);

        // Add Users
        User user1 = new User("u1", "Aditi");
        User user2 = new User("u2", "Raj");
        userDAO.addUser(user1);
        userDAO.addUser(user2);

        // Create Documents for User1
        Document doc1 = documentService.createDocument("u1", "DesignNotes");
        doc1.getEditor().append('H');
        doc1.getEditor().append('e');
        doc1.getEditor().append('l');
        doc1.getEditor().append('l');
        doc1.getEditor().append('o');

        Document doc2 = documentService.createDocument("u1", "LLDGuide");
        doc2.getEditor().append('L');
        doc2.getEditor().append('L');
        doc2.getEditor().append('D');

        // Move cursor and replace characters
        doc2.getEditor().moveArrow(Direction.LEFT);
        doc2.getEditor().replace('X'); // changes second 'L' to 'X'

        // Create Documents for User2
        Document doc3 = documentService.createDocument("u2", "BackendIdeas");
        doc3.getEditor().append('B');
        doc3.getEditor().append('E');
        doc3.getEditor().append('S');
        doc3.getEditor().append('T');

        Document doc4 = documentService.createDocument("u2", "InfraNotes");
        doc4.getEditor().append('I');
        doc4.getEditor().append('N');
        doc4.getEditor().append('F');
        doc4.getEditor().append('R');
        doc4.getEditor().append('A');

        // Display documents for each user
        System.out.println("\n=== Documents for Aditi ===");
        displayDocs(documentService.getUserDocuments("u1"));

        System.out.println("\n=== Documents for Raj ===");
        displayDocs(documentService.getUserDocuments("u2"));

        // Delete a document for Aditi
        System.out.println("\n--- Deleting 'LLDGuide' for Aditi ---");
        documentService.deleteDocument(doc2.getId());

        // Display updated documents
        System.out.println("\n=== Updated Documents for Aditi ===");
        displayDocs(documentService.getUserDocuments("u1"));

        // Attempt to use deleted document
        System.out.println("\n--- Trying to edit deleted document ---");
        documentDAO.getDocumentById(doc2.getId());

        // Display documents again
        System.out.println("\n=== Final Documents for Raj ===");
        displayDocs(documentService.getUserDocuments("u2"));
    }

    private static void displayDocs(List<Document> documents) {
        for (Document doc : documents) {
            System.out.println("Document: " + doc.getName());
            System.out.println("Content:\n" + doc.getEditor().getText());
        }
    }
}