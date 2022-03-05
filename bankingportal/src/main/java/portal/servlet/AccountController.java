package portal.servlet;

import java.io.IOException;

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
		try {

			// response.getWriter().append("Served at: ").append(request.getContextPath());
			String actionPath = request.getServletPath();
			switch (actionPath) {

			case "/deleteAccount": {

				deleteAccount(request, response);
				break;
			}
			case "/activateAccount": {
				activateAccount(request, response);
				break;
			}
			}
		} catch (CustomException ex) {
			ex.getMessage();
		}
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
				updateAccount(request, response);
				break;
			}
			}
		} catch (CustomException ex) {
			ex.getMessage();
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

			request.getRequestDispatcher("AdminDashBoard.jsp").forward(request, response);

		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}

	private void updateAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {

		try {

			AccountData accountObj = new AccountData();
			accountObj.setAccID(Long.parseLong(request.getParameter("AccountNumber")));
			accountObj.setCustID(Long.parseLong(request.getParameter("CustomerID")));
			accountObj.setAccType(request.getParameter("AccType"));
			String location = request.getParameter("Location");
			accountObj.setLocation(location);
			accountObj.setBalance(Long.parseLong(request.getParameter("Balance")));

			logics.updateAccountInfo(accountObj);

			request.getRequestDispatcher("AdminDashBoard.jsp").forward(request, response);

		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}

	private void deleteAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {

		try {
			long customerId = Long.parseLong(request.getParameter("customerId"));
			long accountId = Long.parseLong(request.getParameter("accountId"));
			logics.setAccountStatus(customerId, accountId, false);
			request.getRequestDispatcher("AdminDashBoard.jsp").forward(request, response);
		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}

	private void activateAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {

		try {
			long customerId = Long.parseLong(request.getParameter("customerId"));
			long accountId = Long.parseLong(request.getParameter("accountId"));
			logics.setAccountStatus(customerId, accountId, true);
			request.getRequestDispatcher("AdminDashBoard.jsp").forward(request, response);
		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}
}
