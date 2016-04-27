package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.function.Consumer;

/**
 * Class to handle mouse events
 */
public class MouseHandler implements MouseListener, MouseMotionListener {
  // MAP OR CONSUMER (LAMBDA FUNCTIONS)
  private final Consumer<MouseEvent> clicked;
  private final Consumer<MouseEvent> pressed;
  private final Consumer<MouseEvent> released;
  private final Consumer<MouseEvent> entered;
  private final Consumer<MouseEvent> exited;
  private final Consumer<MouseEvent> dragged;
  private final Consumer<MouseEvent> moved;

  public MouseHandler(Consumer<MouseEvent> clicked, Consumer<MouseEvent> pressed,
      Consumer<MouseEvent> released, Consumer<MouseEvent> entered, Consumer<MouseEvent> exited,
      Consumer<MouseEvent> dragged, Consumer<MouseEvent> moved) {
    this.clicked = clicked;
    this.pressed = pressed;
    this.released = released;
    this.entered = entered;
    this.exited = exited;
    this.dragged = dragged;
    this.moved = moved;
  }


  @Override public void mouseClicked(MouseEvent e) {
    clicked.accept(e);
  }

  @Override public void mousePressed(MouseEvent e) {
    pressed.accept(e);
  }

  @Override public void mouseReleased(MouseEvent e) {
    released.accept(e);
  }

  @Override public void mouseEntered(MouseEvent e) {
    entered.accept(e);
  }

  @Override public void mouseExited(MouseEvent e) {
    exited.accept(e);
  }

  @Override public void mouseDragged(MouseEvent e) {
    dragged.accept(e);
  }

  @Override public void mouseMoved(MouseEvent e) {
    moved.accept(e);
  }
}
