package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Theme;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ThemeRepository extends CrudRepository<Theme,Integer> {
    Theme findThemeByName(String name);
    @Query("select distinct (t.name) from Theme t join Poll p on t.id=p.idTheme where p.shared='Yes'")
    String[] findAllTemplateTheme();
}
