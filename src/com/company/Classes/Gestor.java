package com.company.Classes;
import com.company.Models.LocalX;
import com.company.Models.Vendedor;
import com.company.Estruturas.ArrayUnorderedList;
import com.company.Estruturas.Network;
import java.util.Scanner;
import static java.lang.Long.valueOf;

public class Gestor {

    ArrayUnorderedList<Vendedor> vendedors;

  public Gestor(ArrayUnorderedList<Vendedor> vendedores){
      this.vendedors = vendedores;
  }

    public Gestor() { }

    public Integer changeUser() {
        Scanner input = new Scanner(System.in);
        String choice1;
        System.out.println("Qual utilizador quer alterar?");
        System.out.println("-------------------------\n");
        printSellers();
        choice1 = input.next();
        return Integer.valueOf(choice1);
    }

    public void printSellers(){
        int number = 0;
        for (int i= 0; i < vendedors.size(); i++){
            System.out.println(number + " para selecionar vendedor abaixo:" + "\n" + vendedors.getIndex(i).toString());
            number++;
        }
    }

    public void printSellersToShow(){
        for (int i= 0; i < vendedors.size(); i++){
            System.out.println(vendedors.getIndex(i).toString());
        }
    }

    public void printLocals(Network<LocalX> network){
      Network<LocalX> locais = network;
        for (int i= 0; i < network.size(); i++){
            System.out.println(network.getVertex(i));
        }
    }

    public Integer changeAtribute() {
        Scanner input = new Scanner(System.in);
        String choice1;
        System.out.println("Qual atributo quer alterar?");
        System.out.println("-------------------------\n");
        System.out.println("1-Nome");
        System.out.println("2-Capacidade");
        System.out.println("3-Sair");
        choice1 = input.next();
        return Integer.valueOf(choice1);
    }

    public String changeName() {
        Scanner input = new Scanner(System.in);
        String choice;
        System.out.println("Qual nome?");
        choice = input.next();
        return String.valueOf(choice);
    }

    public long changeCapacidade(){
        Scanner input = new Scanner(System.in);
        String choice;
        System.out.println("Qual capacidade?");
        choice = input.next();
        return valueOf(choice);
    }




}
