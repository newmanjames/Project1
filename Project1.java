import java.util.Locale;
import java.util.Scanner;
import java.util.Arrays;

public class Project1 {

    public static void main(String[] args) {
        enterNewList();
    }

    /**
     * Gets a list of numbers from the user as a string and converts it to an integer array
     */
    public static void getInput() {
        Scanner in = new Scanner(System.in);
        String fromConsole = in.nextLine();
        String[] numberText = fromConsole.split(" ");
        int[] input = new int[numberText.length];
        for (int i = 0; i < numberText.length; ++i) {
            input[i] = Integer.parseInt(numberText[i]);
        }
        displayList(input);
    }

    /**
     * Prints all list options to the user.
     * @param input The user's integer array.
     */
    public static void displayList(int[] input) {
        System.out.println("\nPlease make a selection:");
        System.out.println("1 - Display list statistics");
        System.out.println("2 - Display list in numerical order");
        System.out.println("3 - Display number of even and odd numbers");
        System.out.println("4 - Display number of primes");
        System.out.println("5 - Enter a new number list");
        System.out.println("Q - Exit program\n");
        System.out.print("Enter an option: ");
        getSelection(input);
    }

    /**
     * Gets input for the user's menu selection
     * @param input The user's integer array.
     */
    public static void getSelection(int[] input) {
        Scanner in = new Scanner(System.in);
        String selection = in.next();
        callList(selection, input);
    }

    /**
     * Takes the user's menu selection, determines if the selection is valid, and calls the associated function.
     * @param selection The user's menu selection.
     * @param input The user's integer array.
     */
    public static void callList(String selection, int[] input) {
        if (selection.equals("1")) {
            displayListStats(input);
        }
        else if (selection.equals("2")) {
            displayOrdered(input);
        }
        else if (selection.equals("3")) {
            displayEvenOdd(input);
        }
        else if (selection.equals("4")) {
            displayPrimes(input);
        }
        else if (selection.equals("5")) {
            enterNewList();
        }
        else if (selection.toLowerCase(Locale.ROOT).equals("q")){
            System.out.println("\nGoodbye :-)");
            System.exit(0);
        }
        else {
            System.out.println("\nInvalid input");
        }
        displayList(input);
    }

    /**
     * Calculates and displays all list statistics.
     * @param input The user's integer array.
     */
    public static void displayListStats(int[] input) {
        int minimum = findMinimum(input);
        System.out.println("\nMinimum: " + minimum);
        int maximum = findMaximum(input);
        System.out.println("Maximum: " + maximum);
        int count = input.length;
        System.out.println("Count: " + count);
        int range = maximum - minimum;
        System.out.println("Range: " + range);
        double median = findMedian(input);
        System.out.println("Median: " + median);
        double mean = findMean(input);
        System.out.println("Mean: " + mean);
        String maybeMode = findMode(input);
        int mode;
        try {
            mode = Integer.parseInt(maybeMode);
            System.out.println("Mode: " + mode);
        } catch(Exception e) {
            System.out.println("Mode: N/A");
        }
        double popVar = findVariance(input, mean);
        System.out.printf("Variance: %.2f\n", popVar);
        double standardDev = findSD(input, mean);
        System.out.printf("Standard deviation: %.2f\n", standardDev);
    }

    /**
     * Sorts the user's integer array and displays the array to the user.
     * @param input The user's integer array.
     */
    public static void displayOrdered(int[] input) {
        Arrays.sort(input);
        System.out.println("\n" + Arrays.toString(input));
    }

    /**
     * Calculates the number of even numbers and odd numbers, then displays those numbers to the user.
     * @param input The user's integer array.
     */
    public static void displayEvenOdd(int[] input) {
        int numEvens = 0;
        int numOdds = 0;
        for (int i : input) {
            if (i % 2 == 0) {
                numEvens += 1;
            } else {
                numOdds += 1;
            }
        }
        System.out.println("\nNumber of even numbers: " + numEvens);
        System.out.println("Number of odd numbers: " + numOdds);
    }

    /**
     * Calculates the number of prime numbers and displays it to the user.
     * @param input The user's integer array.
     */
    public static void displayPrimes(int[] input) {
        int primeCount = 0;
        boolean flag;
        for (int i : input) {
            flag = false;
            if (i == 0 || i == 1) {
                continue;
            } else {
                for (int j = 2; j <= i / 2; ++j) {
                    if (i % j == 0) {
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                ++primeCount;
            }
        }
        System.out.println("\nNumber of primes: " +primeCount);
    }

    /**
     * Prompts the user to input an integer array, then calls the function to process the input.
     */
    public static void enterNewList() {
        System.out.print("\nEnter a list of integers separated by spaces: ");
        getInput();
    }

    /**
     * Calculates the minimum given an integer array.
     * @param input The user's integer array.
     * @return The minimum value in the user's number array.
     */
    public static int findMinimum(int[] input) {
        int minimum = input[0];
        for (int i : input) {
            if (i < minimum) {
                minimum = i;
            }
        }
        return minimum;
    }

    /**
     * Calculates the maximum given an integer array.
     * @param input The user's integer array.
     * @return The maximum value in the user's number array
     */
    public static int findMaximum(int[] input) {
        int maximum = input[0];
        for (int i : input) {
            if (i > maximum) {
                maximum = i;
            }
        }
        return maximum;
    }

    /**
     * Calculates the median value given an integer array.
     * @param input The user's integer array.
     * @return The median value in the user's number array.
     */
    public static double findMedian(int[] input) {
        Arrays.sort(input);
        double median;
        if (input.length % 2 == 0) {
            double middleTwo = input[input.length / 2] + input[input.length / 2 - 1];
            median = middleTwo / 2;
        }
        else {
            median = input[input.length / 2];
        }
        return median;
    }

    /**
     * Calculates the mean value given an integer array.
     * @param input The user's integer array
     * @return The mean value in the user's integer array.
     */
    public static double findMean(int[] input) {
        double mean;
        int total = 0;
        for (int i : input) {
            total += i;
        }
        mean = (double)total / (double)input.length;
        return mean;
    }

    /**
     * Calculates the mode given an integer array.
     * @param input The user's integer array.
     * @return The mode of the user's integer array
     */
    public static String findMode(int[] input) {
        int count;
        int maxCount = 0;
        int mode = input[0];
        String output;

        for (int i : input) {
            count = 0;
            for (int j : input) {
                if (i == j) {
                    ++count;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mode = i;
            }
        }
        output = String.valueOf(mode);
        if (maxCount == 1) {
            return "No mode";
        }
        else {
            return output;
        }
    }

    /**
     * Calculates the population variance given an integer array.
     * @param input The user's integer array.
     * @param mean The mean value of the user's integer array.
     * @return The population variance value for the given integer array.
     */
    public static double findVariance(int[] input, double mean) {
        double variance;
        double summation = 0;
        for (int i : input) {
            summation += Math.pow(i - mean, 2);
        }
        variance = summation / input.length;
        return variance;
    }

    /**
     * Calculates the population standard deviation given an integer array.
     * @param input The user's integer array.
     * @param mean The mean value of the user's integer array.
     * @return The population standard deviation value for the given integer array.
     */
    public static double findSD(int[] input, double mean) {
        double standardDev;
        double summation = 0;
        for (int i : input) {
            summation += Math.pow(i - mean, 2);
        }
        standardDev = Math.sqrt(summation/input.length);
        return standardDev;
    }
}