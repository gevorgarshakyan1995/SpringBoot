package com.test;

import com.test.Model.User;
import com.test.Service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
           SpringApplication.run(MainApplication.class, args);
       // ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);
        //UserService userService = (UserService) context.getBean("userServiceImpl");






        //  for(User x : userService.getAllByName("55")){
        //                System.out.println(x.getEmail()+" "+x.getName()+" "+x.getId());
        // }


        //User user = userService.getBYEmail("psvm");
        //System.out.println(user.getName()+" "+user.getEmail());

        //userService.Urdate(10,"koko");

        // User user=userService.getById(10);
        //System.out.println(user.getName()+" "+user.getEmail());

        // userService.DeleteById(2);


        // userService.save(new User("d", "98", "ds"));


        //           for(User x : userService.getall()){
        //              System.out.println(x.getEmail()+" "+x.getName()+" "+x.getId());
        //          }
    }


}
