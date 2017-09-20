package ui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.MD5;

import bll.AdminBLL;
import bll.StudentBLL;

import model.Admin;
import model.Student;

/**
 * Servlet implementation class SigninForm
 */
public class SigninForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SigninForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MD5 md5=new MD5();
//		long identity = Long.parseLong(request.getParameter("identity"));
		
//		String password = request.getParameter("password");
		String password = md5.getMD5(request.getParameter("password").getBytes());
		String type = request.getParameter("type");
//		System.out.println(type);
		if (type.equals("student")) {
			long identity = Long.parseLong(request.getParameter("identity"));
			Student student = new Student();
			student.setIdentity(identity);
			student.setPassword(password);

			StudentBLL studentBLL = new StudentBLL();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			if (studentBLL.login(student) == 0) {
				out.println("<script>");
				out.println("alert('登录失败，用户不存在，请先注册');");
				out.println("window.location.href='register.jsp';");
				out.println("</script>");
			} else {
				if (studentBLL.login(student) == -1) {
					out.println("<script>");
					out.println("alert('密码错误，请重新输入');");
					out.println("history.back();");
					out.println("</script>");
				} else {
					response.sendRedirect("index.jsp?identity="+identity);
				}
			}
		}
		else {
			String adminID=request.getParameter("identity");
			Admin admin=new Admin();
			admin.setIdentity(adminID);
			admin.setPassword(password);

			AdminBLL adminBLL = new AdminBLL();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			if (adminBLL.login(admin) == 0) {
				out.println("<script>");
				out.println("alert('登录失败，用户不存在');");
				out.println("history.back();");
				out.println("</script>");
			} 
			else {
				if (adminBLL.login(admin) == -1) {
					out.println("<script>");
					out.println("alert('密码错误，请重新输入');");
					out.println("history.back();");
					out.println("</script>");
				} 
				else {
					response.sendRedirect("admin.jsp");
				}
			}
		}
	}
}
