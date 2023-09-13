package Login;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.GapContent;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MembershipB extends JFrame {
   JPasswordField pwT,pwD;
   JTextField nameT, idT, tel2T, tel3T, emailT, emailadductionT;
   private JLabel nameL, idL, pwL, phoneL, ageL, ageYearL, ageMonthL, ageDayL, emailL, emailadductionL, emailcomL;;
   public JButton joinB, calneB, emeilokB, idoverlapB, emailB, addressB, pwA;
   public JLabel pwC;
   
   public JTextField addressA;
   public JTextField addressC;
   public JTextField addressD;
   private JLabel lblNewLabel;
   private JLabel lblNewLabel_1;
   JComboBox<String> telC, emailC, ageYearC, ageMonthC, ageDayC;

   public MembershipB() {
      setTitle("회원가입");
      String[] tel = { "010", "02", "031", "032", "033", "041", "042", "043", "044", "051", "052", "053", "054",
            "055", "061", "062", "063", "064" };
      String[] email = { "naver.com", "google.com" };
      String[] ageYear = { "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990",
            "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003",
            "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016",
            "2017", "2018", "2019" };
      String[] ageMonth = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
      String[] ageDay = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };

      telC = new JComboBox<String>(tel);
      emailC = new JComboBox<String>(email);
      ageYearC = new JComboBox<String>(ageYear);
      ageMonthC = new JComboBox<String>(ageMonth);
      ageDayC = new JComboBox<String>(ageDay);

      nameL = new JLabel("이름");
      nameL.setBounds(308, 13, 54, 15);
      nameL.setEnabled(false);
      nameT = new JTextField(15);
      nameT.setBounds(374, 10, 171, 21);
      JPanel p1 = new JPanel();
      p1.setLayout(null);
      p1.add(nameL);
      p1.add(nameT);

      idL = new JLabel("아이디");
      idL.setBounds(353, 36, 63, 15);
      idT = new JTextField(15);
      idT.setBounds(418, 33, 183, 21);
      idoverlapB = new JButton("중복확인");
      idoverlapB.setBounds(613, 33, 87, 23);
      JPanel p2 = new JPanel();
      p2.add(idL);
      p2.add(idT);
      p2.add(idoverlapB);

      pwL = new JLabel("비밀번호");
      pwL.setBounds(242, 9, 65, 15);
      pwT = new JPasswordField(15);
      pwT.setBounds(334, 6, 171, 21);
      pwT.setEchoChar('*');
      JPanel p3 = new JPanel();
      p3.setLayout(null);
      p3.add(pwL);
      p3.add(pwT);

      ageL = new JLabel("생년월일");
      ageL.setBounds(393, 33, 73, 15);
      ageYearL = new JLabel("년도");
      ageYearL.setBounds(471, 33, 43, 15);
      ageMonthL = new JLabel("월");
      ageMonthL.setBounds(519, 33, 31, 15);
      ageDayL = new JLabel("일");
      ageDayL.setBounds(562, 33, 36, 15);
      JPanel p4 = new JPanel();
      
      p4.add(ageL);
      p4.add(ageYearC);
      p4.add(ageYearL);
      p4.add(ageMonthC);
      p4.add(ageMonthL);
      p4.add(ageDayC);
      p4.add(ageDayL);

      phoneL = new JLabel("전화번호");
      phoneL.setBounds(352, 28, 68, 29);
      tel2T = new JTextField(5);
      tel2T.setBounds(422, 32, 61, 21);
      tel3T = new JTextField(5);
      tel3T.setBounds(495, 32, 61, 21);
      JPanel p5 = new JPanel();
      
      p5.add(phoneL);
      p5.add(telC);
      p5.add(tel2T);
      p5.add(tel3T);

      emailL = new JLabel("e-mail");
      emailL.setBounds(374, 39, 37, 15);
      emailT = new JTextField(10);
      emailT.setBounds(423, 36, 116, 21);
      emailcomL = new JLabel("@");
      emailcomL.setBounds(545, 39, 12, 15);
      emailB = new JButton("전송");
      emailB.setBounds(563, 35, 57, 23);
      JPanel p6 = new JPanel();
      p6.add(emailL);
      p6.add(emailT);
      p6.add(emailcomL);
      p6.add(emailC);
      p6.add(emailB);

      emailadductionL = new JLabel("e-mail 인증번호");
      emailadductionL.setBounds(327, 45, 89, 15);
      emailadductionT = new JTextField(10);
      emailadductionT.setBounds(428, 42, 116, 21);
      emeilokB = new JButton("인증확인");
      emeilokB.setBounds(556, 41, 81, 23);
      JPanel p7 = new JPanel();
      p7.add(emailadductionL);
      p7.add(emailadductionT);
      p7.add(emeilokB);

      joinB = new JButton("가입");
      calneB = new JButton("취소");
      JPanel p8 = new JPanel();
      p8.add(joinB);
      p8.add(calneB);

      JPanel p = new JPanel(new GridLayout(8, 1));
      p.add(p1);
      p.add(p2);
      p.add(p3);
      
      pwC = new JLabel("비밀번호 확인");
      pwC.setBounds(226, 50, 81, 15);
      p3.add(pwC);
      
      pwD = new JPasswordField(15);
      pwD.setEchoChar('*');
      pwD.setBounds(334, 47, 171, 21);
      p3.add(pwD);
      
      pwA = new JButton("확인");
      pwA.setBounds(559, 46, 81, 23);
      p3.add(pwA);
      p.add(p4);
      p.add(p5);
      p.add(p6);
      p.add(p7);
      p.add(p8);
      GridBagLayout gbl_p8 = new GridBagLayout();
      gbl_p8.columnWidths = new int[]{328, 0, 24, 111, 116, 57, 0};
      gbl_p8.rowHeights = new int[]{23, 0, 0, 0};
      gbl_p8.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
      gbl_p8.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
      p8.setLayout(gbl_p8);
      
      JLabel address = new JLabel("우편번호");
      address.setBounds(384, 41, 31, 15);
      GridBagConstraints gbc_address = new GridBagConstraints();
      gbc_address.anchor = GridBagConstraints.WEST;
      gbc_address.insets = new Insets(0, 0, 5, 5);
      gbc_address.gridx = 2;
      gbc_address.gridy = 0;
      p8.add(address, gbc_address);
      
      addressA = new JTextField(10);
      addressA.setEditable(false);
      addressA.setBounds(427, 38, 116, 21);
      GridBagConstraints gbc_addressA = new GridBagConstraints();
      gbc_addressA.fill = GridBagConstraints.HORIZONTAL;
      gbc_addressA.insets = new Insets(0, 0, 5, 5);
      gbc_addressA.gridx = 3;
      gbc_addressA.gridy = 0;
      p8.add(addressA, gbc_addressA);
      
      addressB = new JButton("찾기");
      addressB.setBounds(555, 37, 57, 23);
      GridBagConstraints gbc_addressB = new GridBagConstraints();
      gbc_addressB.fill = GridBagConstraints.HORIZONTAL;
      gbc_addressB.insets = new Insets(0, 0, 5, 5);
      gbc_addressB.anchor = GridBagConstraints.NORTH;
      gbc_addressB.gridx = 4;
      gbc_addressB.gridy = 0;
      p8.add(addressB, gbc_addressB);
      
      lblNewLabel = new JLabel("도로명 주소");
      GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
      gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
      gbc_lblNewLabel.anchor = GridBagConstraints.NORTHEAST;
      gbc_lblNewLabel.gridx = 2;
      gbc_lblNewLabel.gridy = 1;
      p8.add(lblNewLabel, gbc_lblNewLabel);
      
      addressC = new JTextField();
      addressC.setEditable(false);
      GridBagConstraints gbc_addressC = new GridBagConstraints();
      gbc_addressC.gridwidth = 2;
      gbc_addressC.fill = GridBagConstraints.HORIZONTAL;
      gbc_addressC.insets = new Insets(0, 0, 5, 5);
      gbc_addressC.gridx = 3;
      gbc_addressC.gridy = 1;
      p8.add(addressC, gbc_addressC);
      addressC.setColumns(10);
      
      lblNewLabel_1 = new JLabel("상세주소");
      GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
      gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
      gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
      gbc_lblNewLabel_1.gridx = 2;
      gbc_lblNewLabel_1.gridy = 2;
      p8.add(lblNewLabel_1, gbc_lblNewLabel_1);
      
      addressD = new JTextField(10);
      GridBagConstraints gbc_addressD = new GridBagConstraints();
      gbc_addressD.gridwidth = 2;
      gbc_addressD.insets = new Insets(0, 0, 0, 5);
      gbc_addressD.fill = GridBagConstraints.HORIZONTAL;
      gbc_addressD.gridx = 3;
      gbc_addressD.gridy = 2;
      p8.add(addressD, gbc_addressD);

      JPanel joinp = new JPanel();
      joinp.add(joinB);
      joinp.add(calneB);

      Container contentPane = this.getContentPane();
      contentPane.add("Center", p);
      contentPane.add("South", joinp);

//      setVisible(true);
      setResizable(false);
      setBounds(400, 200, 1000, 800);
//      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

   }
}