package cs3500.music.view;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;

import javax.sound.midi.MetaEventListener;
import java.io.PrintStream;

/**
 * Represents a console rendering for a music editor that displays information of the music
 */
public class ConsoleView implements View {
  private MusicEditorModel<Note> model;
  private PrintStream output;

  public ConsoleView(MusicEditorModel<Note> model){
    this.model = model;
    this.output = System.out;
  }


  public ConsoleView(MusicEditorModel model, PrintStream output) {
    this(model);
    this.output = output;
  }

  @Override
  public void initialize(){
    output.println(model.showMusic());
  }

  @Override
  public void play() {

  }

  @Override
  public void pause() {

  }

  @Override
  public void rewind() {

  }

  @Override
  public void skip() {

  }

  @Override
  public void next() {

  }

  @Override
  public int getCurrentBeat() {
    return 0;
  }

  @Override
  public void redraw(MusicEditorModel m) {

  }

  @Override
  public void setDisplayPanel(ConcreteGuiViewPanel c) {

  }

  @Override
  public void restart() {

  }

  @Override public void addMetaListener(MetaEventListener m) {

  }

  @Override
  public void removeNote(Object n) {

  }
}
