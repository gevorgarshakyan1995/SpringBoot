package com.test.Repository;

import com.test.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select u from User u where u.email = :email")
    User getByEmail(String email);

    @Query(nativeQuery = true, value = "Select * from user where soundex(name)=soundex(?)") 
    List<User> getAllByName (String name);

    User getByEmailAndPassword (String email,String password);

   @Query(value = "select u from User u where u.ResetPasswordToken = :token")
    User getByResetPasswordToken (String token);
}
