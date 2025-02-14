package com.htbsc.fipe.main;

import com.htbsc.fipe.model.Car;
import com.htbsc.fipe.model.Motocicle;
import com.htbsc.fipe.model.Truck;
import com.htbsc.fipe.service.ConnectionApi;
import com.htbsc.fipe.service.DataConverter;

import java.util.Scanner;

public class Principal {
    Scanner scanner = new Scanner(System.in);
    ConnectionApi connectionApi = new ConnectionApi();
    DataConverter dataConverter = new DataConverter();

    private final String ADDRESS_BEGIN = "https://parallelum.com.br/fipe/api/v1/";
    private final String ADDRESS_END = "/marcas";

    private int menu(){
        System.out.println("Informe qual o número do tipo de veículo gostaria: ");
        System.out.println("""
                 1 - Carro
                 2 - Caminhão
                 3 - Moto
                """);
        int tipoVeiculo = scanner.nextInt();
        scanner.nextLine();

        return tipoVeiculo;
    }

    public void start(){
        var json = "";

        int opcao = this.menu();
        switch (opcao){
            case 1:
                json = connectionApi.getJson(ADDRESS_BEGIN + "carros" + ADDRESS_END);
                Car car = dataConverter.getData(json, Car.class);
                break;
            case 2:
                json = connectionApi.getJson(ADDRESS_BEGIN + "motos" + ADDRESS_END);
                Motocicle motocicle = dataConverter.getData(json, Motocicle.class);
                break;
            case 3:
                json = connectionApi.getJson(ADDRESS_BEGIN + "caminhoes" + ADDRESS_END);
                Truck truck = dataConverter.getData(json, Truck.class);
                break;
        }
    }
}
