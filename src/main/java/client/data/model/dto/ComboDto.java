package client.data.model.dto;

import client.data.model.entity.Book;
import client.data.model.entity.Combo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComboDto {
    private Long id;
    private String name;
    private String description;
    private String image_url;
    private Double sale;
    private Double price;
    private List<Long> products;

    public ComboDto(Combo combo) {
        this.id = combo.getId();
        this.name = combo.getName();
        this.description = combo.getDescription();
        this.image_url = combo.getImage_url();
        this.sale = combo.getSale();
        this.price = combo.getPrice();
        if (combo.getBooks() != null) {
            this.products = combo.getBooks()
                    .stream()
                    .map(Book::getId)
                    .toList();
        }
    }
}
