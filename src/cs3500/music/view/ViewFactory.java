package cs3500.music.view;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.MusicEditorModelImpl;
import cs3500.music.model.Note;

/**
 * Represents a factory of all the possible view types
 */
public class ViewFactory {

  /**
   * Creates a view based on an input type
   *
   * @param viewType a string name for the expected view
   * @return an instance of the appropriate concrete view
   */
  public static View createView(String viewType, MusicEditorModel model) {
    switch (viewType.toLowerCase()) {
      case "console":
        return new ConsoleView(model);
      case "visual":
        return new GuiViewFrame(model);
      case "midi":
        return new MidiViewImpl(model);
      case "audiovisual":
        return new MidiGuiView(model);
      default:
        throw new IllegalArgumentException("This does not support that type of view");
    }
  }
}
