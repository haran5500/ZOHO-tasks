package bankprojectjson.executors;

import java.text.MessageFormat;

import bankprojectjson.account.AccountData;
import bankprojectjson.customer.CustomerData;
import bankprojectjson.logicalapi.ApiBridge;
import inout.Reader;
import userexception.CustomException;

public class Executor {
	ApiBridge logics = new ApiBridge();

	// To Load Default Values Maps
	public void loadOnStart() throws CustomException {
		logics.loadDefaultCustomers();
		logics.loadDefaultAccounts();
	}

	// Case One
	public void accountOpening() {
		Reader.print("Account Opening!");
		Reader.print("1. New Customer 2. Existing Customer");
		int cusType = Reader.getInt("Enter the type of customer:");

		switch (cusType) {
		case 1: {
			newCustomer();
			break;
		}
		case 2: {
			existingCustomer();
			break;
		}
		}
	}

	public void existingCustomer() {

		long custID = Reader.getLong("Enter the Customer ID:");
		newAccount(custID);
	}

	public void newCustomer() {
		Reader.print("Enter the Customer Details:");
		CustomerData customer = new CustomerData();

		String name = Reader.getString("Enter the name:");
		customer.setName(name);
		String city = Reader.getString("Enter the City Name:");
		customer.setCity(city);
		String gender = Reader.getString("Enter Gender:");
		customer.setGender(gender);
		long mobileNo = Reader.getLong("Enter the Mobile Number:");
		customer.setMobileNo(mobileNo);
		try {
			logics.addNewCustomer(customer);
		} catch (CustomException ex) {
			Reader.print(ex.getMessage());

		}
		long custID = customer.getId();

		newAccount(custID);

		Reader.print("Customer Added Successfully!");
	}

	public void newAccount(long customerID) {
		AccountData account = new AccountData();

		String[] locations = logics.loadBranches();

		Reader.print("Bank Branches:");
		Reader.print("Location \t ID");
		for (int iter = 0; iter < locations.length; iter++) {
			Reader.print(locations[iter] + "\t" + (iter + 1));
		}

		int index = Reader.getInt("Select the preferred branch:");
		account.setLocation(locations[index - 1]);
		long balanceAmt = Reader.getLong("Enter the opening balance to deposit:");
		account.setBalance(balanceAmt);

		String[] accTypes = { "Savings", "Current", "Deposit" };
		Reader.print("Account Types!\nTypes\tID");
		for (int i = 0; i < accTypes.length; i++) {
			Reader.print(accTypes[i] + "\t" + (i + 1));
		}

		int accIndex = Reader.getInt("Select th preferred Account Type:");
		account.setAccType(accTypes[accIndex - 1]);
		try {
			logics.addNewAccount(customerID, account);
		} catch (CustomException ex) {
			Reader.print(ex.getMessage());
		}
		Reader.print("Account Created Successfully!");
	}

	// Case Two
	public void getAllCustomer() {
		try {
			Reader.print("the Customer Details:\n" + logics.getAllCustomerDetails());
		} catch (CustomException ex) {
			Reader.print(ex.getMessage());
		}
	}

	// Case Three
	public void getCustomerDetailByID() {
		long custID = Reader.getLong("Enter the CustomerID:");
		try {
			Reader.print(
					"the Details of Customer of ID:" + custID + " is --->\n" + logics.getCustomerDetailsByID(custID));
		} catch (CustomException ex) {
			Reader.print(ex.getMessage());
		}
	}

	// Case Four
	public void getAllAccountDetails() {

		try {
			Reader.print("The Account Details:" + logics.getAllAccounts());
		} catch (CustomException ex) {
			Reader.print(ex.getMessage());
		}
	}

	// Case Five
	public void getAccountByCustomerID() {

		long customerID = Reader.getLong("Enter the Customer ID:");

		try {
			Reader.print("The Account Details of Customer ID:" + customerID + "is --->\n"
					+ logics.getAccountByCustomerID(customerID));
		} catch (CustomException ex) {
			Reader.print(ex.getMessage());
		}
	}

	// Case Six
	public void getAccountDetailsByAccNum() {

		long customerID = Reader.getLong("Enter the Customer ID:");
		long accountNum = Reader.getLong("Enter the Account Number:");

		try {
			Reader.print(MessageFormat.format("The Account Details of Customer ID {0} and Account Number {1} is {2}",
					customerID, accountNum, logics.getAccountByAccountNum(customerID, accountNum)));
		} catch (CustomException ex) {
			Reader.print(ex.getMessage());
		}
	}

