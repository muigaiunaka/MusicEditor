package cs3500.music.view;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.model.Tone;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

import javax.swing.*;

/**
 * A not-so dummy view (anymore) that simply draws a string
 */
public class ConcreteGuiViewPanel extends JPanel {
  private MusicEditorModel<Note> model;

  public static int getYMargin() {
    return YMARG;
  }

  public static int getXMargin() {
    return XMARG;
  }

  public static int getMeasureWidth() {
    return WIDTH;
  }

  public static int getNoteHeight() {
    return HEIGHT;
  }

  final static int XMARG = 40;
  final static int YMARG = 20;
  final static int WIDTH = 100;
  final static int HEIGHT = 25;
  int length;
  int range;
  List<Note> allNotes;
  List<Note> highNotes;
  int lowestPitch;
  int highestPitch;
  List<Pitch> pitches;
  TreeMap<Pitch, Integer> pitchMap;
  private int currentBeat;

  public ConcreteGuiViewPanel(MusicEditorModel<Note> model) {
    this.highNotes = new ArrayList<Note>();
    this.model = model;
    length = model.getLength();
    range = model.getHighestNote().getPitch().getInt()-
        model.getLowestNote().getPitch().getInt() + 1;
    //TODO: ASK ABOUT WILD CARD AND CASTING
    pitches = (List<Pitch>) model.getPitches(); // reversed pitches

    Collections.sort(pitches);
    lowestPitch = pitches.get(0).getInt();
    Collections.reverse(pitches);

    pitchMap = new TreeMap();

    int counter = 0;
    for (Pitch p: pitches) {
      pitchMap.put(p, counter);
      counter++;
    }

  }

  @Override
  public void paintComponent(Graphics g){
    // Handle the default painting
    super.paintComponent(g);
    // Look for more documentation about the Graphics class,
    // and methods on it that may be useful
    //this.paintPitches(g);
    //this.paintLength(g);
    this.paintNote(g);
    this.paintSheet(g);
    this.paintBarMarker(g, currentBeat);
    this.paintOctaveBorders(g);
    this.paintHighNotes(g);

  }

  public void setCurrentBeat(int b) {
    currentBeat = b;
  }

  public int getCurrentBeat(int beat) {
    return beat;
  }

  /**
   * Paints boxes for notes, note heads are painted in red and sustained beats
   * are painted in green.
   * @param g
   */
  private void paintNote(Graphics g) {
    Graphics2D g3 = (Graphics2D) g;
    allNotes = model.getAllNotes();
    Color black = Color.black;
    Color green = Color.GREEN;
    Color highlightRed = Color.red;
    Color highlightBlue = Color.cyan;
    g3.setPaint(black);

    for (Note n: allNotes) {
      g3.setPaint(black);
      g3.fill(new Rectangle2D.Double(n.getStartTime()*(WIDTH/4),
          pitchMap.get(n.getPitch()) * HEIGHT,
          WIDTH/4, HEIGHT));
      for (int w = 1; w < n.getDuration(); w++) {
        g3.setPaint(green);
        g3.fill(new Rectangle2D.Double((WIDTH/4)*n.getStartTime()+(w*(WIDTH/4)),
            pitchMap.get(n.getPitch()) * HEIGHT,
            WIDTH/4, HEIGHT));
      }
    }

  }

  private void paintHighNotes(Graphics g) {
    Graphics2D g3 = (Graphics2D) g;
    Color highlightRed = Color.magenta;
    Color highlightBlue = Color.cyan;
    g3.setPaint(highlightRed);

    for (Note n: highNotes) {
      g3.setPaint(highlightRed);
      g3.fill(new Rectangle2D.Double(n.getStartTime()*(WIDTH/4),
          pitchMap.get(n.getPitch()) * HEIGHT,
          WIDTH/4, HEIGHT));
      for (int w = 1; w < n.getDuration(); w++) {
        g3.setPaint(highlightBlue);
        g3.fill(new Rectangle2D.Double((WIDTH/4)*n.getStartTime()+(w*(WIDTH/4)),
            pitchMap.get(n.getPitch()) * HEIGHT,
            WIDTH/4, HEIGHT));
      }
    }

  }

  /**
   * Print the integer beat every 4 measures above the sheet.
   * @param g
   */
  void paintLength(Graphics g) {
    for (int m = 0; m <= length; m+=4) {
      g.drawString(Integer.toString(m*4), (m*WIDTH), 10);
    }
  }

  /**
   * Print each pitch along the left side of the frame.
   * @param g
   */
  void paintPitches(Graphics g) {
    Graphics2D g1 = (Graphics2D)g;
    g1.setPaint(Color.black);
    int y = (HEIGHT*2/3);
    for (Pitch p: pitches) {
      g.drawString(p.toString(), 5, y);
      y+=HEIGHT;
      if (p.getTone().equals(Tone.C)){
        ((Graphics2D)g).setStroke(new BasicStroke(3));
        //g.drawLine(1, y-15, length*25-1, y-15);
      }
    }
  }

  void paintOctaveBorders(Graphics g){
    Graphics2D g1 = (Graphics2D)g;
    g1.setPaint(Color.black);
    int y = (HEIGHT*2/3);
    for (Pitch p: pitches) {
      //g.drawString(p.toString(), 5, y);
      y+=HEIGHT;
      if (p.getTone().equals(Tone.C)){
        ((Graphics2D)g).setStroke(new BasicStroke(3));
        g.drawLine(1, y-15, length*25-1, y-15);
      }
    }
  }

  /**
   * Print the empty grid sheet to display notes on.
   * @param g
   */
  private void paintSheet(Graphics g) {
    int numBars;
    Graphics2D g5 = (Graphics2D) g;
    g5.setStroke(new BasicStroke(1));
    g5.setPaint(Color.BLACK);

    if (length % 4 != 0) {
      numBars = (length / 4) + 1;
    } else {
      numBars = length/4;
    }

    for (int i = 0; i < numBars; i++) {
      for (int j = 0; j < range; j++) {
        g5.drawRect( ((WIDTH*i)), (HEIGHT*j), WIDTH, HEIGHT) ;
      }
    }

  }

  void paintBarMarker(Graphics g, int beat) {
    Graphics2D g6 = (Graphics2D) g;
    g6.setStroke(new BasicStroke(2));
    g6.setPaint(Color.red);
    g6.drawLine(beat*WIDTH/4, 1, beat*25, 1 + pitches.size()*HEIGHT);
  }

  public static int getXMARG() {
    return XMARG;
  }

  public static int getYMARG() {
    return YMARG;
  }

  public static int getWIDTH() {
    return WIDTH;
  }

  public static int getHEIGHT() {
    return HEIGHT;
  }

  public int getLength() {
    return length;
  }

  public int getRange() {
    return range;
  }

  public JPanel getRHeader() {
    JPanel header = new RowGuiViewPanel(this);
    return header;
  }

  public JPanel getCHeader() {
    JPanel header = new ColGuiViewPanel(this);
    return header;
  }

  public int getLowestPitch() {
    return lowestPitch;
  }

  MusicEditorModel<Note> getModel() {
    return model;
  }

  void highlight(Note n){
    highNotes.add(n);
  }
  void unhighlight(Note n){
    if (highNotes.contains(n)) {
      highNotes.remove(n);
    }
    System.out.println(highNotes);
  }
  void unhighlightAll() {
    highNotes.removeAll(highNotes);
  }
}
