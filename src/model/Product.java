package model;

public class Product {
    private String name;
    private int cost;
    private int id;
    public Product(int id ,String name, int cost){
        this.id =id;
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }
}
