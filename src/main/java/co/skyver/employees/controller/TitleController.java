package co.skyver.employees.controller;

import co.skyver.employees.model.Title;
import co.skyver.employees.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees/{empNo}/titles")
public class TitleController {

    private final TitleService titleService;

    @Autowired
    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @GetMapping
    public List<Title> getTitles(@PathVariable Long empNo) {
        return this.titleService.listAllTitles(empNo);
    }

    @PostMapping
    public Title addTitle(@PathVariable Long empNo, @RequestBody Title title) {
        return this.titleService.assignTitleToEmployee(empNo, title);
    }

}
