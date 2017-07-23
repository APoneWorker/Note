package notes.dao;

import java.util.List;

import notes.bean.Note;

public interface NoteDao {

	boolean addNote(Note note);

	List<Note> getAllNotes();

	Note getNoteById(int noteId);

	boolean deleteNote(int noteId);

}
