package Main;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("CJ", chatServer);
        User user2 = new User("Ramzi", chatServer);
        User user3 = new User("Tay", chatServer);

        chatServer.registerUser(user1);
        chatServer.registerUser(user2);
        chatServer.registerUser(user3);

        List<String> recipients = new ArrayList<>();
        recipients.add("Ramzi");
        user1.sendMessage(recipients, "Hello Ramzi!");

        recipients.clear();
        recipients.add("CJ");
        user2.sendMessage(recipients, "Hi CJ!");

        recipients.clear();
        recipients.add("CJ");
        recipients.add("Ramzi");
        user3.sendMessage(recipients, "Hey CJ and Ramzi!");

        displayChatHistory(user1, "CJ's chat history:");
        displayChatHistory(user2, "\nRamzi's chat history:");
        displayChatHistory(user3, "\nTay's chat history:");

        user1.undoLastMessage();
        System.out.println("\nMessage undone by CJ:");

        user1.blockUser("Ramzi");
        System.out.println("\nCJ blocked Ramzi:");

        user2.sendMessageAndPrint(Collections.singletonList("Tay"), "Hi Tay!");
        user3.sendMessageAndPrint(Collections.singletonList("Ramzi"), "Hello Ramzi!");
        System.out.println("\n");

        displayChatHistory(user1, "User 1's updated chat history:");
        displayChatHistory(user2, "\nUser 2's updated chat history:");
        displayChatHistory(user3, "\nUser 3's updated chat history:");
    }

    private static void displayChatHistory(User user, String header) {
        System.out.println(header);
        Iterator iterator = user.iterator(user);
        while (iterator.hasNext()) {
            Message message = (Message) iterator.next();
            List<String> recipients = new ArrayList<>(message.getRecipients());
            recipients.remove(message.getSender());
            System.out.println("[" + message.getTimestamp() + "] From: " + message.getSender() + " To " + recipients + ": " + message.getContent());
        }
    }
}
