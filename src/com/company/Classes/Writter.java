package com.company.Classes;
import com.company.Estruturas.ArrayUnorderedList;
import com.company.Models.Armazem;
import com.company.Models.Mercado;
import com.company.Models.Vendedor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Writter {
    Gson gson = new Gson();
     private GestaoEmpresa gestaoEmpresa;
     private Gestor gestor;
     private String fileNamePath  = "src/Documents/vendedores.json",
            storagePath = "src/Documents/Storage.json", enterprise = "src/Documents/enterprise.json", marketPath = "src/Documents/Market.json";

     public Writter(){ }

    public Writter(GestaoEmpresa gestaoEmpresa){
        this.gestaoEmpresa =  gestaoEmpresa;
        this.gestor = new Gestor(gestaoEmpresa.getVendedors());
    }

    public void appendEnterprise(GestaoEmpresa p) throws IOException {
        GestaoEmpresa gestaoEmpresa = p;
        String s = gestaoEmpresa.toString();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(this.enterprise);
        gson.toJson(s, writer);
        writer.flush();
        writer.close();
    }

    public void appendPersonToFile(Vendedor p) throws IOException {
            Vendedor vendedor = p;
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            FileWriter writer = new FileWriter(this.fileNamePath);
            gson.toJson(vendedor, writer);
            writer.flush();
            writer.close();
    }


    public void appendStorage(Armazem local) throws IOException {
        Armazem local1 = local;
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(this.storagePath);
        gson.toJson(local1, writer);
        writer.flush();
        writer.close();
    }

    public void appendMarket(Mercado local) throws IOException {
        Mercado local1 = local;
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(this.marketPath);
        gson.toJson(local1, writer);
        writer.flush();
        writer.close();
    }

}
