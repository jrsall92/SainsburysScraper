package com.jsalliaris.domain;

import java.util.Collection;

public class Response {

    private Collection<Item> results;
    private Total total;

    public Response(Collection<Item> results, Total total) {
        this.results = results;
        this.total = total;
    }

    public Collection<Item> getResults() {
        return results;
    }

    public void setResults(Collection<Item> results) {
        this.results = results;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Response{" +
                "results=" + results +
                ", total=" + total +
                '}';
    }
}
