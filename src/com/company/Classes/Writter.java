package com.company.Classes;
import com.company.Estruturas.ArrayUnorderedList;
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
            storagePath = "src/Documents/Storage.json", enterprise = "src/Documents/enterprise.json";


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

    public void exports() throws IOException {
        int choice;
        System.out.println("Qual item quer exportar?");
        System.out.println("1- Empresa");
        System.out.println("2- User");
        System.out.println("3-Storage");
        Scanner input = new Scanner(System.in);
        choice = Integer.parseInt(input.next());
        while (choice != 4) {
            switch (choice) {
                case 1:
                    exportEnterprise();
                    choice = 4;
                    break;
                case 2:
                    exportUser();
                    choice = 4;
                    break;
                case 3:
                    // exportStorage();
                    choice = 4;
                    break;
                default:
                    return;
            }
        }
    }

    public void exportEnterprise() throws IOException {
        appendEnterprise(gestaoEmpresa);
    }

    public void exportUser() throws IOException {
        int sellectStorage = 0;
        String choice;
        Scanner input = new Scanner(System.in);
        System.out.println("Qual utilizador quer exportar?");
        System.out.println("-------------------------\n");
        gestor.printSellers();
        ArrayUnorderedList<Vendedor> vendedores = gestaoEmpresa.getVendedors();
        choice = input.next();
        sellectStorage = Integer.valueOf(choice);
        Vendedor vendedor = vendedores.getIndex(sellectStorage);
        appendPersonToFile(vendedor);
    }

    /*
    public void appendStorage(Local local) throws IOException {
        Local local1 = local;
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(this.storagePath);
        gson.toJson(local, writer);
        writer.flush();
        writer.close();
    }
    */
}
