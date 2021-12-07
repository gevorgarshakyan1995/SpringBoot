package com.test.Controller;

import com.test.Execpton.BadRequestException;
import com.test.Model.Status;
import com.test.Model.User;
import com.test.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User Login(@RequestParam("email") String email, @RequestParam("password") String password) throws BadRequestException {
        User user = userService.getByEmailAndPassword(email,password);
        Status status = Status.valueOf("NOVERIFLED");
        if (user.getStatus() == status){
            throw new BadRequestException("NO VERIFLED");
        }
        return userService.getByEmailAndPassword(email,password);
    }


}
