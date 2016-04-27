package cs3500.music.tests;

import cs3500.music.controller.Controller;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.MusicEditorModelImpl;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.view.*;
import org.junit.Test;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

/**
 * Test suite for the controller
 */
public class ControllerTest {
  Controller contTest;
  Controller contTest2;
  GuiView midiGuiTest;
  GuiView midiGuiTest2;
  View midiTest;
  GuiView guiTest;
  MusicEditorModel modelTest;
  Note testNote1;
  Note testNote2;
  Note testNote3;
  Note testNote4;
  Note testNote5;
  Note testNote6;
  Note testNote7;
  Note testNote8;
  CompositionBuilder<MusicEditorModel> compTest;
  boolean firstBool;
  boolean secondBool;
  boolean thirdBool;
  // initialize keyboard handler
  KeyboardHandler kbd;
  Map<Integer, Runnable> keyTypes;
  Map<Integer, Runnable> keyPresses;
  Map<Integer, Runnable> keyReleases;
  // initialize mouse handler
  MouseHandler mouse;

  @Test
  public void initData() {
    compTest = new MusicEditorModelImpl.Builder();

    testNote1 = new Note(new Pitch(48), 6, 4, 10, 64);
    testNote2 = new Note(new Pitch(49), 8, 4, 10, 64);
    testNote3 = new Note(new Pitch(50), 10, 4, 10, 64);
    testNote4 = new Note(new Pitch(51), 12, 6, 10, 64);
    testNote5 = new Note(new Pitch(52), 14, 6, 10, 64);
    testNote6 = new Note(new Pitch(53), 16, 4, 10, 64);
    testNote7 = new Note(new Pitch(54), 18, 2, 10, 64);
    testNote8 = new Note(new Pitch(55), 20, 8, 10, 64);
    // boolean values to flip
    firstBool = true;
    secondBool = true;
    thirdBool = false;

    // add notes to builder
    compTest.addNote(testNote1.getStartTime(), testNote1.getStartTime() + testNote1.getDuration(),
        testNote1.getInstrument(), testNote1.getPitch().getInt(), testNote1.getVolume());
    compTest.addNote(testNote2.getStartTime(), testNote2.getStartTime() + testNote2.getDuration(),
        testNote2.getInstrument(), testNote2.getPitch().getInt(), testNote2.getVolume());
    compTest.addNote(testNote3.getStartTime(), testNote3.getStartTime() + testNote3.getDuration(),
        testNote3.getInstrument(), testNote3.getPitch().getInt(), testNote3.getVolume());
    compTest.addNote(testNote4.getStartTime(), testNote4.getStartTime() + testNote4.getDuration(),
        testNote4.getInstrument(), testNote4.getPitch().getInt(), testNote4.getVolume());
    compTest.addNote(testNote5.getStartTime(), testNote5.getStartTime() + testNote5.getDuration(),
        testNote5.getInstrument(), testNote5.getPitch().getInt(), testNote5.getVolume());
    compTest.addNote(testNote6.getStartTime(), testNote6.getStartTime() + testNote6.getDuration(),
        testNote6.getInstrument(), testNote6.getPitch().getInt(), testNote6.getVolume());
    compTest.addNote(testNote7.getStartTime(), testNote7.getStartTime() + testNote7.getDuration(),
        testNote7.getInstrument(), testNote7.getPitch().getInt(), testNote7.getVolume());
    compTest.addNote(testNote8.getStartTime(), testNote8.getStartTime() + testNote8.getDuration(),
        testNote8.getInstrument(), testNote8.getPitch().getInt(), testNote8.getVolume());
    // set tempo of the build
    compTest.setTempo(40000);
    // build the composition
    modelTest = compTest.build();

    // set views
    guiTest = new GuiViewFrame(modelTest);
    midiTest = new MidiViewImpl(modelTest);
    // set up mock composite view
    midiGuiTest = new MockGuiView(modelTest, guiTest, midiTest);
    midiGuiTest2 = new MidiGuiView(modelTest);
    contTest = new ControllerImpl(modelTest, midiGuiTest, new KeyboardHandler());
    contTest2 = new ControllerImpl(modelTest, midiGuiTest2);

  }

