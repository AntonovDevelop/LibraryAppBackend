package client.mvc;

import client.data.model.dto.BookDto;
import client.service.CategoryService;
import client.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryMvcController {
    private final CategoryService categoryService;
    private final BookService bookService;

    public CategoryMvcController(CategoryService categoryService, BookService bookService) {
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getAllCategories(Model model) {
        model.addAttribute("categories",
                categoryService.findAllCategories());
        return "categories-main";
    }

    @GetMapping("/products")
    public String getProductsByCategory(@RequestParam(name = "category") String category, Model model) {
        List<BookDto> products = bookService.findProductsByCategory(category);
        model.addAttribute("products", products);
        return "category";
    }
}
