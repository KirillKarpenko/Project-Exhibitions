package model.entity;

import java.util.Objects;

public class Showroom {

    private int id;
    private String name;
    private Exposition exposition;

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

    public Exposition getExposition() {
        return exposition;
    }

    public void setExposition(Exposition exposition) {
        this.exposition = exposition;
    }

    @Override
    public String toString() {
        return id+","+name+","+exposition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Showroom showroom = (Showroom) o;
        return name.equals(showroom.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
