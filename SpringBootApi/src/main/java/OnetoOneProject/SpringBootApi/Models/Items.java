package OnetoOneProject.SpringBootApi.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "items")
public class Items {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "itemname")
    @NotNull
    @Size(max = 1000, min = 1, message = "Item Name cannot be less than 0 or more than 1000")
    private String itemName;

    @Column(name = "itemweight")
    @Range(min = 0)
    @NotNull
    private double itemWeight;

    @Column(name = "itemprice")
    @NotNull
    @Range(min = 0)
    private double itemPrice;

    @Column(name = "status")
    @NotNull
    private String itemStatus;

    

    public Items() {
    }

    public Items(String itemName, double itemWeight, int itemPrice, String itemStatus) {
        this.itemName = itemName;
        this.itemWeight = itemWeight;
        this.itemPrice = itemPrice;
        this.itemStatus = itemStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(double itemWeight) {
        this.itemWeight = itemWeight;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemWeight=" + itemWeight +
                ", itemPrice=" + itemPrice +
                ", itemStatus=" + itemStatus +
                '}';
    }
}
