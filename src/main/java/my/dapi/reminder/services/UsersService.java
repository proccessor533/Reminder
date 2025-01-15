package my.dapi.reminder.services;

import my.dapi.reminder.entity.User;
import my.dapi.reminder.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository userRepository;

    // Аутентификация пользователя
    public Long passcheck(String login, String pass) {
        User userch = userRepository.findByUsername(login);  // TODO обработка ошибок
        if (userch.getPassword().equals(pass)) {
            return userch.getId();
        } else {
            return -1L;
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

}
