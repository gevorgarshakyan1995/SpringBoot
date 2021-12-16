package com.test.Service;

import com.test.Exception.NotFoundException;
import com.test.Model.User;

import java.security.Principal;
import java.util.List;

public interface UserService {

    public List<User> getall(Principal principal) throws NotFoundException;

    public void save(User users) throws NotFoundException;

    public void DeleteById(int id);

    public User getById(int id) throws NotFoundException;

    public void Urdate(int id, String name) throws NotFoundException;

    public User getBYEmail(String email) throws NotFoundException;

    public List<User> getAllByName(String name);

    User getByEmailAndPassword(String Email, String Password);

    void verified(String Email) throws NotFoundException;

    public void sendemail(String email, String subject, String text);

    void ResetPasswordToken(String email) throws NotFoundException;

    User ResetPassword(String token, String password) throws NotFoundException;
}
