package Main;

import java.util.*;

class SearchMessageByUser implements Iterator {
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
