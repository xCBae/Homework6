package Main;

import java.util.*;

public class ChatServer {
    private Map<String, User> users;
    private Map<String, Set<String>> blockedUsers;

    public ChatServer() {
        users = new HashMap<>();
        blockedUsers = new HashMap<>();
    }

    public void registerUser(User user) {
        users.put(user.getUsername(), user);
    }

    public void unregisterUser(User user) {
        users.remove(user.getUsername());
    }

    public void sendMessage(String sender, List<String> recipients, String content) {
        Message message = new Message(sender, recipients, content);
        for (String recipient : recipients) {
            if (!isBlocked(recipient, sender)) {
                User user = users.get(recipient);
                if (user != null) {
                    user.receiveMessage(message);
                }
            }
        }
    }

    public void blockUser(String blocker, String blockee) {
        blockedUsers.computeIfAbsent(blocker, k -> new HashSet<>()).add(blockee);
    }

    private boolean isBlocked(String username, String sender) {
        Set<String> blocked = blockedUsers.get(username);
        return blocked != null && blocked.contains(sender);
    }
}
