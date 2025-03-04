package my.dapi.reminder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @SequenceGenerator(name = "note_id_seq", sequenceName = "notes_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_id_seq")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "remind")
    private Timestamp remind;

    @Column(name = "user_id")
    private Long userId;

    public Note(String title, String description, Timestamp remind, Long userId) {
        this.title = title;
        this.description = description;
        this.remind = remind;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Заметка №" + id +
                " Заголовок: '" + title + '\'' +
                " Описание '" + description + '\'' +
                " Время напоминания: " + remind +
                ", userId=" + userId;
    }
}

