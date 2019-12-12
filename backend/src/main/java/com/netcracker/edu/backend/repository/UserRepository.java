package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);

    @Query("select u from User u where u.role='user' and (:search is null or " +
            "(upper(u.name) like concat('%', upper(:search), '%') or " +
            "(upper(u.surname) like " + "concat('%', upper(:search), '%')) or " +
            "(upper(u.dateOfBirth) like concat('%', upper(:search), '%'))))")
    Page<User> findAllByRole(String search, Pageable pageable);

    User findByEmail(String email);
}
