package com.ub.email.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class EmailStats {

    protected EmailStats(){

    }

    @Id
    @GeneratedValue
    private Long id;
    private Long totalDelivered;
    private Long totalOpened;
    private Long totalClicked;
    @OneToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalDelivered() {
        return totalDelivered;
    }

    public void setTotalDelivered(Long totalDelivered) {
        this.totalDelivered = totalDelivered;
    }

    public Long getTotalOpened() {
        return totalOpened;
    }

    public void setTotalOpened(Long totalOpened) {
        this.totalOpened = totalOpened;
    }

    public Long getTotalClicked() {
        return totalClicked;
    }

    public void setTotalClicked(Long totalClicked) {
        this.totalClicked = totalClicked;
    }
}
