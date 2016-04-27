package cs3500.music.tests;

import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.model.Tone;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

/**
 * Tests for {@code Note} class
 */
public class NoteTest {
  Note G4 = new Note(new Pitch(Tone.G, 4), 26, 2, 10);
  Note E4 = new Note(new Pitch(Tone.E, 4), 0, 1, 10);
  Note F7 = new Note(new Pitch(Tone.F, 7), 5, 4, 10);
  Note FSHARP9 = new Note(new Pitch(Tone.FSHARP, 9), 6, 3, 10);
  Note D3 = new Note(new Pitch(Tone.D, 3), 10, 14, 10);
  Note CSHARP5 = new Note(new Pitch(Tone.CSHARP, 5), 0, 6, 10);
  Note B8 = new Note(new Pitch(Tone.B, 8), 28, 12, 10);

  @Test
  public void testGetPitch() {
    assertTrue(G4.getPitch().equals(new Pitch(Tone.G, 4)));
  }

  @Test
  public void testGetStartTime() {
    assertTrue(F7.getStartTime() == 5);
  }

  @Test
  public void testGetDuration() {
    assertTrue(FSHARP9.getDuration() == 3);
  }

  @Test
  public void testGetInstrument() {
    assertTrue(D3.getInstrument() == 10);
  }

  @Test
  public void testNoteDuration() {
    assertEquals(B8.getDuration(), 12);
  }

  @Test
  public void testEqualsOverridden() {
    assertFalse(B8.equals(CSHARP5));
  }

  @Test public void testHigher() {
    B8.higher();
    assertTrue(B8.getPitch().getTone().equals(Tone.C));
  }

  @Test public void testLower() {
    B8.lower();
    assertTrue(B8.getPitch().equals(new Pitch(Tone.ASHARP, 8)));
  }

  @Test public void testSetDuration() {
    E4.setDuration(20);
    assertEquals(E4.getDuration(), 20);
  }

  @Test public void testSetInstrument() {
    E4.setInstrument(40);
    assertEquals(E4.getInstrument(), 40);
  }

  @Test public void testSetPitch()  {
    E4.setPitch(new Pitch(Tone.F, 10));
    assertEquals(E4.getPitch(), new Pitch(Tone.F, 10));
  }

  @Test
  public void testSetStartTime() {
    E4.setStartTime(4);
    assertEquals(E4.getStartTime(), 4 );
  }

  @Test public void testGetVolume() {
    Note E5 = new Note(new Pitch(Tone.E, 5), 0, 1, 40, 15);
    assertEquals(E5.getVolume(), 15);
  }

  @Test public void testCompareTo() {
    assertTrue(G4.compareTo(B8) < 0);
    assertTrue(CSHARP5.compareTo(E4) > 0);
  }
}
