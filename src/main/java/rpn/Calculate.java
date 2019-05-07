package rpn;

import java.util.ArrayList;
import java.util.Arrays;

public class Calculate {

    static long evaluate(String expression) {

        String startAgain;
        long intermediaire=0;
        // regex permettant de de vérifier si un caractère est un chiffre/nombre (négatif ou positif)
        String regexChiffre = "[-0-9]+";
        // regex permettant de vérifier si un caractère est une opération (+ - / *)
        String regexOp = "[+/\\-*]+";
        String[] tableauExpression = expression.split(" ");
        ArrayList<String> listExpression = new ArrayList<>(Arrays.asList(tableauExpression));

        ArrayList<String> listCheck = new ArrayList<>();
        while(listExpression.size()!=0){

            if(listExpression.get(0).matches(regexChiffre)){
                listCheck.add(listExpression.get(0));
                listExpression.remove(0);
            }
            else if(listExpression.get(0).matches(regexOp)){
                    long number1=Long.parseLong(listCheck.get(listCheck.size()-1));
                    listCheck.remove(listCheck.size()-1);
                    long number2=Long.parseLong(listCheck.get(listCheck.size()-1));
                    listCheck.remove(listCheck.size()-1);

                    switch (listExpression.get(0)) {
                        case "+":
                            intermediaire = number1 + number2;
                        break;
                    case "/":
                            intermediaire = number2 / number1;
                        break;
                    case "-":
                            intermediaire = number1 - number2;
                        break;
                    case "*":
                            intermediaire = number1 * number2;
                        break;
                    }
                    listExpression.remove(0);
                    listCheck.add(intermediaire+"");

            }
        }
        return Long.parseLong(listCheck.get(0));

    }



    static boolean checkExpression(String expression) {

        boolean bool = true;
        String startAgain;
        long number=0;
        // regex permettant de de vérifier si un caractère est un chiffre/nombre (négatif ou positif)
        String regexChiffre = "[-0-9]+";
        // regex permettant de vérifier si un caractère est une opération (+ - / *)
        String regexOp = "[+/\\-*]+";
        String[] tableauExpression = expression.split(" ");
        ArrayList<String> listExpression = new ArrayList<>(Arrays.asList(tableauExpression));

      /*  if (listExpression.size() % 2 == 0  ) {
            bool = false;
        }*/
        ArrayList<String> listCheck = new ArrayList<>();
        while(listExpression.size()!=0 && bool){

            if(listExpression.get(0).matches(regexChiffre)){
                listCheck.add(listExpression.get(0));
                listExpression.remove(0);
            }
            else if(listExpression.get(0).matches(regexOp)){
                if(listCheck.size()<2){
                    bool=false;
                }
                else{
                    long number1=Long.parseLong(listCheck.get(listCheck.size()-1));
                    listCheck.remove(listCheck.size()-1);
                    long number2=Long.parseLong(listCheck.get(listCheck.size()-1));
                    listCheck.remove(listCheck.size()-1);

                    switch (listExpression.get(0)) {
                        case "+":
                            number = number1 + number2;
                            break;
                        case "/":
                            number = number1 / number2;
                            break;
                        case "-":
                            number = number1 - number2;
                            break;
                        case "*":
                            number = number1 * number2;
                            break;
                    }
                    listCheck.add(number+"");
                    listExpression.remove(0);
                }
            }
            else {
                bool=false;
            }
        }
        if(listCheck.size()!=1){
            System.out.print(listCheck);
            bool=false;
        }
        return bool;
    }
}

/* long intermediaire = 0;
        String startAgain;
        // regex permettant de de vérifier si un caractère est un chiffre/nombre (négatif ou positif)
        String regexChiffre = "[-0-9]+";
        // regex permettant de vérifier si un caractère est une opération (+ - / *)
        String regexOp = "[+/\\-*]+";
        String[] tableauExpression = expression.split(" ");
        ArrayList<String> listExpression = new ArrayList<>(Arrays.asList(tableauExpression));




        if (listExpression.size() == 1) {
            return Long.parseLong(expression);
        }
        for (int i = 0; i < listExpression.size() - 1; i++) {
            if (listExpression.get(i + 1).matches(regexChiffre) && listExpression.get(i + 2).matches(regexOp)) {
                switch (listExpression.get(i + 2)) {
                    case "+":
                        intermediaire = Long.parseLong(listExpression.get(i)) + Long.parseLong(listExpression.get(i + 1));
                        break;
                    case "/":
                        intermediaire = Long.parseLong(listExpression.get(i)) / Long.parseLong(listExpression.get(i + 1));
                        break;
                    case "-":
                        intermediaire = Long.parseLong(listExpression.get(i)) - Long.parseLong(listExpression.get(i + 1));
                        break;
                    case "*":
                        intermediaire = Long.parseLong(listExpression.get(i)) * Long.parseLong(listExpression.get(i + 1));
                        break;
                }
                listExpression.set(i, intermediaire + "");
                listExpression.remove(i + 1);
                listExpression.remove(i + 1);
                startAgain = String.join(" ", listExpression);
                return evaluate(startAgain);
            }
        }*/
       /// return Long.parseLong(expression);
