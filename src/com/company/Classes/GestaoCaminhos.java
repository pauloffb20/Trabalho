package com.company.Classes;
import com.company.Models.LocalX;
import com.company.Estruturas.Network;
import java.util.Scanner;

public class GestaoCaminhos {

    private Network<LocalX> localNetwork;

    public GestaoCaminhos(Network<LocalX> network){
        this.localNetwork = network;
    }

    public Network<LocalX> addEdge() {
        int inicio, fim;
        double distancia;
        Object[] objects = localNetwork.getVertices();

        for (int i = 0; i < localNetwork.size(); i++) {
                System.out.println(i + objects[i].toString());
            }

        System.out.println("Escolha um vertice:");
        Scanner input = new Scanner(System.in);
        input.hasNext();
        inicio = Integer.parseInt(input.next());
        LocalX local1 = (LocalX) objects[inicio];

        for (int i = 0; i < localNetwork.size(); i++) {
                System.out.println(i + objects[i].toString());
            }

        System.out.println("Escolha um vertice:");
        Scanner input2 = new Scanner(System.in);
        input2.hasNext();
        fim = Integer.parseInt(input2.next());
        LocalX local2 = (LocalX) objects[fim];

        System.out.println("Escolha um vertice:");
        Scanner input3 = new Scanner(System.in);
        input3.hasNext();
        distancia = Integer.parseInt(input3.next());

        localNetwork.addEdge(local1, local2, distancia);

        return localNetwork;
    }
}
