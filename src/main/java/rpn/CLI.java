package rpn;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI {
    public static  void main(String[] args) {
        String expression = Stream.of(args).collect(Collectors.joining(" "));

        System.out.println("About to evaluate '" + expression + "'");
        long result = evaluate(expression);
        System.out.println("> " + result);
    }

    /**
     * on fait simple
     * on parcourt la chaine
     * on transforme la chaine en liste (en splitant sur l'espace
     * si cette chaine ne contient qu'un élement (un résultat ou simplement une expression avec un seul chiffre)
        * on retourne l'expression
     * on parcourt l'expression
     * si la la chaine actuelle + 2 est une operation on réalise lopération entre la chaine  et la chaine + 1
        * on retire alors l'operation de la chaine
        * on retire la chaine qui nous a servi à faire l'opération (+1 par exemple)
     * On recréé l'expression en fonction de notre liste de string et on recurse
     * pour qu'au final on ait que un chiffre/nombre seul qui soit un résultat
     * Exemple :
     * 3 5 8 * 7 + *
     *   5 * 8
     * 3 40 7 + *
     *   40 + 7
     * 3 47 *
     * 3 * 47
     * 141
     * @param expression expression
     * @return le resultat de l'expression
     */
    static long evaluate(String expression)
    {
       long intermediaire = 0;
       String startAgain;
       // regex permettant de de vérifier si un caractère est un chiffre/nombre (négatif ou positif)
       String regexChiffre = "[-0-9]+";
       // regex permettant de vérifier si un caractère est une opération (+ - / *)
       String regexOp = "[+/\-*]+";
       String[] tableauExpression = expression.split(" ");
       ArrayList<String> listExpression = new ArrayList<>(Arrays.asList(tableauExpression));
       if(listExpression.size() == 1)
       {
           return  Long.parseLong(expression);
       }
        for (int i = 0; i < listExpression.size()-1 ; i++)
        {
            if(listExpression.get(i+1).matches(regexChiffre) && listExpression.get(i+2).matches(regexOp))
            {
                switch (listExpression.get(i+2))
                {
                    case "+":
                        intermediaire = Long.parseLong(listExpression.get(i)) + Long.parseLong(listExpression.get(i+1));
                    break;
                    case "/":
                        intermediaire = Long.parseLong(listExpression.get(i)) / Long.parseLong(listExpression.get(i+1));
                    break;
                    case "-":
                        intermediaire = Long.parseLong(listExpression.get(i)) - Long.parseLong(listExpression.get(i+1));
                    break;
                    case "*":
                        intermediaire = Long.parseLong(listExpression.get(i)) * Long.parseLong(listExpression.get(i+1));
                    break;
                }
                listExpression.set(i, intermediaire+"");
                listExpression.remove(i+1);
                listExpression.remove(i+1);
                startAgain = String.join(" ", listExpression);
                return evaluate(startAgain);
            }
        }
        return Long.parseLong(expression);
    }
}
