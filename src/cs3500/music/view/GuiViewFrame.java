package cs3500.music.view;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;

import java.awt.*;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;
import javax.sound.midi.MetaEventListener;
import javax.swing.*;

/**
 * A GUI Frame (i.e., a window) in Swing to represent the 4 notes displayed at measures that
 * frame the window with Pitches and Beat Start Times
 */
public class GuiViewFrame extends javax.swing.JFrame implements GuiView {

  private ConcreteGuiViewPanel displayPanel;
  private final MusicEditorModel<Note> model;
  int curBeat;
  int currentBeat;
  JScrollPane scroll;

  /**
   * Creates new GuiView
   */
  public GuiViewFrame(MusicEditorModel<Note> model) {
    super("Music Editor WKent x MUnaka");
    this.model = model;
    this.displayPanel = new ConcreteGuiViewPanel(model);
    //this.displayPanel.setCurrentBeat();
    this.displayPanel.setPreferredSize(new Dimension(model.getLength() *
        (ConcreteGuiViewPanel.getMeasureWidth() / 4) + (2 * ConcreteGuiViewPanel.getXMargin()),
        model.getPitches().size() * ConcreteGuiViewPanel.getNoteHeight()
            + (2 * ConcreteGuiViewPanel.getYMargin())));
    JPanel notesLabels = displayPanel.getRHeader();
    JPanel beatsLabels = displayPanel.getCHeader();
    notesLabels.setPreferredSize(new Dimension(ConcreteGuiViewPanel.getXMARG(),
            displayPanel.getHeight()*( displayPanel).getRange()));
    beatsLabels.setPreferredSize(new Dimension(( displayPanel).getLength()*displayPanel.getWidth(),
            ConcreteGuiViewPanel.getYMARG()));
    this.scroll = new JScrollPane(this.displayPanel);
    //scroll.addKeyListener(new KeyboardHandler());
    scroll.setRowHeaderView(notesLabels);
    scroll.setColumnHeaderView(beatsLabels);
    this.displayPanel.setAutoscrolls(true);
    setVisible(true);
    scroll.setVisible(true);
    //notesLabels.setVisible(true);
    scroll.setPreferredSize(new Dimension(940, 480));
    //notesLabels.setPreferredSize(new Dimension(ConcreteGuiViewPanel.getXMARG(), 360));
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(scroll);
    this.setLayout(new GridBagLayout());
    //this.add(scroll);
    this.pack();

  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public int getRange() {
    return displayPanel.getRange();
  }

  @Override public void scrollLeft() {
    int scrollIncr = 100;
    int horScrollVal = this.scroll.getHorizontalScrollBar().getValue();
    this.scroll.getHorizontalScrollBar().setValue(horScrollVal - scrollIncr);
  }

  @Override public void scrollRight() {
    int scrollIncr = 100;
    int horScrollVal = this.scroll.getHorizontalScrollBar().getValue();
    this.scroll.getHorizontalScrollBar().setValue(horScrollVal + scrollIncr);
  }

  @Override public void scrollUp() {
    int scrollIncr = 100;
    int vertScrollVal = this.scroll.getVerticalScrollBar().getValue();
    this.scroll.getVerticalScrollBar().setValue(vertScrollVal - scrollIncr);
  }

  @Override public void scrollDown() {
    int scrollIncr = 100;
    int vertScrollVal = this.scroll.getVerticalScrollBar().getValue();
    this.scroll.getVerticalScrollBar().setValue(vertScrollVal + scrollIncr);
  }

  @Override public int getCurrentBeat(int i) {
    return i;
  }

  @Override
  public void initialize(){
    this.setVisible(true);
    for (int i = 0; i < this.getKeyListeners().length; i++) {
      System.out.println(i + "key listeners: " + this.getKeyListeners()[i]);
    }
  }

  @Override
  public void play() {}

  @Override
  public void pause() {}

  @Override
  public void rewind() {}

  @Override
  public void skip() {}

  @Override
  public void next() {}

  @Override
  public int getCurrentBeat() {
    return 5;
  }

  @Override
  public void redraw(MusicEditorModel m) {
    this.repaint();
  }

  @Override public boolean isHighlighted(Note n) {
    return false;
  }

  @Override
  public void Highlight(Note n) {
    this.displayPanel.highlight(n);
  }

  @Override public List<Note> getHighlighted() {
    return null;
  }

  @Override
  public void unhighlight(Note n) {
    this.displayPanel.unhighlight(n);
  }

  @Override
  public void unhighlightAll() {
    this.displayPanel.unhighlightAll();
  }

  public void setDisplayPanel(ConcreteGuiViewPanel c){
    this.displayPanel = c;
  }

  @Override public void addMouseListener(MouseListener m) {
    this.displayPanel.addMouseListener(m);
  }

  @Override public void addMetaListener(MetaEventListener m) {}

  public int getLowestPitch() {
    return this.displayPanel.getLowestPitch();
  }

  @Override public void highlightNote(Note n) {}

  @Override public void drawMarker(int beat) {}

  @Override public void drawNote(Note n) {}

  @Override public void eraseNote(Note n) {}

  @Override public void redrawNote(Note n) {}

  @Override public void restart() {}

  @Override public void removeNote(Note n) {}


}
