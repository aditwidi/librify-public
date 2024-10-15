package com.kelompok3.librify.controller;

import com.kelompok3.librify.dto.BookDto;
import com.kelompok3.librify.model.Book;
import com.kelompok3.librify.model.User;
import com.kelompok3.librify.service.BookService;
import com.kelompok3.librify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("name")
public class BookController {
    @Autowired
    private UserService userService;

        @Autowired
    private BookService bookService;

    private String getLogedInUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/user/katalog")
    public String showKatalog(Model model,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {
        int pageSize = size.orElse(10);
        long totalBooks = bookService.countBooks();
        int totalPages = (int) Math.ceil((double) totalBooks / pageSize);
        int currentPage = page.orElse(1);
        if (currentPage < 1) {
            currentPage = 1;
        }
        if (currentPage > totalPages) {
            currentPage = totalPages;
        }
        Page<Book> bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("bookPage", bookPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalBooks);
        model.addAttribute("user", userService.findUserByEmail(getLogedInUsername()));

        return "katalog";
    }

    @GetMapping("/caribuku")
    @ResponseBody
    public List<BookDto> searchBooks(@RequestParam("search") String searchTerm) {
        return bookService.searchBooks(searchTerm);
    }

    @GetMapping("/staff/katalog")
    public String showKatalogStaff(Model model,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {
        int pageSize = size.orElse(10);
        long totalBooks = bookService.countBooks();
        int totalPages = (int) Math.ceil((double) totalBooks / pageSize);
        int currentPage = page.orElse(1);
        if (currentPage < 1) {
            currentPage = 1;
        }
        if (currentPage > totalPages) {
            currentPage = totalPages;
        }

        Page<Book> bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("bookPage", bookPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalBooks);
        model.addAttribute("user", userService.findUserByEmail(getLogedInUsername()));

        return "katalogStaff";
    }

    @RequestMapping(value = "/staff/katalog", method = RequestMethod.POST)
    public RedirectView storeBuku(Model model, @ModelAttribute("book") BookDto bookDto) {
        model.addAttribute("book", bookDto);
        bookService.saveBook(bookDto);
        return new RedirectView("/staff/katalog");
    }

    @RequestMapping(value = "/staff/katalog/{bookId}/delete", method = RequestMethod.POST)
    public RedirectView deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return new RedirectView("/staff/katalog");
    }

    @GetMapping("/staff/katalog/tambah-buku")
    public String tambahBuku(Model model) {
        model.addAttribute("book", new BookDto());
        return "tambahbuku";
    }

    @GetMapping("/staff/katalog/{bookId}/update")
    public String viewFormUpdateBuku(ModelMap model, @PathVariable Long bookId) {
        User user = userService.findUserByEmail(getLogedInUsername());
        BookDto book = bookService.getBook(bookId);
        model.put("user", user);
        model.addAttribute("book",book);

        if (book == null) {
            return "/staff/katalog";
        }
        model.addAttribute("book",book);
        return "editbuku";
    }
}
