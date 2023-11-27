package com.untill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.model.LoaiXe;

public class QuanLyLoaiXeDao {
	private DataSource dataSource;

	public QuanLyLoaiXeDao(DataSource dataSource) {
		
		this.dataSource = dataSource;
	}
	public List<LoaiXe> findAllLoaiXes() throws SQLException{
		List<LoaiXe> loaiXes = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();
			String sql = "select * from LOAIXE";
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				int maLoai = rs.getInt(1);
				String tenLoai = rs.getString(2);
				LoaiXe loaiXe = new LoaiXe(maLoai, tenLoai);
				loaiXes.add(loaiXe);
			}
			return loaiXes;
		} finally {
			close(connection, statement, rs);
		}
	}
	private void close(Connection connection, Statement statement, ResultSet rs) {
		try {
			if (connection != null) {
				connection.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
