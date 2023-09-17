package client.data.model.dto;


import client.data.model.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private Long id;
    private Integer score;
    private String text;
    private Long bookId;
    private String book;
    private Long client_id;
    private String client;
    private Boolean liked;

    public ReviewDto(Review review) {
        this.id = review.getId();
        if (review.getScore() != null) {
            this.score = review.getScore();
        }
        if (review.getText() != null) {
            this.text = review.getText();
        }
        if (review.getLiked() != null) {
            this.liked = review.getLiked() == 1;
        }
        this.bookId = review.getBook().getId();
        this.book = review.getBook().getName();
        this.client_id = review.getClient().getId();
        this.client = review.getClient().getName() + ' ' + review.getClient().getSurname();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public void setIntegerLiked(Integer liked) {
        this.liked = liked == 1;
    }
}
