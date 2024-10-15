package com.kelompok3.librify.controller;

import com.kelompok3.librify.dto.BookDto;
import com.kelompok3.librify.dto.KeuanganDto;
import com.kelompok3.librify.model.User;
import com.kelompok3.librify.service.KeuanganService;
import com.kelompok3.librify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("name")
public class KeuanganController {
    @Autowired
    private UserService userService;

    @Autowired
    private KeuanganService keuanganService;

    private String getLogedinUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/staff/tabelkeuangan")
    public String viewTabelKeuangan(ModelMap model) {
        User user = userService.findUserByEmail(getLogedinUsername());
        List<KeuanganDto> keuangans = keuanganService.getKeuangans();
        model.put("user", user);
        model.addAttribute("keuangans", keuangans);
        model.addAttribute("totalKeuangan", keuanganService.getTotal());
        return "tabelkeuangan";
    }

    @GetMapping("/staff/tabelkeuangan/keuangan")
    public String inputKeuangan(ModelMap model) {
        User user = userService.findUserByEmail(getLogedinUsername());
        model.put("user", user);
        model.addAttribute("keuangan", new KeuanganDto());
        return "keuangan";
    }

    @PostMapping("/staff/tabelkeuangan")
    public RedirectView storeKeuangan(@ModelAttribute KeuanganDto keuanganDto) {
        keuanganService.saveKeuangan(keuanganDto);
        return new RedirectView("/staff/tabelkeuangan");
    }

}
