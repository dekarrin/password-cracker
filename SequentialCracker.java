
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
		if (minLength > maxLength || minLength < 1 || args[2].length() < minLength || args[2].length() > maxLength ) {
			badArgs();
		}
		SequentialCracker sc = new SequentialCracker(minLength, maxLength, args[2]);
		long startTime = System.currentTimeMillis();
		String pass = sc.crack();
		long runTime = System.currentTimeMillis() - startTime;
		//System.out.println(String.format("Found the password '%s' in %.3f seconds", pass, (runTime / 1000.0)));
	}
	
	private static void badArgs() {
		System.err.println("Max length must be greater or equal to min length; both must be greater than 0!");
		System.err.println("Also password length has to be between the two.");
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
		charsMap = new HashMap<Character, Integer>();
		possibleChars = new ArrayList<Character>();
		createPossibilities();
	}
	
	public String crack() {
		for (int i = minLength; i < maxLength; i++) {
			// start with a string that is all the first char
			StringBuilder str = new StringBuilder();
			StringBuilder endStr = new StringBuilder();
			for (int j = 0; j < i; j++) {
				str.append(possibleChars.get(0));
				endStr.append(possibleChars.get(possibleChars.size() - 1));
			}
			
			// For the love of 神様 don't let the password be all the first char, but just in case:
			System.out.println(str);
			if (key.equals(str.toString())) {
				return str.toString();
			}
			
			// okay, now try all combos for the current length (i):
			do {
				incrementPosition(str, 0);
				System.out.println(str);
				if (key.equals(str.toString())) {
					return str.toString();
				}
			} while (!str.toString().equals(endStr.toString()));
		}
		return null;
	}
	
	private void incrementPosition(StringBuilder str, int position) {
		if (str.charAt(position) == possibleChars.get(possibleChars.size() - 1)) {
			if (position + 1 > str.length()) {
				System.err.println("You fail. Die.");
				System.exit(2);
			}
			str.replace(position, position + 1, "" + possibleChars.get(0));
			incrementPosition(str, position + 1);
		} else {
			int currentIndex = charsMap.get(str.charAt(position));
			char nextChar = possibleChars.get(currentIndex + 1);
			str.replace(position, position + 1, "" + nextChar);
		}
		
	}
	
	private void createPossibilities() {
		addAll('0', '9');
		addAll('a', 'z');
		addAll('A', 'Z');
	}
	
	private void addAll(char start, char end) {
		for (char i = start; i <= end; i++) {
			possibleChars.add(i);
			charsMap.put(i, possibleChars.size() - 1);
		}
	}
}
