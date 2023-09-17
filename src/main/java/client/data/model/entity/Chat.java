package client.data.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    //done, messages will delete when order
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "chat_fk")
    private List<Message> messages = new ArrayList<>();

    @OneToOne(optional = false, mappedBy = "chat")
    private Order order;

    public void updateMessage(Message message) {
        for (var m : messages) {
            if (Objects.equals(m.getId(), message.getId())) {
                m = message;
                return;
            }
        }
    }
    public Message removeMessage(Long id) {
        for (var message : messages) {
            if (Objects.equals(message.getId(), id)) {
                messages.remove(message);
                return message;
            }
        }
        return null;
    }

    public void setMessage(Message message) {
        if (!messages.contains(message)) {
            messages.add(message);
            if (message.getChat() != this) {
                message.setChat(this);
            }
        }
    }
}
