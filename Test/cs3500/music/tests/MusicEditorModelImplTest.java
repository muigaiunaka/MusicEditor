package cs3500.music.tests;

import cs3500.music.MusicEditor;
import cs3500.music.model.*;
import cs3500.music.model.MusicEditorModelImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by Will on 3/18/16.
 */
public class MusicEditorModelImplTest {

  MusicEditorModel<Note> consoleModel = new MusicEditorModelImpl();
  MusicEditorModel<Note> testModel = new MusicEditorModelImpl();

  Pitch E3 = new Pitch(Tone.E, 3);
  Pitch G3 = new Pitch(Tone.G, 3);
  Pitch C4 = new Pitch(Tone.C, 4);
  Pitch D4 = new Pitch(Tone.D, 4);
  Pitch E4 = new Pitch(Tone.E, 4);
  Pitch G4 = new Pitch(Tone.G, 4);

  // ASSIGNMENT CONSOLE RENDERING
  Note console1 = new Note(E3, 56, 8, 10);
  Note console2 = new Note(G3, 0, 7, 10);
  Note console3 = new Note(G3, 8, 7, 10);
  Note console4 = new Note(G3, 16, 5, 10);
  Note console5 =new Note(G3, 24, 2, 10);
  Note console6 = new Note(G3, 32, 8, 10);
  Note console7 = new Note(G3, 40, 8, 10);
  Note console8 = new Note(G3, 48, 8, 10);
  Note console9 = new Note(C4, 4, 2, 10);
  Note console10 = new Note(C4, 36, 2, 10);
  Note console11 = new Note(C4, 56, 8, 10);
  Note console12 = new Note(D4, 2, 2, 10);
  Note console13 = new Note(D4, 6, 2, 10);
  Note console14 = new Note(D4, 16, 2, 10);
  Note console15 = new Note(D4, 18, 2, 10);
  Note console16 =new Note(D4, 20, 4, 10);
  Note console17 = new Note(D4, 34, 2, 10);
  Note console18 = new Note(D4, 38, 2, 10);
  Note console19 = new Note(D4, 48, 2, 10);
  Note console20 = new Note(D4, 50, 2, 10);
  Note console21 = new Note(D4, 54, 2, 10);
  Note console22 = new Note(E4, 0, 2, 10);
  Note console23 = new Note(E4, 8, 2, 10);
  Note console24 = new Note(E4, 10, 2, 10);
  Note console25 = new Note(E4, 12, 2, 10);
  Note console26 = new Note(E4, 24, 2, 10);
  Note console27 = new Note(E4, 32, 2, 10);
  Note console28 = new Note(E4, 40, 2, 10);
  Note console29 = new Note(E4, 42, 2, 10);
  Note console30 = new Note(E4, 44, 2, 10);
  Note console31 = new Note(E4, 46, 2, 10);
  Note console32 = new Note(E4, 52, 2, 10);
  Note console33 = new Note(G4, 26, 2, 10);
  Note console34 = new Note(G4, 28, 4, 10);

  @Test
  public void testAddNote() throws Exception {
    testModel.addNote(console1);
    testModel.addNote(console2);
    testModel.addNote(console3);
    List<Note> testNotes = testModel.getAllNotes();

    assertTrue(testNotes.contains(console2));
    assertTrue(testNotes.contains(console3));
    assertFalse(testNotes.contains(console7));
  }

  @Test
  public void testRemoveNote() throws Exception {
    testModel.addNote(console1);
    testModel.addNote(console2);
    testModel.addNote(console3);
    testModel.removeNote(console2);
    List<Note> testNotes = testModel.getAllNotes();

    assertTrue(!testNotes.contains(console2));
  }

  @Test
  public void testEditNote() throws Exception {

  }

  @Test
  public void testGetHighestBeat() throws Exception {
    MusicEditorModel<Note> model = new MusicEditorModelImpl();
    assertEquals(model.getHighestBeat(), 16);

  }

