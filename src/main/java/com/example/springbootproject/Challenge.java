package com.example.springbootproject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Entity
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private LocalDateTime dateTime;
    private String title;
    private String tag;
    private String body;
    private boolean completed;
    private ArrayList<Long> depends;

    public Challenge() {}

    public Challenge(String title, String tag, String body, ArrayList<Long> depends) {
        dateTime = LocalDateTime.now();
        this.title = title;
        this.tag = tag;
        this.body = body;
        this.depends = depends;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDateTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy"));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean getComplete() {
        return completed;
    }

    public void setComplete(boolean completed) {
        this.completed = completed;
    }

    public void complete() {
        completed = true;
    }

    public void unComplete() {
        completed = false;
    }

    public ArrayList<Long> getDepends() {
        return depends;
    }

    public void setDepends(ArrayList<Long> depends) {
        this.depends = depends;
    }

    public void addDepends(Long id) {
        depends.add(id);
    }

    public void removeDepends(Long id) {
        if (!depends.contains(id)) {
            return;
        }
        depends.remove(id);
    }
}
