package com.jawad.Instagram.repository;
import com.jawad.Instagram.model.User;
import com.jawad.Instagram.model.UserAuthenticationToken;
import org.springframework.data.repository.ListCrudRepository;
public interface IUserAuthenticationTokenRepo extends ListCrudRepository<UserAuthenticationToken, Long> {
    UserAuthenticationToken findByUser(User user);

}
