package cs3500.music.controller;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;

//Handler for meta events


public class MetaHandler implements MetaEventListener {

  public MetaHandler(){

  }
  @Override public void meta(MetaMessage msg) {
    if (msg.getLength() > 0) {
      System.out.println("Meta Status: " + msg.getStatus()
          + ", \nMeta Type: " + msg.getType()
          + ", \nMeta Length" + msg.getLength()
          + ", \nMeta Data: " + msg.getData());
    }
  }
}
