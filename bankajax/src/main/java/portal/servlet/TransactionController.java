package portal.servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import banking.BankingLogic;
import userexception.CustomException;
import utilities.CheckerUtil;
import validatorutil.Validator;

/**
 * Servlet implementation class TransactionController
 */
//@WebServlet("/TransactionController")
public class TransactionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

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
		// doGet(request, response);

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
			case "/balanceFetch": {
				getBalance(request, response);
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
			BankingLogic logics = (BankingLogic) request.getServletContext().getAttribute("logicApi");

			String str1 = request.getParameter("AccountNumber");
			Validator.validateString(str1);

			long accountNum = Long.parseLong(str1);
			CheckerUtil.validateNumbeNegative(accountNum);

			long customerId = logics.getCustomerIDByAccNum(accountNum);
			CheckerUtil.validateNumbeNegative(customerId);

			String str2 = request.getParameter("amount");
			Validator.validateString(str2);

			double amount = Double.parseDouble(str2);
			CheckerUtil.validateNumbeNegative(amount);

			logics.withdrawAmount(customerId, accountNum, amount);

			request.setAttribute("successMessage", "Withdrawal Done Successfully!");
			request.getRequestDispatcher("AdminDashBoard.jsp").forward(request, response);

		} catch (Exception ex) {

			request.setAttribute("errorMessage", "Withdrawal unsuccessful!" + ex.getMessage());
			request.getRequestDispatcher("WithdrawAmount.jsp").forward(request, response);
			throw new CustomException(ex);
		}
	}

	private void depositAmount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {
		try {
			BankingLogic logics = (BankingLogic) request.getServletContext().getAttribute("logicApi");

			String str1 = request.getParameter("AccountNumber");
			Validator.validateString(str1);

			long accountNum = Long.parseLong(str1);
			CheckerUtil.validateNumbeNegative(accountNum);

			long customerId = logics.getCustomerIDByAccNum(accountNum);
			CheckerUtil.validateNumbeNegative(customerId);

			String str2 = request.getParameter("amount");
			Validator.validateString(str2);

			long amount = Long.parseLong(str2);
			CheckerUtil.validateNumbeNegative(amount);

			logics.depositAmount(customerId, accountNum, amount);

			request.setAttribute("successMessage", "Deposit Done Successfully!");
			request.getRequestDispatcher("AdminDashBoard.jsp").forward(request, response);

		} catch (Exception ex) {

			request.setAttribute("errorMessage", "Deposit unsuccessful!" + ex.getMessage());
			request.getRequestDispatcher("DepositAmount.jsp").forward(request, response);
			throw new CustomException(ex);
		}
	}

	private void transferAmount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {
		try {

			BankingLogic logics = (BankingLogic) request.getServletContext().getAttribute("logicApi");

			HttpSession session = (HttpSession) request.getSession(false);

			String roleValue = (String) session.getAttribute("Role");

			String str1 = request.getParameter("fromAccountNumber");
			Validator.validateString(str1);
			long fromAccountNumber = Long.parseLong(str1);
			CheckerUtil.validateNumbeNegative(fromAccountNumber);

			String str2 = request.getParameter("toAccountNumber");
			Validator.validateString(str2);
			long toAccountNumber = Long.parseLong(str2);
			CheckerUtil.validateNumbeNegative(toAccountNumber);

			String str3 = request.getParameter("amount");
			Validator.validateString(str3);
			double transactAmount = Double.parseDouble(str3);
			CheckerUtil.validateNumbeNegative(transactAmount);

//			System.out.println("fma:" + fromAccountNumber + " tma:" + toAccountNumber + " amt:" + transactAmount);

			logics.tranferAmount(fromAccountNumber, toAccountNumber, transactAmount);

			if (roleValue.equals("Admin")) {

				request.setAttribute("successMessage", "Transaction Done Successfully!");
				request.getRequestDispatcher("AdminDashBoard.jsp").forward(request, response);

			} else if (roleValue.equals("Customer")) {
				request.setAttribute("successMessage", "Transaction Done Successfully!");
				request.getRequestDispatcher("UserDashBoard.jsp").forward(request, response);

			}
		} catch (Exception ex) {

			request.setAttribute("errorMessage", "Transaction unsuccessful!" + ex.getMessage());

			request.getRequestDispatcher("TransferAmount.jsp").forward(request, response);
//
//			HttpSession session = (HttpSession) request.getSession(false);
//			String roleValue = (String) session.getAttribute("Role");
//			if (roleValue.equals("Admin")) {
//
//				request.setAttribute("errorMessage", "Something went wrong! Transaction unsuccessful!");
//
//				request.getRequestDispatcher("TransferAmount.jsp").forward(request, response);
//
//			} else if (roleValue.equals("Customer")) {
//
//				request.setAttribute("errorMessage", "Something went wrong! Transaction unsuccessful!");
//				request.getRequestDispatcher("TransferAmount.jsp").forward(request, response);
//
//			}
			throw new CustomException(ex);
		}
	}

	private void getBalance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {
		try {
			BankingLogic logics = (BankingLogic) request.getServletContext().getAttribute("logicApi");

			String str1 = request.getParameter("fromAccountNumber");
			Validator.validateString(str1);
			long fromAccountNumber = Long.parseLong(str1);
			CheckerUtil.validateNumbeNegative(fromAccountNumber);

			long customerId = logics.getCustomerIDByAccNum(fromAccountNumber);
			double balance = logics.getBalance(customerId, fromAccountNumber);

			System.out.println("Balance:" + balance);

			response.getWriter().print("" + balance);
		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}
}
