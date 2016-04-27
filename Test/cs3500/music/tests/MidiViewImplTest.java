package cs3500.music.tests;

import cs3500.music.model.*;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.MidiViewImpl;
import org.junit.Test;

import javax.sound.midi.Receiver;
import javax.sound.midi.Synthesizer;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test suite for {@code MidiViewImpl} class
 */
public class MidiViewImplTest {

  Synthesizer synth;
  StringBuilder log;

  CompositionBuilder<MusicEditorModel> music;
  MusicEditorModel model;

  @Test
  public void testPlay() throws FileNotFoundException {
    log = new StringBuilder();
    music = new MusicEditorModelImpl.Builder();
    synth = new MockSynthesizer(log);
    // start, end, instrument, pitch, volume
    int start1 = 2;
    int end1 = 7;
    int instrument1 = 4;
    int pitch1 = 90;
    int midipitch = 102;
    int volume1 = 18;
    int tempo1 = 40;
    music.addNote(start1, end1,instrument1, pitch1, volume1);
    music.setTempo(tempo1);

    MidiViewImpl midi = new MidiViewImpl(music.build(), synth);
    midi.play();
    // returns note 144 for NOTE.on, 128 for NOTE.off, instrument - 1, pitch + 12, volume, tempo

    assertEquals(log.toString().substring(30, 31), Integer.toString(instrument1));
    assertEquals(log.toString().substring(39, 41), Integer.toString(pitch1));
    assertEquals(log.toString().substring(50, 52), Integer.toString(volume1));
    assertEquals(log.toString().substring(65, 67), Integer.toString(tempo1*start1));
  }

  @Test
  public void testPlay2() throws FileNotFoundException {
    log = new StringBuilder();
    music = new MusicEditorModelImpl.Builder();
    synth = new MockSynthesizer(log);

    model = MusicReader.parseFile(new FileReader("mystery-1.txt"), music);
    MidiViewImpl midi3 = new MidiViewImpl(model, synth);

    assertTrue(log.toString().equals(""));
    // log is empty then log returns Note information after play
    midi3.play();
    // no longer empty log after play
    assertFalse(log.toString().equals(""));

    // transcript of mystery-1 aka mario
  }

