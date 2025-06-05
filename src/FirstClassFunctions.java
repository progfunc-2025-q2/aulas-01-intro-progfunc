/**
 * Demonstrates first-class functions and custom functional interfaces in Java.
 * <p>
 * This class defines and uses custom functional interfaces for unary, binary, and ternary functions,
 * as well as Java's built-in {@link Function} and {@link BiFunction} interfaces. It shows how functions
 * can be passed as values and used in various contexts.
 * <p>
 * Usage:
 * <pre>
 *   java FirstClassFunctions
 * </pre>
 * <p>
 * Expected output includes examples of applying custom and default functional types.
 */

import java.util.function.BiFunction;
import java.util.function.Function;

public class FirstClassFunctions {
    
    /**
     * A simple functional interface with arity of one.
     */
    @FunctionalInterface
    private static interface UnaryFunction<T, R> {
        R apply(T t);
    }

    /**
     * A simple functional interface with arity of two.
     */
    @FunctionalInterface
    private static interface BinaryFunction<T, U, R> {
        R apply(T t, U u);
    }

    /**
     * A simple functional interface with arity of three.
     */
    @FunctionalInterface
    private static interface TernaryFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }

    /**
     * Demonstrates the declaration and application of typed lambdas, using
     * custom functional interfaces for unary, binary, and ternary functions.
     */
    private static void customFunctionalTypes() {
        UnaryFunction<Integer, Integer> square = x -> x * 2;
        System.out.println("Square of 5: " + square.apply(5));

        BinaryFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
        BinaryFunction<Integer, Integer, Integer> multiply = (x, y) -> x * y;
    
        System.out.println("Sum of 3 and 4: " + add.apply(3, 4));
        System.out.println("Product of 3 and 4: " + multiply.apply(3, 4));

        TernaryFunction<Integer, Integer, Integer, Integer> sumThree = (x, y, z) -> x + y + z;
        System.out.println("Sum of 1, 2, and 3: " + sumThree.apply(1, 2, 3));
    }

    /**
     * Demonstrates the use of Java's built-in functional interfaces such as
     * {@link Function} and {@link BiFunction} for unary and binary operations.
     * This method shows how to use these interfaces with lambda expressions.
     */
    private static void defaultFunctionalTypes() {
        Function<Integer, Integer> square = x -> x * x;
        System.out.println("Square of 5: " + square.apply(5));

        BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
        BiFunction<Integer, Integer, Integer> multiply = (x, y) -> x * y;
        System.out.println("Sum of 3 and 4: " + add.apply(3, 4));
        System.out.println("Product of 3 and 4: " + multiply.apply(3, 4));
    }

    public static void main(String[] args) {
        customFunctionalTypes();
        defaultFunctionalTypes();   
    }
}
