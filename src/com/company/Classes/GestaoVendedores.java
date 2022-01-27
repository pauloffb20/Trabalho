package com.company.Classes;

import com.company.Estruturas.ArrayUnorderedList;
import com.company.Estruturas.NoComparableException;
import com.company.Models.Vendedor;

import java.util.Scanner;

public class GestaoVendedores {

    private GestaoEmpresa gestaoEmpresa;
    private Gestor gestor;

    public GestaoVendedores(GestaoEmpresa gestaoEmpresa){
        this.gestaoEmpresa = gestaoEmpresa;
        this.gestor = new Gestor(gestaoEmpresa.getVendedors());
    }

    public void changeSeller() {
        int sellectSeller = 0 , sellectAtribut = 0;
        ArrayUnorderedList<Vendedor> vendedores = gestaoEmpresa.getVendedors();
        sellectSeller = gestor.changeUser();
        sellectAtribut = gestor.changeAtribute();
        switch (sellectAtribut) {
            case 1:
                Vendedor vendedor = vendedores.getIndex(sellectSeller);
                String string = gestor.changeName();
                vendedor.setNome(string);
                break;
            case 2:
                Vendedor vendedor2 = vendedores.getIndex(sellectSeller);
                long capacidade = gestor.changeCapacidade();
                vendedor2.setCapacidade(capacidade);
                break;
            case 3:
                Vendedor vendedor3 = vendedores.getIndex(sellectSeller);
                ArrayUnorderedList<String> mercados = gestor.atribuirLista();
                vendedor3.setMercados(mercados);
                break;
            default:
        }
    }

    public void addSeller() throws NoComparableException {
        Vendedor vendedor;
        String nome;
        long id, capacidade;

        System.out.println("ID:");
        Scanner input3 = new Scanner(System.in);
        id = Long.parseLong(input3.next());

        System.out.println("Name:");
        Scanner input = new Scanner(System.in);
        nome = input.next();

        System.out.println("Capacidade:");
        Scanner input2 = new Scanner(System.in);
        capacidade = Long.parseLong(input2.next());

        ArrayUnorderedList<String> mercados = new ArrayUnorderedList<String>();
        System.out.println("Adicionar mercado - 1");
        System.out.println("Não adicionar - 2");
        Scanner input5 = new Scanner(System.in);
        int choice = input5.nextInt();

        while(choice != 2){
            System.out.println("Mercados:");
            Scanner input4 = new Scanner(System.in);
            String mercado = String.valueOf(input4.next());
            mercados.addToRear(mercado);
            System.out.println("Adicionar mercado - 1");
            System.out.println("Não adicionar - 2");
            input5.hasNext();
            choice = input5.nextInt();
        }
        vendedor = new Vendedor(id, nome, capacidade, mercados);
        gestaoEmpresa.addVendedor(vendedor);
    }


}
