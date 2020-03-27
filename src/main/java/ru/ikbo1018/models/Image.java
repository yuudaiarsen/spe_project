package ru.ikbo1018.models;

import java.io.InputStream;
import java.sql.Blob;

public class Image {
    private int id;
    private int appeal_id;
    private InputStream data;

    public Image(InputStream data) {
        this.data = data;
    }

    public Image(int id, int appeal_id, InputStream data) {
        this.id = id;
        this.appeal_id = appeal_id;
        this.data = data;
    }

    public Image(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppeal_id() {
        return appeal_id;
    }

    public void setAppeal_id(Integer appeal_id) {
        this.appeal_id = appeal_id;
    }

    public InputStream getData() {
        return data;
    }

    public void setData(InputStream data) {
        this.data = data;
    }

    public Image(Integer appeal_id, InputStream data) {
        this.appeal_id = appeal_id;
        this.data = data;
    }
}
