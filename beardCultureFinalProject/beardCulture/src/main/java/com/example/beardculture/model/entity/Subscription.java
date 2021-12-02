package com.example.beardculture.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subscriptions")
public class Subscription extends BaseEntity{

    private User user;
    private Set<Product> productsInSubscription;

    public Subscription() {
    }

    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Product> getProductsInSubscription() {
        return productsInSubscription;
    }

    public void setProductsInSubscription(Set<Product> subscriptionBox) {
        this.productsInSubscription = subscriptionBox;
    }
}
