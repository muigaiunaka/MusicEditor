package cs3500.music.model;

/**
 * Represents a collection of 12 distinct tones from the Western system of music
 */
public enum Tone {
  C("  C"), CSHARP(" C#"), D("  D"), DSHARP(" D#"), E("  E"), F("  F"), FSHARP(" F#"),
  G("  G"), GSHARP(" G#"), A("  A"), ASHARP(" A#"), B("  B");
  private String note;

  Tone(String note) {

    this.note = note;
  }

  @Override
  public String toString(){

    return this.note;
  }

  /**
   * get the ordinal value of the note
   * @return int ordinal
     */
  int getToneValue(){

    return this.ordinal();
  }

  /**
   * get the letter value of the tone from the given pitch
   * @param pitch
   * @return string tone
     */
  String getToneLetter(int pitch) {
    int toneL = pitch % 12;
    switch (toneL) {
      case 0 : return Tone.C.toString();
      case 1 : return Tone.CSHARP.toString();
      case 2 : return Tone.D.toString();
      case 3 : return Tone.DSHARP.toString();
      case 4 : return Tone.E.toString();
      case 5 : return Tone.F.toString();
      case 6 : return Tone.FSHARP.toString();
      case 7 : return Tone.G.toString();
      case 8 : return Tone.GSHARP.toString();
      case 9 : return Tone.A.toString();
      case 10 : return Tone.ASHARP.toString();
      case 11 : return Tone.B.toString();
      default : throw new IllegalArgumentException("Invalid Tone");
    }
  }

  /**
   * returns the next letter in the enum and wraps if at the end
   * @return the next letter
   */
  Tone getNext() {
    int num = this.ordinal()+1;

    int index = (Math.floorMod(num, this.values().length));
    return this.values()[index];
  }

  /**
   * returns the previous letter in the enum and wraps if at the beginning
   * @return the previous letter
   */
  Tone getPrev() {
    int num = this.ordinal()-1;

    int index = (Math.floorMod(num, this.values().length));
    return this.values()[index];
  }


}
