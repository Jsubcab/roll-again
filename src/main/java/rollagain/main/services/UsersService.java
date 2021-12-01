package rollagain.main.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rollagain.main.entities.Rates;
import rollagain.main.entities.Users;
import rollagain.main.repositories.RatesRepository;
import rollagain.main.repositories.UserRepository;


@Service
public class UsersService
{
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RatesRepository ratesRepository;

    public UsersService(final UserRepository userRepository, final RatesRepository ratesRepository)
    {
        this.userRepository = userRepository;
        this.ratesRepository = ratesRepository;
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

    public void addNewUser(final Users user)
    {
        Optional<Users> usersOptional = userRepository
            .findUsersByEmail(user.getEmail());

        if (usersOptional.isPresent()) {
            throw new IllegalStateException("ERROR: Email already registered.");
        }
        userRepository.save(user);
    }

    public void deleteUser(final Long userId)
    {
            boolean exists = userRepository.existsById(userId);
            if (!exists) {
                throw new IllegalStateException("user with id " + userId + " does not exists.");
            }
            userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId,
                           String newUsername,
                           String newEmail) {
        Users user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalStateException(
                "user with id " + userId + " does not exist."));

        if (newUsername != null && newUsername.length() > 0 && !Objects.equals(user.getUsername(),
            newUsername)) {
            user.setUsername(newUsername);
        }

        if (newEmail != null && newEmail.length() > 0 && !Objects.equals(user.getEmail(),
            newEmail)) {
            Optional<Users> userOptional = userRepository.findUsersByEmail(newEmail);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("email taken.");
            }
            user.setEmail(newEmail);
        }
    }

    public List<Rates> getRates(Long userId)
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
}
