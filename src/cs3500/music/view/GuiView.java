package cs3500.music.view;

import cs3500.music.model.Note;

import javax.sound.midi.MetaEventListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * Sub-interface of View Interface
 */
public interface GuiView extends View<Note> {
  /**
   * Tells whether the note is highlighted or not.
   * @param n the note that may be highlighted
   * @return the status of the note highlight
   */
  boolean isHighlighted(Note n);
  /**
   * Highlights a note to show that it is selected and ready to be edited
   * @param n the note being highlighted
   */
  void highlightNote(Note n);

  /**
   * render the marker that shows where in the piece is playing
   */
  void drawMarker(int beat);

  /**
   * draws the given note in the GUI
   * @param n the note to be drawn in the gui
   */
  void drawNote(Note n);

  /**
   * removes the visual representation of the given note from it's spot in the GUI.
   * @param n the note to be removed
   */
  void eraseNote(Note n);

  /**
   * redraws the given note based on any changes to the fields of the note
   * @param n the note to be redrawn
   */
  void redrawNote(Note n);

  /**
   * adds a given mouse listener to the view
   * @param m the mouse listener to be added
   */
  void addMouseListener(MouseListener m);

  /**
   * adds a given key listener to the view
   * @param k the key listener to be added
   */
  void addKeyListener(KeyListener k);

  /**
   * adds a given MetaEvent listener to the view
   * @param m the MetaEvent listener to be added
   */
  void addMetaListener(MetaEventListener m);
  /**
   * resets the focus of the panel to be in the window of the gui view
   */
  void resetFocus();

  /**
   * Gets the range of pitches as an integer value
   * @return the number of pitches in this view
   */
  int getRange();

  /**
   * Scroll left with press of left arrow key
   */
  void scrollLeft();

  /**
   * Scroll right with press of right arrow key
   */
  void scrollRight();

  /**
   * Scroll up with press of up arrow key
   */
  void scrollUp();

  /**
   * Scroll down with press of down arrow key
   */
  void scrollDown();

  /**
   * Highlights a note
   * @param n the note to be highlighted
   */
  void Highlight(Note n);

  /**
   * Gets a list of all of the highlighted notes
   * @return the highlighted notes
   */
  List<Note> getHighlighted();

  /**
   * Un-highlights a note
   * @param n the note to be un-highlighted
   */
  void unhighlight(Note n);

  /**
   * Un-highlights all notes
   */
  void unhighlightAll();

  /**
   * Gets the current beat of this music editor
   * @param i an integer value
   * @return the current beat
   */
  int getCurrentBeat(int i);

}
