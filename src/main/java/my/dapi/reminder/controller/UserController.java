package my.dapi.reminder.controller;


import lombok.Getter;
import lombok.Setter;
import my.dapi.reminder.entity.Note;
import my.dapi.reminder.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@Controller
public class UserController {
    @Autowired
    private NotesService notesService;

    // Здесь хранится токен авторизации - UserId
    @Setter
    @Getter
    private Long userId;

    // Список всех заметок пользователя
    @GetMapping("/api/v1/reminder/notes")
    public ResponseEntity<List<Note>> UsersNotesById() {
        List<Note> allNotesByUserId = notesService.getAllByUserId(userId);
        return ResponseEntity.ok(allNotesByUserId);
    }

    //Сортировка по name, date, time
    @GetMapping("/api/v1/sort/{type}")
    public ResponseEntity<List<Note>> SortNote(@PathVariable String type) {
        List<Note> allNotesByUserId = notesService.getAllByUserId(userId);
        List<Note> sortedNotes = new ArrayList<>(allNotesByUserId); // Копируем, чтобы не изменять исходный список
        switch (type) {
            case "name":
                sortedNotes.sort(Comparator.comparing(Note::getTitle));
                break;
            case "date":
                sortedNotes.sort(Comparator.comparing(Note::getRemind));
                break;
            case "time":
                sortedNotes.sort(Comparator.comparing(Note::getRemind));
                break;
            default:
                return ResponseEntity.badRequest().body(Collections.emptyList()); // Ошибка, если неверный тип
        }

        return ResponseEntity.ok(sortedNotes);
    }
/*
    //Фильтр date, time
    @GetMapping("/api/v1/filter/{type}")
    public ResponseEntity<List<Note>> FilterNote(@PathVariable String type) {
        List<Note> allNotesByUserId = notesService.getAllByUserId(userId);
        List<Note> filteredNotes = new ArrayList<>(allNotesByUserId); // Копируем, чтобы не изменять исходный список

        return ResponseEntity.ok(filteredNotes);
    }*/

    //Создание новой заметки
    @PostMapping("/api/v1/reminder/create")
    public ResponseEntity<String> CreateNote(@RequestParam String title, @RequestParam String description, @RequestParam Timestamp remind) {
        Note note = new Note();
        note.setTitle(title);
        note.setDescription(description);
        note.setRemind(remind);
        note.setUserId(userId);
        notesService.createNote(note);
        return ResponseEntity.ok("Note successfully created");
    }

    //Удаление заметки по id
    @DeleteMapping("/api/v1/reminder/delete/{id}")
    public ResponseEntity<String> DeleteNote(@PathVariable Long id) {
        Optional<Note> optionalNote = notesService.getNoteById(id);
        //Проверяем, существует ли заметка
        if (optionalNote.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity with ID " + id + " not found");
        }
        Note note = optionalNote.get();
        //Проверяем, принадлежит ли найденная заметка пользователю
        if (!Objects.equals(userId, note.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can't delete this note");
        }
        //Удаляем заметку
        notesService.deleteNote(id);
        return ResponseEntity.ok("Note " + id + " successfully deleted");
    }

    //Внесение изменений в заметку по id
    @PutMapping("/api/v1/reminder/update/{id}")
    public ResponseEntity<String> UpdateNote(@PathVariable Long id, @RequestParam String title, @RequestParam String description, @RequestParam Timestamp remind) {
        //Проверяем, существует ли заметка
        Optional<Note> optionalNote = notesService.getNoteById(id);
        if (optionalNote.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity with ID " + id + " not found");
        }
        Note note = optionalNote.get();
        //Проверяем, принадлежит ли найденная заметка пользователю
        if (!Objects.equals(userId, note.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can't edit this note");
        }
        //Вставляем в этот объект измененные данные
        note.setTitle(title);
        note.setDescription(description);
        note.setRemind(remind);
        note.setUserId(userId);
        //Сохраняем заметку в БД
        notesService.createNote(note);
        return ResponseEntity.ok("Note " + id + " successfully edeted");
    }

    //Найти заметку по id
    @GetMapping("/api/v1/reminder/{id}")
    public ResponseEntity<?> FindNoteByID(@PathVariable Long id) {
        //Проверяем, существует ли заметка
        Optional<Note> optionalNote = notesService.getNoteById(id);
        if (optionalNote.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity with ID " + id + " not found");
        }
        Note note = optionalNote.get();
        //Проверяем, принадлежит ли найденная заметка пользователю
        if (!Objects.equals(userId, note.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can't see this note");
        }
        return ResponseEntity.ok(note);
    }
}
