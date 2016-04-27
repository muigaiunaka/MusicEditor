package cs3500.music.model;

import cs3500.music.util.CompositionBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class MusicEditorModelImpl implements MusicEditorModel<Note> {

  private int length;
  private Pitch lowest; // lowest pitch default: E3
  private Pitch highest; // highest pitch default: G4
  private List<Pitch> pitches;
  private TreeMap<Integer, List<Note>> song;
  private int tempo;

  public MusicEditorModelImpl() {

    this.lowest = new Pitch(Tone.E, 3);
    this.highest = new Pitch(Tone.G, 4);
    // range is 16, PitchToInt(highest) - PitchToInt(lowest)
    this.pitches = new ArrayList<Pitch>();
    for ( int i = lowest.getInt(); i < highest.getInt() + 1; i++){
      pitches.add(new Pitch(i));
    }
    this.song = new TreeMap<>();
    this.length = 16;
    for (int i = 0; i < this.length; i++) {
      if (this.song.get(i) == null) {
        this.song.put(i, new ArrayList<>());
      }
    }
    this.tempo = 300000;
  }

  private MusicEditorModelImpl(int tempo) {
    this.lowest = new Pitch(Tone.E, 3);
    this.highest = new Pitch(Tone.G, 4);
    // range is 16, PitchToInt(highest) - PitchToInt(lowest)
    this.pitches = new ArrayList<Pitch>();
    for ( int i = lowest.getInt(); i < highest.getInt() + 1; i++){
      pitches.add(new Pitch(i));
    }
    this.song = new TreeMap<>();
    this.length = 16;
    for (int i = 0; i < this.length; i++) {
      if (this.song.get(i) == null) {
        this.song.put(i, new ArrayList<>());
      }
    }

    this.tempo = tempo;
  }

  //do this one
  @Override
  public void addNote(Note note) {
    int beat = note.getStartTime();
    int newLength = note.getStartTime() + note.getDuration();

    setLength(newLength);

    for (int i = 0; i < this.length; i++) {
      if (this.song.get(i) == null) {
        this.song.put(i, new ArrayList<>());
      }
    }
    setHighest(note);
    setLowest(note);

    song.get(beat).add(note);
  }

  /**
   * Lengthens the piece of music
   * @param newLength
   */
  private void setLength(int newLength) {
    if (newLength > length) {
      this.length = newLength;
    }
  }

  /**
   * Extends the range of the piece by lowering the lower bound
   * @param note
   */
  private void setLowest(Note note) {
    if (note.getPitch().getInt() < lowest.getInt()){
      Pitch prevLow = lowest;
      lowest = note.getPitch() ;
      for (int i = lowest.getInt(); i < prevLow.getInt(); i++) {
        pitches.add(new Pitch(i));
      }
    }
  }

  /**
   * Extends the range of the piece by increasing the upper bound
   * @param note
   */
  private void setHighest(Note note) {
    if (note.getPitch().getInt() > highest.getInt()) {
      Pitch prevHigh = highest;
      highest = note.getPitch();

      for (int i = prevHigh.getInt() + 1; i < highest.getInt() + 1; i++) {
        pitches.add(new Pitch(i));
      }
    }
  }

  @Override public void removeNote(Note note) {
    if (!this.containsNote(note)) {
      throw new IllegalArgumentException("Cannot remove note, it does not exist in this song!");
    }
    if (this.containsNote(note)) {
      int beat = note.getStartTime();
      song.get(beat).remove(note);
    }

  }

  @Override public void editNote(Note note) {

  }

  @Override public int getHighestBeat() {
      for (List<Note> notes : this.song.values() ) {
        for (Note n: notes) {
          if (length < (n.getStartTime() + n.getDuration() )) {
            length = (n.getStartTime() + n.getDuration());
          }
        }
      }
      return length;
  }

  @Override
  public String showMusic() {
    int range = (highest.getInt()-lowest.getInt()) + 1;
    List<Note> notes = getAllNotes();
    ArrayList<String> pitchStrings = new ArrayList<String>();
    for (Pitch p: pitches){
        pitchStrings.add(p.toString());
    }
    int padding = (int)Math.ceil(Math.log10(length));
    StringBuilder sheet = new StringBuilder();
    for (int i = 0; i < padding; i++) {
      sheet.append(" ");
    }

    for (String p : pitchStrings) {
      sheet.append(p);
    }
    sheet.append("\n");

    StringBuilder lineLength = new StringBuilder("");
    for (int j = 0; j < range*5+1; j++) {
      lineLength.append(" ");
    }
      lineLength.append("\n");

    for (int i = 0; i < length; i++) {
      sheet.append(String.format("%1$" + padding + "d", i));
      sheet.append(lineLength);
    }
    for (Note n : notes){

      int loc = ((n.getStartTime()+1)*(range*5) + (n.getStartTime()*(padding + 2))
              + (pitchStrings.indexOf(n.getPitch().toString()) + 1) * 5 + padding -1) +
              (padding -1);
      sheet.replace(loc, loc+1, "X");

      for (int i = 1; i < n.getDuration() && i < length-n.getStartTime(); i++){
        loc = loc + range * 5 + padding + 2;
        sheet.replace(loc, loc+1, "|");
      }
    }
    return sheet.toString();
  }

  @Override public void playSimultaneously(MusicEditorModel<Note> notes) {
    List<Note> newNotes = notes.getAllNotes();
    for (Note n : newNotes) {
      this.song.get(n.getStartTime()).add(n);
    }

  }

  @Override public void playConsecutively(MusicEditorModel<Note> notes) {
    List<Note> newNotes = notes.getAllNotes();
    int thatLength = notes.getLength();
    for (Note n : newNotes) {
      Note newNote = new Note(n.getPitch(), n.getStartTime() + thatLength,
              n.getDuration(), n.getInstrument());
      this.addNote(newNote);
      //this.song.get(n.getStartTime()).add(newNote);
    }

  }

  @Override
  public List<Note> getNotesStartingAt(int time) {
    List<Note> notesAtT = new ArrayList<>();

    for (Note n: this.song.get(time)) {
      notesAtT.add(n);
    }
    return notesAtT;
  }

  @Override
  public List<Note> getAllNotes() {
    List<Note> allNotes = new ArrayList<>();
    for (List<Note> notes : this.song.values()) {
      for (Note n : notes) {
        allNotes.add(n);
      }
    }
    return allNotes;
  }

  @Override public List<Note> getNotesAtTime(int time) {
    List<Note> allNotes = this.getAllNotes();
    List<Note> notesAtTime = new ArrayList<>();
    int i;

    for (Note n: allNotes) {
      for (i = n.getStartTime(); i < n.getStartTime() + n.getDuration(); i++) {
        if (i == time) {
          notesAtTime.add(n);
        }
      }
    }
    return notesAtTime;
  }

  @Override public Note getLowestNote() {
    List<Note> allNotes = this.getAllNotes();
    Note temp = new Note(lowest, 3, 4, 1);

    for (Note n : allNotes) {
      if (n.getPitch().compareTo(temp.getPitch()) < 0) {
        temp = n;
      }
    }
    return temp;
  }

  @Override public Note getHighestNote() {
    Note temp = new Note(highest, 0, 8, 2);
    List<Note> allNotes = this.getAllNotes();

    for (Note n : allNotes) {
      if (n.getPitch().compareTo(temp.getPitch()) > 0) {
        temp = n;
      }
    }
    return temp;
  }

  @Override public List<Integer> getBeatsFor(Note n) {
    List<Integer> beatsPlaying = new ArrayList<>();
    int i;

    for (i = n.getStartTime(); i < n.getStartTime() + n.getDuration(); i++) {
      beatsPlaying.add(i);
    }
    return beatsPlaying;
  }
  @Override public int getLength() {
    return this.length;
  }

  @Override public List<Pitch> getPitches() {return this.pitches;}

  @Override public int getTempo() { return this.tempo; }

  /**
   * Determines if this music piece contains this specific note
   *
   * @param note the note that music piece may or may not contain
   * @return true if the note is in the music editor
   */
  public boolean containsNote(Note note) {

    boolean containsNote = false;

    for (List<Note> notes : this.song.values()) {
      for (Note n: notes) {
        if (note.equals(n) ) {
          containsNote = true;
        }
      }
    }
    return containsNote;
  }

  /**
   * Builds a {@link MusicEditorModelImpl}, allowing the client to configure several
   * parameters. This is an instance of the <em>builder pattern</em>.
   */
  public static final class Builder implements CompositionBuilder<MusicEditorModel> {

    private int tempo;
    private List<Note> notes = new ArrayList<>();


    @Override public MusicEditorModel build() {
      MusicEditorModel<Note> model = new MusicEditorModelImpl(tempo);
      for (Note n: notes) {
        model.addNote(n);
      }
      return model;

    }

    @Override public CompositionBuilder<MusicEditorModel> setTempo(int tempo) {
      if (tempo < 1) {
        throw new IllegalArgumentException("Tempo must be atleast 1");
      }
      this.tempo = tempo;
      return this;
    }

    @Override
    public CompositionBuilder<MusicEditorModel> addNote(int start, int end, int instrument,
        int pitch, int volume) {
      Note n = new Note(new Pitch(pitch-12), start, end-start, instrument, volume);
      notes.add(n);
      return this;

    }
  }
}
