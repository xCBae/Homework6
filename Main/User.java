package Main;

import java.util.*;

public class User {
    private String username;
    private ChatServer chatServer;
    private ChatHistory chatHistory;

    public User(String username, ChatServer chatServer) {
        this.username = username;
        this.chatServer = chatServer;
        this.chatHistory = new ChatHistory();
    }

    public void sendMessage(List<String> recipients, String content) {
        chatServer.sendMessage(username, recipients, content);
        recipients.remove(username);
        chatHistory.addMessage(new Message(username, recipients, content));
    }


    public void sendMessageAndPrint(List<String> recipients, String content) {
        sendMessage(recipients, content);
        System.out.println("\nAdditional message sent:");
        System.out.println("[" + new Date() + "] From: " + username + " To " + recipients + ": " + content);
    }

    public void receiveMessage(Message message) {
        if (!message.getRecipients().contains(username)) {
            return;
        }
        chatHistory.addMessage(message);
    }

    public void undoLastMessage() {
        chatHistory.undoLastMessage(username);
    }
    
    public void blockUser(String username) {
        chatServer.blockUser(this.username, username);
        chatHistory.removeMessagesFromUser(username);
    }

    public Iterator<Message> iterator(User userToSearchWith) {
        return chatHistory.iterator(userToSearchWith);
    }

    public String getUsername() {
        return username;
    }
}
