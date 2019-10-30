package com.api.avris.jpa;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class SubMenu {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="subId")
    public long id;

    @Column(name="nameSubMenu")
    public String nameSubMenu;

    @Column(name="urlImageSub")
    public String urlImageSub;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menuId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("menuId")
    private Menu menu;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameSubMenu() {
        return nameSubMenu;
    }

    public void setNameSubMenu(String nameSubMenu) {
        this.nameSubMenu = nameSubMenu;
    }

    public String getUrlImageSub() {
        return urlImageSub;
    }

    public void setUrlImageSub(String urlImageSub) {
        this.urlImageSub = urlImageSub;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
