package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.viewModels.ClonePoll;
import com.netcracker.edu.fapi.models.viewModels.ViewPoll;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface PollService {

    ViewPoll findById(Integer id);
    Page<ViewPoll> findAllByUserId(Integer userId,int page,int size,String sort, String order);
    List<ViewPoll> findAllTemplateByTheme(String theme);
    ResponseEntity<?> save(ViewPoll poll);
    void deletePoll(Integer id);
    ViewPoll findByLink(String link);
    Page<ViewPoll> searchBySubstr(String substr,Integer idUser,Integer page,Integer size,String sort, String order);
    ViewPoll clonePoll(ClonePoll clonePoll);
    ViewPoll submitPoll(Integer id);

    Page<ViewPoll> findDraftsByUserId(Integer idUser,Integer page,Integer size,String sort,String order);
    Page<ViewPoll> findPollsByTheme(String theme,Integer idUser,Integer page,Integer size,String sort,String order);
    Page<ViewPoll> findActivePollsByUserId(Integer idUser,Integer page,Integer size,String sort,String order);
    Page<ViewPoll> getPolls(Integer userId, String select, int page, int size, String sort, String order);
}
