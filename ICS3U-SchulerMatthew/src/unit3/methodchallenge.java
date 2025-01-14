package unit3;

public class methodchallenge {
	public static void main(String[] args) {
		// Complete the function below. Don't forget to comment.
		double d1 = distance(-2, 1, 1, 5);
		System.out.println(" (-2,1) to (1,5) => " + d1);

		double d2 = distance(-2, -3, -4, 4);
		System.out.println(" (-2,-3) to (-4,4) => " + d2);

		System.out.println(" (2,-3) to (-1,-2) => " + distance(2, -3, -1, -2));

		System.out.println(" (4,5) to (4,5) => " + distance(4, 5, 4, 5));
	}

	/**
	 * (Description)
	 * 
	 * @param x1 ->
	 * @param y1 ->
	 * @param x2 ->
	 * @param y2 ->
	 * @return
	 */
	public static double distance(int x1, int y1, int x2, int y2) {
		double x2x1 = x2 - x1;
		double y2y1 = y2 - y1;
		double d1 = Math.sqrt(Math.pow(x2x1, 2)) + Math.sqrt(Math.pow(y2y1, 2)); 
		// put your code up in here
		return d1;
	}
}