  @Test
  public void testShowMusicDefaultConstructor() throws Exception {
    MusicEditorModel<Note> model = new MusicEditorModelImpl();

    assertEquals(model.showMusic(),
        "    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   F4  F#4   G4 \n"
            + " 0                                                                            "
            + "     \n"
            + " 1                                                                            "
            + "     \n"
            + " 2                                                                            "
            + "     \n"
            + " 3                                                                            "
            + "     \n"
            + " 4                                                                            "
            + "     \n"
            + " 5                                                                            "
            + "     \n"
            + " 6                                                                            "
            + "     \n"
            + " 7                                                                            "
            + "     \n"
            + " 8                                                                            "
            + "     \n"
            + " 9                                                                            "
            + "     \n"
            + "10                                                                            "
            + "     \n"
            + "11                                                                            "
            + "     \n"
            + "12                                                                            "
            + "     \n"
            + "13                                                                            "
            + "     \n"
            + "14                                                                            "
            + "     \n"
            + "15                                                                            "
            + "     \n");


  }

  @Test
  public void testShowMusic2() {
    StringBuilder pieceState = new StringBuilder();
    pieceState.append(
        "    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   F4  F#4   G4 \n"
            + " 0                 X                                            X                 "
            + " \n"
            + " 1                 |                                            |                 "
            + " \n"
            + " 2                 |                                  X                           "
            + " \n"
            + " 3                 |                                  |                           "
            + " \n"
            + " 4                 |                        X                                     "
            + " \n"
            + " 5                 |                        |                                     "
            + " \n"
            + " 6                 |                                  X                           "
            + " \n"
            + " 7                                                    |                           "
            + " \n"
            + " 8                 X                                            X                 "
            + " \n"
            + " 9                 |                                            |                 "
            + " \n"
            + "10                 |                                            X                 "
            + " \n"
            + "11                 |                                            |                 "
            + " \n"
            + "12                 |                                            X                 "
            + " \n"
            + "13                 |                                            |                 "
            + " \n"
            + "14                 |                                                              "
            + " \n"
            + "15                                                                                "
            + " \n"
            + "16                 X                                  X                           "
            + " \n"
            + "17                 |                                  |                           "
            + " \n"
            + "18                 |                                  X                           "
            + " \n"
            + "19                 |                                  |                           "
            + " \n"
            + "20                 |                                  X                           "
            + " \n"
            + "21                                                    |                           "
            + " \n"
            + "22                                                    |                           "
            + " \n"
            + "23                                                    |                           "
            + " \n"
            + "24                 X                                            X                 "
            + " \n"
            + "25                 |                                            |                 "
            + " \n"
            + "26                                                                             X  "
            + " \n"
            + "27                                                                             |  "
            + " \n"
            + "28                                                                             X  "
            + " \n"
            + "29                                                                             |  "
            + " \n"
            + "30                                                                             |  "
            + " \n"
            + "31                                                                             |  "
            + " \n"
            + "32                 X                                            X                 "
            + " \n"
            + "33                 |                                            |                 "
            + " \n"
            + "34                 |                                  X                           "
            + " \n"
            + "35                 |                                  |                           "
            + " \n"
            + "36                 |                        X                                     "
            + " \n"
            + "37                 |                        |                                     "
            + " \n"
            + "38                 |                                  X                           "
            + " \n"
            + "39                 |                                  |                           "
            + " \n"
            + "40                 X                                            X                 "
            + " \n"
            + "41                 |                                            |                 "
            + " \n"
            + "42                 |                                            X                 "
            + " \n"
            + "43                 |                                            |                 "
            + " \n"
            + "44                 |                                            X                 "
            + " \n"
            + "45                 |                                            |                 "
            + " \n"
            + "46                 |                                            X                 "
            + " \n"
            + "47                 |                                            |                 "
            + " \n"
            + "48                 X                                  X                           "
            + " \n"
            + "49                 |                                  |                           "
            + " \n"
            + "50                 |                                  X                           "
            + " \n"
            + "51                 |                                  |                           "
            + " \n"
            + "52                 |                                            X                 "
            + " \n"
            + "53                 |                                            |                 "
            + " \n"
            + "54                 |                                  X                           "
            + " \n"
            + "55                 |                                  |                           "
            + " \n"
            + "56  X                                       X                                     "
            + " \n"
            + "57  |                                       |                                     "
            + " \n"
            + "58  |                                       |                                     "
            + " \n"
            + "59  |                                       |                                     "
            + " \n"
            + "60  |                                       |                                     "
            + " \n"
            + "61  |                                       |                                     "
            + " \n"
            + "62  |                                       |                                     "
            + " \n"
            + "63  |                                       |                                      "
            + "\n");

    consoleModel.addNote(console1);
    consoleModel.addNote(console2);
    consoleModel.addNote(console3);
    consoleModel.addNote(console4);
    consoleModel.addNote(console5);
    consoleModel.addNote(console6);
    consoleModel.addNote(console7);
    consoleModel.addNote(console8);
    consoleModel.addNote(console9);
    consoleModel.addNote(console10);
    consoleModel.addNote(console11);
    consoleModel.addNote(console12);
    consoleModel.addNote(console13);
    consoleModel.addNote(console14);
    consoleModel.addNote(console15);
    consoleModel.addNote(console16);
    consoleModel.addNote(console17);
    consoleModel.addNote(console18);
    consoleModel.addNote(console19);
    consoleModel.addNote(console20);
    consoleModel.addNote(console21);
    consoleModel.addNote(console22);
    consoleModel.addNote(console23);
    consoleModel.addNote(console24);
    consoleModel.addNote(console25);
    consoleModel.addNote(console26);
    consoleModel.addNote(console27);
    consoleModel.addNote(console28);
    consoleModel.addNote(console29);
    consoleModel.addNote(console30);
    consoleModel.addNote(console31);
    consoleModel.addNote(console32);
    consoleModel.addNote(console33);
    consoleModel.addNote(console34);

    assertEquals(consoleModel.showMusic(), pieceState.toString());

  }

