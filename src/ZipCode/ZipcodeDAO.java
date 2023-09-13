package ZipCode;
import java.sql.*;
import java.util.*;
public class ZipcodeDAO {
    // ����
    private Connection conn;
    // ���� ���� => SQL
    private PreparedStatement ps;
    //���� => ����Ŭ �ּ�
    private final String URL="jdbc:oracle:thin:@localhost:1521:STR";
    
    // ����̹� ���
    public ZipcodeDAO()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    // ����
    public void getConnection() {
        try {
            conn=DriverManager.getConnection(URL,"knh9017","1234");
            //conn hr/happy
        }catch(Exception ex) {}
    }
    //�ݱ�
    public void disConnection() {
        try {
            if(ps!=null)ps.close();
            if(conn!=null)conn.close();
            //exit
        }catch(Exception ex) {}
    }
    // �����ȣ ã��
    public ArrayList<ZipcodeVO> postfind(String dong){
        ArrayList<ZipcodeVO> list=
                new ArrayList<ZipcodeVO>();
        try {
            // ����
            getConnection();
            // SQL ���� ����
            String sql="SELECT zipcode,sido,sigungu,road_name,building_number1,building_name_sigungu FROM address   "+"WHERE road_name LIKE '%'||?||'%'";
            ps=conn.prepareStatement(sql);
            ps.setString(1, dong);
            ResultSet rs=ps.executeQuery();//����
            while(rs.next()){
                ZipcodeVO vo=new ZipcodeVO();
                vo.setZipcode(rs.getString(1));
                vo.setSido(rs.getString(2));
                vo.setGugun(rs.getString(3));
                vo.setroad_name(rs.getString(4));
                vo.setBuilding_num(rs.getString(5));
                vo.setBuilding_name(rs.getString(6));
                
                list.add(vo);
            }
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }finally {
            disConnection();
        }return list;
    }
}