import java.text.Collator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HashFunctionService {


    // m als 10 ist gegeben
    private static int m = 10;
    private static List<Integer> resultListH1 = new LinkedList<>();
    private static List<Integer> resultListH2 = new LinkedList<>();
    private static List<Integer> resultListG = new LinkedList<>();

    private static HashMap<String, Integer> h1HashValues = new HashMap();
    private static HashMap<String, Integer> h2HashValues = new HashMap();

    void getHashValueForListOfTerms(List<String> list, HashMap<Integer, Integer> weights1, HashMap<Integer, Integer> weights2) {



        for (String term: list) {
            int hash1 = getHashValueForOneTermForH1(term, weights1);
            int hash2 = getHashValueForOneTermForH2(term, weights2);
            resultListH1.add(hash1);
            resultListH2.add(hash2);
        }

    }

    private int getHashValueForOneTermForH2(String term, HashMap<Integer, Integer> weights2 ) {
        int multResult=0;
        int resulth2=0;
        char[] chars = term.toCharArray();
        int i=0;

        System.out.println("Für das Wort " + term + " berechnet sich der Hashwert der Funktion h2 wie folgt: ");

        for (char character : chars) {
            int value = (int) character;

            multResult = value * weights2.get(i);
            System.out.println(multResult + "= Asci Wert von " + character + ":" + value + " x Gewicht("+i+"):" + weights2.get(i));
            resulth2 += multResult;
            i++;

        }

        int endResultForTerm = resulth2%m;
        System.out.println("Der Wert der Summe aller Multiplikationen ist dann: " + resulth2);
        System.out.println("Dieser Wert, also " + resulth2 +" modulo " + "m("+m+ ") ergibt für h2(" + term + ") den Hashwert" +
                " = " + endResultForTerm +"\n");

        h2HashValues.putIfAbsent(term, endResultForTerm);

        return endResultForTerm;

    }

    private int getHashValueForOneTermForH1(String term, HashMap<Integer, Integer> weights1) {
        int multResult=0;
        int resulth1=0;
        char[] chars = term.toCharArray();
        int i=0;

        System.out.println("Für das Wort " + term + " berechnet sich der Hashwert der Funktion h1 wie folgt: ");

        for (char character : chars) {
            int value = (int) character;

            multResult = value * weights1.get(i);
            System.out.println(multResult + "= Asci Wert von " + character + ":" + value + " x Gewicht("+i+"):" + weights1.get(i));
            resulth1 += multResult;
            i++;

        }
       // System.out.println("Also ergibt sich in der Klammer zusammengezählt " + resulth1);

        int endResultForTerm = resulth1%m;

        System.out.println("Der Wert der Summe aller Multiplikationen ist dann: " + resulth1);
        System.out.println("Dieser Wert, also " + resulth1 +" modulo " + "m("+m+ ") ergibt für h1(" + term + ") den Hashwert" +
                " = " + endResultForTerm +"\n");

        h1HashValues.putIfAbsent(term, endResultForTerm);

        return endResultForTerm;
    }

    public void formatAndPrintResultsDefault(List<String> termList) {
        h1HashValues.entrySet().stream().map(entry -> "Term = " + entry.getKey() + ", H1 Value = " + entry.getValue()).forEach(System.out::println);

        System.out.println("\n");

        h2HashValues.entrySet().stream().map(entry -> "Term = " + entry.getKey() + ", H2 Value = " + entry.getValue()).forEach(System.out::println);


        java.util.Collections.sort(termList, Collator.getInstance());

        int i=0;
        int size = termList.size();
        for (Iterator it = termList.iterator(); it.hasNext(); ) {
           String term = termList.get(i);
            if (term.contains(" ")) {
                termList.remove(term);
                termList.add(0, term);
                continue;
            }
            if(i<size-1) {
                i++;
            } else {
                break;
            }
        }


        System.out.println("\nTerm | h1() | h2() | h()");
        for (String term: termList) {

            System.out.println(term + " | " + h1HashValues.get(term) + " | " + h2HashValues.get(term) + " | " + termList.indexOf(term));
        }
    }
}
