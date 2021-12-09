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

import rollagain.main.controllers.post.NewOrderRequest;
import rollagain.main.entities.Orders;
import rollagain.main.entities.Permissions;
import rollagain.main.entities.Rates;
import rollagain.main.entities.Users;
import rollagain.main.services.PermissionsService;
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
    @PathVariable("userId") Long userId, @RequestBody Users user) {
            userService.updateUser(userId, user);
        }

    //RATES
    @GetMapping(value = "/rates")
    public List<Rates> getRates() {
        return userService.getRates();
    }

    @GetMapping(value = "{userId}/rates")
    public List<Rates> getRatesById(@PathVariable("userId") Long userId) {
    return userService.getRatesById(userId);
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

    //PERMISSIONS
    @GetMapping(value = "{userId}/permissions")
    public Permissions getPermissions(@PathVariable("userId") Long userId) {
        return userService.getPermissionById(userId);
    }

    //ORDERS
    @GetMapping(value = "/orders")
    public List<Orders> getOrders() {
        return userService.getOrders();
    }

    @GetMapping(value = "{userId}/orders")
    public List<Orders> getOrdersById(@PathVariable("userId") Long userId) {
        return userService.getOrdersById(userId);
    }

    @PostMapping(value = "{userId}/orders")
    public void registerNewOrders(@PathVariable("userId") Long userId, @RequestBody NewOrderRequest order) {
        userService.addNewOrder(order, userId);
    }

    @DeleteMapping(path = "{userId}/orders/{orderId}")
    public void deleteOrdersById(@PathVariable("orderId") Long orderId) {
        userService.deleteOrder(orderId);
    }


}
