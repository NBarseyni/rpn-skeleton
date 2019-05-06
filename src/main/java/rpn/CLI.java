package rpn;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI {
    public static  void main(String[] args) {
        String expression = Stream.of(args).collect(Collectors.joining(" "));

        System.out.println("About to evaluate '" + expression + "'");
        long result = Calculate.evaluate(expression);
        System.out.println("> " + result);
    }
}
