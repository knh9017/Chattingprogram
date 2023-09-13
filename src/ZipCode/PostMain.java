package ZipCode;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.JButton;

public class PostMain extends JFrame implements ActionListener{
    JTextField tf;
    JLabel la;
    DefaultTableModel model;
    public JTable table;
    public JButton submit;
    public String addr, postcode;
    public PostMain() {
        tf=new JTextField(15);
        la=new JLabel("입력");
        
        String[] col= {"우편번호","주소"};
        String[][] row=new String[0][2];
        
        submit = new JButton("확인");
        model=new DefaultTableModel(row,col);
        table=new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane js=new JScrollPane(table);
        JPanel p=new JPanel();
        p.add(la);
        p.add(tf);
        p.add(submit);
        add("North",p);
        add("Center",js);
        
        setSize(450, 500);
        setVisible(false);
        
        tf.addActionListener(this);
    }
    public void run() {
        new PostMain();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==tf) {
            String dong=tf.getText();
            if(dong.length()<1)//입력이 안된 경우
            {
                JOptionPane.showMessageDialog(this, "도로명주소를 입력하세요");
                return;
            }
            //처리
            for(int i=model.getRowCount()-1;i>=0;i--) {
                model.removeRow(i);
            }
            ZipcodeDAO dao=new ZipcodeDAO();
            ArrayList<ZipcodeVO> list=dao.postfind(dong);
            
            // 출력
            for(ZipcodeVO vo:list) {
                String[] data= {vo.getZipcode(), vo.getAddress()};
                model.addRow(data);
            }    
        }
    }

}