package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Poll;
import org.springframework.data.domain.Page;


public interface PollService {

    Poll findById(Integer id);
    Page<Poll> findAllByUserId(Integer userId,int page,int size,String sort, String order);
    Poll[] findAllTemplateByTheme(String theme);
    Poll save(Poll poll);
    void deletePoll(Integer id);
    Poll findByLink(String link);
    Page<Poll> searchBySubstr(String substr,Integer idUser,Integer page,Integer size,String sort, String order);
}
