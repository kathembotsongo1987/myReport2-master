package com.example.ReportSolution.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "myproducts")
public class Items implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private  Long id;
    @Column(name = "items")
    private  String items;
    @Column(name = "quantity")
    private  String quantity;
    @Column(name = "price")
    private  Long price;
    @Column(name = "created_at")
    private Date created_at;
    @Column(name = "updated_at")
    private  Date updated_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
