package day07;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

public class Equation {
    private final long testValue;
    private final long[] numbers;
    private final String operators;

    public Equation(String line, String operators) {
        this.operators = operators;

        String[] parts = line.split(": ");
        this.testValue = Long.parseLong(parts[0]);
        String [] numbersText = parts[1].split(" ");

        this.numbers = new long[numbersText.length];
        for (int i = 0; i < numbersText.length; i++) {
            numbers[i] = Long.parseLong(numbersText[i]);
        }
    }

    public List<String> findOperators() {
        List<String> matchingOperators = new ArrayList<>();

        List<String> combinations = findCombinations();
        for (String combination : combinations) {
            List<String> calculation = makeCalculation(combination);

            long result = calculate(calculation);

            if (result == testValue) {
                matchingOperators.add(combination);
            }
        }

        return matchingOperators;
    }

    private long calculate(List<String> calculation) {
        long result = Long.parseLong(calculation.get(0));
        for (int i = 1; i < calculation.size(); i+=2) {
            switch (calculation.get(i)) {
                case "+" -> result += Long.parseLong(calculation.get(i + 1));
                case "*" -> result *= Long.parseLong(calculation.get(i + 1));
                case "|" -> result = (result * (long) Math.pow(10, Long.toString(Long.parseLong(calculation.get(i+1))).length())) + Long.parseLong(calculation.get(i+1));
            }
        }
        return result;
    }

    private List<String> makeCalculation(String combination) {
        List<String> calculation = new ArrayList<>();
        for (int i = 0; i < combination.length(); i++) {
            calculation.add(Long.toString(numbers[i]));
            calculation.add(combination.substring(i, i+1));
        }
        calculation.add(Long.toString(numbers[numbers.length-1]));
        return calculation;
    }

    public List<String> findCombinations() {
        List<String> combinations = new ArrayList<>();

        for (int i = 0; i< pow(operators.length(), numbers.length-1); i++) {
            StringBuilder combination = new StringBuilder(Integer.toString(i, operators.length()));

            while (combination.length() < numbers.length-1) {
                combination.insert(0, "0");
            }

            StringBuilder appliedCombination = new StringBuilder();
            for (int j=0; j<combination.length(); j++) {
                int digit = Integer.parseInt(combination.substring(j, j+1));
                appliedCombination.append(operators.charAt(digit));
            }
            combinations.add(appliedCombination.toString());
        }

        return combinations;
    }

    public long getTestValue() {
        return testValue;
    }
}