  @Test
  public void testMidiView() throws FileNotFoundException {
    log = new StringBuilder();
    music = new MusicEditorModelImpl.Builder();
    synth = new MockSynthesizer(log);

    model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"), music);
    //View view = ViewFactory.createView("midi", model);
    MidiViewImpl midi2 = new MidiViewImpl(model, synth);
    midi2.play();

    assertEquals(log.toString(),
        "note Status: ON   Instrument: 1 Pitch: 64 Volume: 72 Time stamp: 0\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 72 Time stamp: 400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 70 Time stamp: 0\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 70 Time stamp: 1400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 72 Time stamp: 0\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 72 Time stamp: 400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 70 Time stamp: 0\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 70 Time stamp: 1400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 70 Time stamp: 0\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 70 Time stamp: 1400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 72 Time stamp: 400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 72 Time stamp: 800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 70 Time stamp: 0\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 70 Time stamp: 1400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 72 Time stamp: 400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 72 Time stamp: 800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 70 Time stamp: 0\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 70 Time stamp: 1400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 60 Volume: 71 Time stamp: 800000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 60 Volume: 71 Time stamp: 1200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 70 Time stamp: 0\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 70 Time stamp: 1400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 60 Volume: 71 Time stamp: 800000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 60 Volume: 71 Time stamp: 1200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 70 Time stamp: 0\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 70 Time stamp: 1400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 79 Time stamp: 1200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 79 Time stamp: 1600000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 79 Time stamp: 1200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 79 Time stamp: 1600000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 1600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 3000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 85 Time stamp: 1600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 85 Time stamp: 2000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 1600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 3000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 85 Time stamp: 1600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 85 Time stamp: 2000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 1600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 3000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 78 Time stamp: 2000000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 78 Time stamp: 2400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 1600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 3000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 78 Time stamp: 2000000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 78 Time stamp: 2400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 1600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 3000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 74 Time stamp: 2400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 74 Time stamp: 3000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 1600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 3000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 74 Time stamp: 2400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 74 Time stamp: 3000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 1600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 3000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 74 Time stamp: 2400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 74 Time stamp: 3000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 77 Time stamp: 3200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 77 Time stamp: 4800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 75 Time stamp: 3200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 75 Time stamp: 3600000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 77 Time stamp: 3200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 77 Time stamp: 4800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 75 Time stamp: 3200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 75 Time stamp: 3600000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 77 Time stamp: 3200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 77 Time stamp: 4800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 77 Time stamp: 3600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 77 Time stamp: 4000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 77 Time stamp: 3200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 77 Time stamp: 4800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 77 Time stamp: 3600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 77 Time stamp: 4000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 77 Time stamp: 3200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 77 Time stamp: 4800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 75 Time stamp: 4000000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 75 Time stamp: 4800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 77 Time stamp: 3200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 77 Time stamp: 4800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 75 Time stamp: 4000000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 75 Time stamp: 4800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 77 Time stamp: 3200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 77 Time stamp: 4800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 75 Time stamp: 4000000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 75 Time stamp: 4800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 77 Time stamp: 3200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 77 Time stamp: 4800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 75 Time stamp: 4000000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 75 Time stamp: 4800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 4800000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 5200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 82 Time stamp: 4800000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 82 Time stamp: 5200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 4800000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 5200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 82 Time stamp: 4800000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 82 Time stamp: 5200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 67 Volume: 84 Time stamp: 5200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 67 Volume: 84 Time stamp: 5600000\n"
            + "note Status: ON   Instrument: 1 Pitch: 67 Volume: 84 Time stamp: 5200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 67 Volume: 84 Time stamp: 5600000\n"
            + "note Status: ON   Instrument: 1 Pitch: 67 Volume: 75 Time stamp: 5600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 67 Volume: 75 Time stamp: 6400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 67 Volume: 75 Time stamp: 5600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 67 Volume: 75 Time stamp: 6400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 67 Volume: 75 Time stamp: 5600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 67 Volume: 75 Time stamp: 6400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 67 Volume: 75 Time stamp: 5600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 67 Volume: 75 Time stamp: 6400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 6400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 8000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 73 Time stamp: 6400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 73 Time stamp: 6800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 6400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 8000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 73 Time stamp: 6400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 73 Time stamp: 6800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 6400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 8000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 69 Time stamp: 6800000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 69 Time stamp: 7200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 6400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 8000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 69 Time stamp: 6800000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 69 Time stamp: 7200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 6400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 8000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 60 Volume: 71 Time stamp: 7200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 60 Volume: 71 Time stamp: 7600000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 6400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 8000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 60 Volume: 71 Time stamp: 7200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 60 Volume: 71 Time stamp: 7600000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 6400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 8000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 80 Time stamp: 7600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 80 Time stamp: 8000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 6400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 8000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 80 Time stamp: 7600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 80 Time stamp: 8000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 8000000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 9600000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 84 Time stamp: 8000000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 84 Time stamp: 8400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 8000000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 9600000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 84 Time stamp: 8000000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 84 Time stamp: 8400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 8000000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 9600000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 76 Time stamp: 8400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 76 Time stamp: 8800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 8000000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 9600000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 76 Time stamp: 8400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 76 Time stamp: 8800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 8000000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 9600000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 74 Time stamp: 8800000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 74 Time stamp: 9200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 8000000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 9600000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 74 Time stamp: 8800000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 74 Time stamp: 9200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 8000000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 9600000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 77 Time stamp: 9200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 77 Time stamp: 9600000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 8000000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 79 Time stamp: 9600000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 77 Time stamp: 9200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 77 Time stamp: 9600000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 9600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 11200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 75 Time stamp: 9600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 75 Time stamp: 10000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 9600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 11200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 75 Time stamp: 9600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 75 Time stamp: 10000000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 9600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 11200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 74 Time stamp: 10000000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 74 Time stamp: 10400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 9600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 11200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 74 Time stamp: 10000000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 74 Time stamp: 10400000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 9600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 11200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 81 Time stamp: 10400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 81 Time stamp: 10800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 9600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 11200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 64 Volume: 81 Time stamp: 10400000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 64 Volume: 81 Time stamp: 10800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 9600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 11200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 70 Time stamp: 10800000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 70 Time stamp: 11200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 9600000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 55 Volume: 78 Time stamp: 11200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 62 Volume: 70 Time stamp: 10800000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 62 Volume: 70 Time stamp: 11200000\n"
            + "note Status: ON   Instrument: 1 Pitch: 52 Volume: 72 Time stamp: 11200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 52 Volume: 72 Time stamp: 12800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 60 Volume: 73 Time stamp: 11200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 60 Volume: 73 Time stamp: 12800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 52 Volume: 72 Time stamp: 11200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 52 Volume: 72 Time stamp: 12800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 60 Volume: 73 Time stamp: 11200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 60 Volume: 73 Time stamp: 12800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 52 Volume: 72 Time stamp: 11200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 52 Volume: 72 Time stamp: 12800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 60 Volume: 73 Time stamp: 11200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 60 Volume: 73 Time stamp: 12800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 52 Volume: 72 Time stamp: 11200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 52 Volume: 72 Time stamp: 12800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 60 Volume: 73 Time stamp: 11200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 60 Volume: 73 Time stamp: 12800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 52 Volume: 72 Time stamp: 11200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 52 Volume: 72 Time stamp: 12800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 60 Volume: 73 Time stamp: 11200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 60 Volume: 73 Time stamp: 12800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 52 Volume: 72 Time stamp: 11200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 52 Volume: 72 Time stamp: 12800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 60 Volume: 73 Time stamp: 11200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 60 Volume: 73 Time stamp: 12800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 52 Volume: 72 Time stamp: 11200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 52 Volume: 72 Time stamp: 12800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 60 Volume: 73 Time stamp: 11200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 60 Volume: 73 Time stamp: 12800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 52 Volume: 72 Time stamp: 11200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 52 Volume: 72 Time stamp: 12800000\n"
            + "note Status: ON   Instrument: 1 Pitch: 60 Volume: 73 Time stamp: 11200000\n"
            + "note Status: OFF  Instrument: 1 Pitch: 60 Volume: 73 Time stamp: 12800000\n");


  }


}
