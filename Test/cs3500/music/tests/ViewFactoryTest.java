package cs3500.music.tests;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.MusicEditorModelImpl;

import cs3500.music.view.View;
import cs3500.music.view.ViewFactory;
import org.junit.Test;


import static junit.framework.TestCase.assertEquals;

/**
 * Test suite for {@code ViewFactory} class
 */
public class ViewFactoryTest {
  MusicEditorModel model = new MusicEditorModelImpl();

  @Test
  public void testFactory1(){
    View midi = ViewFactory.createView("midi", model);
    assertEquals("MidiViewImpl", midi.getClass().getSimpleName());
  }

  @Test
  public void testFactory2() {
    View guiview = ViewFactory.createView("visual", model);
    assertEquals("GuiViewFrame", guiview.getClass().getSimpleName());
  }

  @Test
  public void testFactory3() {
    View console = ViewFactory.createView("console", model);
    assertEquals("ConsoleView", console.getClass().getSimpleName());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testException() {
    View midi = ViewFactory.createView("random", model);
  }

}
