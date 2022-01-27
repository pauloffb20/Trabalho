package com.company.Classes;
import com.company.Models.*;
import com.company.Estruturas.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class GestaoEmpresa<T> {
    private ArrayUnorderedList<Vendedor> vendedores;
    //private ArrayUnorderedList<Local> locais;
    private ArrayUnorderedList<Caminho> paths;
    private Gestor gestor;
    private Network<LocalX> networkX;

    public GestaoEmpresa() {
        this.vendedores = new ArrayUnorderedList<>();
        //this.locais = new ArrayUnorderedList<>();
        this.paths = new ArrayUnorderedList<>();
        this.networkX = new Network<>();
        this.gestor = new Gestor(vendedores);
    }

    public void addVendedor(Vendedor m) throws NoComparableException {
        this.vendedores.addToRear(m);
    }

   // public void addLocal(Local m) throws NoComparableException {
     //   this.locais.addToRear(m);
   // }

    public void addCaminho(Caminho m) throws NoComparableException {
        this.paths.addToRear(m);
    }

    //public ArrayUnorderedList<Local> getLocais() {
     //   return locais;
    //}

    public ArrayUnorderedList<Vendedor> getVendedors() {
        return vendedores;
    }

    public void readJson(String path) throws IOException, ParseException, NoComparableException {
        try (FileReader reader = new FileReader(ClassLoader.getSystemResource(path).getFile())) {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONArray lang = (JSONArray) jsonObject.get("vendedores");
            JSONArray locals = (JSONArray) jsonObject.get("locais");
            JSONArray caminhos = (JSONArray) jsonObject.get("caminhos");

            //Users nao mexer
            Iterator i = lang.iterator();
            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();
                long ID = (long) innerObj.get("id");
                String nome = (String) innerObj.get("nome");
                long capacity = (long) innerObj.get("capacidade");
                JSONArray jsonArray = (JSONArray) innerObj.get("mercados_a_visitar");
                ArrayUnorderedList<String> mercados = new ArrayUnorderedList<>();

                if (jsonArray != null)
                    for (int l = 0; l < jsonArray.size(); l++) {
                        String mercado = jsonArray.get(l).toString();
                        mercados.addToRear(mercado);
                    }
                else {
                    mercados.addToRear("null");
                }
                Vendedor vendedor = new Vendedor(ID, nome, capacity, mercados);
                this.addVendedor(vendedor);
            }

            //locais
            Iterator j = locals.iterator();
            while (j.hasNext()) {

                JSONObject newObj = (JSONObject) j.next();
                String name = (String) newObj.get("nome");
                String tipo = (String) newObj.get("tipo");

                if (tipo.equals("Armazém")) {
                    long capacidade = (long) newObj.get("capacidade");
                    long stock = (long) newObj.get("stock");
                    Armazem armazem = new Armazem(name, tipo, (int) capacidade, (int) stock);
                    this.networkX.addVertex(armazem);
                } else if (tipo.equals("Mercado")) {
                    JSONArray jsonArray = (JSONArray) newObj.get("clientes");
                    ArrayUnorderedList<Integer> clienteslist = new ArrayUnorderedList<>();

                    if (jsonArray != null) {
                        for (int l = 0; l < jsonArray.size(); l++) {
                            int clientNr = Integer.parseInt(jsonArray.get(l).toString());
                            clienteslist.addToRear(clientNr);
                        }
                    } else {
                        clienteslist.addToRear(0);
                    }

                    Mercado mercado = new Mercado(name, tipo, clienteslist);
                    this.networkX.addVertex(mercado);
                } else {
                    Sede sede = new Sede(name, tipo);
                    this.networkX.addVertex(sede);
                }
            }

            //caminhos
            Iterator k = caminhos.iterator();
            while (k.hasNext()) {
                JSONObject newObj = (JSONObject) k.next();
                String de = (String) newObj.get("de");
                String para = (String) newObj.get("para");
                long distancia = (long) newObj.get("distancia");
                Caminho caminho = new Caminho(de, para, (int) distancia);

                Object[] locais = networkX.getVertices();
                LocalX inicio = findLocalByName(de);
                LocalX fim = findLocalByName(para);

                for (int t = 0; t < networkX.size(); t++) {
                    LocalX atual = (LocalX) locais[t];
                    if (atual.getLocal_name().equals(de)) {
                        inicio = atual;
                    } else if (atual.getLocal_name().equals(para)) {
                        fim = atual;
                    }
                }

                if (inicio.getLocal_name() != null && fim.getLocal_name() != null) {
                    networkX.addEdge(inicio, fim, distancia);
                }
            }
        }
    }

    public Network<LocalX> getNetworkX(){
        return networkX;
    }

    @Override
    public String toString() {
        String s = "Vendedores:\n";
        s += "*-------------------------------*\n";
        for (Vendedor vendedor: vendedores) {
            s += "\n" + vendedor.toString() + "\n";
        }
        s += "\n" + "*-------------------------------*\n";
        s += "Locais: \n";
        for (int i = 0; i < networkX.size(); i++){
            s += "\n" + networkX.getVertex(i) + "\n";
        }
        s += "\n" + "*-------------------------------*\n";
        return s;
    }

    public void AddOrSetStorage() {
        GestãoArmazem gestãoArmazem = new GestãoArmazem(networkX);
        gestãoArmazem.AddOrSetStorage();
    }

    public void AddOrSetMarkets() {
        GestaoMercados gestaoMercados = new GestaoMercados(networkX);
        gestaoMercados.AddOrSetMarkets();
    }

    public void printMarkets(){
        GestaoMercados gestaoMercados = new GestaoMercados(networkX);
        gestaoMercados.printMarketsToShow();
    }

    public void printStorages(){
        GestãoArmazem gestãoArmazem = new GestãoArmazem(networkX);
        gestãoArmazem.printStoragesToShow();
    }

    public void addEdge(){
        GestaoCaminhos gestaoCaminhos = new GestaoCaminhos(networkX);
        networkX = gestaoCaminhos.addEdge();
    }

    public LocalX findLocalByName(String nome){
        Object[] vertices = networkX.vertices;
        for(Object o : vertices){
            LocalX local = (LocalX) o;
            if(local.getLocal_name().equals(nome)){
                return local;
            }
        }
        return null;
    }


    public void seeMarketsOrStorages(){
        int choice;
        System.out.println("Qual quer ver?");
        System.out.println("1- Mercados");
        System.out.println("2- Armazens");
        System.out.println("3- Vendedores");
        Scanner input = new Scanner(System.in);
        choice = Integer.parseInt(input.next());
        while (choice != 4) {
            switch (choice) {
                case 1:
                    printMarkets();
                    choice = 5;
                    break;
                case 2:
                    printStorages();
                    choice = 5;
                    break;
                case 3:
                    gestor.printSellersToShow();
                    choice = 5;
                    break;
                default:
                    return;
            }
        }
    }
}
