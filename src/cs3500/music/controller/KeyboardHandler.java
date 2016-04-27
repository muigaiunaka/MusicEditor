package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.TreeMap;

/**
 * Handles key events and stores different methods with different key inputs
 */
public class KeyboardHandler implements KeyListener {
  private Map<Integer, Runnable> typed;
  private Map<Integer, Runnable> pressed;
  private Map<Integer, Runnable> released;

  /**
   * Default constructor for a Keyboard handler
   */
  public KeyboardHandler(){
  }

  /**
   * Convenience constructor for a keyboard handler
   * @param typed the map of keyboard integer values and methods to run when that key is typed
   * @param pressed the map of keyboard integer values and methods to run when that key is pressed
   * @param released the map of keyboard integer values and methods to run when that key is released
   */
  public KeyboardHandler(Map<Integer, Runnable> typed, Map<Integer, Runnable> pressed,
      Map<Integer, Runnable> released) {
    this.typed = typed;
    this.pressed = pressed;
    this.released = released;

  }
  /**
   * Set the map for key typed events. Key typed events in Java Swing are characters
   */
  public void setKeyTypedMap(Map<Integer, Runnable> map) {
    typed = map;
  }

  /**
   * Set the map for key pressed events. Key pressed events in Java Swing are integer codes
   */
  public void setKeyPressedMap(Map<Integer, Runnable> map) {
    pressed = map;
  }

  /**
   * Set the map for key released events. Key released events in Java Swing are integer codes
   */
  public void setKeyReleasedMap(Map<Integer, Runnable> map) {
    released = map;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    if (typed.containsKey(e.getKeyCode())) {
      typed.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (pressed.containsKey(e.getKeyCode())) {
      pressed.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (released.containsKey(e.getKeyCode())) {
      released.get(e.getKeyCode()).run();
    }
  }

  /**
   * Allows the user to connect a key event with a method to run with whichever key they
   * wish to invoke that method
   *
   * @param e the key code linked
   * @param r the method associated with the key
   * @param m a string representing the type of key event
   */
  public void keyBind(KeyEvent e, Runnable r, String m) {
    switch (m.toLowerCase()) {
      case "typed":
        typed.put(e.getKeyCode(), r);
      case("pressed"):
        pressed.put(e.getKeyCode(), r);
      case("released"):
        released.put(e.getKeyCode(), r);
      default:
        throw new IllegalArgumentException("You can't set that");
    }
  }


}
