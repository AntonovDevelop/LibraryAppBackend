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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String description;

    private String image_url;

    //messages will delete when order
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "category_fk")
    private List<Book> books = new ArrayList<>();

    public void updateProduct(Book book) {
        for (var p : books) {
            if (Objects.equals(p.getId(), book.getId())) {
                p = book;
                return;
            }
        }
    }
    public Book removeProduct(Long id) {
        for (var p : books) {
            if (Objects.equals(p.getId(), id)) {
                books.remove(p);
                return p;
            }
        }
        return null;
    }

    public void setProduct(Book book) {
        if (!books.contains(book)) {
            books.add(book);
            if (book.getCategory() != this) {
                book.setCategory(this);
            }
        }
    }
}
