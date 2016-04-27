package cs3500.music.view;

import cs3500.music.model.MusicEditorModel;

import javax.sound.midi.MetaEventListener;

/**
 * The view interface
 */
public interface View<K> {

  /**
   * Sets up the view with any default parameters and displays any relevant information
   */
  void initialize();

  /**
   * Plays the song
   */
  void play();

  /**
   * Pauses the song at the current beat
   */
  void pause();

  /**
   * Moves backwards through the song
   */
  void rewind();

  /**
   * Skips to the end of the song
   */
  void skip();

  /**
   * Moves to the next beat
   */
  void next();

  /**
   * Gets the current beat
   *
   * @return the current beat
   */
  int getCurrentBeat();

  /**
   * redraws the view with the adjusted model
   *
   * @param m the model to be redrawn
   */
  void redraw(MusicEditorModel<K> m);

  /**
   * Sets the display panel to the given panel
   *
   * @param c the display panel to be set
   */
  void setDisplayPanel(ConcreteGuiViewPanel c);

  /**
   * restarts a piece
   */
  void restart();

  void removeNote(K n);

  void addMetaListener(MetaEventListener m);

}
