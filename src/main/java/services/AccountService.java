package services;

import models.User;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static final Map<String, User> loginToProfile = new HashMap<>();
    private static final Map<String, User> sessionIdToProfile = new HashMap<>();

    public AccountService() {
        loginToProfile.put("admin", new User("admin", "admin", "jopa@gmail.com"));
    }

    public void addNewUser(User userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public User getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public User getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, User userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}