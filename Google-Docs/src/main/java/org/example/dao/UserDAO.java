package org.example.dao;
import org.example.entity.User;
import java.util.*;

public class UserDAO {
    private final Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public User getUserById(String userId) {
        return users.get(userId);
    }

    public void removeDocumentFromUser(String userId, String docId) {
        User user = users.get(userId);
        if (user != null) {
            user.removeDocument(docId);
        }
    }
}

