package rollagain.main.services;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import rollagain.main.controllers.data.UsersResponse;
import rollagain.main.controllers.post.NewOrderRequest;
import rollagain.main.entities.Orders;
import rollagain.main.entities.Permissions;
import rollagain.main.entities.Products;
import rollagain.main.entities.Rates;
import rollagain.main.entities.Users;
import rollagain.main.repositories.OrdersRepository;
import rollagain.main.repositories.PermissionsRepository;
import rollagain.main.repositories.ProductsRepository;
import rollagain.main.repositories.RatesRepository;
import rollagain.main.repositories.UserRepository;
import rollagain.main.services.enums.EnumPermissions;


@Service
public class UsersService
{
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RatesRepository ratesRepository;

    @Autowired
    private final PermissionsRepository permissionsRepository;

    @Autowired
    private final OrdersRepository ordersRepository;

    @Autowired
    private final ProductsRepository productRepository;


    public UsersService(final UserRepository userRepository,
                        final RatesRepository ratesRepository,
                        final PermissionsRepository permissionsRepository,
                        final OrdersRepository ordersRepository, final ProductsRepository productRepository)
    {
        this.userRepository = userRepository;
        this.ratesRepository = ratesRepository;
        this.permissionsRepository = permissionsRepository;
        this.ordersRepository = ordersRepository;
        this.productRepository = productRepository;
    }

    public List<Users> getUsers(){
        return userRepository.findAll();
    }

