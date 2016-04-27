package cs3500.music.controller;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.view.*;

import javax.sound.midi.MetaEventListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * A controller implementation for a Music Editor View
 */
public class ControllerImpl implements Controller {
  private final MusicEditorModel model;
  private final GuiView view;
  MetaEventListener meta;
  Map<Integer, Pitch> pitchMap;
  private int currentBeat;
  private int mouseStatus;

  public ControllerImpl(MusicEditorModel model, GuiView view) {

    this.model = model;
    this.pitchMap = new TreeMap<Integer, Pitch>();
    for (int i = 0; i < model.getPitches().size(); i++) {
      pitchMap.put(ConcreteGuiViewPanel.getYMARG() + i * 25, (Pitch) model.getPitches().get(i));

    }

    this.view = view;

    this.configureMouseListener();
    this.configureKeyBoardListener();
    this.view.addMetaListener(new MetaHandler());
    this.view.resetFocus();
    this.view.getCurrentBeat(currentBeat);

  }

  /**
   * Creates and sets a keyboard listener for the view. In effect it creates snippets of code as
   * Runnable object, one for each time a key is typed, pressed and released, only for those that
   * the program needs.
   * Last we create our KeyboardListener object, set all its maps and then give it to the view.
   */
  private void configureKeyBoardListener() {
    Map<Integer, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();

    // Space bar to pause and play
    keyPresses.put(32, () -> view.pause());
    // 'H' key to restart and return
    keyPresses.put(72, () -> view.restart());
    // 'J' key skip to the end of a song
    keyPresses.put(74, () -> view.skip());
    // Left arrow key to scroll left
    keyPresses.put(37, () -> view.scrollLeft());
    // Up arrow key to scroll up
    keyPresses.put(38, () -> view.scrollUp());
    // Right arrow key to scroll right
    keyPresses.put(39, () -> view.scrollRight());
    // Down arrow key to scroll down
    keyPresses.put(40, () -> view.scrollDown());
    // D to remove a note
    keyPresses.put(68, () -> view.removeNote(view.getHighlighted().get(0)));
    // 0 to set mouse to add notes
    keyPresses.put(48, () -> this.setMouseStatus(0));
    // 1 to set mouse to highlight/unhighlight notes
    keyPresses.put(49, () -> this.setMouseStatus(1));
    // 2 to set mouse to delete notes
    keyPresses.put(50, () -> this.setMouseStatus(2));

    KeyboardHandler kbd = new KeyboardHandler();
    kbd.setKeyTypedMap(keyTypes);
    kbd.setKeyPressedMap(keyPresses);
    kbd.setKeyReleasedMap(keyReleases);

    view.addKeyListener(kbd);

  }

  /**
   * Creates and sets a mouse listener for the view. In effect it creates snippets of code as
   * Consumer object, one for each time the mouse is clicked, pressed, released, entered, exited,
   * dragged and moved only for those that the program needs.
   *
   * Last we create our MouseListener object, set all its Consumers and then give it to the view.
   */
  private void configureMouseListener() {

    MouseListener mouse = new MouseHandler((MouseEvent me) -> {
      switch(mouseStatus) {
        case 0:
          int pitchInt =  ( ((MidiGuiView) view).getLowestPitch() - 1) +
              (view.getRange() - me.getY() / 25);
          if (pitchInt >= ((MidiGuiView) view).getLowestPitch() &&
              pitchInt <= ((MidiGuiView) view).getLowestPitch() + view.getRange()) {
            Pitch pitch = new Pitch(pitchInt);
            int start = (me.getX() / 25);
            Note n = new Note(pitch, start, 50, 8, 94);
            model.addNote(n);
            view.redraw(model);
          }
          break;

        case 1:
          pitchInt = (((MidiGuiView) view).getLowestPitch() - 1) +
              (view.getRange() - me.getY() / 25);
          if (pitchInt >= ((MidiGuiView) view).getLowestPitch() &&
              pitchInt <= ((MidiGuiView) view).getLowestPitch() + view.getRange()) {
            Pitch pitch = new Pitch(pitchInt);
            int start = (me.getX() / 25);
            List<Note> beats = model.getNotesStartingAt(start);
            for (Note n : beats) {
              if (n.getPitch().getInt() == pitchInt) {
                Note keynote = n;
                if (view.getHighlighted().contains(keynote)) {
                  System.out.println(view.getHighlighted());
                  view.unhighlight(keynote);
                  view.redraw(model);
                  break;
                }
                else {
                  System.out.println(view.getHighlighted());
                  view.highlightNote(keynote);
                  view.redraw(model);
                  break;
                }
              }
            }
          }
        case 2:
          pitchInt = (((MidiGuiView) view).getLowestPitch() - 1) +
              (view.getRange() - me.getY() / 25);
          if (pitchInt >= ((MidiGuiView) view).getLowestPitch() &&
              pitchInt <= ((MidiGuiView) view).getLowestPitch() + view.getRange()) {
            Pitch pitch = new Pitch(pitchInt);
            int start = (me.getX() / 25);
            List<Note> beats = model.getNotesStartingAt(start);
            for (Note n : beats) {
              if (n.getPitch().getInt() == pitchInt) {
                Note keynote = n;
                if (view.getHighlighted().contains(keynote)) {
                  view.removeNote(n);
                  model.removeNote(n);
                  view.redraw(model);
                  break;
                }
              }
            }
          }
      }


    }, (MouseEvent me) -> {
    }, (MouseEvent me) -> {
      System.out.println("Shift Down?: " + me.isShiftDown());
    }, (MouseEvent me) -> {
      //System.out.println("entered");
    }, (MouseEvent me) -> {
      //System.out.println("exited");
    }, (MouseEvent me) -> {
      System.out.println("dragged");
    }, (MouseEvent me) -> {
      System.out.println("moved");
    });

    view.addMouseListener(mouse);

  }

  @Override
  public void initialize() {
    this.view.initialize();
    this.view.play();
  }

  private void setMouseStatus(int s){
    this.mouseStatus = s;
    System.out.println("Mouse Status = " + mouseStatus);
  }
}
