package Tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import Main.*;

public class Send {
    public static void main(String[] args) {
        testSendMessage();
    }

    public static void testSendMessage() {
        ChatServer chatServer = new ChatServer();

        User user1 = new User("CJ", chatServer);
        User user2 = new User("Ramzi", chatServer);
        User user3 = new User("Tay", chatServer);

        chatServer.registerUser(user1);
        chatServer.registerUser(user2);
        chatServer.registerUser(user3);

        user1.sendMessage(Collections.singletonList("Ramzi"), "Hello Ramzi!");
        user2.sendMessage(Collections.singletonList("CJ"), "Hi CJ!");
        user3.sendMessage(Arrays.asList("CJ", "Ramzi"), "Hey CJ and Ramzi!");

        displayChatHistory(user1, "CJ's chat history:");
        displayChatHistory(user2, "\nRamzi's chat history:");
        displayChatHistory(user3, "\nTay's chat history:");
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
