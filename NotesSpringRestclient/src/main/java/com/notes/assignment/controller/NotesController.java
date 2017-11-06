package com.notes.assignment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.notes.assignment.exception.CustomErrorType;
import com.notes.assignment.model.NoteDTO;
import com.notes.assignment.service.NoteServiceImpl;

/**
 * RestEndpoint for getting all notes and doing crud operation to it .
 * Example URL : http://localhost:8087/api
 *
 * @return
 * 
 */
@RestController
@RequestMapping("/api")
public class NotesController {

	
	
	 public static final Logger logger = LoggerFactory.getLogger(NotesController.class);
	 
	    @Autowired
	    NoteServiceImpl noteService; //Service which will do all data retrieval/manipulation work
	 
	    // -------------------Retrieve All notes---------------------------------------------
	 
	    @RequestMapping(value = "/notes/", method = RequestMethod.GET)
	    public ResponseEntity<List<NoteDTO>> findAll() {
	        List<NoteDTO> notes = noteService.findAll();
	        if (notes.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<NoteDTO>>(notes, HttpStatus.OK);
	    }
	 
	    // -------------------Retrieve Single note------------------------------------------
	 
	    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
	    public ResponseEntity<?> getnotes(@PathVariable("id") long id) {
	        logger.info("Fetching note with id {}", id);
	        NoteDTO note = noteService.findById(id);
	        if (note == null) {
	            logger.error("note with id {} not found.", id);
	            return new ResponseEntity(new CustomErrorType("note with id " + id 
	                    + " not found"), HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<NoteDTO>(note, HttpStatus.OK);
	    }
	 
	    // -------------------Create a note-------------------------------------------
	 
	    @RequestMapping(value = "/notes/", method = RequestMethod.POST)
	    public ResponseEntity<?> create(@RequestBody NoteDTO note) {
	        logger.info("Creating note : {}", note);	 
	        NoteDTO newNote = noteService.create(note);
	        return new ResponseEntity<NoteDTO>(newNote, HttpStatus.CREATED);
	    }
	 
	    // ------------------- Update a note ------------------------------------------------
	 
	    @RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<?> updatenotes(@PathVariable("id") long id, @RequestBody NoteDTO note) {
	        logger.info("Updating note with id {}", id);
	 
	        NoteDTO currentNote = noteService.findById(id);
	 
	        if (currentNote == null) {
	            logger.error("Unable to update. note with id {} not found.", id);
	            return new ResponseEntity(new CustomErrorType("Unable to upate. note with id " + id
	            		+ " not found."), HttpStatus.NOT_FOUND);
	        }
	 
	        currentNote.setTitle(note.getTitle());
	        currentNote.setDescription(note.getDescription());
	 
	        noteService.update(id, currentNote);
	        return new ResponseEntity<NoteDTO>(currentNote, HttpStatus.OK);
	    }
	 
	    // ------------------- Delete a note-----------------------------------------
	 
	    @RequestMapping(value = "/notes/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<?> deletenotes(@PathVariable("id") long id) {
	        logger.info("Fetching & Deleting note with id {}", id);
	 
	       NoteDTO note = noteService.findById(id);
	        if (note == null) {
	            logger.error("Unable to delete. note with id {} not found.", id);
	            return new ResponseEntity(new CustomErrorType("Unable to delete. note with id " + id
	            		+ " not found."), HttpStatus.NOT_FOUND);
	        }
	        noteService.delete(id);
	        return new ResponseEntity<NoteDTO>(HttpStatus.NO_CONTENT);
	    }
	 

}
