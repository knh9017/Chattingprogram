package Login;

import java.sql.*;

public class adad {
    // ����
    private static Connection conn;
    // ���� ���� => SQL
    public final static String URL="jdbc:oracle:thin:@localhost:1521:STR";

    public static PreparedStatement pstmt;

    static String[] name={
    "�۽���","ǥ����","�ռ���","ǳ����","�ڰ���","��������","ǥ����","����ȫ��","�Ӽ���","������",
    "������","������","Ȳ����","���뼮","������","ǳ����","Ȳ����","����ȣ","Ȳ����","������",
    "�ɴ�","��ٿ�","���ѱ�","�ӹ���","ȫ����","�۴ٿ�","�����","�ߴ�","������","����",
    "������","�ǳ���","��ٿ�","����","ǳ����","�ſ��","�����","�����","�չ̸�","������",
    "�ӳ���","������","�̹ٴ�","�ź���","���ú���","���Ƹ�","���ٷ�","������","�����","���θ�",
    "���ܵ�", "�ǹε鷹","�߸���","�����","��θ�","�̱���","Ȳ���ε鷹","�Ⱦƶ�","�ۻ��","���ƶ�",
    "����","������","����","�۰�","����","����","�ֿ�","�ֱ�","��ö","�ӱ�",
    "ǳȣ","����ö","��ȣ","�ֱ�","��ö","��ȣ","����","����","����","����",
    "����","ǳ��","����","����","����","������","����","����","����","����",
    "����","����","����","ǳ��","ǳ��","����","����","����","������","����"
    }; 


    private static void connect()
    {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn=DriverManager.getConnection(URL,"knh9017","1234");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    private static void disconnect()
    {
        try {
            if(pstmt!=null)pstmt.close();
            if(conn!=null)conn.close();
            //exit
        }catch(Exception ex) {}
    }
    
    public static void main(String[] args)
    {
        int i=0;
        connect();
        int birth=0;
        while (i<100)
        {
            if(i<10)
            {
                birth=10+i;
            }
            else{
                birth=i;
            }
            try{
            String sql="Insert into UserContent values(num.nextval,?,?,?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"test"+i);
            pstmt.setString(2,"!Q@W3e4r");
            pstmt.setString(3,name[i]);
            pstmt.setString(4,"19"+birth+"0101");
            pstmt.setString(5,"test"+i+"@naver.com");
            pstmt.setString(6,"010"+birth+birth+birth+birth);
            pstmt.setString(7,"���ѹα� ���");
       
            ResultSet rs = pstmt.executeQuery();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            i++;
        }
        disconnect();

    }


}