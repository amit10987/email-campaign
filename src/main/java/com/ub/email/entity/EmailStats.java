package com.ub.email.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EmailStats {

    public EmailStats() {

    }

    @Id
    @GeneratedValue
    private Long id;
    private Long totalDelivered = 0l;
    private Long totalOpened = 0l;
    private Long totalClicked = 0l;
    private Long totalSent = 0l;
    private Long totalFailed = 0l;
    private String campaignName;
    private String templateName;
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Long getTotalFailed() {
        return totalFailed;
    }

    public void setTotalFailed(Long totalFailed) {
        this.totalFailed = totalFailed;
    }

    public Long getTotalSent() {
        return totalSent;
    }

    public void setTotalSent(Long totalSent) {
        this.totalSent = totalSent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalDelivered() {
        return totalDelivered;
    }

    public void setTotalDelivered(Long totalDelivered) {
        this.totalDelivered = totalDelivered;
    }

    public Long getTotalOpened() {
        return totalOpened;
    }

    public void setTotalOpened(Long totalOpened) {
        this.totalOpened = totalOpened;
    }

    public Long getTotalClicked() {
        return totalClicked;
    }

    public void setTotalClicked(Long totalClicked) {
        this.totalClicked = totalClicked;
    }

    public void incrementTotalFailed() {
        totalFailed++;
    }

    public void incrementTotalClicked() {
        totalClicked++;
    }
}
