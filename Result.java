public class Result {
	//Variables
	public int sampleSize;
	public long resultTime;
	public int matchCount;
	
	//Constructor to create the result
	public Result(int size, long timer, int count) {
		this.sampleSize = size;
		this.resultTime = timer;
		this.matchCount = count;
	}
	
	//PRINT OUT RESULT
	void print() {
		System.out.println(" -- SampleSize: " + this.sampleSize + " records, ResultTime: " + this.resultTime + "ms, Matches: " + this.matchCount + " ---");
	}
	
}
