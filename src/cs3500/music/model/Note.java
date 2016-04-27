package cs3500.music.model;

/**
 * Represents an implementation of a single note of music that contains a pitch, octave,
 * start time and end time (duration) and a instrument to play it. Subclass of AbstractNote
 */
public class Note implements Comparable<Note> {
  /**
   * CLASS INVARIANTS:
   *
   * startTime cannot be < 0
   * duration cannot be < 1
   */
  private Pitch pitch;
  private int startTime;
  private int duration;
  private int instrument;
  private int volume;

  /**
   * Constructs a Note with a given pitch, octave, duration and instrument to play it
   *
   * @param pitch the pitch of the Note
   * @param startTime the beat that this note starts on
   * @param duration the duration of this note
   * @param instrument the instrument playing this note
   */
  public Note(Pitch pitch, int startTime, int duration, int instrument, int volume) {

    if (startTime < 0) {
      throw new IllegalArgumentException("Invalid Start Time for the beat");
    }
    if (duration < 1) {
      throw new IllegalArgumentException("Duration must be longer than 1 beat!");
    }
    this.pitch = pitch;
    this.startTime = startTime;
    this.duration = duration;
    this.instrument = instrument;
    this.volume = volume;
  }

  public Note(Pitch pitch, int startTime, int duration, int instrument) {

    if (startTime < 0) {
      throw new IllegalArgumentException("Invalid Start Time for the beat");
    }
    if (duration < 1) {
      throw new IllegalArgumentException("Duration must be longer than 1 beat!");
    }
    this.pitch = pitch;
    this.startTime = startTime;
    this.duration = duration;
    this.instrument = instrument;
    this.volume = 100;
  }

  public Note(Pitch pitch, int startTime){
    if (startTime < 0) {
      throw new IllegalArgumentException("Invalid Start Time for the beat");
    }

    this.duration = 2;
    if (duration < 1) {
      throw new IllegalArgumentException("Duration must be longer than 1 beat!");
    }
    this.pitch = pitch;
    this.startTime = startTime;
    this.instrument = 2;
    this.volume = 100;
  }
  /**
   * increases the pitch of this note
   */
  public void higher() {
    pitch.higher();
  }

  /**
   * decreases the pitch of this note
   */
  public void lower() {
    pitch.lower();
  }

  /**
   * Getter method for the duration of this note
   * @return the duration
   */
  public int getDuration() {
    return this.duration;
  }

  /**
   * Getter method for the instrument of this method
   * @return the instrument as an int
   */
  public int getInstrument() {
    return instrument;
  }

  /**
   * Getter method for the pitch of this note
   * @return the pitch of the note
   */
  public Pitch getPitch() {
    return pitch;
  }

  /**
   * Getter method for the start beat of this note
   * @return the start time of this beat
   */
  public int getStartTime() {
    return startTime;
  }

  /**
   * Setter method for the duration of a note
   * @param duration the duration to set the note's duration
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }

  /**
   * Setter method for the instrument of a note
   * @param instrument the instrument to set the instrument of the note
   */
  public void setInstrument(int instrument) {
    this.instrument = instrument;
  }

  /**
   * Setter method for the Pitch of a note
   * @param pitch the pitch to set the note's pitch to
   */
  public void setPitch(Pitch pitch) {
    this.pitch = pitch;
  }

  /**
   * Setter method for the start time of a note
   * @param startTime the time to set the start of the note
   */
  public void setStartTime(int startTime) {

    this.startTime = startTime;
  }

  /**
   * gets the volume of this note
   * @return volume
     */
  public int getVolume() {

    return volume;
  }

  @Override public int compareTo(Note that) {
    if (that != null) {
      return this.pitch.getInt() - that.getPitch().getInt();
    }
    else throw new IllegalArgumentException("Given Object is not an instance of Note.");
  }
}
