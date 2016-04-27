package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Will on 4/25/16.
 */
public interface IRepeatModel extends MusicEditorModel<Note> {
  /**
   * decrements the count for the given beat repeat.
   * @param b
   */
  void decrement(int b);

  /**
   * sets a new repeat
   * @param at
   * @param to
   * @param num
   */
  void setRepeat(int at, int to, int num);

  /**
   * tells the program if the given beat contans a repeat
   * @param beat
   * @return
   */
  boolean getRepeatAt(int beat);

  /**
   * gets the place to repeat to if the key
   * @param key
   * @return
   */
  int getRepeatTo(int key);

  /**
   * the number of times the piece will repeat at the given key
   * @param key
   * @return
   */
  int getRepeatCount(int key);

  /**
   * checks to see if we should still be repeating
   * @param key
   * @return
   */
  boolean hasRepeatsLeft(int key);

  /**
   * sets the repeat map
   * @param map
   */
  void setMap(Map<Integer, List<Integer>> map);


}
