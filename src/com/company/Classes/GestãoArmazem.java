package com.company.Classes;
import com.company.Models.Armazem;
import com.company.Models.LocalX;
import com.company.Estruturas.Network;
import java.util.Scanner;

public class GestãoArmazem {

    private Network<LocalX> network;

    public GestãoArmazem(Network<LocalX> network){
        this.network = network;
    }

    public void printStorages() {
        Object[] locais = network.getVertices();

        for (int i = 0; i < network.size(); i++) {
            LocalX atual = (LocalX) locais[i];
            if (atual.getType().equals("Armazém")) {
                System.out.println(i + " para escolher o :" + locais[i].toString());
            }
        }
    }

    public void printStoragesToShow() {
        Object[] locais = network.getVertices();
        for (int i = 0; i < network.size(); i++) {
            LocalX atual = (LocalX) locais[i];
            if (atual.getType().equals("Armazém")) {
                System.out.println(locais[i].toString());
            }
        }
    }

    public void setStorage(){
        Object[] locais = network.getVertices();
        int choice, choice2, choice3, choice4;
        String nome;

        printStorages();
        System.out.println("Escolha o armazem a alterar:");
        Scanner input = new Scanner(System.in);
        input.hasNext();
        choice = Integer.parseInt(input.next());

        Armazem armazem = (Armazem) locais[choice];

        System.out.println("Qual item quer alterar?");
        System.out.println("1- Nome");
        System.out.println("2- Capacidade");
        System.out.println("3- Stock");
        System.out.println("4- Exit");
        Scanner input5 = new Scanner(System.in);
        choice4 = Integer.parseInt(input5.next());

        while (choice4 != 4) {
            switch (choice4) {
                case 1:
                    System.out.println("Nome?");
                    Scanner input6 = new Scanner(System.in);
                    input6.hasNext();
                    nome = String.valueOf(input6.next());
                    armazem.setLocal_name(nome);
                    choice4 = 4;
                    break;
                case 2:
                    System.out.println("Capacidade?");
                    Scanner input2 = new Scanner(System.in);
                    input2.hasNext();
                    choice2 = Integer.parseInt(input2.next());
                    armazem.setCapacidade(choice2);
                    choice4 = 4;
                    break;
                case 3:
                    System.out.println("Quanto Stock?");
                    Scanner input3 = new Scanner(System.in);
                    input3.hasNext();
                    choice3 = Integer.parseInt(input3.next());
                    armazem.setStock(choice3);
                    choice4 = 4;
                    break;
                default:
                    return;
            }
        }
    }

    public void addStorage(){
        int choice, choice2;
        String name, tipo = "Armazém";

        System.out.println("Nome:");
        Scanner input4 = new Scanner(System.in);
        input4.hasNext();
        name = String.valueOf(input4.next());

        System.out.println("capacidade:");
        Scanner input6 = new Scanner(System.in);
        input6.hasNext();
        choice2 = Integer.parseInt(input6.next());

        System.out.println("Stock:");
        Scanner input7 = new Scanner(System.in);
        input7.hasNext();
        choice = Integer.parseInt(input7.next());
        Armazem novoarmazem = new Armazem(name, tipo, choice2, choice);
        network.addVertex(novoarmazem);
    }

    public void AddOrSetStorage() {
        int menu;
        Object[] locais = network.getVertices();
        System.out.println("1- Mudar armazem");
        System.out.println("2- Adicionar armazem");
        System.out.println("3 - exit");
        Scanner input = new Scanner(System.in);
        input.hasNext();
        menu = Integer.parseInt(input.next());

        while (menu != 3) {
            switch (menu) {
                case 1:
                    setStorage();
                    System.out.println("1- Mudar armazem");
                    System.out.println("2- Adicionar armazem");
                    System.out.println("3 - exit");
                    Scanner input1 = new Scanner(System.in);
                    input1.hasNext();
                    menu = Integer.parseInt(input1.next());
                    break;
                case 2:
                    addStorage();
                    System.out.println("1- Mudar armazem");
                    System.out.println("2- Adicionar armazem");
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
