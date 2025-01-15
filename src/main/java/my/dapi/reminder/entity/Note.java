package my.dapi.reminder.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "remind")
    private Timestamp remind;

    @Column(name = "user_id")
    private Long userId;

    public Note() {}

    public Note(Long id, String title, String description, Timestamp remind, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.remind = remind;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getRemind() {
        return remind;
    }

    public void setRemind(Timestamp remind) {
        this.remind = remind;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

