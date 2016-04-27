package cs3500.music.util;



import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A helper to read music data and construct a music composition from it.
 */
public class MusicReader {
  /**
   * A factory for producing new music compositions, given a source of music and a
   * builder for constructing compositions.
   *
   * <p>
   *   The input file format consists of two types of lines:
   *   <ul>
   *     <li>Tempo lines: the keyword "tempo" followed by a number,
   *      describing the tempo in microseconds per beat</li>
   *     <li>Note lines: the keyword "note" followed by five numbers indicating
   *      the start and end times of the note, the instrument,
   *      the pitch, and the volume</li>
   *   </ul>
   * </p>
   * @param readable The source of data for the music composition
   * @param piece A builder for helping to construct a new composition
   * @param <T> The main model interface type describing music compositions
   * @return
   */
  public static <T> T parseFile(Readable readable, CompositionBuilder<T> piece) {
    Scanner scanner = new Scanner(readable);
    while (scanner.hasNext()) {
      String lineType = scanner.next();
      switch (lineType) {
        case "tempo":
          try {
            piece.setTempo(scanner.nextInt());
          } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Malformed tempo line: " + scanner.nextLine());
          }
          break;
        case "note":
          try {
            int startBeat = scanner.nextInt();
            int endBeat = scanner.nextInt();
            int instrument = scanner.nextInt();
            int pitch = scanner.nextInt();
            int volume = scanner.nextInt();
            piece.addNote(startBeat, endBeat, instrument, pitch, volume);
          } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Malformed note line: " + scanner.nextLine());
          }
          break;
        default:
          throw new IllegalArgumentException("Bad line type: " + lineType);
      }
    }

    return piece.build();
  }
}
