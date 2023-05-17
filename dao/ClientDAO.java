package MovieTicketing.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import MovieTicketing.model.ClientDTO;
import MovieTicketing.viewer.SignUp;

public class ClientDAO {
	/*
	 * 외부에 Instance 를 제공하는 메서드와 기타, SQL 을 처리하는 메서드만 제외 하고는 모두 private 으로 처리.
	 */
	
	private static Connection conn;
	private static ClientDAO dao = new ClientDAO();
	
	/*
	 * 아래 메서드는 DAO 인스턴스 생성시에 필요한 초기화 작업을 모두 처리. 현재는 Connection 만 맺도록 하는 작업만 처리.
	 */
	private Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", 
					"jooho122", "1234");
			System.out.println("컨넥션 OK..정보 --> " + conn);
		}catch (Exception e) {
			System.out.println("Connection 생성시 예외 발생함.");
			System.out.println("예외 내용 : " + e.getMessage());
		}
		return conn;	
	}
	
	public static ClientDAO getInstance() {
		return dao;
	}
	
	
	public boolean logincheck(String id, String pw) {
		boolean flag = false;
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		
		try {
			String sql = "Select clientpassword from client where clientid = '" + id + "'";
			pstmt = con.prepareStatement(sql);
			ResultSet result = pstmt.executeQuery(sql);
			
			int count = 0;
			while(result.next()) {
				if(pw.equals(result.getString("clientpassword"))) {
					flag = true;
				}
				
				else {
					flag = false;
					System.out.println("로그인 실패");
				}
				count++;
			}
		} catch(Exception e) {
			flag = false;
			System.out.println("로그인 실패 > " + e.toString());
		}
		
		return flag;
	}
	
	
	private void closer(Connection conn) {
		if(this.conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("DB close 시 예외 발생함" + e.getMessage());
			}
		}
	}
	
	
	public int signUp(ClientDTO dto) {
		int result = 0;//가입 안될 경우 리턴값 초기화
		String sql = "Insert into Client (CLIENTID,CLIENTPASSWORD,CLIENTNAME,BIRTHDATE,"
				+ "PHONENUMBER,JOIN_DATE) Values (?,?,?,?,?,sysdate)";
		//오라클에서 시스템 현재 시간을 Date로 리턴하는 함수 sysdate 이용해서 사용자의 가입 날짜 세팅.
		ClientDTO client = new ClientDTO();
			
		//Connection 얻기.
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getClientId());
			pstmt.setString(2, dto.getClientPassword());
			pstmt.setString(3, dto.getClientName());
			pstmt.setInt(4, dto.getBirthdate());
			pstmt.setString(5, dto.getPhonenumber());
			
			result = pstmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null,
                    "회원 가입이 완료되었습니다." + "\n" + "로그인 해주세요.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "이미 가입된 아이디 입니다.");
			new SignUp();
		}finally {
			if(pstmt !=null)
				try {
					pstmt.close();
					closer(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		return result;
		
	}
	
	public ClientDTO changePw(String id, String password) {
		ClientDTO client = null;
		String sql = "Update client SET clientpassword = ?" + "where clientid = ? ";
		
		Connection conn = getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, password);
			pstmt.setString(2, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				client = new ClientDTO();
				client.setClientPassword(password);
				rs.close();
				pstmt.cancel();
			}else {
				rs.close();
				pstmt.cancel();
			}
		} catch (Exception e) {
			System.out.println("암호 변경시 예외 발생 " + e.getMessage());
		}
		return client;
		
	}
	
	public boolean checkPassword(String id, String password) {
		boolean result = false;
		String sql = "Select clientpassword from client where clientid = ?";
		
		Connection conn = getConnection();
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					String dbPass = rs.getString("clientPassword");
					//사용자가 입력한 값과 비교 합니다.
					if(dbPass.equals(password)) {
						return result = true;
					}
				}while(rs.next());
			}
			rs.close();
			pstmt.close();
		}catch (Exception e) {
			System.out.println("암호 조회시 예외 발생 " + e.getMessage());
		}
		return result;
	}
	//ID 가 존재하는지 여부에 대한 validation 메서드 정의
	public int isMember(String id) {
		int result = 0;
		
		String sql = "Select count(*) from client Where clientid = ?";
		try{
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			return result;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result = 0;
	}
	
	public int MDelete(String id) {
		String sql = "Delete from client where clientid = ?";
		Connection conn = getConnection();

		int result = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

}
