package ru.yakovlev.simplerestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.yakovlev.simplerestapi.models.User;

import java.util.Optional;

/**
 * Created by alexi on 28.06.2025
 */
public interface UserRepository extends JpaRepository<User, Long> {
    //User findByEmail(String email);

    @Query(value = "select * from users where email = :email", nativeQuery = true)
    Optional<User> findByEmail(@Param(value = "email") String email);
}
