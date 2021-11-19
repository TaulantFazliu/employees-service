package co.skyver.employees.service;

import co.skyver.employees.model.Title;

import java.util.List;

public interface TitleService {

    Title assignTitleToEmployee(Long empNo, Title titleToAdd);

    List<Title> listAllTitles(Long empNo);
}
