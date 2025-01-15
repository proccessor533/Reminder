package my.dapi.reminder.controller;

import my.dapi.reminder.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    // TODO перенести в юсерконтроллер и убрать гетюзерид
    private Long userId;  // ID пользователя

    @Autowired
    private UsersService usersService;

    // Вход на страницу авторизации
    @GetMapping("/")
    public String AuthorizationHome() {
        return "auth";         // Страница авторизации auth.html
    }

    // Запрос на авторизацию
    @PostMapping("/login")
    public String Authorization(@RequestParam String login, @RequestParam String pass) {   // Проверка пароля.
        this.userId = usersService.passcheck(login, pass);
        return "redirect:/api/v1/reminder/user/" + userId;
    }

    public Long getUserId() {
        return userId;
    }

    // TODO Страница с выводом ошибки юзер не найден/неверный пароль


}
