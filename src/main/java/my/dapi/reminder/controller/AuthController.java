package my.dapi.reminder.controller;

import my.dapi.reminder.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private UserController userController;

    // Вход на страницу авторизации
    @GetMapping("/")
    public String AuthorizationHome() {
        return "Базовая страница";
    }

    // Запрос на авторизацию
    // TODO ВОЗВРАЩАТЬ ЮЗЕР И ПАСС ОПШИОНАЛОМ?
    @PostMapping("/login")
    public String Authorization(@RequestParam String login, @RequestParam String pass) {   // Проверка пароля.
        userController.setUserId(usersService.passcheck(login, pass));
        if (userController.getUserId() >= 0) {
            return "successfully logged in";
        }
        else {
            return "wrong username or password";
        }
    }
    // TODO вывод ошибки юзер не найден/неверный пароль





}
