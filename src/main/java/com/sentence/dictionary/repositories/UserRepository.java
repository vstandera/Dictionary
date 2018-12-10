package com.sentence.dictionary.repositories;

import com.sentence.dictionary.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
