package com.untill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.model.LoaiXe;
import com.model.Xe;

public class QuanlyXeDao {
	private DataSource dataSource;

	public QuanlyXeDao(DataSource dataSource) {
		
		this.dataSource = dataSource;
	}
	public List<Xe> findAllXe() throws SQLException{
		List<Xe> xes = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();
			String sql = "select * from XE";
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next()) {
				int ma = rs.getInt(1);
				String ten = rs.getString(2);
				int gia = rs.getInt(3);
				int namSX = rs.getInt(4);
				int maLoai = rs.getInt(5);
				LoaiXe  loaiXe =findOne(maLoai);
				Xe xe = new Xe(ma, ten, gia, namSX, loaiXe);
				xes.add(xe);
			}
			return xes;
		} finally {
			close(connection,statement,rs);
		}
	}
	public LoaiXe findOne(int id) throws SQLException {
		LoaiXe  loaiXe = null;
		PreparedStatement statement = null;
		Connection connection = null;
		ResultSet rs =null;
		try {
			connection = dataSource.getConnection();
			String sql = "select * from LOAIXE where MALOAI = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			rs = statement.executeQuery(); 
			if (rs.next()) {
				int maLoai = rs.getInt(1);
				String tenLoai = rs.getString(2);
				loaiXe = new LoaiXe(maLoai, tenLoai);		
			}
			return loaiXe;
		} finally {
			close(connection, statement, rs);
		}
	}
	public void deleteXe(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			String sql = "delete XE where MAXE = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.execute();
		} finally {
			close(connection, statement, null);
		}
	}
	public Xe getXe(int id ) throws SQLException {
		Xe xe = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();
			String sql = "select * from XE where MAXE = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			rs = statement.executeQuery();
			if (rs.next()) {
				int ma = rs.getInt(1);
				String ten = rs.getString(2);
				int gia = rs.getInt(3);
				int namSX = rs.getInt(4);
				int maLoai = rs.getInt(5);
				LoaiXe  loaiXe = findOne(maLoai);
				xe = new Xe(ma, ten, gia, namSX, loaiXe);
			}
			return xe;
		} finally {
			close(connection, statement, rs);
		}
	}
	public List<Xe> xeFollowLoaiXe(int id) throws SQLException{
		List<Xe> xes = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();
			String sql = "select MAXE from XE where MALOAI = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			rs = statement.executeQuery();
			while (rs.next()) {
				int ma = rs.getInt(1);
				Xe xe = getXe(ma);
				xes.add(xe);
			}
			return xes;
		} finally {
			close(connection, statement, rs);
		}
	}
	public void insert(Xe xe) throws SQLException {
		Connection connection=null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			String sql = "insert into XE(TENXE,GIA,NAMSX,MALOAI) values(?,?,?,?)";
			statement = connection.prepareStatement(sql);
			statement.setString(1, xe.getTenXe());
			statement.setInt(2, xe.getGia());
			statement.setInt(3, xe.getNamSX());
			statement.setInt(4, xe.getLoaiXe().getMaLoai());
			statement.execute();
		} finally {
			close(connection, statement, null);
		}
	}
	public void update(int id,Xe xe) throws SQLException {
		Connection connection=null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			String sql ="update Xe set TENXE =?, GIA=?, NAMSX=?, MALOAI=? where MAXE = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, xe.getTenXe());
			statement.setInt(2, xe.getGia());
			statement.setInt(3, xe.getNamSX());
			statement.setInt(4, xe.getLoaiXe().getMaLoai());
			statement.setInt(5, id);
			statement.execute();
		} finally {
			close(connection, statement, null);
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
