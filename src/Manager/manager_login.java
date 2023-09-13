package Manager;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.JButton;
import java.awt.Font;
 
public class manager_login extends JFrame {
   
   public JTextField txtID, txtPass;   
   public JButton logBtn;
   public manager_login() {
 
        JPanel panel = new JPanel();
 
        JLabel label = new JLabel("ID : ");
        label.setBounds(147, 109, 23, 15);
 
        JLabel pswrd = new JLabel("PassWord : ");
        pswrd.setBounds(101, 170, 69, 15);
 
        txtID= new JTextField(10);
        txtID.setBounds(194, 106, 133, 21);
 
        txtPass = new JPasswordField(10);
        txtPass.setBounds(194, 167, 133, 21);
 
        logBtn = new JButton("Log in");
        logBtn.setBounds(359, 105, 67, 23);
        panel.setLayout(null);
 
 
        panel.add(label);
        panel.add(txtID);
        panel.add(pswrd);
        panel.add(txtPass);
        panel.add(logBtn);

 
 
 
 
        getContentPane().add(panel);
        
        JLabel lblNewLabel = new JLabel("관리자 로그인");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
        lblNewLabel.setBounds(175, 25, 152, 38);
        panel.add(lblNewLabel);
 
 
        setVisible(false);
 
        setSize( 514 , 325);
 
        setLocationRelativeTo(null);
 
        setResizable(false);
 
 
    }
}