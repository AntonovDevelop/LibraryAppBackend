package client.service;

import client.data.model.dto.BookDto;
import client.data.model.entity.Book;
import client.data.model.entity.Category;
import client.data.repository.ProductRepository;
import client.service.exception.ProductNotFoundException;
import client.util.validation.ValidationException;
import client.util.validation.ValidatorUtil;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    //вроде закончен
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ValidatorUtil validatorUtil;

    public BookService(ProductRepository productRepository, CategoryService categoryService,
                       ValidatorUtil validatorUtil) {
        this.productRepository = productRepository;
        this.validatorUtil = validatorUtil;
        this.categoryService = categoryService;
    }

    //Поиск всех записей в репозитории
    @Transactional(readOnly = true)
    public List<BookDto> findAllProducts() {
        return productRepository.findAll().stream().map(BookDto::new).toList();
    }

    //Создание продукта через поля
    @Transactional
    public Book addProduct(String name,
                           String description,
                           String image_url,
                           Long weight,
                           Double price,
                           Long category_id) {
        if (!StringUtils.hasText(name) || !StringUtils.hasText(description) || category_id == null || category_id <= 0 ||
                weight == null || weight < 0 || price == null || price < 0) {
            throw new IllegalArgumentException("Product fields are null or empty");
        }
        if (findProductByName(name) != null) {
            throw new ValidationException(String.format("Product '%s' is already exist", name));
        }
        final Book book = new Book();
        book.setName(name);
        book.setDescription(description);
        book.setImage_url(image_url);
        book.setWeight(weight);
        book.setPrice(price);
        final Category category = categoryService.findById(category_id);
        book.setCategory(category);
        validatorUtil.validate(book);
        return productRepository.save(book);
    }

    //Создание продукта через Dto
    @Transactional
    public BookDto addProduct(BookDto bookDto) {
        return new BookDto(
                addProduct(
                        bookDto.getName(),
                        bookDto.getDescription(),
                        bookDto.getImage_url(),
                        bookDto.getWeight(),
                        bookDto.getPrice(),
                        bookDto.getCategory_id()
                )
        );
    }

    //Поиск продукта в репозитории
    @Transactional(readOnly = true)
    public Book findProduct(Long id) {
        final Optional<Book> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ProductNotFoundException(id));
    }

    //Поиск продукта в репозитории
    @Transactional(readOnly = true)
    public BookDto findProduct(BookDto bookDto) {
        return new BookDto(findProduct(bookDto.getId()));
    }

    @Transactional(readOnly = true)
    public Book findProductByName(String name) {
        return productRepository.findOneByNameIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    public List<BookDto> findProductsByCategory(String name) { return productRepository.findProductsByCategory(name)
            .stream()
            .map(BookDto::new)
            .toList(); }

    //Изменение продукта по полям
    @Transactional
    public Book updateProduct(Long id, String name,
                              String description,
                              String image_url,
                              Long weight,
                              Double price,
                              Long category_id) {
        if (!StringUtils.hasText(name) || !StringUtils.hasText(description) || id == null || id <= 0 || weight == null
                || weight < 0 || price == null || price < 0 || category_id == null || category_id <= 0) {
            throw new IllegalArgumentException("Product fields are null or empty");
        }
        final Book book = findProduct(id);
        if (book == null) {
            throw new ProductNotFoundException(id);
        }
        if (!book.getName().equals(name) && findProductByName(name) != null) {
            throw new IllegalArgumentException(String.format("Product with name [%s] is existed", name));
        }
        book.setName(name);
        book.setDescription(description);
        book.setImage_url(image_url);
        book.setWeight(weight);
        book.setPrice(price);

        final Category category = categoryService.findById(category_id);
        if (book.getCategory().getId().equals(category_id)) {
            book.getCategory().updateProduct(book);
        } else {
            book.getCategory().removeProduct(id);
            book.setCategory(category);
        }

        validatorUtil.validate(book);
        return productRepository.save(book);
    }

    //Изменение продукта по полям через Dto
    @Transactional
    public BookDto updateProduct(Long id, BookDto bookDto) {
        return new BookDto(updateProduct(id, bookDto.getName(), bookDto.getDescription(),
                bookDto.getImage_url(), bookDto.getWeight(), bookDto.getPrice(), bookDto.getCategory_id()));
    }

    @Transactional
    public Book deleteProduct(Long id) {
        Book current = findProduct(id);
        productRepository.delete(current);
        return current;
    }

    @Transactional
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @Transactional(readOnly = true)
    public List<BookDto> findProducts(List<Long> ids) {
        return productRepository.findAllById(ids)
                .stream()
                .map(BookDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<Long> findProductsByClientCart(Long clientId, Long cartId) {
        return productRepository.findProductsByClient(clientId, cartId)
                .stream()
                .map(Book::getId)
                .toList();
    }

    @Transactional(readOnly = true)
    public Boolean isProductInCart(Long clientId, Long productId, Long cartId) {
        return findProductsByClientCart(clientId, cartId)
                .contains(productId);
    }
}
