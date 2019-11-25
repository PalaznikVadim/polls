package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Poll;
import com.netcracker.edu.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface PollService {
    Optional<Poll> findPollById(Integer id);
    Page<Poll> findAllByUserId(Integer userId, Pageable pageable);
    List<Poll> findAllTemplateByTheme(String theme);
    Poll save(Poll poll);
    void delete(Integer id);
    Poll findByLink(String link);
    Page<Poll> searchBySubstr(String substr,Integer idUser,Pageable pageable);
}