  /**
   * configure the key handler with methods to later test the link between the key listener
   * and the controller
   * @return the configured key handler
   */
  private KeyboardHandler configureTestKeyHandler() {
    keyTypes = new HashMap<>();
    keyPresses = new HashMap<>();
    keyReleases = new HashMap<>();

    kbd = new KeyboardHandler();
    kbd.setKeyTypedMap(keyTypes);
    kbd.setKeyPressedMap(keyPresses);
    kbd.setKeyReleasedMap(keyReleases);

    keyPresses.put(32, () ->  firstBool = true);
    keyTypes.put(32, () -> firstBool = false);
    keyPresses.put(48, () -> midiGuiTest.addNote(new Note(new Pitch(60), 90)));

    return kbd;
  }

  /**
   * configure the key handler with methods to later test the link between the key listener
   * and the controller
   * @return the configured key handler
   */
  private MouseHandler configureTestMouseHandler() {
    mouse = new MouseHandler((MouseEvent me) -> {
    }, (MouseEvent me) -> {
    }, (MouseEvent me) -> {
    }, (MouseEvent me) -> {
    }, (MouseEvent me) -> {
    }, (MouseEvent me) -> {
    }, (MouseEvent me) -> {
    });
    return mouse;
  }

  @Test
  public void testController1() {
    this.initData();

    midiGuiTest.addKeyListener(this.configureTestKeyHandler());
    KeyboardHandler firstKBH = contTest.getKeyboardHandler();

    contTest.setKeyboardHandler(this.configureTestKeyHandler());

    KeyboardHandler secondKBH = contTest.getKeyboardHandler();

    assertFalse(secondKBH.getKeyCodes().contains(45));
    assertTrue(secondKBH.getKeyCodes().contains(32));
    assertNotNull(firstKBH);
  }

  @Test
  public void testController2() {
    this.initData();

    midiGuiTest.addKeyListener(this.configureTestKeyHandler());

    contTest.setKeyboardHandler(this.configureTestKeyHandler());

    assertEquals(((MockGuiView)midiGuiTest).getModel().getHighestBeat(), 28);
    keyPresses.get(48).run();
    assertEquals(((MockGuiView)midiGuiTest).getModel().getHighestBeat(), 92);

  }

  @Test (expected = NullPointerException.class)
  public void testController3() {
    this.initData();

    midiGuiTest.addKeyListener(this.configureTestKeyHandler());
    KeyboardHandler firstKBH = contTest.getKeyboardHandler();

    assertTrue(firstKBH.getKeyCodes().contains(45));
  }

  @Test
  public void testController4() {
    this.initData();

    KeyboardHandler kbh = contTest2.getKeyboardHandler();
    midiGuiTest2.addKeyListener(kbh);

    // 32, 36, 35, 37, 38, 39, 40, 68, 73, 75, 79, 85, 76, 74, 48, 49, 50, 51, 77, 53
    // the handler contains the keys for the runnables
    assertTrue(kbh.getKeyCodes().contains(32));
    assertTrue(kbh.getKeyCodes().contains(36));
    assertTrue(kbh.getKeyCodes().contains(35));
    assertTrue(kbh.getKeyCodes().contains(37));
    assertTrue(kbh.getKeyCodes().contains(38));
    assertTrue(kbh.getKeyCodes().contains(39));
    assertTrue(kbh.getKeyCodes().contains(40));
    assertTrue(kbh.getKeyCodes().contains(68));

  }

  @Test
  public void testController5() {
    this.initData();

    KeyboardHandler kbh = contTest2.getKeyboardHandler();
    midiGuiTest2.addKeyListener(kbh);

    assertFalse(kbh.getKeyCodes().contains(3));

  }

