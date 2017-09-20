package ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.LoadModel;
import bll.Mark;
import bll.Process;
import bll.ScoreBll;
import bll.TestCorpus;

/**
 * Servlet implementation class AnswerForm
 */
public class AnswerForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerForm() {
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
		Process processBLL=new Process();
		request.setCharacterEncoding("utf-8");
		String answer=request.getParameter("answer");
		System.out.println("answer: "+answer);
		long identity = Long.parseLong(request.getParameter("identity"));
		
		processBLL.loadDic();
		String finalAnswer=processBLL.processAnswer(answer, identity);
		/*	验证准确率代码
		TestCorpus testcorpus=new TestCorpus();
		try {
			testcorpus.testAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		Mark mark=new Mark();
//		int score=3;
		int score=mark.markAnswer(identity,finalAnswer);
		ScoreBll scoreBll=new ScoreBll();
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (scoreBll.addScore(identity, score)) {
			response.sendRedirect("score.jsp?identity="+identity+"&&score="+score);
		}
		else {
			out.println("<script>");
			out.println("alert('系统繁忙，请重新提交');");
			out.println("history.back();");
			out.println("</script>");
		}
	}

}
