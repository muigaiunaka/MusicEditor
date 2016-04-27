package cs3500.music.view;

import cs3500.music.model.IRepeatModel;
import cs3500.music.model.MusicEditorModel;

/**
 * Class that extends the functionality of the MidiView Implementation to allow for repeats in
 * the music
 */
public class MidiRepeatView extends MidiViewImpl {
  IRepeatModel model;
  public MidiRepeatView(IRepeatModel model) {
    super(model);
    this.model = model;
  }

  @Override
  public void redraw(MusicEditorModel m) {
    if (m instanceof IRepeatModel) {
      IRepeatModel rm = (IRepeatModel) m;

      int key = (int) sequencer.getTickPosition();
      if (rm.getRepeatAt(key) && rm.hasRepeatsLeft(key)) {
        sequencer.setTickPosition(rm.getRepeatTo(key));
        rm.decrement(key);
        sequencer.setTempoInMPQ(m.getTempo());
      }
    }

  }
}
