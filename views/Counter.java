package views;
import javax.swing.*;
import java.awt.event.*;

public class Counter extends JFrame implements ActionListener {

  private JLabel timeLabel;
  private long startTime;

  public Counter() {
    super("Counter");
    setLayout(null);

    startTime = System.currentTimeMillis();

    timeLabel = new JLabel("00:00:00");
    timeLabel.setBounds(50, 50, 100, 30);
    add(timeLabel);

    Timer timer = new Timer(1000, this); // Update every second
    timer.start();

    setSize(200, 150);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    long currentTime = System.currentTimeMillis();
    long elapsedTime = currentTime - startTime;

    int hours = (int) ((elapsedTime / (1000 * 60 * 60)) % 24);
    int minutes = (int) ((elapsedTime / (1000 * 60)) % 60);
    int seconds = (int) ((elapsedTime / 1000) % 60);

    String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
    timeLabel.setText(formattedTime);
  }

  public static void main(String[] args) {
    new Counter();
  }
}
