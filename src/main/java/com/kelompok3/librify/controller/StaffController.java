package com.kelompok3.librify.controller;

import com.kelompok3.librify.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Kel 3
 */
@Controller
@SessionAttributes("name")
@RequestMapping("/staff/")
public class StaffController {
    @Autowired
    private UserService userService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private PeminjamanService peminjamanService;
    @Autowired
    private BookService bookService;
    @Autowired
    private KeuanganService keuanganService;

    private String getLogedinUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/")
    public String registrationForm(ModelMap model) {
        String username = getLogedinUsername();
        model.put("user", userService.findUserByEmail(username));
        model.put("avgRating", ratingService.getAvgRating());
        model.put("jmlPeminjaman", peminjamanService.getJmlPeminjaman());
        model.put("jmlAnggota", userService.getJmlUser());
        model.put("jmlBuku", bookService.countBooks());
        model.put("pendapatanBulanan", keuanganService.getPendapatanBulanan());
        model.put("pengeluaranBulanan", keuanganService.getPengeluaranBulanan());
        return "staff";
    }
}
