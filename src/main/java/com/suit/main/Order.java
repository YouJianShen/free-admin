package com.suit.main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.suit.base.domain.BaseModel;

@Entity
@Table(name = "main_order")
public class Order extends BaseModel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * email
     */
    @Column(length = 40)
    private String email;

    /**
     * 地区
     */
    @Column(length = 60)
    private String region;

    /**
     * type
     */
    @Column(length = 40)
    private String type;

    @Column(length = 100)
    private String topic;

    @Column(length = 40)
    private String subject;

    @Column(name = "page_num")
    private int pageNum;

    @Column(name = "dead_line")
    private String deadLine;

    @Column(name = "service_type", length = 20)
    private String serviceType;

    @Column(length = 20)
    private String quality;

    @Column(name = "cite_num")
    private int citeNum;

    @Column(length = 20)
    private String citation;

    @Column(length = 100)
    private String instructions;

    @Column(length = 100)
    private String instructions_2;

    @Column(length = 20)
    private String writers;

    @Column(name = "language_level", length = 40)
    private String languageLevel;

    @Column(length = 40)
    private String contact;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int page) {
        this.pageNum = page;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getCiteNum() {
        return citeNum;
    }

    public void setCiteNum(int citeNum) {
        this.citeNum = citeNum;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(String languageLevel) {
        this.languageLevel = languageLevel;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getInstructions_2() {
        return instructions_2;
    }

    public void setInstructions_2(String instructions_2) {
        this.instructions_2 = instructions_2;
    }
}
