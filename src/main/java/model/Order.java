package model;

public class Order {
    private int id;
    private Curator curator;
    private Client client;

    public Order(int id, Curator curator, Client client) {
        this.id = id;
        this.curator = curator;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Curator getCurator() {
        return curator;
    }

    public void setCurator(Curator curator) {
        this.curator = curator;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (getId() != order.getId()) return false;
        if (getCurator() != null ? !getCurator().equals(order.getCurator()) : order.getCurator() != null) return false;
        return getClient() != null ? getClient().equals(order.getClient()) : order.getClient() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getCurator() != null ? getCurator().hashCode() : 0);
        result = 31 * result + (getClient() != null ? getClient().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", curator=" + curator +
                ", client=" + client +
                '}';
    }
}
