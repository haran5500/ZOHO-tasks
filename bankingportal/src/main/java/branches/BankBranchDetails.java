package branches;

public class BankBranchDetails {

	String[] branches = { "Chennai", "Trichy", "Salem", "Coimbatore", "Karaikudi", "Madurai", "Pollachi" };
	String[] ifscCodes = { "XBDB0001", "XBDB0002", "XBDB0003", "XBDB0004", "XBDB0005", "XBDB0006", "XBDB0007" };
	String[] accountTypes = { "Savings", "Current", "Deposit" };

	public String[] getBranches() {
		return branches;
	}

	public String[] getAccountTypes() {
		return accountTypes;
	}

	public String fetchIFSCCode(String branch) {
		String ifsc = "";
		for (int i = 0; i < branches.length; i++) {
			if (branches[i].equals(branch)) {
				ifsc = ifscCodes[i];
			}
		}
		return ifsc;
	}
}
