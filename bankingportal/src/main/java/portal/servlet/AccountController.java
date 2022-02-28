package portal.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import account.AccountData;
import banking.BankingLogic;
import userexception.CustomException;

/**
 * Servlet implementation class AccountController
 */
//@WebServlet("/AccountController")
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static BankingLogic logics;

	public void init(ServletConfig config) {
		try {
			logics = new BankingLogic();
			config.getServletContext().setAttribute("logicApi", logics);

		} catch (CustomException ex) {

			ex.printStackTrace();
		}

	}

	public AccountController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		try {
			String actionType = request.getServletPath();
			switch (actionType) {
			case "/addAccount": {
				addNewAccount(request, response);
				break;
			}
			case "/updateAccount": {
				break;
			}
			}
		} catch (CustomException ex) {

		}
	}

	private void addNewAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {

		try {

			AccountData accountObj = new AccountData();
			accountObj.setCustID(Long.parseLong(request.getParameter("CustomerID")));
			accountObj.setAccType(request.getParameter("AccType"));
			String location = request.getParameter("Location");
			accountObj.setLocation(location);
			accountObj.setBalance(Long.parseLong(request.getParameter("Balance")));

			logics.addNewAccount(accountObj.getCustID(), accountObj);
			response.sendRedirect("AdminDashBoard.jsp");
//			RequestDispatcher dispatch = request.getRequestDispatcher("AdminDashBoard.jsp");
//			dispatch.include(request, response);
		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}
}