  @Test
  public void testPlaySimultaneously() {
    testModel.addNote(console1);
    testModel.addNote(console2);
    testModel.addNote(console3);
    testModel.addNote(console4);
    testModel.addNote(console5);
    testModel.addNote(console6);
    testModel.addNote(console7);
    testModel.addNote(console8);
    testModel.addNote(console9);

    consoleModel.addNote(console1);
    consoleModel.addNote(console2);
    consoleModel.addNote(console3);
    consoleModel.addNote(console4);
    consoleModel.addNote(console5);
    consoleModel.addNote(console6);
    consoleModel.addNote(console7);
    consoleModel.addNote(console8);
    consoleModel.addNote(console9);

    testModel.playSimultaneously(consoleModel);

    StringBuilder consec = new StringBuilder();
    consec.append(
        "    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   F4  F#4   G4 \n"
            + " 0                 X                                                              "
            + " \n"
            + " 1                 |                                                              "
            + " \n"
            + " 2                 |                                                              "
            + " \n"
            + " 3                 |                                                              "
            + " \n"
            + " 4                 |                        X                                     "
            + " \n"
            + " 5                 |                        |                                     "
            + " \n"
            + " 6                 |                                                              "
            + " \n"
            + " 7                                                                                "
            + " \n"
            + " 8                 X                                                              "
            + " \n"
            + " 9                 |                                                              "
            + " \n"
            + "10                 |                                                              "
            + " \n"
            + "11                 |                                                              "
            + " \n"
            + "12                 |                                                              "
            + " \n"
            + "13                 |                                                              "
            + " \n"
            + "14                 |                                                              "
            + " \n"
            + "15                                                                                "
            + " \n"
            + "16                 X                                                              "
            + " \n"
            + "17                 |                                                              "
            + " \n"
            + "18                 |                                                              "
            + " \n"
            + "19                 |                                                              "
            + " \n"
            + "20                 |                                                              "
            + " \n"
            + "21                                                                                "
            + " \n"
            + "22                                                                                "
            + " \n"
            + "23                                                                                "
            + " \n"
            + "24                 X                                                              "
            + " \n"
            + "25                 |                                                              "
            + " \n"
            + "26                                                                                "
            + " \n"
            + "27                                                                                "
            + " \n"
            + "28                                                                                "
            + " \n"
            + "29                                                                                "
            + " \n"
            + "30                                                                                "
            + " \n"
            + "31                                                                                "
            + " \n"
            + "32                 X                                                              "
            + " \n"
            + "33                 |                                                              "
            + " \n"
            + "34                 |                                                              "
            + " \n"
            + "35                 |                                                              "
            + " \n"
            + "36                 |                                                              "
            + " \n"
            + "37                 |                                                              "
            + " \n"
            + "38                 |                                                              "
            + " \n"
            + "39                 |                                                              "
            + " \n"
            + "40                 X                                                              "
            + " \n"
            + "41                 |                                                              "
            + " \n"
            + "42                 |                                                              "
            + " \n"
            + "43                 |                                                              "
            + " \n"
            + "44                 |                                                              "
            + " \n"
            + "45                 |                                                              "
            + " \n"
            + "46                 |                                                              "
            + " \n"
            + "47                 |                                                              "
            + " \n"
            + "48                 X                                                              "
            + " \n"
            + "49                 |                                                              "
            + " \n"
            + "50                 |                                                              "
            + " \n"
            + "51                 |                                                              "
            + " \n"
            + "52                 |                                                              "
            + " \n"
            + "53                 |                                                              "
            + " \n"
            + "54                 |                                                              "
            + " \n"
            + "55                 |                                                              "
            + " \n"
            + "56  X                                                                             "
            + " \n"
            + "57  |                                                                             "
            + " \n"
            + "58  |                                                                             "
            + " \n"
            + "59  |                                                                             "
            + " \n"
            + "60  |                                                                             "
            + " \n"
            + "61  |                                                                             "
            + " \n"
            + "62  |                                                                             "
            + " \n"
            + "63  |                                                                              "
            + "\n");

    assertEquals(testModel.showMusic(), consec.toString());
  }

