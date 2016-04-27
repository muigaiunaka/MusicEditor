package cs3500.music.tests;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * Mock Midi Device Receiver that emulates the default MIDI Receiver
 */
public class MockReceiver implements Receiver {

  StringBuilder builder;
  public MockReceiver(StringBuilder builder) {
    this.builder = builder;
  }

  @Override public void send(MidiMessage message, long timeStamp) {
    ShortMessage msg = (ShortMessage) message;
    builder.append("note Status: ");
    if (msg.getCommand() == 144) {
      builder.append("ON  ");
    } else if (msg.getCommand() == 128) {
      builder.append("OFF ");
    }
    builder.append(" Instrument: ");
    builder.append(msg.getChannel()+1);
    builder.append(" Pitch: ");
    builder.append(msg.getData1());
    builder.append(" Volume: ");
    builder.append(msg.getData2());
    builder.append(" Time stamp: ");
    builder.append(timeStamp);
    builder.append("\n");

  }

  @Override public void close() {

  }
}
