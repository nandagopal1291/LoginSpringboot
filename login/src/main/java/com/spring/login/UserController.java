package com.spring.login;

import org.springframework.beans.factory.annotation.Autowired;
import com.spring.login.MyTopicConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    
	public static  String prod="";
	
	@KafkaListener(topics="products", groupId="group_id")
	public void consumeFromTopic(String message) {
		System.out.println("Consummed message "+message);
		prod= message;
	}
	
	
	@Autowired
    UserRepository userRepository;
    @PostMapping("/users/register")
    public Status registerUser(@Valid @RequestBody User newUser) {
        List<User> users = userRepository.findAll();
        System.out.println("New user: " + newUser.toString());
        for (User user : users) {
            System.out.println("Registered user: " + newUser.toString());
            if (user.equals(newUser)) {
                System.out.println("User Already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }
        userRepository.save(newUser);
        return Status.SUCCESS;
    }
    @PostMapping("/users/login")
    public String loginUser(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();
        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(true);
                //userRepository.save(user);
                return "User{" +
                "id=" + user.getId() +
                ", username='" + user.getUsername() + '\'' +
               ", user_key='" + user.getuser_key() + '\'' +
                     '}';
            }
        }
        return "Failure";
    }
    @PostMapping("/users/logout")
    public Status logUserOut(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();
        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(false);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
    @DeleteMapping("/users/all")
    public Status deleteUsers() {
        userRepository.deleteAll();
        return Status.SUCCESS;
    }
   /*
    @GetMapping("/product/all")
    public String AllProducts(){
    	return prod;
    }
    */
    @GetMapping("/product/all")
    public List<String> getMessages() {
        return MyTopicConsumer.getMessages();
    }


}