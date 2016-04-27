package cs3500.music.model;

import java.util.List;

/**
 * Represents the interface for a Music Piece
 */
public interface MusicEditorModel<K> {

  /**
   * Adds a note to the music editor
   *
   * @param note the note being added to the music
   */
  void addNote(K note);

  /**
   * Removes a note from the music editor
   *
   * @param note the removed note
   */
  void removeNote(K note);

  /**
   * Edits the note
   *
   * @param note the note being edited
   */
  void editNote(K note);

  /**
   * Find the last beat that a note is played at
   *
   * @return the last beat value
   */
  int getHighestBeat();

  /**
   * Renders a music composition to the console as a string of the note range, from
   * the note with the lowest pitch and octave to the note with the highest pitch and octave
   *
   * @return a string output of all the notes playing and starting at a beat integer
   */
  String showMusic();

  /**
   * Plays music notes from other compositions at the same time as these notes
   *
   * @param model a model for a music editor
   */
  void playSimultaneously(MusicEditorModel<K> model);
  /**
   * Plays music notes from other compositions after this music model's notes
   *
   * @param model a model for a music editor
   */
  void playConsecutively(MusicEditorModel<K> model);

  /**
   * Gets all the notes that start at a provided beat
   * @param time beat time
   * @return a list of notes that start at a provided beat time
   */
  List<K> getNotesStartingAt(int time);

  /**
   * Gets all the notes in the music piece
   * @return a list of all notes
   */
  List<K> getAllNotes();

  /**
   * Gets the list of notes that are played at the given beat
   * @param time
   * @return a list of notes
   */
  List<K> getNotesAtTime(int time);

  /**
   * Gets the lowest note in the piece of music
   * @return the lowest note
   */
  K getLowestNote();

  /**
   * gets the highest note in the piece of music
   * @return the highest note
     */
  K getHighestNote();

  /**
   * gets all of the beats that a given note is played during
   * @param note
   * @return a list of beats
     */
  List<Integer> getBeatsFor(K note);

  /**
   * gets the length of the piece in beats
   * @return length
     */
  int getLength();

  /**
   * gets the tempo of the piece
   * @return tempo
     */
  int getTempo();

  /**
   * gets the range of pitches in the piece
   * @return the list of pitches
     */
  List<?> getPitches();
}
