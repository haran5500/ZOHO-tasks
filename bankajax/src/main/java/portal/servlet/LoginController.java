package portal.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import banking.BankingLogic;
import userexception.CustomException;
import validatorutil.Validator;

/**
 * Servlet implementation class LoginController
 */
//@WebServlet("/LoginController")

public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public void init(ServletConfig config) {
		try {
			BankingLogic logics = new BankingLogic();
			config.getServletContext().setAttribute("logicApi", logics);
			super.init(config);
		} catch (CustomException | ServletException ex) {

			ex.printStackTrace();
		}

	}

	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String actionPath = request.getServletPath();
		switch (actionPath) {
		case "/logout": {

			HttpSession session = request.getSession();
			session.invalidate();

			request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
			break;
		}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* doGet(request, response); */

		String actionPath = request.getServletPath();

		BankingLogic logics = (BankingLogic) request.getServletContext().getAttribute("logicApi");

		switch (actionPath) {

		case "/login": {

			try {
				String userStr = request.getParameter("UserID");
				Validator.validateString(userStr);
				long userName = Long.parseLong(userStr);

				String password = request.getParameter("Password");
				Validator.validateString(password);
				password = password.trim();
				Validator.validateString(password);

				long roleID = -1;

				roleID = logics.validateUserLogin(userName, password);

				if (roleID == 101) {

					HttpSession session = request.getSession();
					session.setAttribute("Role", "Admin");

					RequestDispatcher dispatch = request.getRequestDispatcher("AdminDashBoard.jsp");

					dispatch.forward(request, response);
				} else if (roleID == 102) {
					HttpSession session = request.getSession();

					session.setAttribute("Role", "Customer");

					session.setAttribute("customerId", userName);

					RequestDispatcher dispatch = request.getRequestDispatcher("UserDashBoard.jsp");
					dispatch.forward(request, response);
				} else {
					request.setAttribute("errorMessage", "Invalid username or password");
					RequestDispatcher dispatch = request.getRequestDispatcher("/LoginPage.jsp");
					dispatch.forward(request, response);
				}

			} catch (CustomException e) {
				request.setAttribute("errorMessage", "Login Unsuccessful!");
				RequestDispatcher dispatch = request.getRequestDispatcher("/LoginPage.jsp");
				dispatch.forward(request, response);
				e.printStackTrace();
			}
			break;
		}

		}

	}

}
