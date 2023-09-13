package CoControl;

import java.awt.EventQueue;



import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class CoprocessFrame extends JFrame  {
   public JButton sendB;
   public JButton exitB,colorbtn, Filebtn,probtn, emobtn;
   private JPanel contentPane;
   public JTextField field;
   public JTextPane area1;
   public JTextArea partList;
   public JComboBox combocolor;
  
   public JLabel img;
   
  
   /**
    * Launch the application.
    */


   /**
    * Create the frame.
    */
   public CoprocessFrame() {
      ImageIcon i = new ImageIcon("./src/Login/profil.jpg");
      Image im=i.getImage();
      partList = new JTextArea();
      setBounds(100, 100, 450, 557);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      JPanel p1 = new JPanel();
      p1.setBounds(0, 0, 434, 518);
      contentPane.add(p1);
      p1.setLayout(null);
      
      field = new JTextField();
      field.setText("");
      field.setBounds(12, 431, 274, 77);
      p1.add(field);
      field.setColumns(10);
      
      sendB = new JButton("보내기");
      sendB.setFont(new Font("함초롬바탕", Font.BOLD, 15));
      sendB.setBounds(298, 431, 124, 49);
      p1.add(sendB);
      
      JPanel p2 = new JPanel();
      p2.setBounds(12, 85, 410, 339);
      p1.add(p2);
      GridBagLayout gbl_p2 = new GridBagLayout();
      gbl_p2.columnWidths = new int[]{0, 0};
      gbl_p2.rowHeights = new int[]{0, 0};
      gbl_p2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
      gbl_p2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
      p2.setLayout(gbl_p2);
      
      JScrollPane scrollPane = new JScrollPane();
      GridBagConstraints gbc_scrollPane = new GridBagConstraints();
      gbc_scrollPane.fill = GridBagConstraints.BOTH;
      gbc_scrollPane.gridx = 0;
      gbc_scrollPane.gridy = 0;
      p2.add(scrollPane, gbc_scrollPane);
      
      area1 = new JTextPane();
      scrollPane.setViewportView(area1);
      
      exitB = new JButton("나가기");
      exitB.setFont(new Font("굴림", Font.PLAIN, 12));
      exitB.setBounds(325, 22, 97, 23);
      p1.add(exitB);
      
      img = new JLabel("");
      Image img1 = i.getImage();
       Image resize = img1.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
       ImageIcon resizedicon = new ImageIcon(resize);
       img.setIcon(resizedicon);
      p1.add(img);
      img.setBounds(32, 10, 85, 77);
      p1.add(img);
      
      colorbtn = new JButton("\uC0C9\uC0C1");
      colorbtn.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      	}
      });
      colorbtn.setBounds(331, 52, 91, 23);
      p1.add(colorbtn);
      
    
      combocolor = new JComboBox();
      combocolor.setModel(new DefaultComboBoxModel(new String[] {"\uAE00\uC790", "\uBC30\uACBD"}));
      combocolor.setBounds(259, 52, 60, 23);
      p1.add(combocolor);
      
      Filebtn = new JButton("\uD30C\uC77C");
      Filebtn.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		
      	}
      });
      Filebtn.setBounds(298, 485, 124, 23);
      p1.add(Filebtn);
      
      probtn = new JButton("\uD504\uB85C\uD544");
      probtn.setBounds(129, 52, 74, 23);
      p1.add(probtn);
      
      emobtn = new JButton("emoji");
      emobtn.setBounds(239, 22, 74, 23);
      p1.add(emobtn);
      setVisible(false);
   }
   public void seticon(String path)
   {
      ImageIcon Picon = new ImageIcon(path);
      Image img2 = Picon.getImage();
      Image resize = img2.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
      ImageIcon resizedicon = new ImageIcon(resize);
      img.setIcon(resizedicon);
   }
}
