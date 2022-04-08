package listprinter;

import java.util.HashMap;
import java.util.Map;

public class ListData {

	public void printData() {
		Map<Integer, String> studentRecord = new HashMap<Integer, String>();

		studentRecord.put(1001, "Name:Ramu Age:21");
		studentRecord.put(1002, "Name:Raju Age:20");
		studentRecord.put(1003, "Name:Aiswarya Age:23");

		System.out.println("Student Records:");
		for (Integer key : studentRecord.keySet()) {
			System.out.println(studentRecord.get(key));
		}
	}

	public static void main(String[] args) {
		ListData data = new ListData();
		data.printData();
		///////////////////////////////////////////
		for(int i=0; i<3;i++) {
			int j=i;
			System.out.println(j);
		}
	}

}
