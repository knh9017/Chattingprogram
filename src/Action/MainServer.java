package Action;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import DO.Room;

public class MainServer {
	private ServerSocket ss; // ���� ����
	private ServerSocket fs;
	private ArrayList<MainHandler> allUserList; // ��ü �����
	private ArrayList<MainHandler> WaitUserList; // ���� �����
	private ArrayList<Room> roomtotalList;// ��ü �渮��Ʈ

	private Connection conn;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:STR";
	private String user = "knh9017";
	private String password = "1234";

	public MainServer() {

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password); // DB ����

			ss = new ServerSocket(9500);
			fs = new ServerSocket(9501);
			System.out.println("�����غ�Ϸ�");
			allUserList = new ArrayList<MainHandler>(); // ��ü �����
			WaitUserList = new ArrayList<MainHandler>(); // ���� �����
			roomtotalList = new ArrayList<Room>(); // ��ü �渮��Ʈ
			while (true) {
				Socket socket = ss.accept();
				Socket filesock = fs.accept();
				MainHandler handler = new MainHandler(socket, filesock, allUserList, WaitUserList, roomtotalList, conn);// ������ ����
				handler.start();// ������ ����
				allUserList.add(handler);
			} // while
		} catch (IOException io) {
			io.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MainServer();
	}
}