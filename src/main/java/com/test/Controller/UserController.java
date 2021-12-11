package com.test.Controller;

import com.test.Config.CustomUserDetailsService;
import com.test.Execpton.NotFoundException;
import com.test.Model.User;
import com.test.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @RolesAllowed(value = "ROLE_USER")
    @GetMapping
    List<User> getAll(Principal principal)  throws NotFoundException {
        return userService.getall(principal);
    }

    @RolesAllowed(value = "ROLE_ADMIN")
    @GetMapping("{id}")
    User getById(@PathVariable int id) throws NotFoundException {
        return userService.getById(id);
    }

    @GetMapping("/get-by-email")
    User getByEmail(@RequestParam("email") String email) throws NotFoundException {
        return userService.getBYEmail(email);
    }

    @DeleteMapping
    public void DeleteById(@RequestParam("id") int id) {
        userService.DeleteById(id);
    }

    @PutMapping
    public void Urdate(@RequestParam("id") int id, @RequestParam("name") String name) throws NotFoundException {
        userService.Urdate(id, name);

    }

    //  @PostMapping
    //  public User save(@RequestParam("email") String email, @RequestParam("name") String name, @RequestParam("password") String password) {
    //     User user = new User(name, email, password);
    //     return userService.save(user);
    // }

    @GetMapping("/get-all-by-name")
    public List<User> getAllByName(@RequestParam("name") String name) {
        return userService.getAllByName(name);
    }

    @PostMapping
    void save(@RequestBody User user) throws NotFoundException {
        userService.save(user);
    }

    @PostMapping("/verify")
    void verified(@RequestParam("email") String email) throws NotFoundException {
        userService.verified(email);
    }

    @PostMapping("/send-email")
    void sendemail(@RequestParam("email") String toEmail, @RequestParam("subject") String subject, @RequestParam("email") String email) {
        userService.sendemail(toEmail, subject, email);
    }

    @PostMapping("/reset-password")
    void ResetPasswordToken(@RequestParam("email") String Email) throws NotFoundException {
        userService.ResetPasswordToken(Email);
    }

    @PutMapping("/reset-password")
    public User ResetPassword(@RequestParam("token") String token, @RequestParam("password") String password) throws NotFoundException {
        return userService.ResetPassword(token, password);
    }
}

