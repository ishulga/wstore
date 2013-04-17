package com.shulga.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Role implements Serializable, HasId {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ROLE_TO_ROLE", joinColumns = @JoinColumn(name = "CHILD_ID"), inverseJoinColumns = @JoinColumn(name = "PARENT_ID"))
    private Set<Role> parents = new HashSet<Role>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ROLE_TO_ROLE", joinColumns = @JoinColumn(name = "PARENT_ID"), inverseJoinColumns = @JoinColumn(name = "CHILD_ID"))
    private Set<Role> children = new HashSet<Role>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getParents() {
        return parents;
    }

    public void setParents(Set<Role> parents) {
        this.parents = parents;
    }

    public Set<Role> getChildren() {
        return children;
    }

    public void setChildren(Set<Role> children) {
        this.children = children;
    }

}
