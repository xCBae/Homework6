package Tests;

import java.util.Collections;
import java.util.Iterator;

import Main.*;

public class Block {
    public static void main(String[] args) {
        testBlockUser();
    }

    public static void testBlockUser() {
        ChatServer chatServer = new ChatServer();

        User user1 = new User("CJ", chatServer);
        User user2 = new User("Ramzi", chatServer);

        chatServer.registerUser(user1);
        chatServer.registerUser(user2);

        user1.blockUser("Ramzi");

        user2.sendMessage(Collections.singletonList("CJ"), "Hello CJ!");

        displayChatHistory(user1, "CJ's chat history after blocking Ramzi:");
    }

    private static void displayChatHistory(User user, String header) {
        System.out.println(header);
        Iterator<Message> iterator = user.iterator(user);
        while (iterator.hasNext()) {
            Message message = iterator.next();
            System.out.println("[" + message.getTimestamp() + "] From: " + message.getSender() + " To: " + message.getRecipients() + ": " + message.getContent());
        }
    }
}
