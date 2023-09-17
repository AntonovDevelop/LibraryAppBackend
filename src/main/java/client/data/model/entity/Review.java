package client.data.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer score;
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "product_fk")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "client_fk")
    private Client client;

    private Integer liked;

    public void setBook(Book book) {
        this.book = book;
        if (!book.getReviews().contains(this)) {
            book.setReview(this);
        }
    }

    public void setClient(Client client) {
        this.client = client;
        if (!client.getReviews().contains(this)) {
            client.setReview(this);
        }
    }
}
