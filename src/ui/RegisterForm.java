package ui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.MD5;

import bll.StudentBLL;

import model.Student;

/**
 * Servlet implementation class RegisterForm
 */
public class RegisterForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Student student=new Student();
		MD5 md5=new MD5();
		request.setCharacterEncoding("utf-8");
		long identity=Long.parseLong(request.getParameter("identity"));
		String name=request.getParameter("name");
		String banji=request.getParameter("class");
		System.out.println(name);
//		String password=request.getParameter("password");
		String password=md5.getMD5(request.getParameter("password").getBytes());
		System.out.println("Password: "+password);
		student.setIdentity(identity);
		student.setBanji(banji);
		student.setName(name);
		student.setPassword(password);
		
		StudentBLL studentBLL=new StudentBLL();
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (studentBLL.isStudentExist(student)) {
			out.println("<script>");
		    out.println("alert('注册失败，用户已存在');");
		    out.println("history.back();");
		    out.println("</script>");
		}
		
		else {
			if (studentBLL.studentRegist(student)) {			
				out.println("<script>");
				String msg="注册成功";
			    out.println("alert('"+msg+"');");
			    System.out.println("alert("+msg+");");
			    out.println("window.location.href='signin.jsp';");
			    out.println("</script>");
			}	
			else {
				out.println("<script>");
				String msg="注册失败,系统维护中";
			    out.println("alert('"+msg+"');");
			    System.out.println("alert("+msg+");");
			    out.println("history.back();");
			    out.println("</script>");
			}
		}
	}

}
