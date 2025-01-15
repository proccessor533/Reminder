package my.dapi.reminder.repository;

import my.dapi.reminder.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface NotesRepository extends JpaRepository<Note,Long> {

    //Поиск заметок по UserId
    List<Note> findByUserId(Long userId);
}
