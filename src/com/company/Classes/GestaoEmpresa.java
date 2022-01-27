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
    private Gestor gestor = new Gestor();
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
        addVendedor(vendedor);
    }

    public void changeSeller() throws IOException, InvalidIndexException, EmptyException, NotFoundException, java.text.ParseException, NoComparableException, EmptyCollectionException, org.json.simple.parser.ParseException {
        int sellectSeller = 0 , sellectAtribut = 0;
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
            default:
        }
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
}
