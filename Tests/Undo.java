package Tests;

import java.util.Collections;
import java.util.Iterator;

import Main.*;

public class Undo {
    public static void main(String[] args) {
        testUndoLastMessage();
    }

    public static void testUndoLastMessage() {
        ChatServer chatServer = new ChatServer();

        User user = new User("CJ", chatServer);

        chatServer.registerUser(user);

        user.sendMessage(Collections.singletonList("Ramzi"), "Hello Ramzi!");
        user.sendMessage(Collections.singletonList("Ramzi"), "How are you?");
        user.sendMessage(Collections.singletonList("Ramzi"), "I'm fine!");

        displayChatHistory(user, "CJ's initial chat history:");

        user.undoLastMessage();

        displayChatHistory(user, "\nCJ's updated chat history after undoing last message:");
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
