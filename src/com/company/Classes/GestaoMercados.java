package com.company.Classes;
import com.company.Models.LocalX;
import com.company.Models.Mercado;
import com.company.Estruturas.ArrayUnorderedList;
import com.company.Estruturas.Network;
import java.util.Scanner;

public class GestaoMercados {

    private Network<LocalX> network;

    public GestaoMercados(Network<LocalX> localNetwork){
        this.network = localNetwork;
    }

    public void printMarkets() {
        Object[] locais = network.getVertices();

        for (int i = 0; i < network.size(); i++) {
            LocalX atual = (LocalX) locais[i];
            if (atual.getType().equals("Mercado")) {
                System.out.println(i + " para escolher o :" + locais[i].toString());
            }
        }
    }

    public void printMarketsToShow() {
        Object[] locais = network.getVertices();
        for (int i = 0; i < network.size(); i++) {
            LocalX atual = (LocalX) locais[i];
            if (atual.getType().equals("Mercado")) {
                System.out.println(locais[i].toString());
            }
        }
    }

    public void setMarket(){
        Object[] locais = network.getVertices();
        int choice, choice4 = 0, cliente;
        String nome;

        printMarkets();
        System.out.println("Escolha o mercado a alterar:");
        Scanner input = new Scanner(System.in);
        input.hasNext();
        choice = Integer.parseInt(input.next());
        Mercado mercado = (Mercado) locais[choice];

        while (choice4 != 3) {
            System.out.println("Qual item quer alterar?");
            System.out.println("1- Nome");
            System.out.println("2- Lista de clientes");
            System.out.println("3- Exit");
            Scanner input5 = new Scanner(System.in);
            choice4 = Integer.parseInt(input5.next());

            switch (choice4) {
                case 1:
                    System.out.println("Nome?");
                    Scanner input6 = new Scanner(System.in);
                    input6.hasNext();
                    nome = String.valueOf(input6.next());
                    mercado.setLocal_name(nome);
                    choice4 = 3;
                    break;
                case 2:
                    ArrayUnorderedList<Integer> clientes = mercado.getClientes();
                    System.out.println("Clientes?");
                    Scanner input7 = new Scanner(System.in);
                    input7.hasNext();
                    cliente = Integer.parseInt(input.next());
                    clientes.addToRear(cliente);
                    break;
                default:
                    return;
            }
        }
    }

    public void addMarket(){
        String name, tipo = "Mercado";
        int cliente;
        ArrayUnorderedList<Integer> clientes = new ArrayUnorderedList<>();

        System.out.println("Nome:");
        Scanner input4 = new Scanner(System.in);
        input4.hasNext();
        name = String.valueOf(input4.next());

        System.out.println("Cliente:");
        Scanner input = new Scanner(System.in);
        input.hasNext();
        cliente = Integer.parseInt(input.next());
        clientes.addToRear(cliente);

        Mercado novoMercado = new Mercado(name, tipo, clientes);
        network.addVertex(novoMercado);
    }

    public void AddOrSetMarkets() {

        int menu;

        System.out.println("1- Mudar Mercado");
        System.out.println("2- Adicionar mercado");
        System.out.println("3 - exit");
        Scanner input = new Scanner(System.in);
        input.hasNext();
        menu = Integer.parseInt(input.next());

        while (menu != 3) {
            switch (menu) {
                case 1:
                    setMarket();
                    System.out.println("1- Mudar Mercado");
                    System.out.println("2- Adicionar mercado");
                    System.out.println("3 - exit");
                    Scanner input1 = new Scanner(System.in);
                    input1.hasNext();
                    menu = Integer.parseInt(input1.next());
                    break;
                case 2:
                    addMarket();
                    System.out.println("1- Mudar Mercado");
                    System.out.println("2- Adicionar mercado");
                    System.out.println("3 - exit");
                    Scanner input2 = new Scanner(System.in);
                    input2.hasNext();
                    menu = Integer.parseInt(input2.next());
                    break;
                default:
                    return;
            }
        }
    }
}
