
package cs3500.music.tests;

import cs3500.music.controller.MouseHandler;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test suite for the MouseListener implementation
 */
public class MouseHandlerTest {
  MouseListener mouseTest;

  boolean firstState = false;
  boolean secondState = false;
  boolean thirdState = false;

  public void initData() {
    this.firstState = false;
    this.secondState = false;
    this.thirdState = false;
  }

  @Test
  public void mouseClicked() {
    this.initData();

    mouseTest = new MouseHandler((MouseEvent me) -> {
      firstState = true;
    }, (MouseEvent me) -> {
    }, (MouseEvent me) -> {
    }, (MouseEvent me) -> {
    }, (MouseEvent me) -> {
    }, (MouseEvent me) -> {
      firstState = true;
    }, (MouseEvent me) -> {
    });
    mouseTest.mouseClicked(new MouseEvent(new JScrollBar(), 1, 1, 1, 1, 1, 1, true, 1));
    assertTrue(firstState);
    assertFalse(secondState);
  }


  @Test
  public void testMousePressed() {
    this.initData();

    mouseTest = new MouseHandler((MouseEvent me) -> {
      firstState = true;
    }, (MouseEvent me) -> {
      firstState = false;
    }, (MouseEvent me) -> {
    }, (MouseEvent me) -> {
    }, (MouseEvent me) -> {
    }, (MouseEvent me) -> {
    }, (MouseEvent me) -> {
    });

    mouseTest.mouseClicked(new MouseEvent(new JScrollBar(), 1, 1, 1, 1, 1, 1, true, 1));
    assertTrue(firstState);
    mouseTest.mousePressed(new MouseEvent(new JScrollBar(), 1, 1, 1, 1, 1, 1, true, 1));
    assertFalse(firstState);
  }

  @Test
  public void testMouseReleased() {
    this.initData();
    mouseTest = new MouseHandler((MouseEvent me) -> {
      firstState = true;
    }, (MouseEvent me) -> {
      firstState = false;
    }, (MouseEvent me) -> {
      secondState = true;
    }, (MouseEvent me) -> {
    }, (MouseEvent me) -> {
    }, (MouseEvent me) -> {
    }, (MouseEvent me) -> {
    });

    mouseTest.mouseClicked(new MouseEvent(new JScrollBar(), 1, 1, 1, 1, 1, 1, true, 1));
    assertTrue(firstState);
    mouseTest.mousePressed(new MouseEvent(new JScrollBar(), 1, 1, 1, 1, 1, 1, true, 1));
    assertFalse(secondState);
    mouseTest.mouseReleased(new MouseEvent(new JScrollBar(), 1, 1, 1, 1, 1, 1, true, 1));
    assertTrue(secondState);
  }


}
