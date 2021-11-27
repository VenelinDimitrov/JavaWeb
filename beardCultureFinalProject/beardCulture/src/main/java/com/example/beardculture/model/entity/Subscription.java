package com.example.beardculture.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subscriptions")
public class Subscription extends BaseEntity{

    private User user;
    private Set<Product> subscriptionBox;

    public Subscription() {
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "subscription", fetch = FetchType.EAGER)
    public Set<Product> getSubscriptionBox() {
        return subscriptionBox;
    }

    public void setSubscriptionBox(Set<Product> subscriptionBox) {
        this.subscriptionBox = subscriptionBox;
    }
}
