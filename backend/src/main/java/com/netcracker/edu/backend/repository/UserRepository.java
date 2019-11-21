package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.entity.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;




public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    User findByEmailAndPassword(String email,String password);
    Page<User> findAllByRole(Role role, Pageable pageable);
}
