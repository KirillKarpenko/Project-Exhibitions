package model.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Exposition {

    public enum Category {
        ART, ENTERTAINMENT, HISTORY, SCIENCE, TECHNOLOGIES, OTHER
    }

    private int id;
    private String name;
    private Category category;
    private Timestamp startDate;
    private Timestamp endDate;
    private double price;

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

    public Category getCategory() {
        if (category == null)
            category = Category.OTHER;

        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id+","+name+","+category+","+ startDate +","+ endDate +","+ price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exposition that = (Exposition) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
