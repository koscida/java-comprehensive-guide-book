import java.util.Scanner;
public class Calculator {
	public static void main( String[] args ) {
		String input;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("--Calculator--\nadd = addition\nsub = subtraction\nmul = multiply\ndiv = divide\navg = average\nq = quit");
			input = sc.next();

			if(input.equalsIgnoreCase("add")) {
				System.out.println("Sum: " + sum(getAnyNumbers()));
			} else if(input.equalsIgnoreCase("sub")) {
				System.out.println("Subtraction: " + sub(getTwoNumbers()));
			} else if(input.equalsIgnoreCase("mul")) {
				System.out.println("Product: " + multiply(getAnyNumbers()));
			} else if(input.equalsIgnoreCase("div")) {
				System.out.println("Division: " + divide(getTwoNumbers()));
			} else if(input.equalsIgnoreCase("avg")) {
				System.out.println("Average: " + avg(getAnyNumbers()));
			}

			System.out.println("");
		} while(!input.equalsIgnoreCase("q"));
	}

	// Calculator functions
	static double avg(double[] numbers) {
		return sum(numbers) / numbers.length;
	}

	static double sum(double[] numbers) {
		double sum = 0;
		for(double num : numbers)
			sum += num;
		return sum;
	}

	static double sub(double[] numbers) {
		return numbers[0] - numbers[1];
	}

	static double multiply(double[] numbers) {
		double product = 1;
		for(double num : numbers)
			product *= num;
		return product;
	}

	static double divide(double[] numbers) {
		return numbers[0] - numbers[1];
	}

	// Calculator Inputs

	static double[] getAnyNumbers() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter how many numbers you want to enter:");
		int len = Integer.valueOf(sc.next());

		return getNumbers(len);
	}

	static double[] getTwoNumbers() {
		return getNumbers(2);
	}

	static double[] getNumbers(int len) {
		Scanner sc = new Scanner(System.in);
		String input;
		double[] numbers = new double[len];

		System.out.println("Enter your numbers one-by-one: ");
		for(int i=0;i<len;i++) {
			input = sc.next();
			numbers[i] = Double.valueOf(input);
		};

		return numbers;
	}
}
