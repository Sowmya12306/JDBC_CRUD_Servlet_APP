package in.ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.bean.Student;
import in.ineuron.factory.StudentServiceFactory;
import in.ineuron.service.IStudentService;

/**
 * Servlet implementation class ControllerServle
 */
@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	private void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		System.out.println(requestURI);
		IStudentService stdService = StudentServiceFactory.getStudentService();
		RequestDispatcher rd = null;
		if(requestURI.endsWith("layout")) {
			rd = req.getRequestDispatcher("../layout.html");
			rd.forward(req, res);
		}
		if(requestURI.endsWith("insert")) {
			String name = req.getParameter("name");
		    Integer age = Integer.parseInt(req.getParameter("age"));
		    String address = req.getParameter("address");
		    Student std = new Student();
		    std.setName(name);
		    std.setAge(age);
		    std.setAddress(address);
		 
		    String status = stdService.save(std);
		    System.out.println(status);
		    if(status.equals("success")) {
		    	rd = req.getRequestDispatcher("../success.html");
		    	rd.forward(req, res);
		    }
		    else {
		    	rd = req.getRequestDispatcher("failure.html");
		    		rd.forward(req, res);
		    	}
		    }
		if(requestURI.endsWith("select")) {
			Integer sid = Integer.parseInt(req.getParameter("id"));
			Student std = stdService.findById(sid);
			if(std!=null) {
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			
			out.println("<html><head><title>STUDENT DATA</title></head>");
			out.println("<body bgcolor='lightblue'>");
			out.println("<table align ='center' border= '1'>");
			out.println("<tr><th>ID</th><td>"+std.getId()+"</td></tr>");
			out.println("<tr><th>NAME</th><td>"+std.getName()+"</td></tr>");
			out.println("<tr><th>AGE</th><td>"+std.getAge()+"</td></tr>");
			out.println("<tr><th>ADDRESS</th><td>"+std.getAddress()+"</td></tr>");
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
		}
			else {
				rd= req.getRequestDispatcher("../notfound.html");
				rd.forward(req, res);
			}
		}
		if (requestURI.endsWith("deleteform")) {
			String sid = req.getParameter("id");
			String status = stdService.deleteById(Integer.parseInt(sid));

			if (status.equals("success")) {
				rd = req.getRequestDispatcher("../success.html");
				rd.forward(req, res);
			} 
//				else if (status.equals("failure")) {
//				rd = req.getRequestDispatcher("../failure.html");
//				rd.forward(req, res);
//			}
				else {
				rd = req.getRequestDispatcher("../notfound.html");
				rd.forward(req, res);
			}
		
		}
		if(requestURI.endsWith("edit")) {
			Integer sid = Integer.parseInt(req.getParameter("id"));
			Student std = stdService.findById(sid);
            if(std!=null) {
            	res.setContentType("text/html");
            	PrintWriter out = res.getWriter();
            	out.println("<html><head><title>Output</title></head>");
            	out.println("<body bgcolor ='lightsteelblue'>");
            	out.println("<br><br><br>");
            	out.println("<form method='post' action='./update'>");
            	out.println("<table align='center'>");
            	out.println("<tr><th>ID</th><td>" + std.getId() + "</td></tr>");
            	out.println("<input type='hidden' name='id' value='"+std.getId()+"'/>");
            	out.println("<tr><th>NAME</th><td><input type='text' name='name' value='" + std.getName() + "'/></td></tr>");
            	out.println("<tr><th>AGE</th><td><input type='text' name='age' value='" + std.getAge() + "'/></td></tr>");
            	out.println("<tr><th>ADDRESS</th><td><input type='text' name='address' value='" + std.getAddress() + "'/></td></tr>");
            	out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
            	out.println("</table>");
            	out.println("</form>");
    			out.println("</body>");
    			out.println("</html>");
            }else {
            	rd = req.getRequestDispatcher("../notfound.html");
            	rd.forward(req, res);
            }
		}
		if(requestURI.endsWith("update")) {
			String id = req.getParameter("id");
			String name = req.getParameter("name");
			String age = req.getParameter("age");
			String address = req.getParameter("address");
			Student std = new Student();
			std.setId(Integer.parseInt(id));
			std.setName(name);
			std.setAge(Integer.parseInt(age));
			std.setAddress(address);
			String status = stdService.updateById(std);
			if(status.equals("success")) {
				rd=req.getRequestDispatcher("../success.html");
			rd.forward(req, res);
		}
			else {
		    	rd = req.getRequestDispatcher("../failure.html");
		    		rd.forward(req, res);
		    	}
		
		}
	}
}


