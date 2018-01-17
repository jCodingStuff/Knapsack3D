import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame
{
  private static final int FRAME_WIDTH = 520;
  private static final int FRAME_HEIGHT = 180;
  private JLabel label;
  private JTextField parcelA;
  private JTextField parcelB;
  private JTextField parcelC;
  private JTextField pent1;
  private JTextField pent2;
  private JTextField pent3;
  private JLabel A;
  private JLabel B;
  private JLabel C;
  private JLabel L;
  private JLabel P;
  private JLabel T;
  private JLabel maximize1;
  private JLabel maximize2;
  private JComboBox box1;
  private JComboBox box2;
  private JButton button;
  private ActionListener listener;

  public Frame()
  {
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
    setTitle("Cargo Filling");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    getContentPane().setBackground(Color.WHITE);
    createPanel();
    setVisible(true);
  }

  public void createPanel()
  {
    label = new JLabel("Cargo Filling");
    Font font = new Font("Times New Roman", Font.BOLD,20);
    label.setFont(font);
    label.setHorizontalAlignment(JLabel.CENTER);
    A = new JLabel("Parcel A");
    parcelA = new JTextField(5);
    B = new JLabel("Parcel B");
    parcelB = new JTextField(5);
    C = new JLabel("Parcel C");
    parcelC = new JTextField(5);
    L = new JLabel("L");
    pent1 = new JTextField(5);
    P = new JLabel("P");
    pent2 = new JTextField(5);
    T = new JLabel("T");
    pent3 = new JTextField(5);
    maximize1 = new JLabel("Maximize: ");
    JPanel box1 = createComboBox1();
    box1.setBackground(Color.WHITE);
    maximize2 = new JLabel("Maximize: ");
    JPanel box2 = createComboBox2();
    box2.setBackground(Color.WHITE);
    button = new JButton("Fill");
    button.setBackground(Color.WHITE);
  //  ActionListener listener = new ButtonListener();
  //  button.addActionListener(listener);

  add(label, BorderLayout.NORTH);

  JPanel panel = new JPanel();
  panel.setBackground(Color.WHITE);
  panel.add(A);
  panel.add(parcelA);
  panel.add(B);
  panel.add(parcelB);
  panel.add(C);
  panel.add(parcelC);
  panel.add(maximize1);
  panel.add(box1);
  panel.add(L);
  panel.add(pent1);
  panel.add(P);
  panel.add(pent2);
  panel.add(T);
  panel.add(pent3);
  panel.add(maximize2);
  panel.add(box2);

  add(panel, BorderLayout.CENTER);
  add(button, BorderLayout.SOUTH);

  }

  public JPanel createComboBox1()
  {
    box1 = new JComboBox();
    box1.addItem("Volume");
    box1.addItem("Value");
    JPanel panel = new JPanel();
    panel.add(box1);
    return panel;
  }
  public JPanel createComboBox2()
    {
      box2 = new JComboBox();
      box2.addItem("Volume");
      box2.addItem("Value");
      JPanel panel = new JPanel();
      panel.add(box2);
      return panel;
    }
    public static void main(String[] args)
    {
      Frame main = new Frame();
    }

}
