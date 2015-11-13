
import java.util.*;

public class SequentialCracker {
	
	private int minLength;
	
	private int maxLength;
	
	private String key;
	
	private ArrayList<Character> possibleChars;
	
	private HashMap<Character, Integer> charsMap;
	
	public static void main(String[] args) {
		if (args.length < 3) {
			badUsage();
		}
		int minLength = -1;
		int maxLength = -1;
		try {
			minLength = Integer.parseInt(args[0]);
			maxLength = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			badUsage();
		}
		if (minLength > maxLength || minLength < 1) {
			badArgs();
		}
		SeqentialCracker sc = new SequentialCracker(minLength, maxLength, args[2]);
		long startTime = System.getCurrentTimeMillis();
		sc.crack();
	}
	
	private static void badArgs() {
		System.err.println("Max length must be greater or equal to min length; both must be greater than 0!");
		System.exit(1);
	}
	
	private static void badUsage() {
		System.err.println("Usage: SequentialCracker [min length] [max length] [correct key]");
		System.exit(1);
	}
	
	public SequentialCracker(int minLength, int maxLength, String key) {
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.key = key;
		createPossibilities();
	}
	
	public String crack() {
		StringBuilder str = new StringBuilder();
		// assume that min length is 1
	
		
		int curPosition = 0;
		
		if (str.length() < curPosition) {
			str.append(' ');
		}
		for (char c : possibleChars)  {
			str.replace(curPosition, curPostion + 1, "" + c);
			if (key.equals(str)) {
				return str.toString();
			}
		}
	}
	
	private boolean incrementPosition(StringBuilder str, int position) {
		if (str.charAt(position) == possibleChars.get(possibleChars.size() - 1)) {
			if (position + 1 > maxLength) {
				System.err.println("You fail. Die.");
				System.exit(2);
			}
			str.replace(position, position + 1, possibleChars.get(0));
			incrementPosition(str, position + 1);
		} else {
			int currentIndex = charsMap.get(str.charAt(position));
			char nextChar = possibleChars.get(currentIndex + 1);
			str.replace(position, position + 1, nextChar);
		}
		
	}
	
	private void createPossibilities() {
		addAll('0', '9');
		addAll('a', 'z');
		addAll('A', 'Z');
	}
	
	private void addAll(char start; char end) {
		for (char i = start; i <= end; i++) {
			possibleChars.add(i);
			charsMap.put(i, possibleChars.size() - 1);
		}
	}
}
