package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.model.LoaiXe;
import com.model.Xe;
import com.untill.QuanLyLoaiXeDao;
import com.untill.QuanlyXeDao;

/**
 * Servlet implementation class QuanLyXeServlet
 */
@WebServlet(name = "QuanLyXeServlet", urlPatterns = {"/Trang-Chu","/DanhSachLoaiXe","/DanhSachXeTheoLoaiXe","/DanhSachXe","/FormXe","/addXe","/deleteXe","/FormUpXe","/updateXe"})
public class QuanLyXeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private QuanLyLoaiXeDao loaiXeDao;
    private QuanlyXeDao xeDao;
    @Resource(name = "jdbc/Xe")
    private DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuanLyXeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			loaiXeDao = new QuanLyLoaiXeDao(dataSource);
		xeDao = new QuanlyXeDao(dataSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void deleteXe(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String  ma= request.getParameter("maXe");
		int id = Integer.parseInt(ma);
		xeDao.deleteXe(id);
		response.sendRedirect(request.getContextPath()+"/DanhSachXe");
	}
	public void listLoaiXe(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		List<LoaiXe> loaiXes = loaiXeDao.findAllLoaiXes();
		request.setAttribute("model", loaiXes);
		RequestDispatcher rs = request.getRequestDispatcher("LoaiXe.jsp");
		rs.forward(request, response);
	}
	public void findXetheoDSXe(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String ma = request.getParameter("maLX");
		int id = Integer.parseInt(ma);
		List<Xe> xes = xeDao.xeFollowLoaiXe(id);
		request.setAttribute("modelXe", xes);
		RequestDispatcher rs = request.getRequestDispatcher("DSXeTheoLoaiXe.jsp");
		rs.forward(request, response);
	}
	public void findAllXe(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<Xe> xes = xeDao.findAllXe();
		List<LoaiXe> loaiXes = loaiXeDao.findAllLoaiXes();
		request.setAttribute("model", loaiXes);
		request.setAttribute("models", xes);
		RequestDispatcher rs = request.getRequestDispatcher("Xe.jsp");
		rs.forward(request, response);
	}
	public void insertXe(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String tenXe = request.getParameter("tenXe");
		String cout = request.getParameter("gia");
		Integer gia = Integer.parseInt(cout);
		String nam = request.getParameter("namSX");
		int namsx = Integer.parseInt(nam);
		String loaiXe = request.getParameter("loaiXe");
		int idLX = Integer.parseInt(loaiXe);
		LoaiXe loaiXe2 = xeDao.findOne(idLX);
 		Xe xe = new Xe(tenXe, gia, namsx, loaiXe2);
		xeDao.insert(xe);
		response.sendRedirect(request.getContextPath() + "/DanhSachXe");
	}
	public void updateXe(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String ma = request.getParameter("maXe");
		int idXe = Integer.parseInt(ma);
		String tenXe = request.getParameter("tenXe");
		String cout = request.getParameter("gia");
		Integer gia = Integer.parseInt(cout);
		String nam = request.getParameter("namSX");
		int namsx = Integer.parseInt(nam);
		String loaiXe = request.getParameter("loaiXe");
		int idLX = Integer.parseInt(loaiXe);
		LoaiXe loaiXe2 = xeDao.findOne(idLX);
 		Xe xe = new Xe(tenXe, gia, namsx, loaiXe2);
 		xeDao.update(idXe , xe);
 		response.sendRedirect(request.getContextPath() + "/DanhSachXe");
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.endsWith("/Trang-Chu")) {
			RequestDispatcher rs = request.getRequestDispatcher("Trang-Chu.jsp");
			rs.forward(request, response);
		}
		else if (uri.endsWith("/DanhSachLoaiXe")) {
			try {
				listLoaiXe(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (uri.endsWith("/DanhSachXeTheoLoaiXe")) {
			try {
				findXetheoDSXe(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (uri.endsWith("/DanhSachXe")) {
			try {
				findAllXe(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (uri.endsWith("/FormXe")) {
			List<LoaiXe> loaiXes;
			try {
				loaiXes = loaiXeDao.findAllLoaiXes();
				request.setAttribute("model", loaiXes);
				RequestDispatcher rs1 = request.getRequestDispatcher("FomXe.jsp");
				rs1.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(uri.endsWith("/FormUpXe"))
		{
			try {
				String ma = request.getParameter("maXe");
				int id = Integer.parseInt(ma);
				Xe xe= xeDao.getXe(id);
				List<LoaiXe> loaiXes = loaiXeDao.findAllLoaiXes();
				request.setAttribute("models", loaiXes);
				request.setAttribute("model",xe);
				RequestDispatcher rsUp = request.getRequestDispatcher("FormUpdateXe.jsp");
				rsUp.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.endsWith("/addXe")) {
			try {
				insertXe(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (uri.endsWith("/deleteXe")) {
			try {
				deleteXe(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (uri.endsWith("/updateXe")) {
			try {
				updateXe(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
