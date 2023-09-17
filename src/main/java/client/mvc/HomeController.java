package client.mvc;

import client.service.CategoryService;
import client.service.ComboService;
import client.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    private final BookService bookService;
    private final CategoryService categoryService;
    private final ComboService comboService;

    public HomeController(BookService bookService, CategoryService categoryService, ComboService comboService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.comboService = comboService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("products",
                bookService.findAllProducts());
        model.addAttribute("combos",
                comboService.findAllCombos());
        return "index";
    }
}
