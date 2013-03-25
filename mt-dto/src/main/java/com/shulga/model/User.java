package com.shulga.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("restriction")
@XmlRootElement
@Entity
public class User implements Serializable, HasId, Cachable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Transient
    private String key;
    private String name;
    private String lastname;
    private String login;
    private String password;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    private Set<Item> boughtItems = new HashSet<Item>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    private Set<Item> sellItems = new HashSet<Item>();

    @Transient
    private Credentials credentials;

    public User(String userLogin, String usrName) {
        this.login = userLogin;
        this.name = usrName;
    }

    public User() {

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Item> getBoughtItems() {
        return boughtItems;
    }

    public void setBoughtItems(Set<Item> boughtItems) {
        this.boughtItems = boughtItems;
    }

    public Set<Item> getSellItems() {
        return sellItems;
    }

    public void setSellItems(Set<Item> sellItems) {
        this.sellItems = sellItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