  @Test
  public void testPlayConsecutively() {
    testModel.addNote(console1);
    testModel.addNote(console2);
    testModel.addNote(console3);
    testModel.addNote(console4);
    testModel.addNote(console5);
    testModel.addNote(console6);
    testModel.addNote(console7);
    testModel.addNote(console8);
    testModel.addNote(console9);
    testModel.addNote(console10);
    testModel.addNote(console11);
    testModel.addNote(console12);
    testModel.addNote(console13);
    testModel.addNote(console14);
    testModel.addNote(console15);
    testModel.addNote(console16);
    testModel.addNote(console17);
    testModel.addNote(console18);
    testModel.addNote(console19);
    testModel.addNote(console20);
    testModel.addNote(console21);
    testModel.addNote(console22);
    testModel.addNote(console23);
    testModel.addNote(console24);
    testModel.addNote(console25);
    testModel.addNote(console26);
    testModel.addNote(console27);
    testModel.addNote(console28);
    testModel.addNote(console29);
    testModel.addNote(console30);
    testModel.addNote(console31);
    testModel.addNote(console32);
    testModel.addNote(console33);
    testModel.addNote(console34);

    consoleModel.addNote(console1);
    consoleModel.addNote(console2);
    consoleModel.addNote(console3);
    consoleModel.addNote(console4);
    consoleModel.addNote(console5);
    consoleModel.addNote(console6);
    consoleModel.addNote(console7);
    consoleModel.addNote(console8);
    consoleModel.addNote(console9);
    consoleModel.addNote(console10);
    consoleModel.addNote(console11);
    consoleModel.addNote(console12);
    consoleModel.addNote(console13);
    consoleModel.addNote(console14);
    consoleModel.addNote(console15);
    consoleModel.addNote(console16);
    consoleModel.addNote(console17);
    consoleModel.addNote(console18);
    consoleModel.addNote(console19);
    consoleModel.addNote(console20);
    consoleModel.addNote(console21);
    consoleModel.addNote(console22);
    consoleModel.addNote(console23);
    consoleModel.addNote(console24);
    consoleModel.addNote(console25);
    consoleModel.addNote(console26);
    consoleModel.addNote(console27);
    consoleModel.addNote(console28);
    consoleModel.addNote(console29);
    consoleModel.addNote(console30);
    consoleModel.addNote(console31);
    consoleModel.addNote(console32);
    consoleModel.addNote(console33);
    consoleModel.addNote(console34);

    testModel.playConsecutively(consoleModel);

    StringBuilder consec = new StringBuilder();
    consec.append(
        "     E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   F4  F#4   G4 \n"
            + "  0                 X                                            X                "
            + "  \n"
            + "  1                 |                                            |                "
            + "  \n"
            + "  2                 |                                  X                          "
            + "  \n"
            + "  3                 |                                  |                          "
            + "  \n"
            + "  4                 |                        X                                    "
            + "  \n"
            + "  5                 |                        |                                    "
            + "  \n"
            + "  6                 |                                  X                          "
            + "  \n"
            + "  7                                                    |                          "
            + "  \n"
            + "  8                 X                                            X                "
            + "  \n"
            + "  9                 |                                            |                "
            + "  \n"
            + " 10                 |                                            X                "
            + "  \n"
            + " 11                 |                                            |                "
            + "  \n"
            + " 12                 |                                            X                "
            + "  \n"
            + " 13                 |                                            |                "
            + "  \n"
            + " 14                 |                                                             "
            + "  \n"
            + " 15                                                                               "
            + "  \n"
            + " 16                 X                                  X                          "
            + "  \n"
            + " 17                 |                                  |                          "
            + "  \n"
            + " 18                 |                                  X                          "
            + "  \n"
            + " 19                 |                                  |                          "
            + "  \n"
            + " 20                 |                                  X                          "
            + "  \n"
            + " 21                                                    |                          "
            + "  \n"
            + " 22                                                    |                          "
            + "  \n"
            + " 23                                                    |                          "
            + "  \n"
            + " 24                 X                                            X                "
            + "  \n"
            + " 25                 |                                            |                "
            + "  \n"
            + " 26                                                                             X "
            + "  \n"
            + " 27                                                                             | "
            + "  \n"
            + " 28                                                                             X "
            + "  \n"
            + " 29                                                                             | "
            + "  \n"
            + " 30                                                                             | "
            + "  \n"
            + " 31                                                                             | "
            + "  \n"
            + " 32                 X                                            X                "
            + "  \n"
            + " 33                 |                                            |                "
            + "  \n"
            + " 34                 |                                  X                          "
            + "  \n"
            + " 35                 |                                  |                          "
            + "  \n"
            + " 36                 |                        X                                    "
            + "  \n"
            + " 37                 |                        |                                    "
            + "  \n"
            + " 38                 |                                  X                          "
            + "  \n"
            + " 39                 |                                  |                          "
            + "  \n"
            + " 40                 X                                            X                "
            + "  \n"
            + " 41                 |                                            |                "
            + "  \n"
            + " 42                 |                                            X                "
            + "  \n"
            + " 43                 |                                            |                "
            + "  \n"
            + " 44                 |                                            X                "
            + "  \n"
            + " 45                 |                                            |                "
            + "  \n"
            + " 46                 |                                            X                "
            + "  \n"
            + " 47                 |                                            |                "
            + "  \n"
            + " 48                 X                                  X                          "
            + "  \n"
            + " 49                 |                                  |                          "
            + "  \n"
            + " 50                 |                                  X                          "
            + "  \n"
            + " 51                 |                                  |                          "
            + "  \n"
            + " 52                 |                                            X                "
            + "  \n"
            + " 53                 |                                            |                "
            + "  \n"
            + " 54                 |                                  X                          "
            + "  \n"
            + " 55                 |                                  |                          "
            + "  \n"
            + " 56  X                                       X                                    "
            + "  \n"
            + " 57  |                                       |                                    "
            + "  \n"
            + " 58  |                                       |                                    "
            + "  \n"
            + " 59  |                                       |                                    "
            + "  \n"
            + " 60  |                                       |                                    "
            + "  \n"
            + " 61  |                                       |                                    "
            + "  \n"
            + " 62  |                                       |                                    "
            + "  \n"
            + " 63  |                                       |                                    "
            + "  \n"
            + " 64                 X                                            X                "
            + "  \n"
            + " 65                 |                                            |                "
            + "  \n"
            + " 66                 |                                  X                          "
            + "  \n"
            + " 67                 |                                  |                          "
            + "  \n"
            + " 68                 |                        X                                    "
            + "  \n"
            + " 69                 |                        |                                    "
            + "  \n"
            + " 70                 |                                  X                          "
            + "  \n"
            + " 71                                                    |                          "
            + "  \n"
            + " 72                 X                                            X                "
            + "  \n"
            + " 73                 |                                            |                "
            + "  \n"
            + " 74                 |                                            X                "
            + "  \n"
            + " 75                 |                                            |                "
            + "  \n"
            + " 76                 |                                            X                "
            + "  \n"
            + " 77                 |                                            |                "
            + "  \n"
            + " 78                 |                                                             "
            + "  \n"
            + " 79                                                                               "
            + "  \n"
            + " 80                 X                                  X                          "
            + "  \n"
            + " 81                 |                                  |                          "
            + "  \n"
            + " 82                 |                                  X                          "
            + "  \n"
            + " 83                 |                                  |                          "
            + "  \n"
            + " 84                 |                                  X                          "
            + "  \n"
            + " 85                                                    |                          "
            + "  \n"
            + " 86                                                    |                          "
            + "  \n"
            + " 87                                                    |                          "
            + "  \n"
            + " 88                 X                                            X                "
            + "  \n"
            + " 89                 |                                            |                "
            + "  \n"
            + " 90                                                                             X "
            + "  \n"
            + " 91                                                                             | "
            + "  \n"
            + " 92                                                                             X "
            + "  \n"
            + " 93                                                                             | "
            + "  \n"
            + " 94                                                                             | "
            + "  \n"
            + " 95                                                                             | "
            + "  \n"
            + " 96                 X                                            X                "
            + "  \n"
            + " 97                 |                                            |                "
            + "  \n"
            + " 98                 |                                  X                          "
            + "  \n"
            + " 99                 |                                  |                          "
            + "  \n"
            + "100                 |                        X                                    "
            + "  \n"
            + "101                 |                        |                                    "
            + "  \n"
            + "102                 |                                  X                          "
            + "  \n"
            + "103                 |                                  |                          "
            + "  \n"
            + "104                 X                                            X                "
            + "  \n"
            + "105                 |                                            |                "
            + "  \n"
            + "106                 |                                            X                "
            + "  \n"
            + "107                 |                                            |                "
            + "  \n"
            + "108                 |                                            X                "
            + "  \n"
            + "109                 |                                            |                "
            + "  \n"
            + "110                 |                                            X                "
            + "  \n"
            + "111                 |                                            |                "
            + "  \n"
            + "112                 X                                  X                          "
            + "  \n"
            + "113                 |                                  |                          "
            + "  \n"
            + "114                 |                                  X                          "
            + "  \n"
            + "115                 |                                  |                          "
            + "  \n"
            + "116                 |                                            X                "
            + "  \n"
            + "117                 |                                            |                "
            + "  \n"
            + "118                 |                                  X                          "
            + "  \n"
            + "119                 |                                  |                          "
            + "  \n"
            + "120  X                                       X                                    "
            + "  \n"
            + "121  |                                       |                                    "
            + "  \n"
            + "122  |                                       |                                    "
            + "  \n"
            + "123  |                                       |                                    "
            + "  \n"
            + "124  |                                       |                                    "
            + "  \n"
            + "125  |                                       |                                    "
            + "  \n"
            + "126  |                                       |                                    "
            + "  \n"
            + "127  |                                       |                                    "
            + "  \n");

    assertEquals(testModel.showMusic(), consec.toString());
  }

