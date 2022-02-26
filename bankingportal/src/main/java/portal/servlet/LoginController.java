package portal.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import banking.BankingLogic;
import customer.CustomerData;
import userexception.CustomException;

/**
 * Servlet implementation class LoginController
 */
//@WebServlet("/LoginController")

public class LoginController extends HttpServlet {

	static BankingLogic logics;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		try {
			logics = new BankingLogic();
		} catch (CustomException ex) {
			System.out.println("Construct");
			ex.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		long userName = Long.parseLong(request.getParameter("UserID"));
		String password = request.getParameter("Password");

		long roleID = -1;
		try {
			roleID = logics.validateUserLogin(userName, password);

			System.out.print("returned ID:"+roleID);
			if (roleID == 101) {

				HttpSession session = request.getSession();
				session.setAttribute("Role", "Admin");
				
				RequestDispatcher dispatch = request.getRequestDispatcher("AdminDashBoard.jsp");
				dispatch.forward(request, response);
			} else if (roleID == 102) {
				HttpSession session = request.getSession();

				session.setAttribute("Role", "Customer");

				CustomerData customerObj = logics.getCustomerDetailsByID(userName);
				session.setAttribute("Name", customerObj.getName());
				request.setAttribute("CustomerDetails", customerObj);
				RequestDispatcher dispatch = request.getRequestDispatcher("UserDashBoard.jsp");
				dispatch.forward(request, response);
			} else {
				request.setAttribute("errorMessage", "Invalid username or password");
				RequestDispatcher dispatch = request.getRequestDispatcher("LoginPage.jsp");
				dispatch.include(request, response);
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}

}
