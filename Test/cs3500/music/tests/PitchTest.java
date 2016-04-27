package cs3500.music.tests;

import cs3500.music.model.Pitch;
import cs3500.music.model.Tone;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@code Pitch} class
 */
public class PitchTest {
  Pitch C0 = new Pitch(0);
  Pitch cZero = new Pitch(Tone.C, 0);
  Pitch CSharp3 = new Pitch(37);
  Pitch cSharpThree = new Pitch(Tone.CSHARP, 3);

  @Test public void constructorTest() {
    assertEquals(C0, cZero);
    assertEquals(CSharp3, cSharpThree);
  }

  @Test(expected = IllegalArgumentException.class) public void constructorTest2() {
    Pitch D100 = new Pitch(Tone.D, 100);
    D100.getInt();
  }

  @Test(expected = IllegalArgumentException.class) public void constructorTest3() {
    Pitch D100 = new Pitch(1200);
    D100.getTone();
  }

  @Test(expected = IllegalArgumentException.class) public void constructorTest4() {
    Pitch D100 = new Pitch(Tone.D, -100);
    D100.getInt();
  }

  @Test(expected = IllegalArgumentException.class) public void constructorTest5() {
    Pitch D100 = new Pitch(-109);
    D100.getTone();
  }

  @Test public void testToString() throws Exception {
    assertEquals(C0.toString(), "  C0 ");
    assertEquals(CSharp3.toString(), " C#3 ");
  }

  @Test public void testHigher() throws Exception {
    C0.higher();
    assertEquals(C0, new Pitch(1));
  }

  @Test // TODO ISSUE WITH -1 Mod 12, ask about this!!!
  public void testLower() throws Exception {
    //C0.lower();
    Pitch CN1 = new Pitch(-1);
    CN1.lower();
    assertEquals(CN1, new Pitch(-2));
  }

  @Test public void testGetInt() throws Exception {
    assertEquals(CSharp3.getInt(), 37);
  }

  @Test public void testGetTone() throws Exception {
    assertEquals(CSharp3.getTone(), Tone.CSHARP);
  }


  @Test public void testEquals() throws Exception {
    boolean isEquals = CSharp3.equals(new Pitch(37));
    assertTrue(isEquals);
    boolean isNotEquals = (new Pitch(Tone.E, 5).equals(new Pitch(Tone.E, 6)));
    assertFalse(isNotEquals);
  }

  @Test public void testCompareTo() throws Exception {
    int greater = (new Pitch(Tone.G, 3).compareTo(new Pitch(Tone.C, 3)));
    int lower = (new Pitch(Tone.A, 2).compareTo(new Pitch(Tone.B, 8)));
    int equals = (new Pitch(42).compareTo(new Pitch(42)));

    assertEquals(greater, 1);
    assertEquals(lower, -1);
    assertEquals(equals, 0);

  }
}