  @Test
  public void testGetNotesStartingAt()  {
    testModel.addNote(console1);
    testModel.addNote(console2);
    testModel.addNote(console3);
    testModel.addNote(console8);
    testModel.addNote(console19);
    testModel.addNote(console20);
    testModel.addNote(console15);

    List<Note> notesAt48 = new ArrayList<>();
    notesAt48.add(console8);
    notesAt48.add(console19);

    assertEquals(notesAt48, testModel.getNotesStartingAt(48));
  }

  @Test
  public void testGetAllNotes() {
    consoleModel.addNote(console1);
    consoleModel.addNote(console2);
    consoleModel.addNote(console3);
    consoleModel.addNote(console4);
    consoleModel.addNote(console5);
    consoleModel.addNote(console6);
    consoleModel.addNote(console7);
    consoleModel.addNote(console8);
    consoleModel.addNote(console9);
    consoleModel.addNote(console10);
    consoleModel.addNote(console11);
    consoleModel.addNote(console12);
    consoleModel.addNote(console13);
    consoleModel.addNote(console14);
    consoleModel.addNote(console15);
    consoleModel.addNote(console16);
    consoleModel.addNote(console17);
    consoleModel.addNote(console18);
    consoleModel.addNote(console19);
    consoleModel.addNote(console20);
    consoleModel.addNote(console21);
    consoleModel.addNote(console22);
    consoleModel.addNote(console23);
    consoleModel.addNote(console24);
    consoleModel.addNote(console25);
    consoleModel.addNote(console26);
    consoleModel.addNote(console27);
    consoleModel.addNote(console28);
    consoleModel.addNote(console29);
    consoleModel.addNote(console30);
    consoleModel.addNote(console31);
    consoleModel.addNote(console32);
    consoleModel.addNote(console33);
    consoleModel.addNote(console34);

    List<Note> newPiece = new ArrayList<>();
    newPiece.add(console1);
    newPiece.add(console2);
    newPiece.add(console3);
    newPiece.add(console4);
    newPiece.add(console5);
    newPiece.add(console6);
    newPiece.add(console7);
    newPiece.add(console8);
    newPiece.add(console9);
    newPiece.add(console10);
    newPiece.add(console11);
    newPiece.add(console12);
    newPiece.add(console13);
    newPiece.add(console14);
    newPiece.add(console15);
    newPiece.add(console16);
    newPiece.add(console17);
    newPiece.add(console18);
    newPiece.add(console19);
    newPiece.add(console20);
    newPiece.add(console21);
    newPiece.add(console22);
    newPiece.add(console23);
    newPiece.add(console24);
    newPiece.add(console25);
    newPiece.add(console26);
    newPiece.add(console27);
    newPiece.add(console28);
    newPiece.add(console29);
    newPiece.add(console30);
    newPiece.add(console31);
    newPiece.add(console32);
    newPiece.add(console33);
    newPiece.add(console34);

    // SORT THE NOTES SO THE ORDERING DOES NOT MATTER
    // SHOWS ALL NOTES CONTAINED ARE THE SAME
    Collections.sort(newPiece);
    List<Note> musicEditorNotesSorted = consoleModel.getAllNotes();
    Collections.sort(musicEditorNotesSorted);

    assertEquals(newPiece, musicEditorNotesSorted);
  }

