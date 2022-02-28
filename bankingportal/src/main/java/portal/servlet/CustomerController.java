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
			case "/addCustomer": {
				addNewCustomer(request, response);
				break;
			}
			case "/updateAccount": {
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

			response.sendRedirect("CustomerList.jsp");

		} catch (Exception ex) {
			throw new CustomException(ex);
		}
	}
}
