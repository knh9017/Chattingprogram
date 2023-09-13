package Login;

import java.awt.Container;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import javax.swing.text.Style;

import javax.swing.JTextPane;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import CoControl.Emoticon;

import Action.Protocol;
import CoControl.CoprocessFrame;

import FunctionTest.Email.SendMail;
import Room.RoomFrame;
import Room.RoomMake;
import ZipCode.*;
import Manager.*; 

public class EnterFrame extends JFrame implements ActionListener, Runnable, ListSelectionListener {
   private JPasswordField pwT;
   private JTextField idT;// , pwT;
   private JButton idB, pwB, accessB, searchidB, searchpwB, membershipB, logBtn, mag, colorbtn, Filebtn, emobtn;
   private JLabel loginL, logoutL;
   private ImageIcon loginC, logoutC, modifiedC,loginS;
   private Socket socket;
   private BufferedReader br;
   private PrintWriter pw;
   private SimpleAttributeSet sa;
   private StyledDocument document;
   private JTextPane area1;
   private Color colour=Color.blue;
   private Color ucolor = Color.black;
   private JFileChooser fileC = new JFileChooser();
   private Socket filesock;
   MembershipB menbersShipF; // ȸ������
   SearchidB searchF; // ID ã��
   SearchpwB searchpwF; // PASSWORD ã��
   RoomFrame RoomF; // ����
   RoomMake rMakeF; // �游���
   CoprocessFrame chattingF;
   PostMain post;
   manager_login mlg;
   MenuJTabaleExam mje;
   Emoticon emo;
   
   private String sNumber = "><^^"; // default ��ũ���ѹ�
   private boolean condition_S = false; // �̸��� ����Ȯ��
   private boolean condition_Id = false; // ID �ߺ�üũ
   private boolean condition_sd = false; // ��й�ȣ �ߺ�üũ
   private boolean condition_mg = false;
   private String folderPath="";
   private String idname;
   private JLabel logimage;

   public EnterFrame() {
	      network();

	      menbersShipF = new MembershipB();
	      searchF = new SearchidB();
	      searchpwF = new SearchpwB();
	      RoomF = new RoomFrame(br, pw);
	      rMakeF = new RoomMake();
	      chattingF = new CoprocessFrame();
	      post = new PostMain();
	      mlg = new manager_login();
	      mje = new MenuJTabaleExam();
	      emo = new Emoticon(pw);

	      idB = new JButton("���̵�");
	      idB.setBounds(20, 343, 106, 23);
	      idT = new JTextField(15);
	      idT.setBounds(138, 338, 193, 33);
	      pwB = new JButton("�н�����");
	      pwB.setBounds(20, 386, 106, 23);
	      pwT = new JPasswordField(15);
	      pwT.setBounds(138, 381, 193, 33);
	      pwT.setEchoChar('*');
	     

	      JPanel p2 = new JPanel();
	      p2.setLayout(null);
	      p2.add(idB);
	      p2.add(idT);
	      p2.add(pwB);
	      p2.add(pwT);
	     
	      JPanel p3 = new JPanel();

	      loginC = new ImageIcon("img/�α���.png");
	      loginL = new JLabel(loginC);

	     

	      

	      JPanel p4 = new JPanel();
	      p4.add(loginL);

	      Container contentPane = this.getContentPane();
	      contentPane.add("Center", p2);
	      
	      mag = new JButton("������ ��� �α���");
	      mag.setBounds(22, 519, 362, 23);
	      p2.add(mag);
	      
	            searchidB = new JButton("���̵� ã��");
	            searchidB.setBounds(154, 552, 129, 23);
	            p2.add(searchidB);
	            searchpwB = new JButton("\uBE44\uBC00\uBC88\uD638\uCC3E\uAE30");
	            searchpwB.setBounds(21, 552, 135, 23);
	            p2.add(searchpwB);
	            accessB = new JButton("����");
	            accessB.setBounds(155, 420, 159, 33);
	            p2.add(accessB);
	            membershipB = new JButton("ȸ������");
	            membershipB.setBounds(281, 552, 103, 23);
	            p2.add(membershipB);
	            
	            
	            loginS = new ImageIcon("/img/N.png");
	            logimage = new JLabel(loginS);
	            logimage.setBounds(0, 0, 427, 260);
	            p2.add(logimage);
	            
	       
	     
	      

	      setVisible(true);
	      setResizable(false);
	      setBounds(400, 200, 441, 662);
	      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	      setDefaultCloseOperation(EXIT_ON_CLOSE);
	      event();

	   }

