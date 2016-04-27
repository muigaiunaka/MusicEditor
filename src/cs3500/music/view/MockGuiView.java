package cs3500.music.view;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sound.midi.MetaEventListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * Mock class for a view
 */
public class MockGuiView implements GuiView {
  GuiView guiMockParam;
  View midiMockParam;
  MusicEditorModel model;

  public MockGuiView(MusicEditorModel model, GuiView guiMockParam, View midiMockParam) {
    this.model = model;
    this.guiMockParam = guiMockParam;
    this.midiMockParam = midiMockParam;
  }

  public MusicEditorModel getModel() {
    return this.model;
  }

  @Override public void drawMarker(int beat) {

  }

  @Override public void addMouseListener(MouseListener m) {

  }

  @Override public void addKeyListener(KeyListener k) {
    this.guiMockParam.addKeyListener(k);
  }

  @Override public void addMetaListener(MetaEventListener m) {

  }

  @Override public void resetFocus() {

  }

  @Override public int getRange() {
    return 0;
  }

  @Override public void scrollLeft() {

  }

  @Override public void scrollRight() {

  }

  @Override public void scrollUp() {

  }

  @Override public void scrollDown() {

  }

  @Override public void Highlight(Note n) {

  }

  @Override public List<Note> getHighlighted() {
    return null;
  }

  @Override public void unhighlight(Note n) {

  }

  @Override public void unhighlightAll() {

  }

  @Override public void followBar(int beat) {

  }


  @Override public void initialize() {

  }

  @Override public void play() {

  }

  @Override public void pause() {

  }

  @Override public void rewind() {

  }

  @Override public void skip() {

  }

  @Override public void next() {

  }

  @Override public int getCurrentBeat() {
    return 0;
  }

  @Override public void redraw(MusicEditorModel<Note> m) {

  }

  @Override public void setDisplayPanel(ConcreteGuiViewPanel c) {

  }

  @Override public void restart() {

  }

  @Override public void removeNote(Note n) {

  }

  @Override public void addNote(Note n) {
    this.model.addNote(n);
  }

  @Override public void setCurrentBeat(int i) {

  }

  @Override public void addRepeat() {

  }
}