  @Test
  public void testGetNotesAtTime()  {
    testModel.addNote(new Note(new Pitch(17), 0, 4, 6, 19));
    testModel.addNote(new Note(new Pitch(4), 6, 4, 6));
    testModel.addNote(new Note(new Pitch(99), 3, 7, 4, 22));
    testModel.addNote(new Note(new Pitch(17), 1, 4, 6, 19));
    testModel.addNote(new Note(new Pitch(4), 1, 3, 6));
    testModel.addNote(new Note(new Pitch(99), 3, 7, 4, 22));

    List<Note> notesAtT = new ArrayList<>();
    notesAtT.add(new Note(new Pitch(17), 0, 4, 6, 19));
    notesAtT.add(new Note(new Pitch(17), 1, 4, 6, 19));
    notesAtT.add(new Note(new Pitch(4), 1, 3, 6));

    //assertEquals(testModel.getNotesAtTime(2), notesAtT);
  }

  @Test
  public void testGetLowestNote() {
    testModel.addNote(new Note(new Pitch(17), 0, 4, 6, 19));
    testModel.addNote(new Note(new Pitch(4), 0, 4, 6));
    testModel.addNote(new Note(new Pitch(99), 3, 7, 4, 22));
    assertEquals(testModel.getLowestNote().getPitch(), new Pitch(Tone.E, 0));

  }

  @Test
  public void testGetHighestNote() {
    testModel.addNote(new Note(new Pitch(17), 0, 4, 6, 19));
    testModel.addNote(new Note(new Pitch(4), 0, 4, 6));
    testModel.addNote(new Note(new Pitch(99), 3, 7, 4, 22));
    assertEquals(testModel.getHighestNote().getPitch(), new Pitch(Tone.DSHARP, 8));
  }

  @Test
  public void testGetBeatsFor() throws Exception {

  }
}
