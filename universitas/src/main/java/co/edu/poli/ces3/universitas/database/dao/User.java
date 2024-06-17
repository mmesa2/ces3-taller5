package co.edu.poli.ces3.universitas.database.dao;

import java.util.Date;

public class User {

    private int id;
    private String courseName;
    private String semester;
    private String status;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public User(int id, String courseName, String semester, String status, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.courseName = courseName;
        this.semester = semester;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public User(String courseName, String semester, String status) {
        this.courseName = courseName;
        this.semester = semester;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
