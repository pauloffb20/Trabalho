package com.company.Models;

public class Caminho {

    private String de;
    private String para;
    private int distancia;

    public Caminho(String de, String para, int distancia){
        this.de = de;
        this.para = para;
        this.distancia = distancia;
    }

    public Caminho(String caminho1) {

    }

    public int getDistancia() {
        return distancia;
    }

    public String getDe() {
        return de;
    }

    public String getPara() {
        return para;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "Caminho{" +
                "de='" + de + '\'' +
                ", para='" + para + '\'' +
                ", distancia=" + distancia +
                '}';
    }
}
