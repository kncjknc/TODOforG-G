package EmployeesOf.G.G.model;

import EmployeesOf.G.G.dto.CustomRevisionListner;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import java.util.Date;

@Entity
@Table(name = "revinfo")
@RevisionEntity(CustomRevisionListner.class)
public class CustomRevisionEntity extends DefaultRevisionEntity {
    private String modifierUser;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getModifierUser() {
        return modifierUser;
    }

    public void setModifierUser(String modifierUser) {
        this.modifierUser = modifierUser;
    }
}

