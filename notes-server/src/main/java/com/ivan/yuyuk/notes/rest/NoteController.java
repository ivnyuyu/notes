package com.ivan.yuyuk.notes.rest;

import com.ivan.yuyuk.notes.dto.NoteDTO;
import com.ivan.yuyuk.notes.model.Note;
import com.ivan.yuyuk.notes.service.NoteService;
import com.ivan.yuyuk.notes.service.ValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/notes")
public class NoteController {

    private final NoteService noteService;
    private final ValidationErrorService validator;

    public NoteController(NoteService noteService, ValidationErrorService validator) {
        this.noteService = noteService;
        this.validator = validator;
    }


    @GetMapping("")
    public Iterable<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        var noteFromDb = noteService.getNoteById(id);
        return new ResponseEntity<>(noteFromDb, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createNote(@RequestBody @Valid NoteDTO noteDTO, BindingResult validationResult) {
        var result = validator.validate(validationResult);
        if(result != null) {
            return result;
        }
        Note newNote = noteService.saveNote(noteDTO);
        return new ResponseEntity<>(newNote, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNote(@RequestBody @Valid NoteDTO noteDTO, BindingResult validationResult, @PathVariable Long id) {
        var result = validator.validate(validationResult);
        if(result != null) {
            return result;
        }
        var updateNote = noteService.updateNote(noteDTO, id);
        return new ResponseEntity<>(updateNote, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        noteService.delete(id);
        return new ResponseEntity<>(String.format("Note with Id: %d was deleted", id), HttpStatus.OK);
    }
}
