package my.dapi.reminder.repository;


import my.dapi.reminder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<User,Long> {

    //Поиск пользователя по username
    User findByUsername(String username);
}