    public Users getUserById(Long userId) {
        Users user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalStateException(
                "user with id " + userId + " does not exist."));
        return user;
    }

    public Users getUserByUsername(String username) {
        Users user = userRepository.findUsersByUsername(username)
            .orElseThrow(() -> new IllegalStateException(
                "user with username " + username + " does not exist."));
        return user;
    }

    public void addNewUser(final Users user)
    {
        if (userRepository.existsUsersByEmail(user.getEmail()) || userRepository.existsUsersByUsername(user.getUsername()))
        {
            throw new IllegalStateException("ERROR: Email or username already registered.");
        }

            Permissions permissionUser = permissionsRepository.findByLevel(EnumPermissions.USER.toString().toLowerCase());
            userRepository.save(createNewUser(user, permissionUser));

    }

    private Users createNewUser(Users users, Permissions permissions) {
        Users newUser = new Users();
        newUser.setUsername(users.getUsername().toLowerCase());
        newUser.setPassword(users.getPassword());
        newUser.setCity(users.getCity().toLowerCase());
        newUser.setZipcode(users.getZipcode());
        newUser.setPhone(users.getPhone());
        newUser.setEmail(users.getEmail().toLowerCase());
        newUser.setPermission(permissions);

        return newUser;
    }

    public void deleteUser(final Long userId)
    {
            boolean exists = userRepository.existsById(userId);
            if (!exists) {
                throw new IllegalStateException("user with id " + userId + " does not exists.");
            }
            userRepository.deleteById(userId);
    }

    public UsersResponse loginUser(String username, String pwd) {

        if (userRepository.existsUsersByUsername(username)) {
            String token = getJWTToken(username);
            UsersResponse user = new UsersResponse();
            user.setUsername(username);
            user.setToken(token);
            return user;
        } else {
            throw new IllegalStateException("username " + username + " does not exists.");
        }
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
            .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
            .builder()
            .setId("softtekJWT")
            .setSubject(username)
            .claim("authorities",
                grantedAuthorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 600000))
            .signWith(SignatureAlgorithm.HS512,
                secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    @Transactional
    public void updateUser(Long userId,
                           final Users newUserData) {
       Users user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalStateException(
                "user with id " + userId + " does not exist."));

        if (newUserData.getUsername() != null && newUserData.getUsername().length() > 0 && !Objects.equals(user.getUsername(),
            newUserData.getUsername())) {
            user.setUsername(newUserData.getUsername());
        }

        if (newUserData.getEmail() != null && newUserData.getEmail().length() > 0 && !Objects.equals(user.getEmail(),
            newUserData.getEmail())) {
            Users userOptional = userRepository.findUsersByEmail(newUserData.getEmail());
            if (userOptional.getEmail().equals(user.getEmail()))
            {
                throw new IllegalStateException("email taken.");
            }
            user.setEmail(newUserData.getEmail());
        }

        if (newUserData.getCity() != null && newUserData.getCity().length() > 0 && !Objects.equals(user.getCity(),
            newUserData.getCity())) {
            user.setCity(newUserData.getCity());
        }

        if (newUserData.getPassword() != null && newUserData.getPassword().length() > 0 && !Objects.equals(user.getPassword(),
            newUserData.getPassword())) {
            user.setPassword(newUserData.getPassword());
        }

        if (newUserData.getPhone() != null && newUserData.getPhone().toString().length() > 0 && !Objects.equals(user.getPhone(),
            newUserData.getPhone())) {
            user.setPhone(newUserData.getPhone());
        }

        if (newUserData.getZipcode() != null && newUserData.getZipcode().length() > 0 && !Objects.equals(user.getZipcode(),
            newUserData.getZipcode())) {
            user.setZipcode(newUserData.getZipcode());
        }

        if (newUserData.getZipcode() != null && newUserData.getZipcode().length() > 0 && !Objects.equals(user.getZipcode(),
            newUserData.getZipcode())) {
            user.setZipcode(newUserData.getZipcode());
        }

        if (user.getPermission() != null
            && user.getPermission().getLevel() != null
            && newUserData.getPermission() != null
            && !user.getPermission().getLevel().equals(newUserData.getPermission().getLevel())) {

            Permissions newPermission = permissionsRepository.findByLevel(newUserData.getPermission().getLevel());
            user.setPermission(newPermission);
        }

        userRepository.save(user);
        userRepository.flush();
    }




    public List<Rates> getRates()
    {
        return ratesRepository.findAll();
    }

    public List<Rates> getRatesById(Long userId)
    {
        return ratesRepository.findRatesById(userId);
    }

    public void addNewRate(final Rates rates, Long userId)
    {
        if (rates.getRating() > 5 || rates.getRating() < 0 || rates.getRating().isNaN()) {
            throw new IllegalStateException("Rates should be between 0 and 5");
        }
        rates.setUser(getUserById(userId));
        ratesRepository.save(rates);
    }

    public void deleteRate(final Long rateId)
    {
        boolean exists = ratesRepository.existsById(rateId);
        if (!exists) {
            throw new IllegalStateException("Rate with id " + rateId + " does not exists.");
        }
        ratesRepository.deleteById(rateId);
    }

    @Transactional
    public void updateRate(final Long rateId,
                           Double newRating,
                           String newComment)
    {
        Rates rate = ratesRepository.findById(rateId)
            .orElseThrow(() -> new IllegalStateException(
                "Rate with id " + rateId + " does not exist."));

        if (newRating != null && newRating > 0 && newRating <= 5 && !Objects.equals(rate.getRating(),
            newRating)) {
            rate.setRating(newRating);
        }

        if (newComment != null && newComment.length() > 0 && !Objects.equals(rate.getComment(),
            newComment)) {
            rate.setComment(newComment);
        }
    }

    public Permissions getPermissionById(Long userId)
    {
        return userRepository.findPermissionById(userId);
    }

    public List<Orders> getOrders()
    {
        return ordersRepository.findAll();
    }

    public List<Orders> getOrdersById(final Long userId)
    {
        return ordersRepository.findOrdersById(userId);
    }

    public void addNewOrder(final NewOrderRequest order, final Long userId)
    {
        Optional<Users> user = userRepository.findById(userId);
        Optional<Products> product = productRepository.findById(order.getProductId());

        Orders newOrder = new Orders();
        newOrder.setUser(user.get());
        newOrder.setProduct(product.get());

        ordersRepository.save(newOrder);
    }

    public void deleteOrder(final Long orderId)
    {
        boolean exists = ordersRepository.existsById(orderId);
        if (!exists) {
            throw new IllegalStateException("Order with id " + orderId + " does not exists.");
        }
        ratesRepository.deleteById(orderId);
    }


}
