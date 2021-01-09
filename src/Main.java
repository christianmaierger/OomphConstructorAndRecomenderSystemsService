

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static HashMap<Integer, Integer> weights1 = new HashMap();
    private static HashMap<Integer, Integer> weights2 = new HashMap();



    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        boolean typeAcceptable = false;
        System.out.println("Please type 1 or 2 if you want to use Service for Hasfunctions with default values and 2 " +
                "if you want to try own values(in progress) or type 2 or 3 for Recomenderfunctionsservice with default values " +
                "or your own values(in progress)!");

        while (!typeAcceptable) {
            String input = scanner.nextLine();
            try {

                int type = Integer.parseInt(input);
                if (type != 1 && type != 2 && type != 3 && type != 4) {
                    System.out.println("Please just type 1, 2, 3 or 4!");
                }
                if (type == 1) {
                    System.out.println("You choose hashfunctions");

                    typeAcceptable = true;
                    useHashFunctionService();
                    break;
                } else if (type == 2) {
                    System.out.println("You choose hashfunctionsservice with own values" +
                            " a feature yet to be implemented");


                    // typeAcceptable=true;
                    System.out.println("Please choose again");
                } else if (type == 3) {
                    System.out.println("You choose recomenderfunctionsservice");
                    System.out.println("Please type 1 for normal calculation of c() and 2 for optimized version");
                    input = scanner.nextLine();
                    try {

                        type = Integer.parseInt(input);
                        if (type != 1 && type != 2) {
                            System.out.println("Please just type 1, 2, 3 or 4!");
                        }
                        if (type == 1) {
                            System.out.println("You choose normal");

                            typeAcceptable = true;
                            boolean opt = false;
                            useRecomenderFunctionsService(opt);
                            break;
                        } else if (type == 2) {
                            System.out.println("You choose optimized");

                            typeAcceptable = true;
                            boolean opt = true;
                            useRecomenderFunctionsService(opt);
                            break;

                        } else if (type == 4) {
                            System.out.println("You choose recomenderfunctionsservice with own values" +
                                    " a feature yet to be implemented");

                            // typeAcceptable=true;
                            System.out.println("Please choose again");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Sorry only integer allowed as input, you typed in: " + input);
                    }
                }


            }  catch (NumberFormatException e) {
                System.out.println("Sorry only integer allowed as input, you typed in: " + input);
            }
        }

    }

    private static void useRecomenderFunctionsService(boolean opt) {

        List<Person> personList = new LinkedList<>();
        Person alice;
        personList.add(alice = new Person("Alice", new LinkedList<Item>()));
        Person bob;
        personList.add(bob = new Person("Bob", new LinkedList<Item>()));
        Person carol;
        personList.add(carol = new Person("Carol", new LinkedList<Item>()));
        Person diana;
        personList.add(diana = new Person("Diana", new LinkedList<Item>()));
        Person emil;
        personList.add(emil = new Person("Emil", new LinkedList<Item>()));
        Person fran;
        personList.add(fran = new Person("Fran", new LinkedList<Item>()));
        Person gordon;
        personList.add(gordon = new Person("Gordon", new LinkedList<Item>()));
        Person hugh;
        personList.add(hugh = new Person("Hugh", new LinkedList<Item>()));
        Person isaac;
        personList.add(isaac = new Person("Isaac", new LinkedList<Item>()));

        List<Item> itemList = new LinkedList<>();

        Item theChild = new Item("The Child");
        Item theSin = new Item("The Sin");
        Item sanctuary = new Item("Sanctuary");
        Item theReckoning = new Item("The Reckoning");
        Item redemption = new Item("Redemption");

        itemList.add(theChild);
        itemList.add(theSin);
        itemList.add(sanctuary);
        itemList.add(theReckoning);
        itemList.add(redemption);


        RecomenderFunctionsService recomService = new RecomenderFunctionsService(personList, itemList);

        recomService.getPerson("Alice").setStandartItemListAndFastSetRatings(3, 1, 2,  0.0, 5);
        recomService.getPerson("Bob").setStandartItemListAndFastSetRatings(3,4,2, 0.0 ,4);
        recomService.getPerson("Carol").setStandartItemListAndFastSetRatings(1, 0.0, 4, 0.0, 2);
        recomService.getPerson("Diana").setStandartItemListAndFastSetRatings(3, 0.0, 0.0, 3, 0.0);
        recomService.getPerson("Emil").setStandartItemListAndFastSetRatings(3, 5, 4, 3, 5);
        recomService.getPerson("Fran").setStandartItemListAndFastSetRatings(3,5, 4, 3, 0.0);
        recomService.getPerson("Gordon").setStandartItemListAndFastSetRatings(4,5,3, 4,5);
        recomService.getPerson("Hugh").setStandartItemListAndFastSetRatings(2,3,3, 4,4);
        recomService.getPerson("Isaac").setStandartItemListAndFastSetRatings(5,1,3,5,4);

        recomService.getItem("The Sin").setStandartPersonListAndFastSetRatings(1, 4, 0,0,5,5 ,5,3,1 );
        recomService.getItem("Redemption").setStandartPersonListAndFastSetRatings(5,4,2,0 , 5,0,5,4,4);

        recomService.calculateSim(recomService.getPerson("Fran") ,recomService.getPerson("Alice"));
        recomService.calculateSim(recomService.getPerson("Fran") , recomService.getPerson("Bob"));
        recomService.calculateSim(recomService.getPerson("Fran") , recomService.getPerson("Carol"));
        recomService.calculateSim(recomService.getPerson("Fran") , recomService.getPerson("Diana"));
        recomService.calculateSim(recomService.getPerson("Fran") , recomService.getPerson("Emil"));
        recomService.calculateSim(recomService.getPerson("Fran") , recomService.getPerson("Gordon"));
        recomService.calculateSim(recomService.getPerson("Fran") , recomService.getPerson("Hugh"));
        recomService.calculateSim(recomService.getPerson("Fran")  , recomService.getPerson("Isaac"));

        recomService.printSims(fran);

        recomService.calculateR(fran, fran.getItem("Redemption"), opt);


        recomService.calculateSimItemToItem(recomService.getItem("The Sin") ,recomService.getItem("Redemption"));
    }

    private static void useHashFunctionService() {
        List<String> termList = new LinkedList<>();

        // djarin, child, caras, nevarro, ” sorgan“ , rebels, sandcrawler, spaceport, mythrol

        termList.add("djarin");
        termList.add("child");
        termList.add("caras");
        termList.add("nevarro");
        termList.add(" sorgan");
        termList.add("rebels");
        termList.add("sandcrawler");
        termList.add("spaceport");
        termList.add("mythrol");


        weights1.putIfAbsent(0, 1);
        weights1.putIfAbsent(1, 1);
        weights1.putIfAbsent(2, 9);
        weights1.putIfAbsent(3, 3);
        weights1.putIfAbsent(4, 4);
        weights1.putIfAbsent(5, 10);
        weights1.putIfAbsent(6, 3);
        weights1.putIfAbsent(7, 2);
        weights1.putIfAbsent(8, 4);
        weights1.putIfAbsent(9, 0);
        weights1.putIfAbsent(10, 14);


        weights2.putIfAbsent(0, 11);
        weights2.putIfAbsent(1, 6);
        weights2.putIfAbsent(2, 8);
        weights2.putIfAbsent(3, 12);
        weights2.putIfAbsent(4, 4);
        weights2.putIfAbsent(5, 2);
        weights2.putIfAbsent(6, 14);
        weights2.putIfAbsent(7, 12);
        weights2.putIfAbsent(8, 13);
        weights2.putIfAbsent(9, 7);
        weights2.putIfAbsent(10, 6);

        HashFunctionService hashFunctionService = new HashFunctionService();

        hashFunctionService.getHashValueForListOfTerms(termList, weights1, weights2);

        hashFunctionService.formatAndPrintResultsDefault(termList);
    }


}
