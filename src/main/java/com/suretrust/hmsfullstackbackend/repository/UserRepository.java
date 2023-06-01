package com.suretrust.hmsfullstackbackend.repository;

import com.suretrust.hmsfullstackbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
