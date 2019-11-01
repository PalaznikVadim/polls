package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollRepository extends CrudRepository<Poll,Integer> {
}
