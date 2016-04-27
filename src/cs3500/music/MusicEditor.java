package cs3500.music;

import cs3500.music.model.*;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.View;
import cs3500.music.view.ViewFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException,
      InterruptedException {
    MusicReader reader = new MusicReader();
    CompositionBuilder<MusicEditorModel> piece = new MusicEditorModelImpl.Builder();

    String songName = args[0];
    String viewType = args[1];

    Readable mystery3 = new FileReader(songName);

    MusicEditorModel<Note> modelMystery3 = reader.parseFile(mystery3, piece);

    View viewer = ViewFactory.createView(viewType, modelMystery3);

    viewer.initialize();
    viewer.play();

    //Thread.sleep(30000);
    // You probably need to connect these views to your model, too...
  }
}
