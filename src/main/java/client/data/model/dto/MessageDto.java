package client.data.model.dto;

import client.data.model.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private Long id;
    private String text;
    private Long time;
    private Long sender_id;
    private Long chat_id;

    public MessageDto(Message message) {
        this.id = message.getId();
        this.text = message.getText();
        this.time = message.getTime();
        this.sender_id = message.getSender_id();
        this.chat_id = message.getChat().getId();
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Long getTime() {
        return time;
    }

    public Long getSender_id() {
        return sender_id;
    }

    public Long getChat_id() {
        return chat_id;
    }
}
