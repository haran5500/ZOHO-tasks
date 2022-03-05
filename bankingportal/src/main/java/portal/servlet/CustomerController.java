package portal.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banking.BankingLogic;
import customer.CustomerData;
import userexception.CustomException;

/**
 * Servlet implementation class CustomerController
 */
//@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
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
		doGet(request, response);

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

		}
	}

	private void addNewCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {

		try {

			CustomerData customerObj = new CustomerData();

			customerObj.setName(request.getParameter("CustomerName"));
			customerObj.setGender(request.getParameter("Gender"));
			customerObj.setCity(request.getParameter("City"));
			customerObj.setMobileNo(Long.parseLong(request.getParameter("MobileNo")));

			logics.addNewCustomer(customerObj);

			request.getRequestDispatcher("CustomerList.jsp").forward(request, response);

		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}

	private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {

		try {

			CustomerData customerObj = new CustomerData();

			System.out.println("id:" + request.getParameter("CustomerID"));
			long customerId = Long.parseLong(request.getParameter("CustomerID"));
			customerObj.setId(customerId);
			customerObj.setName(request.getParameter("CustomerName"));
			customerObj.setGender(request.getParameter("Gender"));
			customerObj.setCity(request.getParameter("City"));
			customerObj.setMobileNo(Long.parseLong(request.getParameter("MobileNo")));

			logics.updateCustomerInfo(customerObj);

			request.getRequestDispatcher("CustomerList.jsp").forward(request, response);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new CustomException(ex);
		}
	}

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CustomException {
		try {
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
			long customerId = Long.parseLong(request.getParameter("customerId"));
			logics.changeCustomerStatus(customerId, true);

			request.getRequestDispatcher("CustomerList.jsp").forward(request, response);
		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}
}
