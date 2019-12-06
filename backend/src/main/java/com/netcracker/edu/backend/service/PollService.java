package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Poll;
import com.netcracker.edu.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface PollService {
    Optional<Poll> findPollById(Integer id);
    Page<Poll> findAllByUserId(Integer userId,String substr, Integer page,Integer size,String sort,String order);
    List<Poll> findAllTemplateByTheme(String theme);
    Poll save(Poll poll);
    void delete(Integer id);
    Poll findByLink(String link);
    //Page<Poll> searchBySubstr(String substr,Integer idUser,Integer page,Integer size, String sort,String order);
    Page<Poll> findAllByTheme(String theme,String substr, Integer idUser,Integer page,Integer size,String sort,String order);
    Page<Poll> findDraftsByUserId(Integer idUser,String substr,Integer page,Integer size,String sort,String order);
    Page<Poll> findActivePollsByUserId(Integer idUser,String substr,Integer page,Integer size,String sort,String order);
}
