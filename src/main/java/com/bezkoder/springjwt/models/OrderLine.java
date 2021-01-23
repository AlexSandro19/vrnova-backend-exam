package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class OrderLine {

    @EmbeddedId
    @JsonIgnore
    private OrderLineKey pk;

    private Integer progress;

    public OrderLine() {
        super();
    }

    public OrderLine(Order order, Course product, Integer progress) {
        pk = new OrderLineKey();
        pk.setOrder(order);
        pk.setCourse(product);
        this.progress = progress;
    }

    @Transient
    public Course getCourse() {
        return this.pk.getCourse();
    }

    @Transient
    public Double getTotalPrice() {
        return getCourse().getPrice();
    }

    public OrderLineKey getPk() {
        return pk;
    }

    public void setPk(OrderLineKey pk) {
        this.pk = pk;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pk == null) ? 0 : pk.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OrderLine other = (OrderLine) obj;
        if (pk == null) {
            if (other.pk != null) {
                return false;
            }
        } else if (!pk.equals(other.pk)) {
            return false;
        }

        return true;
    }
}
