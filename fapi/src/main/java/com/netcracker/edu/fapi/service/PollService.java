package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.viewModels.ClonePoll;
import com.netcracker.edu.fapi.models.viewModels.ViewPoll;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface PollService {

    ViewPoll findById(int id);

    List<ViewPoll> findAllTemplateByTheme(String theme);

    ResponseEntity<?> save(ViewPoll poll);

    void deletePoll(int id);

    ViewPoll findByLink(String link);

    ViewPoll clonePoll(ClonePoll clonePoll);

    ViewPoll submitPoll(int id);

    Page<ViewPoll> findAllByUserId(int userId, String theme, String substr,
                                   int page, int size, String sort, String order);

    Page<ViewPoll> findDraftsByUserId(int idUser, String theme, String substr,
                                      int page, int size, String sort, String order);

    Page<ViewPoll> findActivePollsByUserId(int idUser, String theme, String substr,
                                           int page, int size, String sort, String order);

    Page<ViewPoll> getPolls(int userId, String select, String theme, String substr,
                            int page, int size, String sort, String order);
}
