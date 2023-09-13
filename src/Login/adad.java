package Login;

import java.sql.*;

public class adad {
    // 연결
    private static Connection conn;
    // 문장 전송 => SQL
    public final static String URL="jdbc:oracle:thin:@localhost:1521:STR";

    public static PreparedStatement pstmt;

    static String[] name={
    "송승희","표상혁","손세훈","풍기정","박경자","제갈문정","표현진","제갈홍준","임세욱","윤햐윤",
    "윤제선","정태일","황성원","문용석","배은정","풍혜성","황정일","유경호","황영지","추지윤",
    "심달","배다운","심한길","임버들","홍믿음","송다운","정우람","추달","사공우람","고나길",
    "성으뜸","권나길","양다운","허우람","풍믿음","신우람","노믿음","백믿음","손미르","고나라우람",
    "임나비","예고은","이바다","신보라","남궁보다","윤아름","류다래","조나봄","정라온","조두리",
    "박잔디", "권민들레","추리온","사공별","배두리","이구슬","황보민들레","안아람","송사랑","전아람",
    "강건","제갈혁","최혁","송건","복광","조웅","최웅","최광","봉철","임광",
    "풍호","남궁철","권호","최광","윤철","정호","성훈","조혁","오건","오광",
    "조성","풍설","유설","유지","오진","제갈상","전지","윤지","문설","송은",
    "고현","봉은","양진","풍리","풍란","정지","예재","문성","제갈린","서란"
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
            pstmt.setString(7,"대한민국 어딘가");
       
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