package Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import javax.swing.table.DefaultTableModel;
 
public class UserDefaultJTableDAO {
   
    /**
     * �ʿ��� ��������
     * */
    Connection con;
    Statement st;
    PreparedStatement ps;
    ResultSet rs;
 
    /**
     * �ε� ������ ���� ������
     * */
    public UserDefaultJTableDAO() {
        try {
            // �ε�
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // ����
            con = DriverManager
                    .getConnection("jdbc:oracle:thin:@localhost:1521:STR","knh9017", "1234");
 
        } catch (ClassNotFoundException e) {
            System.out.println(e + "=> �ε� fail");
        } catch (SQLException e) {
            System.out.println(e + "=> ���� fail");
        }
    }//������
 
    /**
     * DB�ݱ� ��� �޼ҵ�
     * */
    public void dbClose() {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (ps != null) ps.close();
        } catch (Exception e) {
            System.out.println(e + "=> dbClose fail");
        }
    }//dbClose() ---
 
    /**
     * �μ��� ���� ID�� �ش��ϴ� ���ڵ� �˻��Ͽ� �ߺ����� üũ�ϱ� ���ϰ��� true =��밡�� , false = �ߺ���
     * */
    public boolean getIdByCheck(String id) {
        boolean result = true;
 
        try {
            ps = con.prepareStatement("SELECT * FROM UserContent WHERE idname=?");
            ps.setString(1, id.trim());
            rs = ps.executeQuery(); //����
            if (rs.next())
                result = false; //���ڵ尡 �����ϸ� false
 
        } catch (SQLException e) {
            System.out.println(e + "=>  getIdByCheck fail");
        } finally {
            dbClose();
        }
 
        return result;
 
    }//getIdByCheck()
 
    /**
     * userlist ȸ�������ϴ� ��� �޼ҵ�
     * */
    public int userListInsert(UserJDailogGUI user) {
        int result = 0;
        try {
            ps = con.prepareStatement("insert into UserContent values(num.nextval,?,?,?,?,?,?,?)");
            ps.setString(1, user.id.getText());
            ps.setString(3, user.name.getText());
            ps.setString(2, user.password.getText());
            ps.setString(4, user.age.getText());
            ps.setString(5, user.email.getText());
            ps.setString(6, user.phone.getText());
            ps.setString(7, user.addr.getText());
 
            result = ps.executeUpdate(); //���� -> ����
 
        } catch (SQLException e) {
            System.out.println(e + "=> userListInsert fail");
        } finally {
            dbClose();
        }
 
        return result;
 
    }//userListInsert()
 
    /**
     * userlist�� ��� ���ڵ� ��ȸ
     * */
    public void userSelectAll(DefaultTableModel t_model) {
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from UserContent order by idname");
 
            // DefaultTableModel�� �ִ� ���� ������ �����
            for (int i = 0; i < t_model.getRowCount();) {
                t_model.removeRow(0);
            }
 
            while (rs.next()) {
                Object data[] = { rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8) };
 
                t_model.addRow(data); //DefaultTableModel�� ���ڵ� �߰�
            }
 
        } catch (SQLException e) {
            System.out.println(e + "=> userSelectAll fail");
        } finally {
            dbClose();
        }
    }//userSelectAll()
 
    /**
     * ID�� �ش��ϴ� ���ڵ� �����ϱ�
     * */
    public int userDelete(String id) {
        int result = 0;
        try {
            ps = con.prepareStatement("delete UserContent where idname = ? ");
            ps.setString(1, id.trim());
            result = ps.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e + "=> userDelete fail");
        }finally {
            dbClose();
        }
 
        return result;
    }//userDelete()
 
    /**
     * ID�� �ش��ϴ� ���ڵ� �����ϱ�
     * */
    public int userUpdate(UserJDailogGUI user) {
        int result = 0;
        String sql = "UPDATE UserContent SET idname=?, password=?, name=?, "
        		+ "age=?, email=?, phonenumber1=?, address=? WHERE idname=?";
 
        try {
            ps = con.prepareStatement(sql);
            // ?�� ������� �� �ֱ�
            ps.setString(1, user.id.getText());
            ps.setString(2, user.password.getText());
            ps.setString(3, user.name.getText());
            ps.setString(4, user.age.getText());
            ps.setString(5, user.email.getText());
            ps.setString(6, user.phone.getText());
            ps.setString(7, user.addr.getText().trim());
            ps.setString(8,user.id.getText());
 
            // �����ϱ�
            result = ps.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e + "=> userUpdate fail");
        } finally {
            dbClose();
        }
 
        return result;
    }//userUpdate()
 
    /**
     * �˻��ܾ �ش��ϴ� ���ڵ� �˻��ϱ� (like�����ڸ� ����Ͽ� _, %�� ����Ҷ��� PreparedStatemnet�ȵȴ�. �ݵ��
     * Statement��ü�� �̿���)
     * */
    public void getUserSearch(DefaultTableModel dt, String fieldName,
            String word) {
        String sql = "SELECT * FROM UserContent WHERE " + fieldName.trim()
                + " LIKE '%" + word.trim() + "%'";
 
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
 
            // DefaultTableModel�� �ִ� ���� ������ �����
            for (int i = 0; i < dt.getRowCount();) {
                dt.removeRow(0);
            }
 
            while (rs.next()) {
                Object data[] = { rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8) };
 
                dt.addRow(data);
            }
 
        } catch (SQLException e) {
            System.out.println(e + "=> getUserSearch fail");
        } finally {
            dbClose();
        }
 
    }//getUserSearch()
 
}// Ŭ������
