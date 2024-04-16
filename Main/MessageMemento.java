package Main;

import java.util.Date;

class MessageMemento {
    private String content;
    private Date timestamp;

    public MessageMemento(String content, Date timestamp) {
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
