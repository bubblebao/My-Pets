package com.project.mypetsphuket.Model;

public class Images {

    private String name , description , url ,location ;


    public Images (){}

    public Images(String name, String description, String url, String location) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
