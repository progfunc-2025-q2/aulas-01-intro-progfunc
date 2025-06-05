import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Demonstrates the use of higher-order functions in Java.
 * <p>
 * This class provides examples of mapping functions over lists and generating operations
 * dynamically using functional interfaces such as {@link Function} and {@link BiFunction}.
 * It also defines a method to generate arithmetic operations represented as lambdas.
 * <p>
 * Usage:
 * <pre>
 *   java HigherOrderFunctions
 * </pre>
 * <p>
 * Expected output:
 * <pre>
 *   Squares: [1, 4, 9, 16, 25]
 *   Doubles: [2, 4, 6, 8, 10]
 *   ...
 * </pre>
 */

public class HigherOrderFunctions {

    /**
     * Maps a function over an ArrayList of integers.
     * This method applies the provided lambda to each element of the input list
     * and returns a new ArrayList containing the results.
     * @param arr
     * @param func
     * @return
     */
    public static ArrayList<Integer> map(ArrayList<Integer> arr, Function<Integer, Integer> func) {
        var result = new ArrayList<Integer>(arr.size());

        for (Integer i : arr) {
            result.add(func.apply(i));
        }

        return result;
    }

    /**
     * An enumeration representing basic arithmetic operations.
     * This enum is used to generate specific operations using the {@link genOperation} method.
     */
    public static enum Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
    
    /**
     * Generates a {@link BiFunction} representing the specified arithmetic operation.
     * This method takes an {@link Operation} enum value and returns a corresponding
     * lambda function that performs the operation on two integers.
     * @param op the operation to generate
     * @return a BiFunction that performs the specified operation
     */
    public static BiFunction<Integer, Integer, Integer> genOperation(Operation op) {
        switch (op) {
            case ADD:
                return (a, b) -> a + b;
            case SUBTRACT:
                return (a, b) -> a - b;
            case MULTIPLY:
                return (a, b) -> a * b;
            case DIVIDE:
                return (a, b) -> {
                    if (b == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    return a / b;
                };
        }
        throw new IllegalArgumentException("Invalid operation");
    }

    /**
     * Main method to demonstrate the use of higher-order functions.
     * @param args
     */
    public static void main(String[] args) {
        /**
         * Example usage of map function to apply a lambda to each element of an ArrayList.
         */
        ArrayList<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5));

        /**
         * Using map to apply a lambda function that squares each number
         * and another that doubles each number.
         */
        ArrayList<Integer> squares = map(numbers, x -> x * x);
        System.out.println("Squares: " + squares);
        ArrayList<Integer> doubles = map(numbers, x -> x * 2);
        System.out.println("Doubles: " + doubles);

        /**
         * Demonstrating the use of the genOperation method to create
         * arithmetic operations dynamically.
         * This shows how to generate functions for addition, subtraction,
         * multiplication, and division using the Operation enum.
         * In fact, the genOperation method is a higher-order function
         * that acts as a factory for creating specific operations.
         */
        for (Operation op : Operation.values()) {
            BiFunction<Integer, Integer, Integer> operation = genOperation(op);
            System.out.println("Operation: " + op);
            System.out.println("Result of 10 and 5: " + operation.apply(10, 5));
        }
    }
}
