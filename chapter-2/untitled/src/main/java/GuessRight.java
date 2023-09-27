public class GuessRight {
	public static void main(String[] args) {
		int number = (int) (Math.random() * 5 + 1);
		while (true) {
			System.out.println("What number do I have in mind between 1 and 5?");
			int guess = new java.util.Scanner(System.in).nextInt();
			if (guess < 1 || guess > 5) {
				System.out.println("Only numbers between 1 and 5!");
				continue;
			}
			if (number == guess) {
				System.out.println("Good guess!");
				break; // End of loop
			} else {
// More beautiful code
			}
		}
	}
}
