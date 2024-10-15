package com.kelompok3.librify.controller;

import com.kelompok3.librify.dto.BookDto;
import com.kelompok3.librify.dto.PeminjamanDto;
import com.kelompok3.librify.dto.UserDto;
import com.kelompok3.librify.model.User;
import com.kelompok3.librify.service.BookService;
import com.kelompok3.librify.service.PeminjamanService;
import com.kelompok3.librify.service.PengembalianService;
import com.kelompok3.librify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import static com.kelompok3.librify.mapper.UserMapper.mapToUserDto;

@Controller
@SessionAttributes("name")
public class PeminjamanController {
    @Autowired
    private UserService userService;
    @Autowired
    private PeminjamanService peminjamanService;
    @Autowired
    private BookService bookService;
    @Autowired
    private PengembalianService pengembalianService;
    
    private String getLogedinUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/user/tabelpeminjaman/{bookId}/peminjaman")
    public String viewFormPeminjaman(ModelMap model, @PathVariable Long bookId) {
        User user = userService.findUserByEmail(getLogedinUsername());
        BookDto book = bookService.getBook(bookId);
        model.addAttribute("user", user);
        PeminjamanDto peminjamanDto = new PeminjamanDto();
        peminjamanDto.setUser(mapToUserDto(user));
        peminjamanDto.setBook(book);

        if (book == null) {
            return "/user/tabelpeminjaman";
        }
        model.addAttribute("peminjaman",peminjamanDto);
        return "peminjaman";
    }

    @RequestMapping(value = "/user/tabelpeminjaman", method = RequestMethod.POST)
    public RedirectView store(Model model,
                              @ModelAttribute("peminjaman") PeminjamanDto peminjamanDto,
                              @RequestParam("book_id") long book_id
    ) {
        model.addAttribute("peminjaman", peminjamanDto);
        User user = userService.findUserByEmail(getLogedinUsername());
        peminjamanService.savePeminjaman(peminjamanDto, user, book_id);
        return new RedirectView("/user/tabelpeminjaman");
    }

    @GetMapping("/user/tabelpeminjaman")
    public String viewTabelPeminjaman(ModelMap model) {
        User user = userService.findUserByEmail(getLogedinUsername());
        model.addAttribute("user", user);
        model.addAttribute("peminjamans", peminjamanService.getPeminjamans(user.getId()));
        return "tabelpeminjaman";
    }
// Mapping For ROLE_ADMIN
    @GetMapping("/staff/tabelpeminjaman")
    public String viewTabelPeminjamanStaff(ModelMap model) {
        User user = userService.findUserByEmail(getLogedinUsername());
        model.addAttribute("user", user);
        model.addAttribute("peminjamans", peminjamanService.getPeminjamans());
        return "tabelpeminjamanStaff";
    }

    @RequestMapping(value = "/staff/tabelpeminjaman", method = RequestMethod.POST)
    public RedirectView approve(Model model,
                              @ModelAttribute("peminjaman") PeminjamanDto peminjamanDto,
                                @RequestParam("peminjaman_id") long peminjaman_id)
    {
        PeminjamanDto peminjamanDto1 = peminjamanService.getPeminjaman(peminjaman_id);
        pengembalianService.savePengembalian(peminjamanDto1);
        return new RedirectView("/staff/tabelpeminjaman");
    }
}
