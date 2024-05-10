package com.example.demo.Models;

public class User {
    public User(){}
    public User(Integer id, String login, String password, String name, String surname, Double bonuses, String rights) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.bonuses = bonuses;
        this.rights = rights;
    }
    private Integer id = 0;
    private String login = "unknown";
    private String password = "unknown";
    private String name = "unknown";
    private String surname = "unknown";
    private Double bonuses = 0.0;
    private String rights = "unknown";
    private boolean access = false;

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Double getBonuses() {
        return bonuses;
    }

    public void setBonuses(Double bonuses) {
        this.bonuses = bonuses;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }
}