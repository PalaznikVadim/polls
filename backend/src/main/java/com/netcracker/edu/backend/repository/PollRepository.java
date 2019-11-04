package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Poll;
import com.netcracker.edu.backend.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PollRepository extends CrudRepository<Poll,Integer> {
    Collection<Poll> findPollByUserByIdUser_Id(Integer id);
}
