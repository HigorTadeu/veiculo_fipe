package com.htbsc.fipe.main;

import com.htbsc.fipe.model.*;
import com.htbsc.fipe.service.ConnectionApi;
import com.htbsc.fipe.service.DataConverter;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    ConnectionApi connectionApi = new ConnectionApi();
    DataConverter dataConverter = new DataConverter();

    private final String ADDRESS_BEGIN = "https://parallelum.com.br/fipe/api/v1/";
    private final String ADDRESS_MARCA = "/marcas";
    private final String ADDRESS_MODEL = "/modelos";
    private final String ADDRESS_YEAR = "/anos";

    private int menu(){
        System.out.println("Informe qual o número do tipo de veículo gostaria: ");
        System.out.println("""
                 1 - Carro
                 2 - Caminhão
                 3 - Moto
                 Opção: """);
        int tipoVeiculo = scanner.nextInt();
        scanner.nextLine();

        return tipoVeiculo;
    }

    public void start(){
        var jsonMarca = "";
        var jsonModelo = "";
        var jsonAno = "";
        var jsonFinal = "";
        var codeModelo = "";

        int opcao = this.menu();
        switch (opcao){
            case 1:
                jsonMarca = connectionApi.getJson(ADDRESS_BEGIN + "carros" + ADDRESS_MARCA);
                List<Car> cars = new ArrayList<>();
                cars = dataConverter.getList(jsonMarca, Car.class);
                cars.forEach(System.out::println);

                System.out.println("Informe o modelo que deseja consultar: ");
                codeModelo = scanner.nextLine();

                this.pesquisaModelo("carro", codeModelo);
                this.pesquisaAno(codeModelo);

//                System.out.println("Informe o modelo que deseja consultar: ");
//                var codeModelo = scanner.nextLine();
//                jsonModelo = connectionApi.getJson(ADDRESS_BEGIN + "carros" + ADDRESS_MARCA + "/" + codeModelo + ADDRESS_MODEL);
//
//                var modelsList = dataConverter.getData(jsonModelo, Models.class);
//                modelsList.models().forEach(d -> System.out.println(d.toString()));
//
//                System.out.print("Informe parte do nome do modelo que deseja pesquisa: ");
//                var modelName = scanner.nextLine();
//
//                modelsList.models().stream()
//                        .filter(d -> d.getName().toLowerCase().contains(modelName.toLowerCase()))
//                        .forEach(System.out::println);

                System.out.print("Informe o código do modelo que deseja esquisar os anos: ");
                var code = scanner.nextLine();

                //https://parallelum.com.br/fipe/api/v1/carros/marcas/21/modelos/560/anos
                jsonAno = connectionApi.getJson(ADDRESS_BEGIN + "carros" + ADDRESS_MARCA + "/" + codeModelo + ADDRESS_MODEL + "/" + code + ADDRESS_YEAR);
                List<CData> yearsModels = dataConverter.getList(jsonAno, CData.class);
                yearsModels.forEach(System.out::println);

                System.out.print("Informe o código que deseja pesquisar: ");
                var codeYear = scanner.nextLine();
                jsonFinal = connectionApi.getJson(ADDRESS_BEGIN + "carros" + ADDRESS_MARCA + "/" + codeModelo + ADDRESS_MODEL + "/" + code + ADDRESS_YEAR + "/" + codeYear);

                var veiculo = dataConverter.getData(jsonFinal, Veiculo.class);
                System.out.println(veiculo);

                break;
            case 2:
                jsonMarca = connectionApi.getJson(ADDRESS_BEGIN + "motos" + ADDRESS_MARCA);
                List<Motocicle> motocs = new ArrayList<>();
                motocs = dataConverter.getList(jsonMarca, Motocicle.class);
                motocs.forEach(System.out::println);

                System.out.println("Informe o modelo que deseja consultar: ");
                codeModelo = scanner.nextLine();

                this.pesquisaModelo("moto", codeModelo);

                break;
            case 3:
                jsonMarca = connectionApi.getJson(ADDRESS_BEGIN + "caminhoes" + ADDRESS_MARCA);
                List<Truck> trucks = new ArrayList<>();
                trucks = dataConverter.getList(jsonMarca, Truck.class);
                trucks.forEach(System.out::println);

                System.out.println("Informe o modelo que deseja consultar: ");
                codeModelo = scanner.nextLine();

                this.pesquisaModelo("caminhao", codeModelo);

                break;
        }
    }

    private void pesquisaModelo(String tipoVeiculo, String codeModel){
        String jsonModelo= "";
        String address = "";

//        System.out.println("Informe o modelo que deseja consultar: ");
//        var codeModelo = scanner.nextLine();

        if(tipoVeiculo.equals("carro")) {
            address = ADDRESS_BEGIN + "carros" + ADDRESS_MARCA + "/" + codeModel + ADDRESS_MODEL;
        }else if (tipoVeiculo.equals("moto")){
            address = ADDRESS_BEGIN + "motos" + ADDRESS_MARCA + "/" + codeModel + ADDRESS_MODEL;
        } else {
            address = ADDRESS_BEGIN + "caminhoes" + ADDRESS_MARCA + "/" + codeModel + ADDRESS_MODEL;
        }

        jsonModelo = connectionApi.getJson(address);

        var modelsList = dataConverter.getData(jsonModelo, Models.class);
        modelsList.models().forEach(d -> System.out.println(d.toString()));

        System.out.print("Informe parte do nome do modelo que deseja pesquisa: ");
        var modelName = scanner.nextLine();

        modelsList.models().stream()
                .filter(d -> d.getName().toLowerCase().contains(modelName.toLowerCase()))
                .forEach(System.out::println);

    }

    private void pesquisaAno(String codeModel){
        String json = "";
        String address = "";

        System.out.print("Informe o código do modelo que deseja esquisar os anos: ");
        var code = scanner.nextLine();

        //https://parallelum.com.br/fipe/api/v1/carros/marcas/21/modelos/560/anos
        json = connectionApi.getJson(ADDRESS_BEGIN + "carros" + ADDRESS_MARCA + "/" + codeModel + ADDRESS_MODEL + "/" + code + ADDRESS_YEAR);
        List<CData> yearsModels = dataConverter.getList(json, CData.class);
        yearsModels.forEach(System.out::println);
    }
}