   public void event() {
      // --------------------ȸ�����԰���----------------------------------
      membershipB.addActionListener(this); // ȸ������(��ư)
      menbersShipF.calneB.addActionListener(this); // ȸ������ ���(�α���ȭ������)
      menbersShipF.joinB.addActionListener(this); // ȸ������ ȭ�鿡�� join
      menbersShipF.idoverlapB.addActionListener(this);// ȸ������ ȭ�� �ߺ�Ȯ��
      menbersShipF.emailB.addActionListener(this);// ȸ������ �̸��� ����
      menbersShipF.emeilokB.addActionListener(this); // �̸��� ����Ȯ��
      menbersShipF.addressB.addActionListener(this); //���� �ּҰ˻�
      menbersShipF.pwA.addActionListener(this);
      post.submit.addActionListener(this); 
      // --------------------IDã�����----------------------------------
      searchidB.addActionListener(this); // ���̵� ã��
      searchF.joinB.addActionListener(this); // ���̵�ã�� (join��ư)
      searchF.emailB.addActionListener(this); // ���̵�ã�� (Email ��������)
      searchF.emeilokB.addActionListener(this); // ���̵�ã��(Email ����Ȯ��)
      searchF.cancelB.addActionListener(this); // IDã�� ���

      // --------------------PWã�����----------------------------------
      searchpwB.addActionListener(this); // PW ã��
      searchpwF.cancleB.addActionListener(this); // PWã�� ���

      // --------------------�α��ΰ���----------------------------------
      accessB.addActionListener(this); // ����(Login)
      RoomF.exitB.addActionListener(this); // Room -> �α���Page

      // ---------------------�޼��� ����---------------------------------
      RoomF.sendB.addActionListener(this); // ���濡�� ����

      // ----------------------�� ���� ------------------------------------
      RoomF.makeB.addActionListener(this);
      rMakeF.makeB.addActionListener(this);
      rMakeF.canB.addActionListener(this);
      chattingF.exitB.addActionListener(this);
      chattingF.sendB.addActionListener(this);
      chattingF.Filebtn.addActionListener(this);
      chattingF.probtn.addActionListener(this);
      // ----------------------ä�ù� ���� ---------------------------------
      /*chattingF.openB.addActionListener(this);
      chattingF.saveB.addActionListener(this);
      chattingF.loadB.addActionListener(this);
      chattingF.list2.addListSelectionListener(this);*/
      chattingF.colorbtn.addActionListener(this);
      chattingF.emobtn.addActionListener(this);

      //----------------------������ �α��� ����---------------------------------
      mag.addActionListener(this); //���� �α��� ��ư
      mlg.logBtn.addActionListener(this);
   }

