package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.consts.BackendConsts;
import com.netcracker.edu.backend.entity.Poll;
import com.netcracker.edu.backend.repository.PollRepository;
import com.netcracker.edu.backend.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PollServiceImpl implements PollService {

    @Autowired
    private PollRepository pollRepository;

    @Override
    public Optional<Poll> findPollById(int id) {
        return pollRepository.findById(id);
    }

    @Override
    public Page<Poll> findAllByUserId(int userId, String theme, String substr, int page, int size,
                                      String sort, String order) {
        Pageable pageable = createPageable(page, size, sort, order);
        if (theme.equals(BackendConsts.ALL)) {
            theme = null;
        }
        return pollRepository.findPollsByIdUser(userId, theme, substr, pageable);
    }

    @Override
    public List<Poll> findAllTemplateByTheme(String theme) {
        return (List<Poll>) pollRepository.findAllTemplateByTheme(theme);
    }

    @Override
    public Poll save(Poll poll) {
        return pollRepository.save(poll);
    }

    @Override
    public void delete(int id) {
        pollRepository.deleteById(id);
    }

    @Override
    public Poll findByLink(String link) {
        return pollRepository.findByLink(link);
    }


    @Override
    public Page<Poll> findDraftsByUserId(int idUser, String theme, String substr,
                                         int page, int size, String sort, String order) {
        Pageable pageable = createPageable(page, size, sort, order);
        if (theme.equals(BackendConsts.ALL))
            theme = null;
        return pollRepository.findDraftsByUserId(theme, substr, idUser, pageable);
    }

    @Override
    public Page<Poll> findActivePollsByUserId(int idUser, String theme, String substr,
                                              int page, int size, String sort, String order) {
        if (theme.equals(BackendConsts.ALL))
            theme = null;
        Pageable pageable = createPageable(page, size, sort, order);
        return pollRepository.findActivePollsByUserId(theme, substr, idUser, pageable);
    }

    private Pageable createPageable(int page, int size, String sort, String order) {
        Pageable pageable;
        if (order.toLowerCase().contains(BackendConsts.ASC)) {
            pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        }
        return pageable;
    }

}
