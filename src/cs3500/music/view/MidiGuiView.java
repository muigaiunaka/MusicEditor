package cs3500.music.view;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;

import javax.sound.midi.MetaEventListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Composite View for Midi Sound and GUI visuals
 */
public class MidiGuiView implements GuiView {
  GuiView guiview;
  View midiview;
  int currentBeat;
  int curBeat;
  private List<Note> highlighted;

  public MidiGuiView(MusicEditorModel model) {
    if (ViewFactory.createView("visual", model) instanceof  GuiView) {
      this.guiview = (GuiView) ViewFactory.createView("visual", model);
    }
    this.midiview = ViewFactory.createView("midi", model);
    this.currentBeat = this.getCurrentBeat(currentBeat);
    this.highlighted = new ArrayList<Note>();
  }

  @Override public void initialize() {
    guiview.initialize();
    midiview.initialize();
  }

  @Override public void play() {
    midiview.play();
    guiview.play();
  }

  @Override public void pause() {
    guiview.pause();
    midiview.pause();
  }

  @Override public void rewind() {
    guiview.rewind();
    midiview.rewind();
  }

  @Override public void skip() {
    guiview.skip();
    midiview.skip();
  }

  @Override public void next() {
    guiview.next();
    midiview.next();
  }

  @Override
  public void restart() {
    midiview.restart();
    guiview.restart();
  }

  @Override public int getCurrentBeat() {
    return this.midiview.getCurrentBeat();
  }

  @Override
  public int getCurrentBeat(int b) {
    b = this.midiview.getCurrentBeat();
    return b;
  }

  @Override
  public void redraw(MusicEditorModel m) {
    this.guiview.redraw(m);
    this.midiview.redraw(m);
  }

  public int getLowestPitch() {
    return ((GuiViewFrame) this.guiview).getLowestPitch();
  }

  @Override
  public void setDisplayPanel(ConcreteGuiViewPanel c) {}

  /**
   * METHODS FOR ADD LISTENERS TO THE VIEW FOR DIFFERENT INTERACTIONS
   */
  @Override
  public void addMouseListener(MouseListener m) {
    this.guiview.addMouseListener(m);
  }

  @Override public void addKeyListener(KeyListener k) {
    this.guiview.addKeyListener(k);
  }

  public void addMetaListener(MetaEventListener m) {
    this.midiview.addMetaListener(m);
  }

  @Override
  public void resetFocus() {
  }

  public int getRange() {
    return guiview.getRange();
  }

  /**
   * METHODS FOR SCROLLING THROUGH THE VIEW
   */

  @Override public void scrollLeft() {
    this.guiview.scrollLeft();
  }

  @Override public void scrollRight() {
    this.guiview.scrollRight();
  }

  @Override public void scrollUp() {
    this.guiview.scrollUp();
  }

  @Override public void scrollDown() {
    this.guiview.scrollDown();
  }

  /**
   * METHODS FOR HIGHLIGHTING NOTES IN THE VIEW
   */

  @Override
  public boolean isHighlighted(Note n) {
    return false;
  }

  @Override
  public void highlightNote(Note n) {
    this.highlighted.add(n);
    this.guiview.highlightNote(n);
  }

  @Override
  public void Highlight(Note n) {
    guiview.highlightNote(n);
    this.highlighted.add(n);
  }

  @Override
  public List<Note> getHighlighted() {
    return this.highlighted;
  }

  @Override
  public void unhighlight(Note n) {
    if (highlighted.contains(n)) {
      highlighted.remove(n);
      this.guiview.unhighlight(n);
    }
  }

  @Override
  public void unhighlightAll() {
    highlighted.removeAll(highlighted);
    this.guiview.unhighlightAll();
  }

  /**
   * UNUSED METHODS AT THE MOMENT
   */
  @Override public void removeNote(Note n) {}

  @Override public void drawMarker(int beat) {}

  @Override public void drawNote(Note n) {}

  @Override public void eraseNote(Note n) {}

  @Override public void redrawNote(Note n) {}

}

