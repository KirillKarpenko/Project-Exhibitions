package model.entity;

import java.util.Objects;

public class Ticket {

    private Exposition exposition;
    private int quantity;

    public Exposition getExposition() {
        return exposition;
    }

    public void setExposition(Exposition exposition) {
        this.exposition = exposition;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "exposition=" + exposition +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return exposition.equals(ticket.exposition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exposition);
    }

}
