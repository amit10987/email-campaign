package com.ub.email.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Campaign {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany
    private List<EmailTemplate> templates;

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
}
