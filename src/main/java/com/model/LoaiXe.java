package com.model;

public class LoaiXe {
	private int maLoai;
	private String tenLoai;
	public int getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(int maLoai) {
		this.maLoai = maLoai;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	public LoaiXe(int maLoai, String tenLoai) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
	}
	public LoaiXe() {
	}
	public LoaiXe(int maLoai) {
		super();
		this.maLoai = maLoai;
	}
	
}
