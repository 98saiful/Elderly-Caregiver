package com.sazs.fyptest1;

public class Guide {
    private int id;
    private String title;
    private String subtitle;
    private String description;
    private String picture;

    public Guide(int id, String title, String subtitle, String description, String picture) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }
}
