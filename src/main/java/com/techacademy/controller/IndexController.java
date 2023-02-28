package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
public class IndexController {
    private final ReportService reportService;

    public IndexController(ReportService service) {
        this.reportService = service;
    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserDetail userDetail, Model model) {
        model.addAttribute("username", userDetail.getEmployee().getName());
        model.addAttribute("reportlist", reportService.getReportListByEmployee(userDetail.getEmployee()));
        return "index";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/logout")
    public String postLogout() {
        return "redirect:/login";
    }
}
