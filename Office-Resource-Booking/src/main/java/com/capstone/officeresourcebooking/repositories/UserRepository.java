package com.capstone.officeresourcebooking.repositories;

import com.capstone.officeresourcebooking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods can go here, if needed
}

