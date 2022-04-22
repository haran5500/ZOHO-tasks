package portal.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import account.AccountData;
import banking.BankingLogic;
import userexception.CustomException;
import utilities.MapperUtility;
import validatorutil.Validator;

/**
 * Servlet implementation class AccountController
 */
//@WebServlet("/AccountController")
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			case "/getAccounts": {
				getAccounts(request, response);
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
//		doGet(request, response);

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
			BankingLogic logics = (BankingLogic) request.getServletContext().getAttribute("logicApi");

			AccountData accountObj = new AccountData();
			String str1 = request.getParameter("CustomerID");
			Validator.validateString(str1);
			accountObj.setCustID(Long.parseLong(str1));

			String str2 = request.getParameter("AccType");
			Validator.validateString(str2);
			str2 = str2.trim();
			Validator.validateString(str2);
			accountObj.setAccType(str2);

			String location = request.getParameter("Location");
			Validator.validateString(location);
			location = location.trim();
			Validator.validateString(location);
			accountObj.setLocation(location);

			String str3 = request.getParameter("Balance");
			Validator.validateString(str3);
			accountObj.setBalance(Double.parseDouble(str3));

			logics.addNewAccount(accountObj.getCustID(), accountObj);

			request.setAttribute("successMessage", "Account Added Successfully!");
			request.getRequestDispatcher("AdminDashBoard.jsp").forward(request, response);

		} catch (Exception ex) {
			request.setAttribute("accountId", Long.parseLong("0"));
			request.setAttribute("errorMessage", "Something went wrong! Account couldn't added!");
			request.getRequestDispatcher("AddorUpdateAccount.jsp").forward(request, response);
			throw new CustomException(ex);
		}
	}

	private void updateAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {
		String accStr = "";
		try {
			BankingLogic logics = (BankingLogic) request.getServletContext().getAttribute("logicApi");

			AccountData accountObj = new AccountData();

			String str1 = request.getParameter("AccountNumber");
			Validator.validateString(str1);
			accStr = str1;
			accountObj.setAccID(Long.parseLong(str1));

			String str2 = request.getParameter("CustomerID");
			Validator.validateString(str2);
			accountObj.setCustID(Long.parseLong(str2));

			String str3 = request.getParameter("AccType");
			Validator.validateString(str3);
			str3 = str3.trim();
			Validator.validateString(str3);
			accountObj.setAccType(str3);

			String location = request.getParameter("Location");
			Validator.validateString(location);
			location = location.trim();
			Validator.validateString(location);
			accountObj.setLocation(location);

			String str4 = request.getParameter("Balance");
			Validator.validateString(str4);
			accountObj.setBalance(Double.parseDouble(str4));

			logics.updateAccountInfo(accountObj);

			request.setAttribute("successMessage", "Account Updated Successfully!");

			request.getRequestDispatcher("AdminDashBoard.jsp").forward(request, response);

		} catch (Exception ex) {
			request.setAttribute("accountId", Long.parseLong(accStr));
			request.setAttribute("errorMessage", "Something went wrong! Account couldn't updated!");
			request.getRequestDispatcher("AddorUpdateAccount.jsp").forward(request, response);
			throw new CustomException(ex);
		}
	}

	private void deleteAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {

		try {
			BankingLogic logics = (BankingLogic) request.getServletContext().getAttribute("logicApi");

			String str1 = request.getParameter("customerId");
			Validator.validateString(str1);
			long customerId = Long.parseLong(str1);

			String str2 = request.getParameter("accountId");
			Validator.validateString(str2);
			long accountId = Long.parseLong(str2);

			logics.setAccountStatus(customerId, accountId, false);
			request.getRequestDispatcher("AdminDashBoard.jsp").forward(request, response);
		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}

	private void activateAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {

		try {
			BankingLogic logics = (BankingLogic) request.getServletContext().getAttribute("logicApi");

			String str1 = request.getParameter("customerId");
			Validator.validateString(str1);
			long customerId = Long.parseLong(str1);

			String str2 = request.getParameter("accountId");
			Validator.validateString(str2);
			long accountId = Long.parseLong(str2);

			logics.setAccountStatus(customerId, accountId, true);
			request.getRequestDispatcher("AdminDashBoard.jsp").forward(request, response);
		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}

	private void getAccounts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {

		try {
			BankingLogic logics = new BankingLogic();
			Map<Long, Map<Long, AccountData>> accountsMap = logics.getAllActiveAccounts();

			response.getWriter().write(MapperUtility.accountMapToJson(accountsMap));
		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}

}
