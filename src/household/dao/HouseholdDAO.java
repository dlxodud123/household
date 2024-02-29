package household.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import household.dto.HouseholdDTO;

public class HouseholdDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	public HouseholdDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "c##LTY", pwd = "1213";
			con = DriverManager.getConnection(url, id, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<HouseholdDTO> list() {
		ArrayList<HouseholdDTO> arr = new ArrayList<>();
		String sql = "select * from houseHold";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				HouseholdDTO dto = new HouseholdDTO();
				dto.setType(rs.getString("type"));
				dto.setMoney(rs.getInt("money"));
				arr.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	public int add(String type, int money) {
		String sql = "insert into houseHold values(?, ?)";
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, type);
			ps.setInt(2, money);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int modify(String type, int money) {
		int result = 0;
		String sql = "update houseHold set money = ? where type = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, money);
			ps.setString(2, type);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int delete(String type) {
		int result = 0;
		String sql = "delete from houseHold where type = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, type);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int typeCheck(String type) {
		int result = 0;
		String sql = "select * from houseHold where type = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, type);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = 1; // 계정이 있음
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
