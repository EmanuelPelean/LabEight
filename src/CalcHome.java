import java.util.Scanner;

/**
 * 
 */

/**
 * @author Manu
 *
 */
public class CalcHome {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		// Ask user how many players he wants to compare and create 2D array
		int playersTotal = Math.abs(Validator.getInt(scan,
				"Welcome to the batting statistics calculator. How many players do you want to enter? "));
		int[][] arrBatters = new int[playersTotal][7];

		// Boolean used to verify if user wants to repeat the program
		boolean proceed = true;
		while (proceed) {
			int player = 0;

			// We get the times at bat for each user and pass the values to the methods
			// below
			for (int i = 0; i < playersTotal; i++) {

				int atBats = Math.abs(Validator.getInt(scan,
						"Please enter the number of times at bat for player " + player + ": ", 0, 4));

				addAtBats(player, atBats, arrBatters); // Method to add atBats to array[player][0]
				addBasesEarned(player, atBats, arrBatters, scan); // Method to add bases earned to array[player][1] tp
																	// array[player][4]
				calculateMeaningfulBats(player, arrBatters); // Method to add bats with bases to array[player][5]
				calculateTotalBases(player, arrBatters);// Method to add total bases to array[player][6]
				System.out.println("Batting average: " + calcAve(player, arrBatters));
				System.out.println("Slugging percentage: " + calcSlug(player, arrBatters));

				player++;
			}

			String response = Validator.getStringYN(scan, "Start over?");

			if (response.equalsIgnoreCase("n")) {
				proceed = false;

				System.out.println("Thank you for using the batting statistics calculator.");

			} else {

			}

		}
		scan.close();

	}

	public static double calcAve(int playerVal, int[][] arr) {

		double ave = 0.0;

		for (int[] num : arr) {
			double a = arr[playerVal][0];
			double b = arr[playerVal][5];

			ave = b / a;
			String.format("%.3f", ave);
		}
		return ave;
	}

	public static double calcSlug(int playerVal, int[][] arr) {

		double slug = 0.0;

		for (int[] num : arr) {
			double a = arr[playerVal][0];
			double c = arr[playerVal][6];

			slug = c / a;
			String.format("%.3f", slug);
		}
		return slug;
	}

	public static void calculateTotalBases(int playerVal, int[][] arr) {
		int count = 0;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 1; j <= 4; j++) {

				if (arr[i][j] != 0) {
					count += arr[i][j];

				}
			}
			arr[playerVal][6] = count;

			count = 0;
		}

		playerVal++;

	}

	public static void calculateMeaningfulBats(int playerVal, int[][] arr) {
		int count = 0;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 1; j <= 4; j++) {

				if (arr[i][j] != 0) {
					count++;
					arr[playerVal][5] = count;

				}
			}
			count = 0;
		}

		playerVal++;

	}

	public static int[][] addBasesEarned(int playerVal, int atBatsVal, int[][] arr, Scanner sc) {

		for (int i = 1; i <= atBatsVal; i++) {
			arr[playerVal][i] = Validator.getInt(sc,
					"Please enter how many bases earned for batter " + playerVal + " at bat #" + i + "\n");
		}

		return arr;
	}

	public static int[][] addAtBats(int playerVal, int atBatsVal, int[][] arr) {

		arr[playerVal][0] = atBatsVal;

		return arr;
	}

}
