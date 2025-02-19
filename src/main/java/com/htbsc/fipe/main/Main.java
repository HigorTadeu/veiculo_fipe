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
        var codeModelo = "";
        var retorno = false;
        var tipoVeiculo = "";

        int opcao = this.menu();
        switch (opcao){
            case 1:
                tipoVeiculo = "carro";
                jsonMarca = connectionApi.getJson(ADDRESS_BEGIN + "carros" + ADDRESS_MARCA);
                List<Car> cars = new ArrayList<>();
                cars = dataConverter.getList(jsonMarca, Car.class);
                cars.forEach(System.out::println);

                System.out.println("\nInforme o modelo que deseja consultar: ");
                codeModelo = scanner.nextLine();

                retorno = this.pesquisaModelo(tipoVeiculo, codeModelo);
                if (retorno){
                    System.out.print("\nInforme o código do modelo que deseja esquisar os anos: ");
                    var code = scanner.nextLine();

                    this.pesquisaAno(tipoVeiculo, codeModelo, code);
                    this.pesquisaVeiculo(tipoVeiculo, codeModelo, code);
                }

                break;
            case 2:
                tipoVeiculo = "moto";
                jsonMarca = connectionApi.getJson(ADDRESS_BEGIN + "motos" + ADDRESS_MARCA);
                List<Motocicle> motocs = new ArrayList<>();
                motocs = dataConverter.getList(jsonMarca, Motocicle.class);
                motocs.forEach(System.out::println);

                System.out.println("\nInforme o modelo que deseja consultar: ");
                codeModelo = scanner.nextLine();

                retorno = this.pesquisaModelo(tipoVeiculo, codeModelo);
                if (retorno){
                    System.out.print("\nInforme o código do modelo que deseja esquisar os anos: ");
                    var code = scanner.nextLine();

                    this.pesquisaAno(tipoVeiculo, codeModelo, code);
                    this.pesquisaVeiculo(tipoVeiculo, codeModelo, code);
                }

                break;
            case 3:
                tipoVeiculo = "caminhao";
                jsonMarca = connectionApi.getJson(ADDRESS_BEGIN + "caminhoes" + ADDRESS_MARCA);
                List<Truck> trucks = new ArrayList<>();
                trucks = dataConverter.getList(jsonMarca, Truck.class);
                trucks.forEach(System.out::println);

                System.out.println("\nInforme o modelo que deseja consultar: ");
                codeModelo = scanner.nextLine();

                retorno = this.pesquisaModelo(tipoVeiculo, codeModelo);
                if (retorno){
                    System.out.print("\nInforme o código do modelo que deseja esquisar os anos: ");
                    var code = scanner.nextLine();

                    this.pesquisaAno(tipoVeiculo, codeModelo, code);
                    this.pesquisaVeiculo(tipoVeiculo, codeModelo, code);
                }

                break;
        }
    }

    private boolean pesquisaModelo(String tipoVeiculo, String codeModel){
        String jsonModelo= "";
        String address = "";

        if(tipoVeiculo.equals("carro")) {
            address = ADDRESS_BEGIN + "carros" + ADDRESS_MARCA + "/" + codeModel + ADDRESS_MODEL;
        }else if (tipoVeiculo.equals("moto")){
            address = ADDRESS_BEGIN + "motos" + ADDRESS_MARCA + "/" + codeModel + ADDRESS_MODEL;
        } else {
            address = ADDRESS_BEGIN + "caminhoes" + ADDRESS_MARCA + "/" + codeModel + ADDRESS_MODEL;
        }

        jsonModelo = connectionApi.getJson(address);

        var modelsList = dataConverter.getData(jsonModelo, Models.class);
        if (modelsList != null ){
            if(modelsList.models() != null){
                modelsList.models().forEach(d -> System.out.println(d.toString()));
            } else {
                System.out.println("\nNão existe o modelo solicitado!");
                return false;
            }
        }

        System.out.print("\nInforme parte do nome do modelo que deseja pesquisa: ");
        var modelName = scanner.nextLine();

        modelsList.models().stream()
                .filter(d -> d.getName().toLowerCase().contains(modelName.toLowerCase()))
                .forEach(System.out::println);

        return true;
    }

    private void pesquisaAno(String tipoVeiculo, String codeModel, String code){
        String json = "";

        if(tipoVeiculo.equals("carro")) {
            json = connectionApi.getJson(ADDRESS_BEGIN + "carros" + ADDRESS_MARCA + "/" + codeModel + ADDRESS_MODEL + "/" + code + ADDRESS_YEAR);
        } else if (tipoVeiculo.equals("moto")) {
            json = connectionApi.getJson(ADDRESS_BEGIN + "motos" + ADDRESS_MARCA + "/" + codeModel + ADDRESS_MODEL + "/" + code + ADDRESS_YEAR);
        } else {
            json = connectionApi.getJson(ADDRESS_BEGIN + "caminhoes" + ADDRESS_MARCA + "/" + codeModel + ADDRESS_MODEL + "/" + code + ADDRESS_YEAR);
        }

        List<CData> yearsModels = dataConverter.getList(json, CData.class);
        if (!yearsModels.isEmpty())
            yearsModels.forEach(System.out::println);
    }

    private void pesquisaVeiculo(String tipoVeiculo, String codeModelo, String code){
        String json = "";

        System.out.print("\nInforme o código que deseja pesquisar: ");
        var codeYear = scanner.nextLine();

        if (tipoVeiculo.equals("carro")) {
            json = connectionApi.getJson(ADDRESS_BEGIN + "carros" + ADDRESS_MARCA + "/" + codeModelo + ADDRESS_MODEL + "/" + code + ADDRESS_YEAR + "/" + codeYear);
        } else if (tipoVeiculo.equals("moto")) {
            json = connectionApi.getJson(ADDRESS_BEGIN + "motos" + ADDRESS_MARCA + "/" + codeModelo + ADDRESS_MODEL + "/" + code + ADDRESS_YEAR + "/" + codeYear);
        } else {
            json = connectionApi.getJson(ADDRESS_BEGIN + "caminhoes" + ADDRESS_MARCA + "/" + codeModelo + ADDRESS_MODEL + "/" + code + ADDRESS_YEAR + "/" + codeYear);
        }

        var veiculo = dataConverter.getData(json, Veiculo.class);

        if (veiculo != null)
            System.out.println(veiculo);
    }
}
