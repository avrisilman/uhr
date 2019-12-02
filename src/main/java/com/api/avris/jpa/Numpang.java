package com.api.avris.jpa;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;

@Entity
public class Numpang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long id;

    @Column(name="goingto")
    public String goingto;

    @Column(name="clock")
    public String clock;

    @Column(name="date")
    public String date;

    @Column(name="information")
    public String information;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usersId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("usersId")
    private Users users;

    @Column(name="idNumpang")
    public long idNumpang;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGoingto() {
        return goingto;
    }

    public void setGoingto(String goingto) {
        this.goingto = goingto;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public long getIdNumpang() {
        return idNumpang;
    }

    public void setIdNumpang(long idNumpang) {
        this.idNumpang = idNumpang;
    }
}
