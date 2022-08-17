package library_project.models;

public class People {
    private String name;
    private int yearBorn;

    public People(String name, int yearBorn) {
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
}
