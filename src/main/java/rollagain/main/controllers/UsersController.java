package rollagain.main.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rollagain.main.controllers.data.OrdersResponse;
import rollagain.main.controllers.data.ProductsResponse;
import rollagain.main.controllers.data.RatesResponse;
import rollagain.main.controllers.data.UsersResponse;
import rollagain.main.controllers.post.NewOrderRequest;
import rollagain.main.entities.Orders;
import rollagain.main.entities.Permissions;
import rollagain.main.entities.Products;
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
    public List<UsersResponse> getUsers(){

        final List<Users> usersList = userService.getUsers();
        if (CollectionUtils.isEmpty(usersList)) {
            return Collections.emptyList();
        }

        List<UsersResponse> response = new ArrayList<>();
        for (Users u : usersList) {
            UsersResponse newUser = createUsersResponse(u);
            response.add(newUser);
        }
        return response;
    }

    private UsersResponse createUsersResponse(final Users u)
    {
        UsersResponse newUser = new UsersResponse();
        newUser.setId(u.getId());
        newUser.setUsername(u.getUsername());
        newUser.setEmail(u.getEmail());
        newUser.setCity(u.getCity());
        newUser.setPhone(u.getPhone());
        newUser.setZipcode(u.getZipcode());
        newUser.setPassword(u.getPassword());
        newUser.setPermissions(u.getPermission());

        if (u.getProducts() != null) {
            final Set<ProductsResponse> productsResponses = u.getProducts().stream()
                .map(product ->  createProductsResponse(product))
                .collect(Collectors.toSet());
            newUser.setProducts(productsResponses);
        }
        return newUser;
    }

    private ProductsResponse createProductsResponse(final Products product)
    {
        ProductsResponse productsResponse = new ProductsResponse();
        productsResponse.setName(product.getName());
        productsResponse.setDescription(product.getDescription());
        productsResponse.setState(product.getState());
        productsResponse.setPrice(product.getPrice());
        productsResponse.setPicture(product.getPicture());
        productsResponse.setId(product.getId());
        return productsResponse;
    }

    //USERS
    @GetMapping(value = "{userId}")
    public UsersResponse getUserById(@PathVariable("userId") Long userId){

        final Users usersList = userService.getUserById(userId);
        UsersResponse response = new UsersResponse();
        response.setId(usersList.getId());
        response.setCity(usersList.getCity());
        response.setEmail(usersList.getEmail());
        response.setPhone(usersList.getPhone());
        response.setZipcode(usersList.getZipcode());
        response.setUsername(usersList.getUsername());
        response.setPassword(usersList.getPassword());

        if (usersList.getProducts() != null) {
            final Set<ProductsResponse> productsResponses = usersList.getProducts().stream()
                .map(product ->  createProductsResponse(product))
                .collect(Collectors.toSet());
            response.setProducts(productsResponses);
        }

        return response;
    }

    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    public UsersResponse getUserByUsername(@PathVariable String username){

        final Users usersList = userService.getUserByUsername(username);
        UsersResponse response = new UsersResponse();
        response.setId(usersList.getId());
        response.setCity(usersList.getCity());
        response.setEmail(usersList.getEmail());
        response.setPhone(usersList.getPhone());
        response.setZipcode(usersList.getZipcode());
        response.setUsername(usersList.getUsername());
        response.setPassword(usersList.getPassword());

        if (usersList.getProducts() != null) {
            final Set<ProductsResponse> productsResponses = usersList.getProducts().stream()
                .map(product ->  createProductsResponse(product))
                .collect(Collectors.toSet());
            response.setProducts(productsResponses);
        }

        return response;
    }

    @PostMapping("/signup")
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
    public List<RatesResponse> getRates() {

        final List<Rates> ratesList = userService.getRates();
        if (CollectionUtils.isEmpty(ratesList)) {
            return Collections.emptyList();
        }

        List<RatesResponse> response = new ArrayList<>();
        for (Rates r : ratesList) {
            RatesResponse newRate = createRatesResponse(r);
            response.add(newRate);
        }
        return response;

    }

    private RatesResponse createRatesResponse(final Rates r)
    {
        RatesResponse newRate = new RatesResponse();
        newRate.setId(r.getId());
        newRate.setComment(r.getComment());
        newRate.setRating(r.getRating());

        newRate.setUser(new UsersResponse());

        newRate.getUser().setId(r.getUser().getId());
        newRate.getUser().setCity(r.getUser().getCity());
        newRate.getUser().setEmail(r.getUser().getEmail());
        newRate.getUser().setPhone(r.getUser().getPhone());
        newRate.getUser().setUsername(r.getUser().getUsername());
        newRate.getUser().setPassword(r.getUser().getPassword());
        newRate.getUser().setZipcode(r.getUser().getZipcode());

        return newRate;
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
    public List<OrdersResponse> getOrders() {

        final List<Orders> ordersList = userService.getOrders();
        if (CollectionUtils.isEmpty(ordersList)) {
            return Collections.emptyList();
        }

        List<OrdersResponse> response = new ArrayList<>();
        for (Orders o : ordersList) {
            OrdersResponse newOrder = createOrdersResponse(o);
            response.add(newOrder);
        }
        return response;
    }

    private OrdersResponse createOrdersResponse(final Orders o)
    {
        OrdersResponse newOrder = new OrdersResponse();
        newOrder.setId(o.getId());

        newOrder.setUser(new UsersResponse());

        newOrder.getUser().setId(o.getUser().getId());
        newOrder.getUser().setUsername(o.getUser().getUsername());
        newOrder.getUser().setEmail(o.getUser().getEmail());
        newOrder.getUser().setCity(o.getUser().getCity());
        newOrder.getUser().setPhone(o.getUser().getPhone());
        newOrder.getUser().setZipcode(o.getUser().getZipcode());
        newOrder.getUser().setPassword(o.getUser().getPassword());

        newOrder.setProducts(new ProductsResponse());

        newOrder.getProducts().setId(o.getProduct().getId());
        newOrder.getProducts().setDescription(o.getProduct().getDescription());
        newOrder.getProducts().setName(o.getProduct().getName());
        newOrder.getProducts().setState(o.getProduct().getState());
        newOrder.getProducts().setPrice(o.getProduct().getPrice());

        return newOrder;
    }

    @GetMapping(value = "{userId}/orders")
    public List<OrdersResponse> getOrdersById(@PathVariable("userId") Long userId) {

        final List<Orders> ordersList = userService.getOrdersById(userId);
        if (CollectionUtils.isEmpty(ordersList)) {
            return Collections.emptyList();
        }

        List<OrdersResponse> response = new ArrayList<>();
        for (Orders o : ordersList) {
            OrdersResponse newOrder = createOrdersResponse(o);
            response.add(newOrder);
        }
        return response;

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
