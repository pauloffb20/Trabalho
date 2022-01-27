package com.company.Classes.Models;

import com.company.Estruturas.ArrayUnorderedList;

public class Local {

    private String local_name, type;
    private int capacidade , stock;
    private ArrayUnorderedList<Integer> clientes;

    public Local(String name, String type, int capacidade, int stock, ArrayUnorderedList<Integer> clientes){
        this.local_name = name;
        this.type = type;
        this.capacidade = capacidade;
        this.stock = stock;
        this.clientes = clientes;
    }

    public Local() {

    }

    public String getLocal_name() {
        return local_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLocal_name(String local_name) {
        this.local_name = local_name;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ArrayUnorderedList<Integer> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayUnorderedList<Integer> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        String s = "";
        s += "Local{" +
                "local_name='" + local_name + '\'' +
                ", type='" + type + '\'' +
                ", capacidade=" + capacidade +
                ", stock=" + stock +
                ", clientes=";

        for (Integer cliente: clientes) {
            s +=  cliente.toString() + "\n";
        }

        return s;
    }
}
