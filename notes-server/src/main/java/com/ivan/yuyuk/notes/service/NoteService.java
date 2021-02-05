package com.ivan.yuyuk.notes.service;


import com.ivan.yuyuk.notes.dto.NoteDTO;
import com.ivan.yuyuk.notes.model.Note;

public interface NoteService {
    Iterable<Note> getAllNotes();

    Note getNoteById(Long id);

    Note saveNote(NoteDTO noteDTO);

    Note updateNote(NoteDTO noteDTO, Long id);

    void delete(Long id);
}
