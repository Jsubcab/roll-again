package rollagain.main.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService
{
    @Autowired
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addNewUser(final User user)
    {
        System.out.println(user);
    }
}
