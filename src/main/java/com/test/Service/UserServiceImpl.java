package com.test.Service;

import com.test.Execpton.NotFoundException;
import com.test.Model.Address;
import com.test.Model.Status;
import com.test.Model.User;
import com.test.Repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private AddresService addresService;

    @Autowired
    private MailSender mailSender;


    @Override
    public List<User> getall() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void save(User user) throws NotFoundException {
        Address address = addresService.getAllByNumberAndCityAndStreet(user.getAddress());
        if (address == null) {
            addresService.save(user.getAddress());
        } else {
            user.setAddress(address);
        }
        phoneService.save(user.getPhone());
        Status status = Status.valueOf("UNVERIFIED");
        user.setStatus(status);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        try {
            mailSender.sendSimpleMessage("gevorgarshkyan1995@gmail.com", "Verifiled" + " " + user.getEmail(), user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DeleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getById(int id) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new NotFoundException("not id");
        }
        return optionalUser.get();
    }

    @Override
    public void Urdate(int id, String name) throws NotFoundException {
        User user = getById(id);
        user.setName(name);
        userRepository.save(user);
    }

    @Override
    public User getBYEmail(String email) throws NotFoundException {
        User user = userRepository.getByEmail(email);

        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
    }

    @Override
    public List<User> getAllByName(String name) {
        return userRepository.getAllByName(name);
    }

    @Override
    public User getByEmailAndPassword(String Email, String Password) {
        return userRepository.getByEmailAndPassword(Email, Password);
    }

    @Override
    public void verified(String Email) throws NotFoundException {
        User user = getBYEmail(Email);
        if (user == null) {
            throw new NotFoundException();
        } else if (user.getStatus().equals(Status.UNVERIFIED)) {
            Status status = Status.valueOf("VERIFLED");
            user.setStatus(status);
            userRepository.save(user);
        }
    }

    @Override
    public void sendemail(String toEmail, String subject, String email) {
        try {
            mailSender.sendSimpleMessage(toEmail, subject, email);
        } catch (
                Exception e
        ) {
            e.printStackTrace();
        }
    }

    @Override
    public void ResetPasswordToken(String email) throws NotFoundException {
        User user = getBYEmail(email);
        String token = RandomString.make(10);
        user.setResetPasswordToken(token);
        userRepository.save(user);
        mailSender.tokenSimpleMessage("gevorgarshkyan1995@gmail.com", "Reset Password", token);
    }

    @Override
    public User ResetPassword(String token, String password) throws NotFoundException {
        User user = userRepository.getByResetPasswordToken(token);
        if (user == null) {
            throw new NotFoundException();
        }
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.save(user);
        return user;
    }


}