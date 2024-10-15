/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok3.librify.controller;

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
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    PeminjamanService peminjamanService;
    @Autowired
    BookService bookService;
    @Autowired
    PengembalianService pengembalianService;

    private String getLogedinUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/")
    public String registrationForm(ModelMap model) {
        User user = userService.findUserByEmail(getLogedinUsername());
            model.put("user", user);
            model.addAttribute("peminjamans", pengembalianService.getPeminjamanUser(user.getId()));
            return "user";
    }
}
