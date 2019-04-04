package com.ub.email.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @OneToMany
    private List<EmailTemplate> templates;

    public Campaign(){

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

    public List<EmailTemplate> getTemplates() {
        return templates;
    }

    public void setTemplates(List<EmailTemplate> templates) {
        this.templates = templates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
