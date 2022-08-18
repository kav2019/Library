package library_project.models;

public class People {
    private int id;
    private String name;
    private int yearBorn;

    public People(int id, String name, int yearBorn) {
        this.id = id;
        this.name = name;
        this.yearBorn = yearBorn;
    }

    public People() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearBorn() {
        return yearBorn;
    }

    public void setYearBorn(int yearBorn) {
        this.yearBorn = yearBorn;
    }

    public int getId() {
        return id;
    }
}
