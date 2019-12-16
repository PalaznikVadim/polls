package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Poll;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PollService {
    Optional<Poll> findPollById(int id);

    Page<Poll> findAllByUserId(int userId, String theme, String substr, int page,
                               int size, String sort, String order);

    List<Poll> findAllTemplateByTheme(String theme);

    Poll save(Poll poll);

    void delete(int id);

    Poll findByLink(String link);

    Page<Poll> findDraftsByUserId(int idUser, String theme, String substr,
                                  int page, int size, String sort, String order);

    Page<Poll> findActivePollsByUserId(int idUser, String theme, String substr,
                                       int page, int size, String sort, String order);
}
