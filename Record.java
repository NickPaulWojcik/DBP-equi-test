// Record class to produce records for the TestMethods main function
public class Record {
	//Three attributes to reflect specifications
	private Integer attributeOne;
	private String attributeTwo;
	private long attributeThree;
	
	//constructor to create a record
	public Record (Integer attOne, String attTwo, long attThree) {
		this.attributeOne = attOne;
		this.attributeTwo = attTwo;
		this.attributeThree = attThree;
	}
	
	
	void print () {
		System.out.println("( " + attributeOne + "    " + attributeTwo + "    " + attributeThree + " )");
	}
	int getAttributeOne () {
		return this.attributeOne;
	}
	String getAttributeTwo () {
		return this.attributeTwo;
	}
	long getAttributeThree () {
		return this.attributeThree;
	}

}
