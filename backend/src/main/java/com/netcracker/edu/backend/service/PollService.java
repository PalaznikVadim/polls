package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Poll;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PollService {
    Optional<Poll> findPollById(Integer id);

    Page<Poll> findAllByUserId(Integer userId, String theme, String substr, Integer page, Integer size, String sort, String order);

    List<Poll> findAllTemplateByTheme(String theme);

    Poll save(Poll poll);

    void delete(Integer id);

    Poll findByLink(String link);

    Page<Poll> findDraftsByUserId(Integer idUser, String theme, String substr, Integer page, Integer size, String sort, String order);

    Page<Poll> findActivePollsByUserId(Integer idUser, String theme, String substr, Integer page, Integer size, String sort, String order);
}
