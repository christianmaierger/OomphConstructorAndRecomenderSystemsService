import java.util.LinkedList;
import java.util.List;

public class Person {

    private String name;
    private List<Item> itemList = new LinkedList<>();
    private Double averageRating = 0.00000000000000000000000000000000000000000;
    private double currentSim =0;
    private Double rating= 0.00000000000000000000000000000000000000000;



    public Person(String name, List<Item> itemList) {
        this.name = name;
        this.itemList = itemList;
    }



    public double getCurrentSim() {
        return currentSim;
    }

    public void setCurrentSim(double currentSim) {
        this.currentSim = currentSim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Item getItem(String name) {

        for (Item item : getItemList()) {
            if (item.getTitle().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public void setStandartItemList() {
        List<Item> standartList = new LinkedList<>();

        Item theChild = new Item("The Child");
        Item theSin = new Item("The Sin");
        Item sanctuary = new Item("Sanctuary");
        Item theReckoning = new Item("The Reckoning");
        Item redemption = new Item("Redemption");

        standartList.add(theChild);
        standartList.add(theSin);
        standartList.add(sanctuary);
        standartList.add(theReckoning);
        standartList.add(redemption);

        this.setItemList(standartList);
    }

    public void setStandartItemListAndFastSetRatings(double a, double b, double c, double d, double e) {
        setStandartItemList();

        List<Double> params = new LinkedList<>();

        params.add(a);
        params.add(b);
        params.add(c);
        params.add(d);
        params.add(e);


        fastSetItemRatings(params);

    }

    public void fastSetItemRatings (List<Double> params) {

        for(int i = 0; i<getItemList().size(); i++) {
            getItemList().get(i).setRating(Double.valueOf(params.get(i)));
        }
    }



}
