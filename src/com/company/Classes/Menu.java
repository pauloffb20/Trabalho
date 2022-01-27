package com.company.Classes;
import com.company.Models.LocalX;
import com.company.Models.Vendedor;
import com.company.Estruturas.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Scanner;

public class Menu {
    private String docFileName = null;
    private GestaoEmpresa gestaoEmpresa = new GestaoEmpresa();
    private Gestor gestor = new Gestor(gestaoEmpresa.getVendedors());
    private Writter writter;

    public Menu() { }

    public void run() throws IOException, InvalidIndexException, EmptyException, NotFoundException, ParseException, NoComparableException, EmptyCollectionException, org.json.simple.parser.ParseException {
        int choice = 0;
        System.out.println("Welcome!");
        while (choice != 15) {
            choice = initialMenu();

            switch (choice) {
                case 1:
                    isDocRead();
                    gestaoEmpresa.readJson(docFileName);
                    break;
                case 2:
                    System.out.println(gestaoEmpresa.toString());
                    break;
                case 3:
                    gestor.printSellersToShow();
                    break;
                case 4:
                    gestaoEmpresa.changeSeller();
                    break;
                case 5:
                    exports();
                    break;
                case 6:
                    gestaoEmpresa.addSeller();
                    break;
                case 7:
                    printGraph();
                    break;
                case 8:
                    gestaoEmpresa.AddOrSetStorage();
                    break;
                case 9:
                    gestaoEmpresa.AddOrSetMarkets();
                    break;
                case 10:
                    seeMarketsOrStorages();
                case 11:
                    gestaoEmpresa.addEdge();
                default:
            }
        }
    }

    private boolean isDocRead() throws NoComparableException, IOException, EmptyCollectionException {
        boolean isread = false;

        if (this.docFileName == null) {
            String mapFileName = readDoc();
            this.docFileName = "Documents/" + mapFileName;
            isread = true;
        }
        return isread;
    }

    private String readDoc() {
        ArrayUnorderedList<String> results = new ArrayUnorderedList<>();
        int counter = 1;
        System.out.println("Choose a file");

        File[] files = new File("src/Documents").listFiles();

        for (File file : files) {
            if (file.isFile()) {
                results.addToRear(file.getName());
                System.out.println(counter + " - " + file.getName());
                counter++;
            }
        }
        int mapFile;
        Scanner inputMoves = new Scanner(System.in);
        mapFile = inputMoves.nextInt();
        if (mapFile <= results.size()) {
            System.out.println("Valid file!");
        } else {
            return "NotFound";
        }
        return results.getIndex(mapFile - 1);
    }

    public Integer initialMenu() {
        Scanner input = new Scanner(System.in);
        String choice;
            System.out.println("Escolha uma das opções:");
            System.out.println("-------------------------\n");
            System.out.println("1 - Importar documento"); //funcional!!
            System.out.println("2 - Ver info da empresa"); //colocar grafo em vez de lista
            System.out.println("3 - Ver vendedores"); //funcional!!
            System.out.println("4 - Atualizar vendedor"); //funcional!!
            System.out.println("5 - Exports"); //funcional mas locais , storages e markets tem de ser separados e atraves da network
            System.out.println("6 - Adicionar vendedor"); //funcional!!
            System.out.println("7 - Mostrar Network"); // funcional!!
            System.out.println("8 - Adicionar ou alterar armazém"); //funcional!! Colocar um toString melhor
            System.out.println("9 - Adicionar ou alterar mercado"); // funcional!! Colocar toString melhor + adicionar + clientes no adicionar
            System.out.println("10- Ver mercados ou armazéns");// funcional!!
            System.out.println("11- Adicionar caminho");
            choice = input.next();
        return Integer.valueOf(choice);
    }

    public void seeMarketsOrStorages(){
        int choice;
        System.out.println("Qual quer ver?");
        System.out.println("1- Mercados");
        System.out.println("2- Armazens");
        Scanner input = new Scanner(System.in);
        choice = Integer.parseInt(input.next());

        while (choice != 3) {
            switch (choice) {
                case 1:
                    gestaoEmpresa.printMarkets();
                    choice = 3;
                    break;
                case 2:
                    gestaoEmpresa.printStorages();
                    choice = 3;
                    break;
                default:
                    return;
            }
        }
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
            Writter writter = new Writter();
            writter.appendEnterprise(gestaoEmpresa);
    }

    public void exportUser() throws IOException {
        int sellectStorage = 0;
        Scanner input = new Scanner(System.in);
        String choice;
        System.out.println("Qual utilizador quer exportar?");
        System.out.println("-------------------------\n");
        gestor.printSellers();
        ArrayUnorderedList<Vendedor> vendedores = gestaoEmpresa.getVendedors();
        choice = input.next();
        sellectStorage = Integer.valueOf(choice);
        Vendedor vendedor = vendedores.getIndex(sellectStorage);
        Writter writter = new Writter();
        writter.appendPersonToFile(vendedor);
    }

    /*
    public void exportStorage() throws IOException {
            int sellectStorage = 0;
            Scanner input = new Scanner(System.in);
            String choice;

            System.out.println("Qual local quer exportar?");
            System.out.println("-------------------------\n");
            gestor.printLocals();
            ArrayUnorderedList<Local> locals = gestaoEmpresa.getLocais();

            choice = input.next();
            sellectStorage = Integer.valueOf(choice);
            Local local = locals.getIndex(sellectStorage);
            Writter writter = new Writter();
            writter.appendStorage(local);
        } */

    public void printGraph() throws EmptyException {
        Network<LocalX> network;
        network = gestaoEmpresa.getNetworkX();
        Iterator<LocalX> i = network.iteratorBFS(0);
        while(i.hasNext()){
            System.out.println(i.next());
        }
    }
}
