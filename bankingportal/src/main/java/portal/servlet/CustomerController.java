package portal.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banking.BankingLogic;
import customer.CustomerData;
import userexception.CustomException;
import validatorutil.Validator;

/**
 * Servlet implementation class CustomerController
 */
//@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerController() {
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
		try {

			// response.getWriter().append("Served at: ").append(request.getContextPath());

			String actionPath = request.getServletPath();
			switch (actionPath) {

			case "/deleteCustomer": {

				deleteCustomer(request, response);
				break;
			}
			case "/activateCustomer": {
				activateCustomer(request, response);
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
		// doGet(request, response);

		try {
			String actionType = request.getServletPath();
			switch (actionType) {
			case "/addCustomer": {
				addNewCustomer(request, response);
				break;
			}
			case "/updateInfo": {
				updateCustomer(request, response);
				break;
			}
			}
		} catch (CustomException ex) {
			ex.getMessage();
		}
	}

	private void addNewCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {

		try {
			BankingLogic logics = (BankingLogic) request.getServletContext().getAttribute("logicApi");

			CustomerData customerObj = new CustomerData();

			String str1 = request.getParameter("CustomerName");
			Validator.validateString(str1);
			str1 = str1.trim();
			Validator.validateString(str1);
			customerObj.setName(str1);

			String str2 = request.getParameter("Gender");
			Validator.validateString(str2);
			str2 = str2.trim();
			Validator.validateString(str2);
			customerObj.setGender(str2);

			String str3 = request.getParameter("City");
			Validator.validateString(str3);
			str3 = str3.trim();
			Validator.validateString(str3);
			customerObj.setCity(str3);

			String str4 = request.getParameter("MobileNo");
			Validator.validateString(str4);
			customerObj.setMobileNo(Long.parseLong(str4));

			logics.addNewCustomer(customerObj);

			request.setAttribute("successMessage", "Customer Added Successfully!");
			request.getRequestDispatcher("CustomerList.jsp").forward(request, response);

		} catch (Exception ex) {
			request.setAttribute("CustomerId", Long.parseLong("0"));
			request.setAttribute("errorMessage", "Something went wrong! Customer couldn't added!");
			request.getRequestDispatcher("AddorUpdateCustomer.jsp").forward(request, response);

			throw new CustomException(ex);
		}
	}

	private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {
		String custId = "";
		try {
			BankingLogic logics = (BankingLogic) request.getServletContext().getAttribute("logicApi");

			CustomerData customerObj = new CustomerData();

			String str1 = request.getParameter("CustomerID");
			Validator.validateString(str1);
			custId = str1;
			long customerId = Long.parseLong(str1);
			customerObj.setId(customerId);

			String str2 = request.getParameter("CustomerName");
			Validator.validateString(str2);
			str2 = str2.trim();
			Validator.validateString(str2);
			customerObj.setName(str2);

			String str3 = request.getParameter("Gender");
			Validator.validateString(str3);
			str3 = str3.trim();
			Validator.validateString(str3);
			customerObj.setGender(str3);

			String str4 = request.getParameter("City");
			Validator.validateString(str4);
			str4 = str4.trim();
			Validator.validateString(str4);
			customerObj.setCity(str4);

			String str5 = request.getParameter("MobileNo");
			Validator.validateString(str5);
			customerObj.setMobileNo(Long.parseLong(str5));

			logics.updateCustomerInfo(customerObj);
			System.out.println("values:" + customerObj);

			request.setAttribute("successMessage", "Customer Updated Successfully!");
			request.getRequestDispatcher("CustomerList.jsp").forward(request, response);

		} catch (Exception ex) {
			request.setAttribute("CustomerId", Long.parseLong(custId));

			request.setAttribute("errorMessage", "Something went wrong! Customer couldn't updated!");
			request.getRequestDispatcher("AddorUpdateCustomer.jsp").forward(request, response);

			ex.printStackTrace();
			throw new CustomException(ex);
		}
	}

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {
		try {
			BankingLogic logics = (BankingLogic) request.getServletContext().getAttribute("logicApi");

			long customerId = Long.parseLong(request.getParameter("customerId"));
			logics.changeCustomerStatus(customerId, false);

			request.getRequestDispatcher("CustomerList.jsp").forward(request, response);
		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}

	private void activateCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {
		try {
			BankingLogic logics = (BankingLogic) request.getServletContext().getAttribute("logicApi");

			long customerId = Long.parseLong(request.getParameter("customerId"));
			logics.changeCustomerStatus(customerId, true);

			request.getRequestDispatcher("CustomerList.jsp").forward(request, response);
		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}
}
