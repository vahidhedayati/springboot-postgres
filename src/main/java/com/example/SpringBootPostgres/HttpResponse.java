package com.example.SpringBootPostgres;

public class HttpResponse {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public HttpResponse() { }
    public HttpResponse(String text) {
        this.text = text;
    }
}
