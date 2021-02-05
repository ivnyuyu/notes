package com.ivan.yuyuk.notes.dto;

import com.ivan.yuyuk.notes.model.Note;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

public class NoteDTO implements Serializable {

    @NotBlank(message = "Заголовок обязательно поле!")
    private String title;
    private String content;
    @Future(message = "Укажите будущую дату!")
    private Date notificationDate;

    public NoteDTO() {

    }

    public Note convertToNoteModel() {
        var tempNote = new Note();
        tempNote.setTitle(title);
        tempNote.setContent(content);
        tempNote.setNotificationDate(notificationDate);
        return tempNote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }
}
