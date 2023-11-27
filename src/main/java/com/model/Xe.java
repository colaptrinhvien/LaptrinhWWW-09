package com.model;

public class Xe {
	private int maXe;
	private String tenXe;
	private int gia;
	private int namSX;
	private LoaiXe loaiXe;
	public int getMaXe() {
		return maXe;
	}
	public void setMaXe(int maXe) {
		this.maXe = maXe;
	}
	public String getTenXe() {
		return tenXe;
	}
	public void setTenXe(String tenXe) {
		this.tenXe = tenXe;
	}
	public int getGia() {
		return gia;
	}
	public void setGia(int gia) {
		this.gia = gia;
	}
	public int getNamSX() {
		return namSX;
	}
	public void setNamSX(int namSX) {
		this.namSX = namSX;
	}
	public LoaiXe getLoaiXe() {
		return loaiXe;
	}
	public void setLoaiXe(LoaiXe loaiXe) {
		this.loaiXe = loaiXe;
	}
	public Xe(int maXe, String tenXe, int gia, int namSX, LoaiXe loaiXe) {
		super();
		this.maXe = maXe;
		this.tenXe = tenXe;
		this.gia = gia;
		this.namSX = namSX;
		this.loaiXe = loaiXe;
	}
	
	public Xe(String tenXe, int gia, int namSX, LoaiXe loaiXe) {
		super();
		this.tenXe = tenXe;
		this.gia = gia;
		this.namSX = namSX;
		this.loaiXe = loaiXe;
	}
	public Xe() {
		
	}
	
}