	// Case Seven
	public void getActiveAccounts() {
		long customerID = Reader.getLong("Enter the Customer ID:");

		try {

			Reader.print(MessageFormat.format("The Active Account Details of Customer ID {0} is {1}", customerID,
					logics.getActiveAccounts(customerID)));

		} catch (CustomException ex) {
			Reader.print(ex.getMessage());
		}
	}

	// Case Eight
	public void getInactiveAccounts() {
		long customerID = Reader.getLong("Enter the Customer ID:");

		try {
			Reader.print(MessageFormat.format("The Inactive Account Details of Customer ID {0} is {1}", customerID,
					logics.getInActiveAccounts(customerID)));

		} catch (CustomException ex) {
			Reader.print(ex.getMessage());
		}
	}

	// Case Nine
	public void getBalanceByAccNum() {
		long customerID = Reader.getLong("Enter the Customer ID:");
		long accountNum = Reader.getLong("Enter the Account Number:");

		try {
			Reader.print(MessageFormat.format("The Balance of Account Number {0} of Customer ID {1} is {2}", customerID,
					accountNum, logics.getBalance(customerID, accountNum)));
		} catch (CustomException ex) {
			Reader.print(ex.getMessage());
		}

	}

	// Case Ten
	public void depositAmount() {

		long customerID = Reader.getLong("Enter the Customer ID:");
		long accountNum = Reader.getLong("Enter the Account Number:");
		long amount = Reader.getLong("Enter the Amount to be deposited:");

		try {

			logics.depositAmount(customerID, accountNum, amount);
			Reader.print("Deposited Successfully!");
		} catch (CustomException ex) {
			Reader.print(ex.getMessage());
		}

	}

	// Case Eleven
	public void withdrawAmount() {
		long customerID = Reader.getLong("Enter the Customer ID:");
		long accountNum = Reader.getLong("Enter the Account Number:");
		long amount = Reader.getLong("Enter the Amount to be withdrawn:");

		try {
			logics.withdrawAmount(customerID, accountNum, amount);
			Reader.print("Withdrawal Successful!");
		} catch (CustomException ex) {
			Reader.print(ex.getMessage());
		}
	}

	// case Twelve
	public void changeStatus() {

		long customerID = Reader.getLong("Enter the Customer ID:");
		long accountNum = Reader.getLong("Enter the Account Number:");
		boolean status = Reader.getBool("Enter the status as true or false:");

		try {

			logics.setAccountStatus(customerID, accountNum, status);
			Reader.print("Status set success!");

		} catch (CustomException ex) {
			Reader.print(ex.getMessage());
		}
	}

	public static void main(String[] args) {
		Executor execute = new Executor();

		Reader.print("Welcome to XBD Bank!");

		try {
			execute.loadOnStart();
		} catch (CustomException ex) {
			ex.printStackTrace();
		}

		Reader.print(
				"Banking Operations: \n1. New Account Opening\n2.Get All Customer Details\n3. Get Customer Details by ID\n4.Get Details of All Account\n5. Get Account Details by Customer ID\n6. Get Details by Account Number\n 7.Get Active Accounts of a Customer\n8. Get Inactive Accounts of a Customer\n9. Get Balance by Account Number\n10. Deposit Amount\n11. Withdraw Amount\n12. Change Account Status\n13. Exit");
		int option;
		do {
			option = Reader.getInt("Enter the option:");

			switch (option) {
			case 1: {
				execute.accountOpening();
				break;
			}

			case 2: {
				execute.getAllCustomer();
				break;
			}
			case 3: {
				execute.getCustomerDetailByID();
				break;
			}
			case 4: {
				execute.getAllAccountDetails();
				break;
			}
			case 5: {
				execute.getAccountByCustomerID();
				break;
			}
			case 6: {
				execute.getAccountDetailsByAccNum();
				break;
			}
			case 7: {
				execute.getActiveAccounts();
				break;
			}
			case 8: {
				execute.getInactiveAccounts();
				break;
			}
			case 9: {
				execute.getBalanceByAccNum();
				break;
			}
			case 10: {
				execute.depositAmount();
				break;
			}
			case 11: {
				execute.withdrawAmount();
				break;
			}
			case 12: {
				execute.changeStatus();
				break;
			}
			case 13: {
				Reader.close();
				Reader.print("Exit Point!");
				break;
			}
			default: {
				Reader.print("Invalid Option!");
				break;
			}
			}
		} while (option != 13);
	}
}
