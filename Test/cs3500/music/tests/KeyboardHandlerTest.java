package cs3500.music.tests;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.model.MusicEditorModelImpl;
import cs3500.music.view.MidiGuiView;
import cs3500.music.view.View;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test suite for the KeyListener implementation
 */
public class KeyboardHandlerTest {
  KeyListener keyTest;
  Map<Integer, Runnable> typed = new HashMap<>();
  Map<Integer, Runnable> pressed = new HashMap<>();
  Map<Integer, Runnable> released = new HashMap<>();

  StringBuilder name;

  public int times5(int m) {
    return m*5;
  }

  public void printCodeName(StringBuilder name) {
    name.append(" Jackson");
  }

  public void printTyped(StringBuilder name) {
    name.append(" typed");
  }

  @Test
  public void testKeyTyped() {
    name = new StringBuilder();
    name.append("this just");
    typed.put(32, () -> printTyped(name));
    keyTest = new KeyboardHandler(typed, pressed, released);
    assertEquals(name.toString(), "this just");
    typed.get(32).run();
    assertEquals(name.toString(), "this just typed");
    typed.get(32).run();
    typed.get(32).run();
    assertEquals(name.toString(), "this just typed typed typed");
  }

  @Test
  public void testKeyPressed() {
    name = new StringBuilder();
    name.append("Jeff");

    pressed.put(32, () -> printCodeName(name));
    keyTest = new KeyboardHandler(typed, pressed, released);
    assertEquals(name.toString(), "Jeff");
    pressed.get(32).run();
    assertEquals(name.toString(), "Jeff Jackson");
  }

  @Test
  public void testKeyRelease() {
    int x = 1;
    released.put(42, () -> this.times5(x));
    keyTest = new KeyboardHandler(typed, pressed, released);
    assertEquals(x, 1);
    released.get(42).run();
    assertEquals(x, 1);

  }

  @Test (expected = NullPointerException.class)
  public void testKeyRelease2() {
    int x = 0;
    released.put(42, () -> times5(x));
    keyTest = new KeyboardHandler(typed, pressed, released);
    released.get(12).run();
  }


}
