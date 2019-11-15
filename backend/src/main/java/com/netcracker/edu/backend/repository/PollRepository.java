package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Poll;
import com.netcracker.edu.backend.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface PollRepository extends CrudRepository<Poll,Integer> {
    Collection<Poll> findPollsByIdUser(Integer idUser);
    @Query("select p from Poll p where p.shared='Yes' and p.idTheme=(select t.id from Theme t where t.name=:theme)")
    Collection<Poll> findAllTemplateByTheme(@Param("theme") String theme);
}
