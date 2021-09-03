package com.example.examenmichelle;

public class Journal1 {
    private String issue_id;
    private String date_published;
    private String title;
    private String doi;
    private String cover;

    public Journal1(String issue_id, String date_published, String title, String doi, String cover) {
        this.issue_id = issue_id;
        this.date_published = date_published;
        this.title = title;
        this.doi = doi;
        this.cover = cover;
    }

    public String getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(String issue_id) {
        this.issue_id = issue_id;
    }

    public String getDate_published() {
        return date_published;
    }

    public void setDate_published(String date_published) {
        this.date_published = date_published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
