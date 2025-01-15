package my.dapi.reminder.services;

import my.dapi.reminder.entity.Note;
import my.dapi.reminder.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    // Получение всех заметок всех пользователей
    public List<Note> getAll(){
        return notesRepository.findAll();
    }

    // Получение всех заметок пользователя по id
    public List<Note> getAllByUserId(Long id){
        return notesRepository.findByUserId(id);
    }

    public Note createNote(Note note) {
        return notesRepository.save(note);}

    public void deleteNote(Long id) {
        notesRepository.deleteById(id);
    }

    //TODO Создать, удалить, редактировать

}
