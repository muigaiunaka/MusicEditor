package cs3500.music.model;

import cs3500.music.util.CompositionBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Will on 4/25/16.
 */
public class RepeatModel extends MusicEditorModelImpl implements IRepeatModel {
  private Map<Integer, List<Integer>> repeats;
  RepeatModel() {
    super();
    repeats = new TreeMap();
   // List<Integer> repeatData = new ArrayList<Integer>();
   // repeatData.add(3);
   // repeatData.add(16);
   // repeats.put(32, repeatData);
  }


  RepeatModel(int tempo) {
    super();
    repeats = new TreeMap();
    this.tempo = tempo;
    //List<Integer> repeatData = new ArrayList<Integer>();
    //repeatData.add(3);
    //repeatData.add(16);
    //repeats.put(32, repeatData);
  }

  @Override
  public void setMap(Map<Integer, List<Integer>> map){
    this.repeats = map;
  }
  @Override
  public void decrement(int b) {
    if (repeats.containsKey(b)) {
      Integer repeatTimes = repeats.get(b).get(0).intValue();
      repeatTimes--;
      repeats.get(b).set(0, repeatTimes);
    }
  }
  @Override
  public int getRepeatCount(int key) {
    if (repeats.containsKey(key)) {
      return repeats.get(key).get(0);
    }
    else {
      return -1;
    }
  }

  @Override
  public void setRepeat(int at, int to, int num) {
    if (at > to && num > 0 ) {
      List<Integer> repeatStuff = new ArrayList<Integer>();
      repeatStuff.add(num);
      repeatStuff.add(to);
      repeats.put(at, repeatStuff);
    }

  }

  @Override
  public boolean getRepeatAt(int beat) {
    return repeats.containsKey(beat);
  }

  @Override
  public int getRepeatTo(int key) {
    if (repeats.containsKey(key) && repeats.get(key).size() == 2) {
      return repeats.get(key).get(1);
    }
    else {
      return 0;
    }
  }

  @Override
  public boolean hasRepeatsLeft(int key) {
    if (repeats.containsKey(key) && repeats.get(key).size() == 2) {
      return repeats.get(key).get(0) > 0;

    }
    else {
      return false;
    }
  }

  /**
   * Builds a {@link RepeatModel}, allowing the client to configure several
   * parameters. This is an instance of the <em>builder pattern</em>.
   */
  public static final class Builder implements CompositionBuilder<MusicEditorModel> {

    private int tempo;
    private Map<Integer, List<Integer>> map = new TreeMap();
    private List<Note> notes = new ArrayList<>();
    IRepeatModel model;


    @Override public IRepeatModel build() {
      model = new RepeatModel(tempo);
      for (Note n: notes) {
        model.addNote(n);
      }
      model.setMap(map);
      return model;

    }

    @Override public CompositionBuilder<MusicEditorModel> setTempo(int tempo) {
      if (tempo < 1) {
        throw new IllegalArgumentException("Tempo must be atleast 1");
      }
      this.tempo = tempo;
      return this;
    }

    @Override
    public CompositionBuilder<MusicEditorModel> addNote(int start, int end, int instrument,
                                                        int pitch, int volume) {
      Note n = new Note(new Pitch(pitch-12), start, end-start, instrument, volume);
      notes.add(n);
      return this;

    }

    @Override
    public CompositionBuilder<MusicEditorModel> addRepeat(int repeatAt, int repeatTo) {

      if (!map.containsKey(repeatAt)) {
        List<Integer> newStuff = new ArrayList();
        newStuff.add(1);
        newStuff.add(repeatTo);
        map.put(repeatAt, newStuff);
      }
      else {
        int repeater = map.get(repeatAt).get(0);
        repeater ++;
        List<Integer> newStuff = new ArrayList();
        newStuff.add(repeater);
        newStuff.add(repeatTo);
        map.put(repeatAt, newStuff);
      }
      return this;
    }
  }

}
