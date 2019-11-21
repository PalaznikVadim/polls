package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Poll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import java.util.Collection;

public interface PollRepository extends PagingAndSortingRepository<Poll,Integer> {
    Page<Poll> findPollsByIdUser(Integer idUser, Pageable pageable);
    @Query("select p from Poll p where p.shared='Yes' and p.idTheme=(select t.id from Theme t where t.name=:theme)")
    Collection<Poll> findAllTemplateByTheme(@Param("theme") String theme);

    Poll findByLink(String link);
}
