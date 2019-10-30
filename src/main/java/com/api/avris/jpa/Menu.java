package com.api.avris.jpa;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="menus")
public class Menu {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="menuId")
    public long id;

    @Column(name="nameMenu")
    public String nameMenu;

    @Column(name="urlImage")
    public String urlImage;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "menus")
    private Set<Role> roles = new HashSet<>();

}

