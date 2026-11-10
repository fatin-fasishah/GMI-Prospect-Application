package com.week17.gmiprospect;
public class Programme {
    final String title;
    final String category;
    final String department;
    final String duration;
    final String fee;
    final String description;
    final String url;

    public Programme(String title, String category, String department,
                     String duration, String fee, String description, String url) {
        this.title = title;
        this.category = category;
        this.department = department;
        this.duration = duration;
        this.fee = fee;
        this.description = description;
        this.url = url;
    }
}
