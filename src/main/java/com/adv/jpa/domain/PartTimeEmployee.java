package com.adv.jpa.domain;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class PartTimeEmployee extends Employee {

    public PartTimeEmployee() {
    }

    public PartTimeEmployee(String name, BigDecimal hourPayment) {
        super(name);
        this.hourPayment = hourPayment;
    }

    private BigDecimal hourPayment;

    public BigDecimal getHourPayment() {
        return hourPayment;
    }

    public void setHourPayment(BigDecimal hourPayment) {
        this.hourPayment = hourPayment;
    }
}
