package com.jsalliaris.domain;

public class Total {

    private double gross;
    private double vat;

    public Total(double gross) {
        this.gross = gross;
        this.vat = gross * 0.2;
    }

    public double getGross() {
        return gross;
    }

    public double getVat() {
        return vat;
    }
}
