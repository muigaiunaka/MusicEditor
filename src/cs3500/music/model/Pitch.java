package cs3500.music.model;

import java.util.Objects;

/**
 * Created by Will on 2/25/16.
 */
public class Pitch implements Comparable<Pitch> {

  Tone t;
  int octave;

  public Pitch(Tone t, int octave) {
    this.t = t;
    if (octave > 99 || octave < -9){
      throw new IllegalArgumentException("that's not even remotely audible");
    }
    this.octave = octave;
  }
  public Pitch(int num){
    if (num > 1188 || num < -108){
      throw new IllegalArgumentException("that's not even remotely audible");
    }
    this.t = Tone.values()[Math.floorMod(num,12)];
    this.octave = num/12;
  }

  /**
   * creates a string containing the letter and the octave, if the octave is
   * one digit it adds a space
   * @return the Pitch as a string
   */
  public String toString() {
    if (octave < 10 && octave >=0) {
      return t.toString() + octave + " ";
    }
    else {
      return t.toString() + octave;
    }
  }


  /**
   * increases the pitch by one letter, when going from B to C it also increase the octave
   */
  public void higher(){
    this.t = this.t.getNext();
    if (this.t == Tone.C){
      if (this.octave >= 99){
        this.t = this.t.getPrev();
      }
      else {
        this.octave++;
      }
    }
  }

  /**
   * decreases the pitch by one letter, when going from C to B it also decreases the octave
   */
  public void lower() {
    this.t = this.t.getPrev();
    if (this.t == Tone.B) {
      if (this.octave <= -9) {
        this.t = this.t.getNext();
      } else {
        this.octave--;
      }
    }
  }

  /**
   * get an integer value of the pitch
   * @return pitch int
     */
  public int getInt(){

    return octave * 12 + t.ordinal();
  }

  /**
   * get the tone of the pitch
   * @return tone
     */
  public Tone getTone() {
    return t;
  }

  @Override
  public boolean equals(Object that){
    if (that instanceof Pitch){
      return (this.t == ((Pitch) that).t && this.octave == ((Pitch) that).octave);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(t, octave);
  }

  @Override
  public int compareTo(Pitch p) {
    if (octave < p.octave){
      return -1;
    }
    if (octave > p.octave){
      return 1;
    }
    else {
      if(t.ordinal() < p.t.ordinal()) {
        return -1;
      }
      if (t.ordinal() > p.t.ordinal()){
        return 1;
      }
      return 0;
    }
  }
}
