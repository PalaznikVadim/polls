package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Poll;
import com.netcracker.edu.backend.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PollRepository extends CrudRepository<Poll,Integer> {
    //@Query("select u from User u where u.email = :email")
    Collection<Poll> findAllByUserByIdUser(User user);
}
