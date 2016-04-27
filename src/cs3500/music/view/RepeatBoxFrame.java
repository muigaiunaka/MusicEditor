package cs3500.music.view;

import cs3500.music.model.IRepeatModel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dialog box that pops up on user command to allow for adding Repeats
 */
public class RepeatBoxFrame extends javax.swing.JFrame implements ActionListener {
  JTextField text;
  JButton button;
  IRepeatModel model;
  JTextField repeatAt;
  JTextField repeatTo;
  JPanel panel;
  JTextField repeatCount;
  BasicOptionPaneUI.ButtonActionListener listener;

  public RepeatBoxFrame(IRepeatModel model) {
    super("Set Repeats");
    this.model = model;

    this.panel = new JPanel();
    BoxLayout boxlt = new BoxLayout(this.panel, BoxLayout.Y_AXIS);
    this.panel.setLayout(boxlt);

    panel.setPreferredSize(new Dimension(300, 300));
    // Input fields for the text
    this.repeatAt = new JTextField(10);
    this.repeatTo = new JTextField(10);
    this.repeatCount = new JTextField(10);
    button = new JButton("Enter");
    this.button.addActionListener(this);
    //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    JLabel repeatAtLabel = new JLabel("Repeat At: ");
    panel.add(repeatAtLabel);
    panel.add(repeatAt);

    JLabel repeatToLabel = new JLabel("Repeat To: ");
    panel.add(repeatToLabel);
    panel.add(repeatTo);

    JLabel repeatCountLabel = new JLabel("Repeat Amount: ");
    panel.add(repeatCountLabel);
    panel.add(repeatCount);

    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(button);

    this.setSize(400, 300);
    this.getContentPane().add(panel);
    this.setVisible(false);
    panel.setVisible(true);

  }

  public void setVisible() {
    if (this.isVisible()) {
      this.setVisible(false);
    } else {
      this.setVisible(true);
    }
  }

  public void addRepeat() {
    int at = Integer.parseInt(repeatAt.getText());
    int to = Integer.parseInt(repeatTo.getText());
    int count = Integer.parseInt(repeatCount.getText());
    if (to > at) {
      throw new IllegalArgumentException("That's not right");
    }
    this.model.setRepeat(at, to, count);
    System.out.println("Repeat At: " + repeatAt.getText());
    System.out.println("Repeat To: " + repeatTo.getText());
    System.out.println("Repeat Count: " + repeatCount.getText());
  }


  @Override public void actionPerformed(ActionEvent e) {
    try {
      this.addRepeat();
      this.setVisible();
    } catch (IllegalArgumentException e1) {
      e1.printStackTrace();
    }


  }
}
