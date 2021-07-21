package by.belstu.it.andreev;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Login_servlet
 */
@WebServlet("/login_servlet")
public class Login_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Login_servlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		UserDAO userDAO = new UserDAO();
		
		try {
			UserData ud = userDAO.findUserByLogin(login);
			// pass the request along the filter chain
			if (ud != null && ud.password.equals(password)) {
				writer.write("Hello, " + ud.username + "<br/>");
			} else {
				response.getWriter().print("Неправильный логин или пароль");
			}
			if (ud == null) {
				writer.write("Login wrong");
				//String path = "/login_wrong.html";
		        //ServletContext servletContext = request.getServletContext();
		        //RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
		        //requestDispatcher.forward(request, response);
				String path = "/login_wrong.jsp";
		        ServletContext servletContext = getServletContext();
		        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
		        requestDispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace(writer);
		}
	}

}