   public void network() {

      // ���� ����
      try {
         socket = new Socket("0.0.0.0", 9500);
         filesock = new Socket("127.0.0.1", 9501);
         br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

      } catch (UnknownHostException e) {
         System.out.println("������ ã�� �� �����ϴ�");
         e.printStackTrace();
         System.exit(0);
      } catch (IOException e) {
         System.out.println("������ ������ �ȵǾ����ϴ�");
         e.printStackTrace();
         System.exit(0);
      }

      // �̺�Ʈ

      // ������ ����
      Thread t = new Thread(this);
      t.start();
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == membershipB) { // ���������� -----------> ȸ�����Թ�ư
         this.setVisible(false);
         menbersShipF.setVisible(true);
      }else if(e.getSource() == chattingF.emobtn) {
    	  emo.setVisible(true);
    	  
      }else if(e.getSource() == chattingF.probtn){     //������ �̹��� ���ε�
          String folderPath="";  
          JFileChooser a = new JFileChooser();
          a.setCurrentDirectory(new File ("C://"));
          a.setAcceptAllFileFilterUsed(true);
          a.setDialogTitle("������ �̹���");
          a.setFileSelectionMode(JFileChooser.FILES_ONLY);
          FileNameExtensionFilter filter = new FileNameExtensionFilter("ImgFIle(png,jpg)", "png","jpg");
          a.setFileFilter(filter);
          int ret=a.showOpenDialog(a);
          if(ret == JFileChooser.APPROVE_OPTION) { // ���⸦ Ŭ�� 
             folderPath = a.getSelectedFile().getAbsolutePath();
             System.out.println(folderPath);
             chattingF.seticon(folderPath);
          }else if(ret == JFileChooser.CANCEL_OPTION){ // ��Ҹ� Ŭ��
             System.out.println("cancel"); 
             folderPath = "";
          }
          

       } 
      else if(e.getSource() == chattingF.Filebtn) {
    	  folderPath="";  
			JFileChooser a = new JFileChooser();
			a.setCurrentDirectory(new File ("C://"));
			a.setAcceptAllFileFilterUsed(true);
			a.setDialogTitle("���� �����ϱ�");
			a.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int ret=a.showOpenDialog(a);
			if(ret == JFileChooser.APPROVE_OPTION) { // ���⸦ Ŭ�� 
				folderPath = a.getSelectedFile().getAbsolutePath();

				
				File fs = new File(folderPath);
				pw.println(Protocol.CHATTINGFILESEND_FILE+ "|" + fs.length() + "|" +  fs.getName() + "|" + idname + "|" + Integer.toString(colour.getRGB()));
				pw.flush();

				OutputStream os;
				try {

					os = filesock.getOutputStream();
					FileInputStream fis = new FileInputStream(fs.getAbsoluteFile());
					// ������ ������ ������
					byte[] b = new byte[2048];
					int n;
					while ((n = fis.read(b, 0, b.length)) > 0) {
						os.write(b, 0, n);
					}
					os.flush();
					fis.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}


			}else if(ret == JFileChooser.CANCEL_OPTION){ // ��Ҹ� Ŭ��
				System.out.println("cancel"); 
				folderPath = "";
			}
			
			
      }else if (e.getSource() == menbersShipF.joinB) { // ȸ������������ -----------> ���Թ�ư

         String name = menbersShipF.nameT.getText();
         String id = menbersShipF.idT.getText();
         String pw1 = menbersShipF.pwT.getText();
         String ageYear = (String) menbersShipF.ageYearC.getSelectedItem();
         String ageMonth = (String) menbersShipF.ageMonthC.getSelectedItem();
         String ageDay = (String) menbersShipF.ageDayC.getSelectedItem();
         String tel = (String) menbersShipF.telC.getSelectedItem();
         String tel2 = menbersShipF.tel2T.getText();
         String tel3 = menbersShipF.tel3T.getText();
         String email = menbersShipF.emailT.getText();
         String email1 = (String) menbersShipF.emailC.getSelectedItem();
         String emailok = menbersShipF.emailadductionT.getText();

         if (name.length() == 0 || id.length() == 0 || pw1.length() == 0 || tel2.length() == 0 || tel3.length() == 0
               || email.length() == 0 || emailok.length() == 0) {
            JOptionPane.showMessageDialog(this, "���� �Է����ּ���");
         } else if (condition_S && condition_Id && condition_sd) { // -> �̸���, �ߺ�Ȯ�� ������ �Ȱ�

            String line = "";
            line += (menbersShipF.idT.getText() + "%" + menbersShipF.pwT.getText() + "%"
                  + menbersShipF.nameT.getText() + "%" + menbersShipF.ageYearC.getSelectedItem()
                  + menbersShipF.ageMonthC.getSelectedItem() + menbersShipF.ageDayC.getSelectedItem() + "%"
                  + menbersShipF.emailT.getText() + "@" + menbersShipF.emailC.getSelectedItem()) + "%"
                  + menbersShipF.telC.getSelectedItem() + "" + menbersShipF.tel2T.getText()
                  + menbersShipF.tel3T.getText() + "%" + menbersShipF.addressA.getText() + "-"
                  + menbersShipF.addressC.getText() + " " + menbersShipF.addressD.getText(); 
            System.out.println(line);

            pw.println(Protocol.REGISTER + "|" + line);
            pw.flush();
            JOptionPane.showMessageDialog(this, "ȸ������ �Ϸ�");
            menbersShipF.setVisible(false);
            this.setVisible(true);

            menbersShipF.nameT.setText("");
            menbersShipF.idT.setText("");
            menbersShipF.pwT.setText("");
            menbersShipF.ageYearC.setSelectedIndex(0);
            menbersShipF.ageMonthC.setSelectedIndex(0);
            menbersShipF.ageDayC.setSelectedIndex(0);
            menbersShipF.telC.setSelectedIndex(0);
            menbersShipF.tel2T.setText("");
            menbersShipF.tel3T.setText("");
            menbersShipF.emailT.setText("");
            menbersShipF.emailC.setSelectedIndex(0);
            menbersShipF.emailadductionT.setText("");
            menbersShipF.addressA.setText("");

            condition_S = false;
            condition_Id = false;
            condition_sd = false;
            sNumber = "><^^";

         } else if (!condition_Id && condition_S) {
            JOptionPane.showMessageDialog(this, "ID �ߺ�Ȯ�� ���ּ���");
         } else if (!condition_S && condition_Id) {
            JOptionPane.showMessageDialog(this, "�̸��� ������ ���ּ���");
         } else if (!condition_Id && !condition_S) {
            JOptionPane.showMessageDialog(this, "ID �ߺ�,�̸��� ������ ���ּ���");
         } else if (!condition_sd) {
             JOptionPane.showMessageDialog(this, "��й�ȣ�� Ȯ�����ּ���");
         }

      } else if (e.getSource() == menbersShipF.calneB) { // ȸ������������ -----------> ���
         menbersShipF.setVisible(false);
         this.setVisible(true);
         condition_S = false;
         sNumber = "><^^";

      } else if (e.getSource() == menbersShipF.idoverlapB) { // ȸ������ ������ID -----------> �ߺ�Ȯ��

         if (menbersShipF.idT.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "���̵� �Է��ϼ���");
         } else {
            pw.println(Protocol.IDSEARCHCHECK + "|" + menbersShipF.idT.getText());
            pw.flush();
         }
         

      }else if (e.getSource()==mlg.logBtn){
        String id = "knh9017";
        String pass = "rlaskrgns1!";
        
         if (id.equals(mlg.txtID.getText()) &&  pass.equals(mlg.txtPass.getText())) {
               JOptionPane.showMessageDialog( null, "ȯ���մϴ� �����ڴ�!" );
               mje.setVisible(true);
            } 
            else {
               mje.setVisible(false);
               JOptionPane.showMessageDialog( null , " �α��ο� �����Ͽ����ϴ�. ");

    }
         }
      else if (e.getSource() == menbersShipF.emailB) // ȸ������ ������ -----------> ������ȣ ����
      {
         if (menbersShipF.emailT.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "�̸��� �Է��ϼ���");
         } else {
            JOptionPane.showMessageDialog(this, "������ȣ�� ���۵Ǿ����ϴ�.");
            String emailString = menbersShipF.emailT.getText() + "@"
                  + (String) menbersShipF.emailC.getSelectedItem();
            System.out.println(emailString);
            sNumber = String.valueOf(SendMail.SendMail(emailString));
         }

      } else if (e.getSource() == menbersShipF.emeilokB) { // ȸ������ ������ -----------> ������ȣȮ��
         if (sNumber.compareTo(menbersShipF.emailadductionT.getText()) == 0) {
            JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�");
            condition_S = true;
         } else {
            JOptionPane.showMessageDialog(this, "������ȣ�� Ʋ�Ƚ��ϴ�");
         }
      }
      else if(e.getSource() == menbersShipF.addressB)
      {
         post.setVisible(true);
      }
      else if(e.getSource() == post.submit) {
         int x = post.table.getSelectedRow();
         menbersShipF.addressA.setText((String)post.table.getValueAt(x, 0));
         menbersShipF.addressC.setText((String)post.table.getValueAt(x, 1));
         post.setVisible(false);
      }
      else if(e.getSource() == menbersShipF.pwA)
      {
         cpd checkpwd = new cpd();
         if(!menbersShipF.pwT.getText().equals(menbersShipF.pwD.getText())) {
            JOptionPane.showMessageDialog(this, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
            condition_sd = false;
         }
         else {
            if(checkpwd.ckpwd(menbersShipF.pwT.getText())) {
               condition_sd = true;
               JOptionPane.showMessageDialog(this, "��й�ȣ�� �����մϴ�.");
            }
            else {
               JOptionPane.showMessageDialog(this, "��й�ȣ�� �������� �ʽ��ϴ�.");
               condition_sd = false;
            }
         }
      }
      else if(e.getSource() == mag) {
         mlg.setVisible(true);
      }
      else if (e.getSource() == searchpwB) { // ���������� -----------> ���ã�� ��ư
         this.setVisible(false);
         searchpwF.setVisible(true);
      } else if (e.getSource() == searchidB) { // ���������� -----------> ���̵� ã��
         this.setVisible(false);
         searchF.setVisible(true);
      } else if (e.getSource() == searchF.joinB) { // ID ã�� -----------> Ȯ��
         String name = searchF.nameT.getText();
         String ageYear = (String) searchF.ageYearC.getSelectedItem();
         String ageMonth = (String) searchF.ageMonthC.getSelectedItem();
         String ageDay = (String) searchF.ageDayC.getSelectedItem();
         String tel2 = searchF.tel2T.getText();
         String tel3 = searchF.tel3T.getText();
         String email = searchF.emailT.getText();
         String email1 = (String) searchF.emailC.getSelectedItem();
         String emailok = searchF.emailadductionT.getText();

         if (name.length() == 0 || tel2.length() == 0 || tel3.length() == 0 || email.length() == 0
               || emailok.length() == 0) {
            JOptionPane.showMessageDialog(this, "��ĭ�� �Է����ּ���");
         } else if (condition_S) {
            String line = "";

            line += (searchF.nameT.getText() + "%" + searchF.ageYearC.getSelectedItem()
                  + searchF.ageMonthC.getSelectedItem() + searchF.ageDayC.getSelectedItem() + "%"
                  + searchF.emailT.getText() + "@" + searchF.emailC.getSelectedItem()) + "%"
                  + searchF.telC.getSelectedItem() + "" + searchF.tel2T.getText() + searchF.tel3T.getText();
            System.out.println(line);

            pw.println(Protocol.IDSEARCH + "|" + line);
            pw.flush();

            searchF.nameT.setText("");
            searchF.ageYearC.setSelectedIndex(0);
            searchF.ageMonthC.setSelectedIndex(0);
            searchF.ageDayC.setSelectedIndex(0);
            searchF.telC.setSelectedIndex(0);
            searchF.tel2T.setText("");
            searchF.tel3T.setText("");
            searchF.emailT.setText("");
            searchF.emailC.setSelectedIndex(0);
            searchF.emailadductionT.setText("");
            searchF.emailadductionT.setText("");
            condition_S = false;
            sNumber = "><^^";
         } else if (!condition_S) {
            JOptionPane.showMessageDialog(this, "�̸��� ������ ���ּ���");
         }

      } else if (e.getSource() == searchF.emeilokB) // IDã�������� -----------> ������ȣ Ȯ��
      {
         if (sNumber.compareTo(searchF.emailadductionT.getText()) == 0) {
            JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�");
            condition_S = true;
         } else {
            JOptionPane.showMessageDialog(this, "������ȣ�� Ʋ�Ƚ��ϴ�");
         }
      } else if (e.getSource() == searchF.emailB) // IDã�� ������ -----------> ������ȣ ����
      {
         if (searchF.emailT.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "�̸��� �Է��ϼ���");
         } else {
            JOptionPane.showMessageDialog(this, "������ȣ�� ���۵Ǿ����ϴ�.");
            String emailString = searchF.emailT.getText() + "@" + (String) searchF.emailC.getSelectedItem();
            System.out.println(emailString);
            sNumber = String.valueOf(SendMail.SendMail(emailString));
         }
      } else if (e.getSource() == searchF.cancelB) { // IDã�������� -----------> IDã�� ���

         searchF.setVisible(false);
         this.setVisible(true);
         searchF.nameT.setText("");
         searchF.ageYearC.setSelectedIndex(0);
         searchF.ageMonthC.setSelectedIndex(0);
         searchF.ageDayC.setSelectedIndex(0);
         searchF.telC.setSelectedIndex(0);
         searchF.tel2T.setText("");
         searchF.tel3T.setText("");
         searchF.emailT.setText("");
         searchF.emailC.setSelectedIndex(0);
         searchF.emailadductionT.setText("");
         searchF.emailadductionT.setText("");
         condition_S = false;
         sNumber = "><^^";

      } else if (e.getSource() == accessB) { // ���������� --> ���� (Login)

         String id = idT.getText();
         String pwss = pwT.getText();

         if (id.length() == 0 || pwss.length() == 0) {
            JOptionPane.showMessageDialog(this, "��ĭ�� �Է����ּ���");
         } else {
            String line = id + "%" + pwss;
            pw.println(Protocol.ENTERLOGIN + "|" + line);
            pw.flush();
            idname = id;
         }
         idT.setText("");
         pwT.setText("");

      } else if (e.getSource() == searchpwF.cancleB) { // PWã�������� -->PW ã�� ���
         searchpwF.setVisible(false);
         this.setVisible(true);
      } else if (e.getSource() == RoomF.exitB) { // ���� -> �α���Page (�α׾ƿ�)

         RoomF.setVisible(false);
         this.setVisible(true);

         pw.println(Protocol.EXITWAITROOM + "|" + "message");
         pw.flush();

      } else if (e.getSource() == RoomF.sendB) // ���� ������ -----------> MESSAGE ����
      {
         String line = RoomF.chattxt.getText();
         if (RoomF.chattxt.getText().length() != 0) {
            pw.println(Protocol.SENDMESSAGE + "|" + line);
            pw.flush();
            RoomF.chattxt.setText("");
         }

      } else if (e.getSource() == RoomF.makeB) {
         RoomF.setVisible(false);
         rMakeF.setVisible(true);
      } else if (e.getSource() == rMakeF.makeB) { // �游��� ������ -----> �游��� ��ư
         String title = rMakeF.tf.getText();
         String rPassword = rMakeF.pf.getText();
         String userCount = (String) rMakeF.combo1.getSelectedItem();
         String subject = (String) rMakeF.combo.getSelectedItem();
         int condition = rMakeF.cb.isSelected() ? 1 : 0;

         if (title.length() == 0) {
            JOptionPane.showMessageDialog(this, "������ �Է����ּ���");
         } else {
            if (condition == 1 && rPassword.length() == 0) // PW�� ���ٰ��߳��� �ȳ�����
            {
               JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է����ּ���");
            } else if (condition == 1 && rPassword.length() != 0) {// PW�� ���ٰ��߳��� �������

               String line = "";
               line += (title + "%" + rPassword + "%" + userCount + "%" + subject + "%" + condition);
               pw.println(Protocol.ROOMMAKE + "|" + line);
               pw.flush();

               rMakeF.setVisible(false);
               RoomF.setVisible(true);

               rMakeF.tf.setText("");
               rMakeF.pf.setText("");
               rMakeF.combo1.setSelectedIndex(0);
               rMakeF.combo.setSelectedIndex(0);
               rMakeF.cb.setSelected(false);

            } else if (condition == 0 && rPassword.length() != 0) {
               JOptionPane.showMessageDialog(this, "��й�ȣ ����� �������ּ���.");
            } else if (condition == 0) // ������
            {
               String line = "";
               line += (title + "%" + userCount + "%" + subject + "%" + condition);
               pw.println(Protocol.ROOMMAKE + "|" + line);
               pw.flush();

               rMakeF.setVisible(false);
               RoomF.setVisible(false);
               rMakeF.tf.setText("");
               rMakeF.pf.setText("");
               rMakeF.combo1.setSelectedIndex(0);
               rMakeF.combo.setSelectedIndex(0);
               rMakeF.cb.setSelected(false);
            }

         }

      } else if (e.getSource() == rMakeF.canB) { // �游��������� ----> ��ҹ�ư
         rMakeF.setVisible(false);
         RoomF.setVisible(true);
         rMakeF.tf.setText("");
         rMakeF.pf.setText("");
         rMakeF.combo1.setSelectedIndex(0);
         rMakeF.combo.setSelectedIndex(0);
         rMakeF.cb.setSelected(false);
      } else if (e.getSource() == chattingF.exitB) { // ä�ù濡�� ������ ��ư

         chattingF.setVisible(false);
         RoomF.setVisible(true);
         //chattingF.model.removeAllElements();

         pw.println(Protocol.EXITCHATTINGROOM + "|" + "Message");
         pw.flush();

         //chattingF.partList.setText("asd");

      } else if (e.getSource() == chattingF.sendB) {
         pw.println(Protocol.CHATTINGSENDMESSAGE + "|" + chattingF.field.getText()+ "|"+Integer.toString(colour.getRGB())); // �޼����� ����
         pw.flush();
         chattingF.field.setText("");
      }else if (e.getSource() == chattingF.colorbtn) {
    	
    	  String fieldName = chattingF.combocolor.getSelectedItem().toString();
    	  JColorChooser palette = new JColorChooser();
    	  colour = palette.showDialog(null, "Palette", Color.white);
    	  
    	  if (fieldName.trim().equals("����")) {
    		
    	  }else if(fieldName.trim().equals("���")){
    		 chattingF.area1.setBackground(colour); 
    	  }
    	  
      }
      }/*else if (e.getSource() == chattingF.openB) // ä�ù濡�� ------> ������ ���� ����
      }
      {
         chattingF.openDialog();
         chattingF.fileRead();

      } else if (e.getSource() == chattingF.saveB) // ä�ù濡�� ------> ������ ��������
      {
         chattingF.fileSave();
         chattingF.fileWrite();
//         listUpload();
         chattingF.openB.setEnabled(true);
         chattingF.saveB.setEnabled(true);
         chattingF.loadB.setEnabled(true);
         chattingF.deleteB.setEnabled(false);
         chattingF.exitB.setEnabled(true);

      }  else if (e.getSource() == chattingF.loadB) {
         chattingF.openDialog();
         pw.println(Protocol.CHATTINGFILESEND_SYN + "|" + chattingF.file.getName());
         pw.flush();
      }*/
      
   

   /*@Override
   public void valueChanged(ListSelectionEvent arg0) {
      System.out.println("Listlistioner");
      for (int i = 0; i < chattingF.model.getSize(); i++) {
         if (chattingF.list2.isSelectedIndex(i)) {
            chattingF.fileSave();
            pw.println(Protocol.CHATTINGFILEDOWNLOAD_SYN + "|" + chattingF.list2.getSelectedValue());
            pw.flush();
         }
      }
   }*/

   @Override
   public void run() {
      // �޴���
      String line[] = null;
      while (true) {
         try {
            line = br.readLine().split("\\|");
            if (line == null) {
               br.close();
               pw.close();
               socket.close();
               filesock.close();
               

               System.exit(0);
            } else if (line[0].compareTo(Protocol.IDSEARCHCHECK_OK) == 0) { // ȸ������ ID �ߺ� �ȵ�
               JOptionPane.showMessageDialog(this, "��밡��");
               condition_Id = true;
            } else if (line[0].compareTo(Protocol.IDSEARCHCHECK_NO) == 0) { // ȸ������ ID �ߺ� ��
               JOptionPane.showMessageDialog(this, "��� �Ұ���");
               condition_Id = false;
            } else if (line[0].compareTo(Protocol.IDSEARCH_OK) == 0) // ID ã�� ������ ����
            {
               JOptionPane.showMessageDialog(this, line[1]);
               searchF.setVisible(false);
               this.setVisible(true);
            } else if (line[0].compareTo(Protocol.IDSEARCH_NO) == 0) // ID�� ����
            {
               JOptionPane.showMessageDialog(this, line[1]);
               searchF.setVisible(false);
               this.setVisible(true);
            } else if (line[0].compareTo(Protocol.ENTERLOGIN_OK) == 0) // �α��� ����
            {
               this.setVisible(false);
               RoomF.setVisible(true);
               RoomF.chatarea.append(line[1] + line[2] + '\n');

               String text[] = line[3].split(":");
               String userlist = "";
               for (int i = 0; i < text.length; i++) {
                  userlist += (text[i] + "\n");
               }
               RoomF.usertxt.setText(userlist);

            } else if (line[0].compareTo(Protocol.ENTERLOGIN_NO) == 0) // �α��� ����
            {
               JOptionPane.showMessageDialog(this, line[1]);
               System.out.println("�α��ν���");
            } else if (line[0].compareTo(Protocol.EXITWAITROOM) == 0) // �α׾ƿ� [���� -> �α���������]
            {
               RoomF.chatarea.append(line[1] + line[2] + '\n');

               String text[] = line[3].split(":");
               String userlist = "";
               for (int i = 0; i < text.length; i++) {
                  userlist += (text[i] + "\n");
               }
               RoomF.usertxt.setText(userlist);

            } else if (line[0].compareTo(Protocol.SENDMESSAGE_ACK) == 0) // ������ �޼��� ���� [����]
            {
               RoomF.chatarea.append("[" + line[1] + "] :" + line[2] + '\n');

            } else if (line[0].compareTo(Protocol.ROOMMAKE_OK) == 0) // �游�����
            {
               System.out.println("�̰� �ǳ�?");
               String roomList[] = line[1].split("-"); // �� ����
               for (int i = 0; i < roomList.length; i++) {
                  System.out.print(roomList[i] + "/");
               }

               String roomListDetail[]; // �漼��
               System.out.println("RoomList size : " + roomList.length);

               RoomF.containPanelClear(); // �� �����ӿ� �����̳ʸ� ����ְ�
               for (int i = 0; i < roomList.length; i++) {

                  RoomF.dp[i].init(); // �渮��Ʈ�� �����ŷ� �ٽ� �������ְ�
                  roomListDetail = roomList[i].split("%");
                  String userNumber = "";

                  if (roomListDetail.length == 8) // �������
                  {
                     userNumber += (roomListDetail[7] + "/" + roomListDetail[3]);

                     RoomF.dp[i].labelArray[1].setText(roomListDetail[0]); // ���ȣ
                     RoomF.dp[i].labelArray[3].setText(roomListDetail[5]); // ������
                     RoomF.dp[i].labelArray[5].setText(userNumber); // �ο���
                     RoomF.dp[i].labelArray[7].setText(roomListDetail[1]); // ������
                     RoomF.dp[i].labelArray[8].setText("������ : " + roomListDetail[4]); // ������
                  } else if (roomListDetail.length == 7) // ������
                  {
                     userNumber += (roomListDetail[6] + "/" + roomListDetail[2]);
                     RoomF.dp[i].labelArray[1].setText(roomListDetail[0]); // ���ȣ
                     RoomF.dp[i].labelArray[3].setText(roomListDetail[5]); // ������
                     RoomF.dp[i].labelArray[5].setText(userNumber); // �ο���
                     RoomF.dp[i].labelArray[7].setText(roomListDetail[1]); // ������
                     RoomF.dp[i].labelArray[8].setText("������ : " + roomListDetail[3]); // ������
                  }
                  System.out.println("userNumber : " + userNumber);

               }
               //chattingF.area.setText("");
               chattingF.area1.setText("");
               rMakeF.setVisible(false); // ���� ȭ�� ����
               RoomF.setVisible(true);

            } else if (line[0].compareTo(Protocol.ROOMMAKE_OK1) == 0) // �游����� (���� �����) // ����
            {
               rMakeF.setVisible(false); // ���� ȭ�� ����
               //chattingF.area.setText("");
               chattingF.setVisible(true);
               chattingF.partList.setText(line[1] + "\n");

            } else if (line[0].compareTo(Protocol.ENTERROOM_OK1) == 0) // ������ �����ϴ� �����
            {
               System.out.println("����ȭ�� ��ȯ");
               RoomF.setVisible(false);
               chattingF.area1.setText("");
               //chattingF.area.setText("");
               chattingF.setVisible(true);
               System.out.println(line[2]);
               String roomMember[] = line[2].split("%");//�뿡 ���»����
               chattingF.partList.append(line[1]); //�ڱ� �߰����ְ�
               for (int i = 0; i < roomMember.length; i++) {
                  chattingF.partList.append(roomMember[i] + "\n");
               }

            } else if (line[0].compareTo(Protocol.ENTERROOM_USERLISTSEND) == 0) // ä�ù� ����Ʈ ���ΰ�ħ
            {

                String roomMember[] = line[1].split("%");// �뿡 ���»����
                String lineList = "";
                for (int i = 0; i < roomMember.length; i++) {
                   lineList += (roomMember[i] + "\n");
                }

                chattingF.partList.setText(lineList);
                document = chattingF.area1.getStyledDocument();
                sa= new SimpleAttributeSet();

                try{
                   
                   StyleConstants.setAlignment(sa, StyleConstants.ALIGN_CENTER);
                   StyleConstants.setForeground(sa, Color.red);
                   document.setParagraphAttributes(document.getLength(), document.getLength() + 1, sa, true);
                   document.insertString(document.getLength(),line[2] + "\n",sa);
                }
                catch (Exception e)
                {
                   e.printStackTrace();
                }

                if (line.length == 4) {
                   String fileList[] = line[3].split("%");
                   /*chattingF.model.removeAllElements();
                   for (int i = 0; i < fileList.length; i++) {
                      chattingF.model.addElement(fileList[i]);
                   }*/
                }

             } else if (line[0].compareTo(Protocol.CHATTINGSENDMESSAGE_OK) == 0) {
                 try{
                    document = chattingF.area1.getStyledDocument();
                     sa= new SimpleAttributeSet();
                     if(line[1].equals(idname))
                     {
                        StyleConstants.setAlignment(sa, StyleConstants.ALIGN_RIGHT);
                        StyleConstants.setForeground(sa, colour);
                        document.setParagraphAttributes(document.getLength(), document.getLength() + 1, sa, true);
                        document.insertString(document.getLength(),"[" + line[1] + "] :" + line[2] + "\n",sa);
                     }

                     
                     else
                     {
                        StyleConstants.setAlignment(sa, StyleConstants.ALIGN_LEFT);
                        StyleConstants.setForeground(sa, ucolor = new Color (Integer.parseInt(line[3])));
                        document.setParagraphAttributes(document.getLength(), document.getLength() + 1, sa, true);
                        document.insertString(document.getLength(),"[" + line[1] + "] :" + line[2] + "\n",sa);
                     }
                  }
                  catch (Exception e)
                  {
                     e.printStackTrace();
                  }

               }else if (line[0].compareTo(Protocol.IMAGESEND)==0)
				{
					System.out.println("�� ���� Size : " + line[1]);

					long filesize = Long.parseLong(line[1]);

					InputStream is = filesock.getInputStream();

					// ������ ������½�Ʈ�� ��ü ����

					String path = "./temp" + "/" + line[2];
					File fs = new File(path);
					fs.createNewFile();

					FileOutputStream fos = new FileOutputStream(path);

					System.out.println("���� �ٿ�ε� ���� !!!");

					// ������ ���� ������ ���Ͽ� ����

					byte[] b = new byte[2048];

					int n = 0;
					int result = 0;

					while ((n = is.read(b, 0, b.length)) > 0) {

						fos.write(b, 0, n);
						System.out.println("N:" + n);
						result += n;
						System.out.println(result + "bytes �ٿ�ε� !!!");
						if (result >= filesize)
							break;
					}

					fos.close();
					System.out.println("���� �ٿ�ε� �� !!!");

					try{
						if(line[4].equals(idname))
						{
							document = chattingF.area1.getStyledDocument();
							sa= new SimpleAttributeSet();
							StyleConstants.setAlignment(sa, StyleConstants.ALIGN_RIGHT);
							StyleConstants.setForeground(sa, colour);
							document.setParagraphAttributes(document.getLength(), document.getLength() + 1, sa, true);
							document.insertString(document.getLength(),"[" + line[4] + "]" + "\n",sa);
							
							ImageIcon icon = new ImageIcon("./temp" + "/" + line[2]);
							Image img = icon.getImage();
							Image resize = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

							Style style = document.addStyle("StyleName", null);
							StyleConstants.setIcon(style, new ImageIcon(resize));
							document.insertString(document.getLength(), "invisible text" + "\n", style);
						}
						else
						{
							document = chattingF.area1.getStyledDocument();
							sa= new SimpleAttributeSet();
							StyleConstants.setAlignment(sa, StyleConstants.ALIGN_LEFT);
							StyleConstants.setForeground(sa, ucolor = new Color(Integer.parseInt(line[3])));
							document.setParagraphAttributes(document.getLength(), document.getLength() + 1, sa, true);
							document.insertString(document.getLength(),"[" + line[4] + "]" + "\n",sa);

							ImageIcon icon = new ImageIcon("./temp" + "/" + line[2]);
							Image img = icon.getImage();
							Image resize = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

							Style style = document.addStyle("StyleName", null);
							StyleConstants.setIcon(style, new ImageIcon(resize));
							document.insertString(document.getLength(), "invisible text" + "\n", style);
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				

					

     			
				}
            
            /* else if (line[0].compareTo(Protocol.CHATTINGFILESEND_SYNACK) == 0) {

               //pw.println(Protocol.CHATTINGFILESEND_FILE + "|" + chattingF.file.length());
               pw.flush();

               OutputStream os = socket.getOutputStream();

               System.out.println("���� ������ ���� !!!");
               // ���� ������ �Է� ��Ʈ�� ��ü ����
               //FileInputStream fis = new FileInputStream(chattingF.file.getAbsoluteFile());

               // ������ ������ ������
               byte[] b = new byte[512];
               int n;
               while ((n = fis.read(b, 0, b.length)) > 0) {
                  os.write(b, 0, n);
                  System.out.println(n + "bytes ���� !!!");
               }

               // ���Ͽ��� ���� ��� ��Ʈ���� ���Ѵ�.
            } else if (line[0].compareTo(Protocol.CHATTINGFILESEND_FILEACK) == 0) {

               String[] fileList = line[1].split("%");

               chattingF.model.removeAllElements();
               for (int i = 0; i < fileList.length; i++) {
                  chattingF.model.addElement(fileList[i]);
               }

            } /*else if (line[0].compareTo(Protocol.CHATTINGFILEDOWNLOAD_SEND) == 0) { // ������ ����
               String path = chattingF.file.getAbsolutePath();

               FileOutputStream fos = new FileOutputStream(path);
               InputStream is = socket.getInputStream();

               System.out.println("���� �ٿ�ε� ���� !!!");

               // ������ ���� ������ ���Ͽ� ����

               byte[] b = new byte[512];

               int n = 0;
               long filesize = Long.parseLong(line[1]);

               while ((n = is.read(b, 0, b.length)) > 0) {

                  fos.write(b, 0, n);
                  System.out.println("N:" + n);
                  System.out.println(n + "bytes �ٿ�ε� !!!");
                  n += n;
                  if (n >= filesize)
                     break;
               }

               fos.close();
               System.out.println("���� �ٿ�ε� �� !!!");
            }*/
               else if (line[0].compareTo(Protocol.EMOSEND)==0)
				{ try {
					if(line[1].equals(idname))
						{
							document = chattingF.area1.getStyledDocument();
							sa= new SimpleAttributeSet();
							StyleConstants.setAlignment(sa, StyleConstants.ALIGN_RIGHT);
							StyleConstants.setForeground(sa, colour);
							document.setParagraphAttributes(document.getLength(), document.getLength() + 1, sa, true);
							document.insertString(document.getLength(),"[" + line[1] + "]" + "\n",sa);
							
							ImageIcon icon = new ImageIcon(line[2]);
							Image img = icon.getImage();
							Image resize = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

					 
							Style style = document.addStyle("StyleName", null);
							StyleConstants.setIcon(style, new ImageIcon(resize));
							document.insertString(document.getLength(), "invisible text" + "\n", style);
						}
						else
						{
							document = chattingF.area1.getStyledDocument();
							sa= new SimpleAttributeSet();
							StyleConstants.setAlignment(sa, StyleConstants.ALIGN_LEFT);
							StyleConstants.setForeground(sa, ucolor);
							document.setParagraphAttributes(document.getLength(), document.getLength() + 1, sa, true);
							document.insertString(document.getLength(),"[" + line[1] + "]" + "\n",sa);
							ImageIcon icon = new ImageIcon(line[2]);
							Image img = icon.getImage();
							Image resize = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

						
							Style style = document.addStyle("StyleName", null);
							StyleConstants.setIcon(style, new ImageIcon(resize));
							document.insertString(document.getLength(), "invisible text" + "\n", style);
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
					}
         } catch (IOException io) {
            io.printStackTrace();
         }

      } // while
   
   }

@Override
public void valueChanged(ListSelectionEvent e) {
   // TODO Auto-generated method stub
   
}
}