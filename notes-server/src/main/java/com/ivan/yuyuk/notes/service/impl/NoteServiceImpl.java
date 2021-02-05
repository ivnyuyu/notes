package com.ivan.yuyuk.notes.service.impl;

import com.ivan.yuyuk.notes.dto.NoteDTO;
import com.ivan.yuyuk.notes.model.Note;
import com.ivan.yuyuk.notes.repository.NoteRepository;
import com.ivan.yuyuk.notes.service.NoteService;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Iterable<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow();
    }

    @Override
    public Note saveNote(NoteDTO noteDTO) {
        var savedNote = noteDTO.convertToNoteModel();
        return noteRepository.save(savedNote);
    }

    @Override
    public Note updateNote(NoteDTO noteDTO, Long id) {
        var updateNote = noteRepository.findById(id).orElseThrow();
        updateNote.setTitle(noteDTO.getTitle());
        updateNote.setContent(noteDTO.getContent());
        updateNote.setNotificationDate(noteDTO.getNotificationDate());
        return noteRepository.save(updateNote);
    }

    @Override
    public void delete(Long id) {
        var note = noteRepository.findById(id).orElseThrow();
        noteRepository.delete(note);
    }
}
