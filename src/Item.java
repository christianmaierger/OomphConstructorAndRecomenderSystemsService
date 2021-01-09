import java.util.LinkedList;
import java.util.List;

public class Item {

    private Double rating= 0.00000000000000000000000000000000000000000;
    private String title;
    private List<Person> personList = new LinkedList<>();
    private Double averageRating = 0.00000000000000000000000000000000000000000;
    private double currentSim =0.0;


    public Item(String title) {
        this.rating = 0.00000000000000000000000000000000000000000;
        this.title = title;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public double getCurrentSim() {
        return currentSim;
    }

    public void setCurrentSim(double currentSim) {
        this.currentSim = currentSim;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public void setStandartPersonList() {
        List<Person> standartList = new LinkedList<>();


        Person alice;
        standartList.add(alice = new Person("Alice", new LinkedList<Item>()));
        Person bob;
        standartList.add(bob = new Person("Bob", new LinkedList<Item>()));
        Person carol;
        standartList.add(carol = new Person("Carol", new LinkedList<Item>()));
        Person diana;
        standartList.add(diana = new Person("Diana", new LinkedList<Item>()));
        Person emil;
        standartList.add(emil = new Person("Emil", new LinkedList<Item>()));
        Person fran;
        standartList.add(fran = new Person("Fran", new LinkedList<Item>()));
        Person gordon;
        standartList.add(gordon = new Person("Gordon", new LinkedList<Item>()));
        Person hugh;
        standartList.add(hugh = new Person("Hugh", new LinkedList<Item>()));
        Person isaac;
        standartList.add(isaac = new Person("Isaac", new LinkedList<Item>()));


        this.setPersonList(standartList);
    }

    public void setStandartPersonListAndFastSetRatings(double a, double b, double c, double d, double e, double f, double g, double h, double i) {
        setStandartPersonList();

        List<Double> params = new LinkedList<>();

        params.add(a);
        params.add(b);
        params.add(c);
        params.add(d);
        params.add(e);
        params.add(f);
        params.add(g);
        params.add(h);
        params.add(i);

        fastSetPersonRatings(params);

    }

    public void fastSetPersonRatings (List<Double> params) {

        for(int i = 0; i<getPersonList().size(); i++) {
            getPersonList().get(i).setRating(Double.valueOf(params.get(i)));
        }
    }

}
