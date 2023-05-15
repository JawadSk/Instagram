package com.jawad.Instagram.repository;
import com.jawad.Instagram.model.User;
import org.springframework.data.repository.ListCrudRepository;

public interface IUserRepository extends ListCrudRepository<User, Long> {

    boolean existsByEmail(String email);

    User findByEmail(String email);
}
