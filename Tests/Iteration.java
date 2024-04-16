package Tests;

import java.util.Collections;
import java.util.Iterator;

import Main.*;

public class Iteration {
    public static void main(String[] args) {
        testIterator();
    }

    public static void testIterator() {
        ChatServer chatServer = new ChatServer();

        User user = new User("CJ", chatServer);

        chatServer.registerUser(user);

        user.sendMessage(Collections.singletonList("Ramzi"), "Hello Ramzi!");
        user.sendMessage(Collections.singletonList("Ramzi"), "How are you?");
        user.sendMessage(Collections.singletonList("Ramzi"), "I'm fine!");

        System.out.println("CJ's chat history:");
        Iterator<Message> iterator = user.iterator(user);
        while (iterator.hasNext()) {
            Message message = iterator.next();
            System.out.println("[" + message.getTimestamp() + "] From: " + message.getSender() + " To: " + message.getRecipients() + ": " + message.getContent());
        }
    }
}
