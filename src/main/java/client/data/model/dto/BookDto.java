package client.data.model.dto;


import client.data.model.entity.Book;

public class BookDto {
    private Long id;
    private String name;
    private String description;
    private String image_url;
    private Long weight;
    private Double price;
    private Long category_id;
    private String category;
    private Long combo_id;
    private String combo;

    public BookDto() {
    }

    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.image_url = book.getImage_url();
        this.weight = book.getWeight();
        this.price = book.getPrice();
        this.category_id = book.getCategory().getId();
        this.category = book.getCategory().getName();
        if (book.getCombo() != null) {
            this.combo = book.getCombo().getName();
            this.combo_id = book.getCombo().getId();
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_url() {
        return image_url;
    }

    public Long getWeight() {
        return weight;
    }

    public Double getPrice() {
        return price;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public String getCategory() {
        return category;
    }

    public Long getCombo_id() {
        return combo_id;
    }

    public String getCombo() {
        return combo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCombo_id(Long combo_id) {
        this.combo_id = combo_id;
    }

    public void setCombo(String combo) {
        this.combo = combo;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
