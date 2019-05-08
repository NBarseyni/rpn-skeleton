package rpn;

import java.util.ArrayList;
import java.util.Arrays;

public class Calculate {

    static long evaluate(String expression) {

        long intermediaire=0;
        // regex permettant de de vérifier si un caractère est un chiffre/nombre (négatif ou positif)
        String regexChiffre = "-?[1-9]\\d*|0";
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
                            intermediaire = number2 - number1;
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
        long number=0;
        // regex permettant de de vérifier si un caractère est un chiffre/nombre (négatif ou positif)
        String regexChiffre = "-?[1-9]\\d*|0";
        // regex permettant de vérifier si un caractère est une opération (+ - / *)
        String regexOp = "[+/\\-*]+";
        String[] tableauExpression = expression.split(" ");
        ArrayList<String> listExpression = new ArrayList<>(Arrays.asList(tableauExpression));


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
                            number = number2 / number1;
                            break;
                        case "-":
                            number = number2 - number1;
                            break;
                        case "*":
                            number = number1 * number2;
                            break;
                            default:System.out.println("opération non reconnue");
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
