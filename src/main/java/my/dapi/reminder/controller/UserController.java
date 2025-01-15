package my.dapi.reminder.controller;


import my.dapi.reminder.entity.Note;
import my.dapi.reminder.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private NotesService notesService;
    @Autowired
    private AuthController authController; //todo можно не инжектить

    private Long userId; // запихнуть сюда юзер id

    // Список всех заметок
    @GetMapping("/api/v1/reminder/all")
    public String allUsersNotes(Model model) {
        List<Note> allNotes = notesService.getAll();
        System.out.println(allNotes);
        model.addAttribute("allNotes", allNotes);  //wrong
        return "notes";
    }

    // Список всех заметок пользователя
    @GetMapping("/api/v1/reminder/user/{userId}")
    public String UsersNotesById(@PathVariable Long userId, Model model) {
        List<Note> allNotesByUserId = notesService.getAllByUserId(userId);
        System.out.println(allNotesByUserId);
        model.addAttribute("allNotes", allNotesByUserId);  //wrong
        return "notes";
    }


    @GetMapping("/api/v1/sort")
    public String SortNote() {
        System.out.println("sort");
        return "redirect:/api/v1/reminder";
    }

    @GetMapping("/api/v1/filter")
    public String FilterNote() {
        System.out.println("filter");
        return "redirect:/api/v1/reminder";
    }

    //Создание новой заметки
    //TODO Не работает
    @PostMapping("/api/v1/reminder/create")
    public String CreateNote(@RequestParam String title, @RequestParam String description, @RequestParam Timestamp remind) {
        Note note = new Note();
        note.setTitle(title);
        note.setDescription(description);
        note.setRemind(remind);
        note.setUserId(authController.getUserId());
        notesService.createNote(note);
        System.out.println("create");
        return "redirect:/api/v1/reminder/all";
    }

    @DeleteMapping("/api/v1/reminder/delete/{id}")
    public String DeleteNote(@PathVariable Long id) {
        notesService.deleteNote(id);
        System.out.println("delete");
        return "redirect:/api/v1/reminder/all";
    }


    @GetMapping("/api/v1/reminder/update")
    public String UpdateNote() {
        System.out.println("update");
        return "redirect:/api/v1/reminder";
    }

    @GetMapping("/api/v1/reminder/{id}")
    public String FindNoteByID(@PathVariable int id) {
        System.out.println("id");
        return "redirect:/api/v1/reminder";
    }
}
