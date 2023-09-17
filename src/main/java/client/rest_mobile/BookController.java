package client.rest_mobile;

import client.configuration.WebConfiguration;
import client.data.model.dto.BookDto;
import client.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAll")
    public List<BookDto> getAllProducts() {
        return bookService.findAllProducts();
    }

    @GetMapping("/getOne")
    public BookDto getOneProduct(@RequestBody BookDto bookDto) {
        return bookService.findProduct(bookDto);
    }

    @PostMapping("/addOne")
    public BookDto createOne(@RequestBody BookDto dto) {
        return bookService.addProduct(dto);
    }

    @PostMapping("/updateOne/{id}")
    public BookDto updateOne(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return bookService.updateProduct(id, bookDto);
    }
}