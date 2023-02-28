package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService service) {
        this.reportService = service;
    }

    @GetMapping()
    public String getReportList(Model model) {
        model.addAttribute("reportlist", reportService.getReportList());
        return "report/list";
    }

    @GetMapping("/register")
    public String getRegister(@AuthenticationPrincipal UserDetail userDetail, @ModelAttribute Report report) {
        Employee employee = new Employee();
        employee.setName(userDetail.getEmployee().getName());
        report.setEmployee(employee);
        return "report/register";
    }

    @PostMapping("/register")
    public String postRegister(@AuthenticationPrincipal UserDetail userDetail, @Validated Report report,
            BindingResult res, Model model) {
        if (res.hasErrors()) {
            return getRegister(userDetail, report);
        }
        report.setEmployee(userDetail.getEmployee());
        reportService.saveReport(report);
        return "redirect:/report";
    }

    @GetMapping("/detail/{id}")
    public String getReport(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("report", reportService.getReport(id));
        return "report/detail";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable("id") Integer id, Model model) {
        if (id != null) {
            Report report = reportService.getReport(id);
            model.addAttribute("report", report);
        }
        return "report/update";
    }

    @PostMapping("/update/{id}")
    public String postUpadate(@Validated Report report, BindingResult res, Model model) {
        Report dbReport = reportService.getReport(report.getId());
        dbReport.setReportDate(report.getReportDate());
        dbReport.setTitle(report.getTitle());
        dbReport.setContent(report.getContent());
        reportService.saveReport(dbReport);
        return "redirect:/report";
    }
}
