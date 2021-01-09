import java.util.*;
import java.util.stream.Collectors;

public class RecomenderFunctionsService {

    private List<String> formatedResultList = new LinkedList<>();
    private List<Person> personList = new LinkedList<>();
    private List<Item> itemList = new LinkedList<>();
    private List<Double> simList = new LinkedList<>();
    HashMap<String, Double> personSimPairs = new HashMap<String, Double>();


    public RecomenderFunctionsService(List<Person> personList, List<Item> itemList) {
        this.itemList = itemList;
        this.personList = personList;
    }



    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }


    public Person getPerson(String name) {

        for (Person person : getPersonList()) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Item getItem(String name) {

        for (Item item: getItemList()) {
            if (item.getTitle().equals(name)) {
                return item;
            }
        }
        return null;
    }


    public List<String> getFormatedResultList() {
        return formatedResultList;
    }

    public void setFormatedResultList(List<String> formatedResultList) {
        this.formatedResultList = formatedResultList;
    }

    public HashMap<String, Double> getPersonSimPairs() {
        return personSimPairs;
    }

    public void setPersonSimPairs(HashMap<String, Double> personSimPairs) {
        this.personSimPairs = personSimPairs;
    }

    public void calculateSim(Person a, Person b) {
        List<Item> aAverageList = new LinkedList<>();
        List<Item> bAverageList = new LinkedList<>();

        calculateAverageRating(a, b, aAverageList, bAverageList);

   double subZaehlerResult=0.00000000000000000000000000000000000000000;
     double subResultFirstWurzel=0.00000000000000000000000000000000000000000;
     double subResultSecondWurzel=0.00000000000000000000000000000000000000000;

        System.out.println("Nun wird die sim(" + a.getName() + ", " + b.getName()+") berechnet mit der Formel aus der Vorlesung:\n");

        System.out.println("Für den Zähler ergibt sich: ");
        for(int i=0; i< aAverageList.size(); i++) {
            double subResultAOneItem = aAverageList.get(i).getRating() - a.getAverageRating();
            double subResultBOneItem = bAverageList.get(i).getRating() - b.getAverageRating();

            System.out.println("("+ aAverageList.get(i).getRating()+" - " + a.getAverageRating()+") * (" + bAverageList.get(i).getRating()+" - "
                    + b.getAverageRating()+") = " + subResultAOneItem + " * " + subResultBOneItem + " = " + subResultAOneItem* subResultBOneItem
                    + " + ");


            subZaehlerResult += subResultAOneItem* subResultBOneItem;

        }
        System.out.println("=" + subZaehlerResult );

        System.out.println("\nUnd der Nenner berechnet sich durch: ");

        System.out.println("Linke Wurzel: ");
        for(int i=0; i< aAverageList.size(); i++) {

            double subResultAOneItem = aAverageList.get(i).getRating() - a.getAverageRating();
            subResultAOneItem = Math.pow(subResultAOneItem, 2);

            System.out.println("("+ aAverageList.get(i).getRating()+" - " + a.getAverageRating()+")^2 = "  + subResultAOneItem + " + ");
            subResultFirstWurzel = subResultFirstWurzel + subResultAOneItem;
        }

        System.out.println("= Wurzel(" + subResultFirstWurzel +")" +" = " + Math.sqrt(subResultFirstWurzel));
        subResultFirstWurzel = Math.sqrt(subResultFirstWurzel);


        System.out.println("\nRechte Wurzel: ");
        for(int i=0; i< bAverageList.size(); i++) {

            double subResultBOneItem = bAverageList.get(i).getRating() - b.getAverageRating();
            subResultBOneItem = Math.pow(subResultBOneItem, 2);

            System.out.println("("+ bAverageList.get(i).getRating()+" - " + b.getAverageRating()+")^2 = "  + subResultBOneItem + " + ");
            subResultSecondWurzel = subResultSecondWurzel + subResultBOneItem;
        }

        System.out.println("= Wurzel(" + subResultSecondWurzel +")" +" = " + Math.sqrt(subResultSecondWurzel));
        double subResultSecondWurzelAfterSqrt = Math.sqrt(subResultSecondWurzel);


        if ((subResultFirstWurzel*subResultSecondWurzelAfterSqrt)==0) {
            String resultString = "Teilung durch 0 nicht möglich, sim(" + a.getName() + ", " + b.getName()+") ist nicht berechenbar";
            formatedResultList.add(resultString);
            simList.add(1.00);
            b.setCurrentSim(-2);
            return;
        }


        if ((subResultFirstWurzel==subResultSecondWurzelAfterSqrt)) {
            System.out.println("\nDamit ergibt sich als Gesamtbruch am Ende: " + subZaehlerResult + " / Wurzel(" + subResultSecondWurzel+ ") * Wurzel(" + subResultSecondWurzel+ ") = ");
            double result = subZaehlerResult/subResultSecondWurzel;

            /*String resultString = "sim(" + a.getName() + ", " + b.getName()+") = " + result;
            System.out.println(result);
            formatedResultList.add(resultString);*/

            simList.add(result);
            b.setCurrentSim(result);
            personSimPairs.put(b.getName() , result);
            return;
        }


        System.out.println("Damit ergibt sich als Gesamtbruch am Ende: " + subZaehlerResult + " / " + subResultFirstWurzel + "  * " + subResultSecondWurzelAfterSqrt+ " = ");

        double result = subZaehlerResult/(subResultFirstWurzel*subResultSecondWurzelAfterSqrt);

        System.out.println(result);
        simList.add(result);
        b.setCurrentSim(result);

            /*String resultString = "sim(" + a.getName() + ", " + b.getName()+") = " + result;
            System.out.println(result);
            formatedResultList.add(resultString);*/

        personSimPairs.put(b.getName() , result);
    }


    public void calculateAverageRating(Person a, Person b, List<Item> aAverageList, List<Item> bAverageList ) {

        Double aAverageRating= 0.0;
        Double bAverageRating =0.0;

        for (int i=0; i<b.getItemList().size(); i++) {
            if(b.getItemList().get(i).getRating()!=0.0 && a.getItemList().get(i).getRating()!=0.0) {


                bAverageList.add(b.getItemList().get(i));
                aAverageList.add(a.getItemList().get(i));

                bAverageRating += b.getItemList().get(i).getRating();
                aAverageRating += a.getItemList().get(i).getRating();
            }


        }
        aAverageRating= aAverageRating/aAverageList.size();
        bAverageRating= bAverageRating/bAverageList.size();


        a.setAverageRating(aAverageRating);
        b.setAverageRating(bAverageRating);

        List<String> formatListb = formatItemList(bAverageList);
        List<String> formatLista = formatItemList(aAverageList);

        System.out.println("\nFür den Durchschnitt der Bewertungen von " + b.getName() + " sind nur folgende Titel relevant, da auch von " +
                a.getName() + " bewertet. " + a.getName() + ": " + formatLista + " | " + b.getName() + ": " + formatListb);
        System.out.println("Bei einem Vergleich von "+ a.getName() + " und " + b.getName() + " ergibt sich aus den" +
                " gemeinsam bewerteten Items ein Durchschnitt der Bewertung von " + aAverageRating + " und " + bAverageRating + "\n");

    }

    public void calculateAverageItemRating(Item a, Item b, List<Person> aAverageList, List<Person> bAverageList ) {

        System.out.println("\nNun wird die sim(" + a.getTitle() + ", " + b.getTitle() + ") berechnet");

        Double aAverageRating= 0.0;
        Double bAverageRating =0.0;

        for (int i=0; i<b.getPersonList().size(); i++) {
            if(b.getPersonList().get(i).getRating()!=0.0 && a.getPersonList().get(i).getRating()!=0.0) {


                bAverageList.add(b.getPersonList().get(i));
                aAverageList.add(a.getPersonList().get(i));

                bAverageRating += b.getPersonList().get(i).getRating();
                aAverageRating += a.getPersonList().get(i).getRating();
            }


        }
        aAverageRating= aAverageRating/aAverageList.size();
        bAverageRating= bAverageRating/bAverageList.size();


        a.setAverageRating(aAverageRating);
        b.setAverageRating(bAverageRating);

        List<String> formatList = formatPersonList(bAverageList);

        System.out.println("Für den Durchscnitt von " + b.getTitle() + " sind nur folgende Personen relevant, da Sie auch " +
                a.getTitle() + " bewertet haben :" + formatList);
        System.out.println("Bei einem Vergleich von "+ a.getTitle() + " und " + b.getTitle() + " ergibt sich aus den" +
                " gemeinsam bewerteten Items ein Durchschnitt von " + aAverageRating + " und " + bAverageRating + "\n");

    }



    public double calculateC(Person a, Item i, boolean optimized) {

        double subResultZaehler = 0.0;
        double subResultNenner = 0.0;

        syncAndCleanPersonAndSimList(a, optimized);

        int counter = 0;


        System.out.println("\nc(" + a.getName() + ", " + i.getTitle() + ") berechnet sich wie folgt: ");


        System.out.println("Der Zähler ergibt sich aus: ");
        for (Person person : personList) {
            double subResult = person.getItem(i.getTitle()).getRating() - person.getAverageRating();

            System.out.println(person.getItem(i.getTitle()).getRating() + " - " + person.getAverageRating() + " = " + subResult + " * " + simList.get(counter) + " = " + subResult * simList.get(counter) + " +");

            subResult = subResult * simList.get(counter);

            subResultZaehler += subResult;
            counter++;
        }
        System.out.println("Der Zähler ist somit = " + subResultZaehler);

        counter=0;
        System.out.println("\nDer Nenner ergibt sich aus: ");
        for (Person person : personList) {
        subResultNenner += Math.abs(simList.get(counter));

        System.out.println(Math.abs(simList.get(counter)) + " + ");

        counter++;
    }
        System.out.println("Der Nenner ist somit = " + subResultNenner);

        System.out.println("\nc(" + a.getName() + ", " + i.getTitle()+") = " + subResultZaehler + " / " + subResultNenner + " = " + subResultZaehler / subResultNenner);

        return subResultZaehler / subResultNenner;

    }

    private void syncAndCleanPersonAndSimList(Person a, boolean optimized) {


        getPersonList().remove(a);

        int in =0;
        for (Iterator it = getPersonList().iterator(); it.hasNext(); ) {
            if(in>=getPersonList().size() || in>=simList.size()) {
                break;
            }
            if(getPersonList().get(in).getCurrentSim()<-1 || getPersonList().get(in).getCurrentSim()>1 ) {
                simList.remove(in);
                getPersonList().remove(in);
            }
            in++;
        }

        if (optimized) {
            personList.subList(5, personList.size()).clear();
            simList.subList(5, simList.size()).clear();
        }



    }

    public void printSims(Person a) {
        System.out.println("\nDie Werte für die Berechnung der sim() sind:");


        List<Map.Entry<String, Double>> list = new LinkedList<>(personSimPairs.entrySet());
        boolean order = false;
        // Sorting the list based on values
        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));

        HashMap<String, Double> orderedPairs = list.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (b, c) -> c, LinkedHashMap::new));

        Collections.sort(simList);

        personSimPairs = new HashMap<String, Double>(orderedPairs);


       // getPersonList().remove(a);

        LinkedList<Double> simListCopy = new LinkedList<Double>();
        LinkedList<Person> personListCopy = new LinkedList<Person>();

        int in=0;
        Iterator it = orderedPairs.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();


            String resultString = "sim(" + a.getName() + ", " + this.getPerson(pair.getKey().toString()).getName()+") = " + pair.getValue();
            formatedResultList.add(resultString);

            simListCopy.add(in,(Double) pair.getValue());
            personListCopy.add(in, this.getPerson(pair.getKey().toString()));

            it.remove(); // avoids a ConcurrentModificationException
            in++;
        }

        simList = simListCopy;
        personList = personListCopy;



        for (String string: this.getFormatedResultList()) {
            System.out.println(string);
        }
    }


    public double calculateR(Person a, Item i, boolean optimized) {

        double c= calculateC(a, i, optimized);
        double r = a.getAverageRating() + c;

        System.out.println("\nr(" + a.getName() + ", " + i.getTitle()+ ") = " + a.getAverageRating() + " + " + c);
        System.out.println("r(" + a.getName() + ", " + i.getTitle()+ ") = " + r + " was gerundet auf Skalenwert ergibt: "
        + Math.round(r));

        return Math.round(r);
    }


    private List<String> formatItemList(List<Item> bAverageList) {
        List<String> formatList = new LinkedList<>();

        for (Item item: bAverageList) {
            formatList.add(item.getTitle() + " " + item.getRating());
        }
        return formatList;
    }

    private List<String> formatPersonList(List<Person> bAverageList) {
        List<String> formatList = new LinkedList<>();

        for (Person person: bAverageList) {
            formatList.add(person.getName() + " " + person.getRating());
        }
        return formatList;
    }

    public void calculateSimItemToItem(Item a, Item b) {
        List<Person> aAverageList = new LinkedList<>();
        List<Person> bAverageList = new LinkedList<>();

        calculateAverageItemRating(a, b, aAverageList, bAverageList);


        double subZaehlerResult=0.00000000000000000000000000000000000000000;
        double subResultFirstWurzel=0.00000000000000000000000000000000000000000;
        double subResultSecondWurzel=0.00000000000000000000000000000000000000000;


        System.out.println("Für den Zähler ergibt sich: ");
        for(int i=0; i< aAverageList.size(); i++) {
            double subResultAOneItem = aAverageList.get(i).getRating() - a.getAverageRating();
            double subResultBOneItem = bAverageList.get(i).getRating() - b.getAverageRating();

            System.out.println("("+ aAverageList.get(i).getRating()+" - " + a.getAverageRating()+") * (" + bAverageList.get(i).getRating()+" - "
                    + b.getAverageRating()+") = " + subResultAOneItem + " * " + subResultBOneItem + " = " + subResultAOneItem* subResultBOneItem
                    + " + ");


            subZaehlerResult += subResultAOneItem* subResultBOneItem;

        }
        System.out.println("=" + subZaehlerResult );

        System.out.println("\nUnd der Nenner berechnet sich durch: ");

        System.out.println("Linke Wurzel: ");
        for(int i=0; i< aAverageList.size(); i++) {

            double subResultAOneItem = aAverageList.get(i).getRating() - a.getAverageRating();
            subResultAOneItem = Math.pow(subResultAOneItem, 2);

            System.out.println("("+ aAverageList.get(i).getRating()+" - " + a.getAverageRating()+")^2 = "  + subResultAOneItem + " + ");
            subResultFirstWurzel = subResultFirstWurzel + subResultAOneItem;
        }

        System.out.println("= Wurzel(" + subResultFirstWurzel +")" +" = " + Math.sqrt(subResultFirstWurzel));
        subResultFirstWurzel = Math.sqrt(subResultFirstWurzel);


        System.out.println("\nRechte Wurzel: ");
        for(int i=0; i< bAverageList.size(); i++) {

            double subResultBOneItem = bAverageList.get(i).getRating() - b.getAverageRating();
            subResultBOneItem = Math.pow(subResultBOneItem, 2);

            System.out.println("("+ bAverageList.get(i).getRating()+" - " + b.getAverageRating()+")^2 = "  + subResultBOneItem + " + ");
            subResultSecondWurzel = subResultSecondWurzel + subResultBOneItem;
        }

        System.out.println("= Wurzel(" + subResultSecondWurzel +")" +" = " + Math.sqrt(subResultSecondWurzel));
        double subResultSecondWurzelAfterSqrt = Math.sqrt(subResultSecondWurzel);


        if ((subResultFirstWurzel*subResultSecondWurzelAfterSqrt)==0) {
            String resultString = "Teilung durch 0 nicht möglich, sim(" + a.getTitle() + ", " + b.getTitle()+") ist nicht berechenbar";
            formatedResultList.add(resultString);
            simList.add(1.00);
            b.setCurrentSim(-2);
            return;
        }

        if ((subResultFirstWurzel==subResultSecondWurzelAfterSqrt)) {
            System.out.println("Damit ergibt sich als Gesamtbruch am Ende: " + subZaehlerResult + " / Wurzel(" + subResultSecondWurzel+ ") * Wurzel(" + subResultSecondWurzel+ ") = ");
            double result = subZaehlerResult/subResultSecondWurzel;
            String resultString = "sim(" + a.getTitle() + ", " + b.getTitle()+") = " + result;
            System.out.println(result);
            formatedResultList.add(resultString);
            simList.add(result);
            b.setCurrentSim(result);
            return;
        }


        System.out.println("\nDamit ergibt sich als Gesamtbruch am Ende: " + subZaehlerResult + " / " + subResultFirstWurzel + "  * " + subResultSecondWurzelAfterSqrt+ " = ");

        double result = subZaehlerResult/(subResultFirstWurzel*subResultSecondWurzelAfterSqrt);

        System.out.println(result);
        simList.add(result);
        b.setCurrentSim(result);

        System.out.println("\nsim(" + a.getTitle() + ", " + b.getTitle()+") = " + result);
    }
}