  @Test
  public void testController6() {
    this.initData();

    KeyboardHandler kbh = contTest2.getKeyboardHandler();
    midiGuiTest2.addKeyListener(kbh);

    assertTrue(midiGuiTest2.getHighlighted().isEmpty());

    midiGuiTest2.getHighlighted().add(testNote1);
    midiGuiTest2.getHighlighted().add(testNote2);
    midiGuiTest2.getHighlighted().add(testNote3);

    assertEquals(midiGuiTest2.getHighlighted().size(), 3);

    //KeyEvent mockEvent = new KeyEvent(new ConcreteGuiViewPanel(modelTest), 1, 1, 0,
    //    68, '\u0000');
    //kbh.keyPressed(mockEvent);

    //assertEquals(midiGuiTest2.getHighlighted().size(), 0);
    //assertEquals(modelTest.getLowestNote(), testNote4);
  }

  @Test
  public void testController7() {
    this.initData();

    KeyboardHandler kbh = contTest2.getKeyboardHandler();
    midiGuiTest2.addKeyListener(kbh);

    assertTrue(midiGuiTest2.getHighlighted().isEmpty());

    midiGuiTest2.getHighlighted().add(testNote1);
    midiGuiTest2.getHighlighted().add(testNote2);
    midiGuiTest2.getHighlighted().add(testNote3);


    assertTrue(testNote2.getDuration() == 4);
    //KeyEvent mockEvent = new KeyEvent(new ConcreteGuiViewPanel(modelTest), 1, 1, 0,
    //    79, '\u0000');
    //kbh.keyPressed(mockEvent);

    //assertTrue(testNote2.getDuration() == 5);

  }

  @Test
  public void testController8() {
    this.initData();

    KeyboardHandler kbh = contTest2.getKeyboardHandler();
    midiGuiTest2.addKeyListener(kbh);

    assertTrue(midiGuiTest2.getHighlighted().isEmpty());

    midiGuiTest2.getHighlighted().add(testNote2);
    midiGuiTest2.getHighlighted().add(testNote3);

    assertTrue(testNote2.getDuration() == 4);
    //KeyEvent mockEvent = new KeyEvent(new ConcreteGuiViewPanel(modelTest), 1, 1, 0,
    //    85, '\u0000');
    //kbh.keyPressed(mockEvent);
    //kbh.keyPressed(mockEvent);

    //assertTrue(testNote2.getDuration() == 2);

  }

  @Test
  public void testController9() {
    this.initData();

    KeyboardHandler kbh = contTest2.getKeyboardHandler();
    midiGuiTest2.addKeyListener(kbh);

    assertTrue(midiGuiTest2.getHighlighted().isEmpty());
    assertTrue(testNote2.getPitch().getInt() == 49);

    midiGuiTest2.getHighlighted().add(testNote2);
    midiGuiTest2.getHighlighted().add(testNote7);

    assertFalse(midiGuiTest2.getHighlighted().isEmpty());

    assertTrue(testNote2.getDuration() == 4);
    //KeyEvent mockEvent = new KeyEvent(new ConcreteGuiViewPanel(modelTest), 1, 1, 0,
    //    73, '\u0000');
    //kbh.keyPressed(mockEvent);
    //kbh.keyPressed(mockEvent);

    assertTrue(testNote2.getPitch().getInt() == 51);
  }

  @Test
  public void testController10() {
    this.initData();

    KeyboardHandler kbh = contTest2.getKeyboardHandler();
    midiGuiTest2.addKeyListener(kbh);

    assertTrue(midiGuiTest2.getHighlighted().isEmpty());
    assertTrue(testNote2.getPitch().getInt() == 49);

    midiGuiTest2.getHighlighted().add(testNote2);
    midiGuiTest2.getHighlighted().add(testNote7);

    assertFalse(midiGuiTest2.getHighlighted().isEmpty());

    assertTrue(testNote2.getDuration() == 4);
    //KeyEvent mockEvent = new KeyEvent(new ConcreteGuiViewPanel(modelTest), 1, 1, 0,
    //    75, '\u0000');
    //kbh.keyPressed(mockEvent);
    //kbh.keyPressed(mockEvent);

    //assertTrue(testNote2.getPitch().getInt() == 47);
  }



}
