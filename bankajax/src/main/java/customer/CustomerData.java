package customer;

public class CustomerData {

	private long customerId=this.getId();
	private String customerName=this.getName();
	private String gender=this.getGender();
	private long mobileNumber=this.getMobileNo();
	private String city=this.getCity();
	private boolean Status = true;

	public long getId() {
		return customerId;
	}

	public void setId(long custID) {
		customerId = custID;
	}

	public String getName() {
		return customerName;
	}

	public void setName(String custName) {
		customerName = custName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String genderVal) {
		gender = genderVal;
	}

	public long getMobileNo() {
		return mobileNumber;
	}

	public void setMobileNo(long mobNo) {
		mobileNumber = mobNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String cityName) {
		city = cityName;
	}

	public boolean getStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	public String toString() {
		return String.format("ID:%d Name:%s Gender:%s city:%s mobile:%d status:"+getStatus(), getId(), getName(), getGender(), getCity(),
				getMobileNo());
	}
}
