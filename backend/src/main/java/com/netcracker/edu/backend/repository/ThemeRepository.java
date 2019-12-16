package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Theme;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThemeRepository extends CrudRepository<Theme, Integer> {
    Theme findThemeByName(String name);

    @Query("select distinct (t.name) from Theme t join Poll p on t.id=p.idTheme where p.shared='Yes'")
    List<String> findAllTemplateTheme();

    @Query("select distinct (t.name) from Theme t join Poll p on t.id=p.idTheme where p.idUser=:idUser")
    List<String> findAllByIdUser(@Param("idUser") int idUser);
}
