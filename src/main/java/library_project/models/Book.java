package library_project.models;

public class Book {
    private String name;
    private String author;
    private int year;
    private int id;

    public Book(String name, String author, int year, int id) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.id = id;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
