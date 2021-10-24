package rollagain.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rollagain.main.entities.Users;
import rollagain.main.services.UsersService;


@RestController
@RequestMapping(path = "api/users")
public class UsersController
{

    private final UsersService userService;

    @Autowired
    public UsersController(final UsersService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Users> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public void registerNewUser(@RequestBody Users user) {
        userService.addNewUser(user);
    }
}
