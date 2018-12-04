import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class TestMethods {
	public static String alphaNumCharset = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static int stringSize = 1000;
	public static int sampleStartingSize = 0;
	public static int runCount = 81;
	
	public static Random rng = new Random();
	public static Record[] tableS;
	public static Record[] tableR;
	public static Hashtable<Object, Object> tableHash;
	public static ArrayList<Record> tableHashOverflow;
	public static ArrayList<Result> results = new ArrayList<Result>();
	public static int matches = 0;
	public static long timer;

	public static void main(String[] args) {
		
		//SET TEST SAMPLE CYCLE COUNT (runCount)
		for (int h = 0; h < runCount; h++) {
			
			//SET TEST SAMPLE INCREMENTER
			sampleStartingSize = sampleStartingSize + 2000;
			
			//CREATE TABLE S
			tableS = new Record[sampleStartingSize];
			for (int i = 0; i < sampleStartingSize; i++) {
				tableS[i] = new Record((Integer) generateRandomInt(1, 30), 
						generateRandomCharString(alphaNumCharset, stringSize), i);
			}		
			
			//CREATE TABLE R
			tableR = new Record[sampleStartingSize];
			for (int i = 0; i < sampleStartingSize; i++) {
				tableR[i] = new Record((Integer) generateRandomInt(1, 30), 
						generateRandomCharString(alphaNumCharset, stringSize), i);
			}

			//PERFORM AND TIME THE EQUIJOIN
			timer = System.currentTimeMillis();
			equiJoin(tableS, tableR, sampleStartingSize);
			timer = (System.currentTimeMillis() - timer );
			
			//ADD RESULT TO ARRAY LIST
			results.add(new Result(sampleStartingSize, timer, matches));
			
			for(int i = 0; i < results.size(); i++) {
				(results.get(i)).print();
			}
			
/*			//Creates a hashtable to store records, and an arraylist to handle the overflow
			tableHash = new Hashtable<Object, Object>();
			tableHashOverflow = new ArrayList<Record>();
			
			//Populates the hashtable with tableR
			for(int j = 0; j < tableR.length; j++) {
				if(tableHash.containsKey(tableR[j].getAttributeOne())) {
					tableHashOverflow.add(tableR[j]);
				}
				tableHash.put(tableR[j].getAttributeOne(),tableR[j].getAttributeTwo());
			}
			
			//PERFORM AND TIME THE HASH EQUIJOIN
			timer = System.currentTimeMillis();
			equiJoinHash(tableS, tableHash, tableHashOverflow);
			timer = (System.currentTimeMillis() - timer);
			
			//ADD RESULT TO ARRAY LIST
			results.add(new Result(sampleStartingSize, timer, matches));
			System.out.println(h);
*/		}
		//PRINT RESULTS
		for(int i = 0; i < results.size(); i++) {
			(results.get(i)).print();
		}
/*		//PRINT OVERFLOW TABLE
		for(int i = 0; i < tableHashOverflow.size(); i++) {
			System.out.println(tableHashOverflow.get(i));
		}
*/		
	}
	
	// ********************** GENERATE INT ******************************
	private static int generateRandomInt (int low, int high) {
		int result;
		result = rng.nextInt(high - low);
		result = result + low;
		return result;
	}
	
	// ********************* GENERATE STRING ****************************
	private static String generateRandomCharString(String charset, int size) {
		StringBuilder result = new StringBuilder();
		Random random = new Random();
		
		for (int i = 0; i < size; i++) {
			result.append(charset.charAt(random.nextInt(charset.length())));
		}
		return result.toString();
	}
	
	// ****************** PERFORM 1D-1D equijoin *************************
	private static void equiJoin (Record[] a, Record[] b, int size) {
		ArrayList<Record> result = new ArrayList<Record>();
		matches = 0;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(((a[i]).getAttributeOne()) == ((b[j]).getAttributeOne())) {
					result.add(new Record((a[i].getAttributeOne()), (a[j].getAttributeTwo()),
							(a[j].getAttributeThree())));
					matches++;
				}
			}
		}
	}
	
	// ****************** PERFORM 1D-Hash equijoin **********************
	private static void equiJoinHash(Record[] a, Hashtable b, ArrayList c) {
		for(int i = 0; i < a.length; i++) {
			if(b.containsKey(a[i].getAttributeOne())){
				//hit
				matches++;
			}
			else if(c.contains(new Record(a[i].getAttributeOne(), a[i].getAttributeTwo(), 
					a[i].getAttributeThree()))){
				//hit
				matches++;
			}
			else if(b.get(a[i].getAttributeOne()) == null) {
				//miss
				System.out.println("miss");
			}
		}	
	}
}
