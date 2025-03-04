package my.dapi.reminder.services;

import my.dapi.reminder.entity.Note;
import my.dapi.reminder.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    // Получение всех заметок пользователя по id
    public List<Note> getAllByUserId(Long userId){
        return notesRepository.findByUserId(userId);
    }

    public Optional<Note> getNoteById(Long id){
        return notesRepository.findById(id);
    }

    //Создание заметки
    public Note createNote(Note note) { return notesRepository.save(note);}

    //Удаление заметки
    public void deleteNote(Long id) {
        notesRepository.deleteById(id);
    }

    //TODO Создать, удалить, редактировать

}
