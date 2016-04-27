package cs3500.music.util;

/**
 * A builder of compositions.  Since we do not know in advance what
 * the name of the main type is for a model, we parameterize this builder interface
 * by an unknown type.
 *
 * @param <T> The type of the constructed composition
 */
public interface CompositionBuilder<T> {
  /**
   * Constructs an actual composition, given the notes that have been added
   * @return The new composition
   */
  T build();

  /**
   * Sets the tempo of the piece
   * @param tempo The speed, in microseconds per beat
   * @return This builder
   */
  CompositionBuilder<T> setTempo(int tempo);

  /**
   * Adds a new note to the piece
   * @param start The start time of the note, in beats
   * @param end The end time of the note, in beats
   * @param instrument The instrument number (to be interpreted by MIDI)
   * @param pitch The pitch (in the range [0, 127], where 60 represents C4,
   *              the middle-C on a piano)
   * @param volume The volume (in the range [0, 127])
   * @return
   */
  CompositionBuilder<T> addNote(int start, int end, int instrument, int pitch, int volume);
}
