package cs3500.music.view;

import cs3500.music.controller.MetaHandler;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import javax.sound.midi.*;
import java.util.List;

/**
 * Implementation of a Midi View
 */
public class MidiViewImpl implements View<Note>  {
  private Synthesizer synth;
  private Receiver receiver;
  private MusicEditorModel<Note> model;
  private boolean paused;
  private int curBeat;
  private int channel;
  private Sequencer sequencer;
  private Sequence sequence;
  private MetaEventListener metaEventListener;
  private int currentBeat;


  /**
   * Default constructor
   * @param model the model to be played
   */
  public MidiViewImpl(MusicEditorModel<Note> model) {
    try {
      this.sequencer = MidiSystem.getSequencer();
      this.sequencer.open();
      this.sequence = new Sequence(Sequence.PPQ, 1);
      this.synth = MidiSystem.getSynthesizer();
      this.receiver = synth.getReceiver();
      this.model = model;
      this.synth.open();
      this.paused = false;
      this.curBeat = 0;
      this.channel = 0;
    } catch (MidiUnavailableException | InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  /**
   * Constructor used for testing
   * @param model the music model
   * @param synth the synth to generate sounds
   */
  public MidiViewImpl(MusicEditorModel<Note> model, Synthesizer synth) {
    try {
      this.synth = synth;
      this.receiver = synth.getReceiver();
      this.model = model;
      this.synth.open();
      this.paused = false;
      this.curBeat = 0;
      this.channel = 0;
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  /**
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   *  <li>{@link MidiSystem#getSynthesizer()}</li>
   *  <li>{@link Synthesizer}
   *    <ul>
   *      <li>{@link Synthesizer#open()}</li>
   *      <li>{@link Synthesizer#getReceiver()}</li>
   *      <li>{@link Synthesizer#getChannels()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link Receiver}
   *    <ul>
   *      <li>{@link Receiver#send(MidiMessage, long)}</li>
   *      <li>{@link Receiver#close()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link MidiMessage}</li>
   *  <li>{@link ShortMessage}</li>
   *  <li>{@link MidiChannel}
   *    <ul>
   *      <li>{@link MidiChannel#getProgram()}</li>
   *      <li>{@link MidiChannel#programChange(int)}</li>
   *    </ul>
   *  </li>
   * </ul>
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   *   https://en.wikipedia.org/wiki/General_MIDI
   *   </a>
   */

  /**
   * Plays the given note using the midi synthesizer
   * @param n the given note
   * @throws InvalidMidiDataException
   */

  public void playNote(Note n) throws InvalidMidiDataException {

  }

  public void loadTracks() throws InvalidMidiDataException {
    List<Note> notes = this.model.getAllNotes();
    this.sequencer.setSequence(this.sequence);
    Track track = this.sequence.createTrack();
    this.sequencer.setTempoInMPQ(model.getTempo());

    for (Note n: notes) {
      MidiEvent start = new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, n.getInstrument()-1,
          n.getPitch().getInt() + 12, n.getVolume()), n.getStartTime());
      MidiEvent stop = new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, n.getInstrument()-1,
          n.getPitch().getInt() + 12, n.getVolume()),
          (n.getStartTime() + (n.getDuration())));
      track.add(start);
      track.add(stop);
      MetaMessage metax = new MetaMessage();
      MidiMessage msg = start.getMessage();
      metax.setMessage(0,
          msg.getMessage(),
          msg.getLength());
      //System.out.println("\nMeta msg: " + metax);

      /*System.out.println("Byte Message start :" + start.getMessage().getMessage()
          + "\nByte Message stop :" + stop.getMessage().getMessage()
          + "\nstart time stamp: " + start.getTick()
          + "\nstop timestamp: " + stop.getTick()
          + "\n divided by tempo: " + start.getTick()/model.getTempo()
          + "\n stop divided by tempo: " + stop.getTick()/model.getTempo()
      + "\n Something: " + start.getMessage().getStatus()
      + "\n Something 2: " + start.getMessage().getLength());
      for (int i = 0; i < n.getStartTime(); i++) {
        this.currentBeat = i;
      }*/
    }

  }

  /**
   * Meta Event listener and handling the current beat
   */
  @Override
  public void addMetaListener(MetaEventListener m) {
    this.sequencer.addMetaEventListener(m);
  }

  @Override
  public int getCurrentBeat() {
    return this.currentBeat;
  }

  public int setCurrentBeat() {
    return 2;
  }

  @Override
  public void play() {
    try {
      this.sequencer.setSequence(this.sequence);
      this.sequencer.start();
      /*while (this.sequencer.isOpen()) {
        //System.out.println("Tick pos: " + sequencer.getTickPosition());
        for (int i = 0; i < sequencer.getTickPosition(); i++) {
          this.currentBeat = i;
        }
        //System.out.println(currentBeat);
      }*/
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void initialize() {

    try {
      this.loadTracks();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    this.play();
  }

  @Override
  public void pause() {
    if (this.sequencer.isRunning()) {
      this.sequencer.stop();
    }
    else {
      if (!this.sequencer.isRunning()) {
        this.sequencer.start();
        this.sequencer.setTempoInMPQ(model.getTempo());
      }
    }
  }

  @Override
  public void rewind() {
    paused = false;
    for (curBeat = curBeat; curBeat > 0; curBeat--) {
      curBeat--;
      List<Note> curNotes = model.getNotesAtTime(curBeat);
      for (Note n : curNotes) {
        try {
          if (!paused) {
            this.playNote(n);
          }
        } catch (InvalidMidiDataException e) {

        }
      }
    }
  }

  @Override
  public void skip() {
    this.sequencer.setMicrosecondPosition(this.sequencer.getMicrosecondLength());
  }

  @Override
  public void next() {
    curBeat++;
    List<Note> curNotes = model.getNotesAtTime(curBeat);
    for (Note n : curNotes) {
      try {
        this.playNote(n);
      }
      catch (InvalidMidiDataException e) {
      }
    }
  }

  @Override
  public void redraw(MusicEditorModel m) {
    try {
      this.loadTracks();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void restart() {
    this.sequencer.setMicrosecondPosition(0);

  }


  @Override
  public void setDisplayPanel(ConcreteGuiViewPanel c) {

  }

  @Override public void removeNote(Note n) {

  }

  /**
   * METHOD FOR TESTING THE MOCK SYNTHESIZER
   * This was the previous method used with Synthesizer and Receiver,
   *
   * @param n the note to be played
   * @throws InvalidMidiDataException
   */
  public void playNoteTest(Note n) throws InvalidMidiDataException {
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, n.getInstrument()-1,
        n.getPitch().getInt() + 12, n.getVolume());
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, n.getInstrument()-1,
        n.getPitch().getInt() + 12, n.getVolume());
    this.receiver.send(start, n.getStartTime() * model.getTempo());
    this.receiver.send(stop, (n.getStartTime() + (n.getDuration())) * model.getTempo());

  }
  // TEST METHOD
  public void playTest() {
    paused = false;
    for (curBeat = curBeat; curBeat < model.getLength(); curBeat++) {
      List<Note> curNotes = model.getNotesAtTime(curBeat);
      for (Note n : curNotes) {
        try {
          if (!paused) {
            this.playNoteTest(n);
          }
        } catch (InvalidMidiDataException ignored) {

        }
      }
    }
    this.receiver.close(); // Only call this once you're done playing *all* notes
  }

  // TEST METHOD
  public void pauseTest() {
    paused = !paused;
  }

}
