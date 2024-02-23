package com.example.rickandmorty.Data.Network;

import java.util.List;

public class ApiResponse<T> {
    private List<T> results;
    private T result;
    private Throwable error;

    public ApiResponse(List<T> results) {
        this.results = results;
        this.error = null;
    }

    public ApiResponse(Throwable error) {
        this.error = error;
        this.results = null;
        this.result = null;
    }

    public ApiResponse(T result) {
        this.result = result;
        this.error = null;
    }

    public List<T> getResults() {
        return results;
    }

    public Throwable getError() {
        return error;
    }

    public T getResult() {
        return result;
    }
}
