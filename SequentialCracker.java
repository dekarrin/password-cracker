
public class SequentialCracker {
	
	private int minLength;
	
	private int maxLength;
	
	private String key;
	
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
	}
	
	public void crack() {
		long time = System.getCurrentTimeMillis();
	}
}
