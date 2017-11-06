package com.notes.assignment.model;

public class NoteDTO {
    private Long id;
    private String description;
    private String title;

    public NoteDTO() {
    }

    public NoteDTO(Long id, String description, String title) {
        this.id = id;
        this.description = description;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
