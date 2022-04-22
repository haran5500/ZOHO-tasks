package account;


public class AccountData {

	private long accountID;
	private long customerID;
	private String accountType;
	private String location;
	private double accBalance;
	private boolean status=true;
	private String ifscCode;
	
	public long getAccID()
	{
		return accountID;
	}
	
	public void setAccID(long accID)
	{
		accountID=accID;
	}
	
	public long getCustID()
	{
		return customerID;
	}
	
	public void setCustID(long custID)
	{
		customerID=custID;
	}
	
	public String getAccType()
	{
		return accountType;
	}
	
	public void setAccType(String accType)
	{
		accountType=accType;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public void setLocation(String locName)
	{
		location=locName;
	}
	
	public double getBalance()
	{
		return accBalance;
	}
	
	public void setBalance(double balAmt)
	{
		accBalance=balAmt;
	}
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String toString()
	{
		return String.format("Account Number:%d Customer ID:%d Account Type:%s Location:%s IFSC Code:%s Balance:%f Active:%b ", getAccID(),getCustID(),getAccType(),getLocation(),getIfscCode(),getBalance(),getStatus());
	}
}
