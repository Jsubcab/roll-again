package rollagain.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rollagain.main.entities.Rates;
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

    //USERS
    @GetMapping
    public List<Users> getUsers(){
        return userService.getUsers();
    }

    //USERS
    @GetMapping(value = "{userId}")
    public Users getUserById(@PathVariable("userId") Long userId){

        return userService.getUserById(userId);
    }


    @PostMapping
    public void registerNewUser(@RequestBody Users user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUsers(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUsers(
    @PathVariable("userId") Long userId,
    @RequestParam(required = false) String username,
    @RequestParam(required = false) String email) {
            userService.updateUser(userId, username, email);
        }

    //RATES
    @GetMapping(value = "{userId}/rates")
    public List<Rates> getRates(@PathVariable("userId") Long userId) {
    return userService.getRates(userId);
    }

    @PostMapping(value = "{userId}/rates")
    public void registerNewRates(@PathVariable("userId") Long userId, @RequestBody Rates rates) {
    userService.addNewRate(rates, userId);
    }

    @DeleteMapping(path = "{userId}/rates/{rateId}")
    public void deleteRateById(@PathVariable("rateId") Long rateId) {
        userService.deleteRate(rateId);
    }

    @PutMapping(path = "{userId}/rates/{rateId}")
    public void updateRates(
        @PathVariable("rateId") Long rateId,
        @RequestParam(required = false) Double rating,
        @RequestParam(required = false) String comment) {
        userService.updateRate(rateId, rating, comment);
    }

}
