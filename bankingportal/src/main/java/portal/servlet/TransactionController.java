package portal.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import banking.BankingLogic;
import userexception.CustomException;

/**
 * Servlet implementation class TransactionController
 */
//@WebServlet("/TransactionController")
public class TransactionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	static BankingLogic logics;

	public void init(ServletConfig config) {
		try {
			logics = new BankingLogic();
			config.getServletContext().setAttribute("logicApi", logics);

		} catch (CustomException ex) {

			ex.printStackTrace();
		}

	}

	public TransactionController() {
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
			case "/withdraw": {
				withdrawAmount(request, response);
				break;
			}
			case "/deposit": {
				depositAmount(request, response);
				break;
			}

			case "/transfer": {

				transferAmount(request, response);
				break;
			}
			}
		} catch (CustomException ex) {
			ex.printStackTrace();
			ex.getMessage();
		}
	}

	private void withdrawAmount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {
		try {
			long accountNum = Long.parseLong(request.getParameter("AccountNumber"));
			long customerId = logics.getCustomerIDByAccNum(accountNum);
			long amount = Long.parseLong(request.getParameter("amount"));

			logics.withdrawAmount(customerId, accountNum, amount);

			request.getRequestDispatcher("AdminDashBoard.jsp").include(request, response);

		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}

	private void depositAmount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {
		try {
			long accountNum = Long.parseLong(request.getParameter("AccountNumber"));
			long customerId = logics.getCustomerIDByAccNum(accountNum);
			long amount = Long.parseLong(request.getParameter("amount"));

			logics.depositAmount(customerId, accountNum, amount);
			request.getRequestDispatcher("AdminDashBoard.jsp").forward(request, response);

		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}

	private void transferAmount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {
		try {

			HttpSession session = (HttpSession) request.getSession();
			String roleValue = (String) session.getAttribute("Role");

			long fromAccountNumber = Long.parseLong(request.getParameter("fromAccountNumber"));
			long toAccountNumber = Long.parseLong(request.getParameter("toAccountNumber"));
			double transactAmount = Double.parseDouble(request.getParameter("amount"));

			System.out.println("fma:" + fromAccountNumber + " tma:" + toAccountNumber + " amt:" + transactAmount);
			logics.tranferAmount(fromAccountNumber, toAccountNumber, transactAmount);

			if (roleValue.equals("Admin")) {

				request.getRequestDispatcher("AdminDashBoard.jsp").forward(request, response);

			} else if (roleValue.equals("Customer")) {
				request.getRequestDispatcher("UserDashBoard.jsp").forward(request, response);

			}
		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}
}
