package Main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ChatHistory {
    private List<Message> messages;

    public ChatHistory() {
        messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void undoLastMessage(String username) {
        for (int i = messages.size() - 1; i >= 0; i--) {
            Message message = messages.get(i);
            if (message.getSender().equals(username)) {
                messages.remove(i);
                return;
            }
        }
    }

    public void removeMessagesFromUser(String username) {
        messages.removeIf(message -> message.getSender().equals(username));
    }

    public Iterator iterator(User userToSearchWith) {
        return new SearchMessageByUser(userToSearchWith, messages);
    }

    private class SearchMessageByUser implements Iterator {
        private int currentIndex;
        private String username;
        private List<Message> filteredMessages;

        public SearchMessageByUser(User userToSearchWith, List<Message> messages) {
            this.username = userToSearchWith.getUsername();
            this.filteredMessages = new ArrayList<>();
            for (Message message : messages) {
                if (message.getSender().equals(username) || message.getRecipients().contains(username)) {
                    filteredMessages.add(message);
                }
            }
        }

        @Override
        public boolean hasNext() {
            return currentIndex < filteredMessages.size();
        }

        @Override
        public Message next() {
            return filteredMessages.get(currentIndex++);
        }
    }
}
