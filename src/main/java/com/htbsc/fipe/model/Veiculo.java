package com.htbsc.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Veiculo {
    @JsonAlias("TipoVeiculo")
    private Integer type;
    @JsonAlias("Valor")
    private String price;
    @JsonAlias("Marca")
    private String marca;
    @JsonAlias("Modelo")
    private String model;
    @JsonAlias("AnoModelo")
    private Integer year;
    @JsonAlias("Combustivel")
    private String fuel;
    @JsonAlias("CodigoFipe")
    private String codeFipe;
    @JsonAlias("MesReferencia")
    private String monthReference;
    @JsonAlias("SiglaCombustivel")
    private String siglaCombustivel;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getCodeFipe() {
        return codeFipe;
    }

    public void setCodeFipe(String codeFipe) {
        this.codeFipe = codeFipe;
    }

    public String getMonthReference() {
        return monthReference;
    }

    public void setMonthReference(String monthReference) {
        this.monthReference = monthReference;
    }

    public String getSiglaCombustivel() {
        return siglaCombustivel;
    }

    public void setSiglaCombustivel(String siglaCombustivel) {
        this.siglaCombustivel = siglaCombustivel;
    }

    @Override
    public String toString() {
        return "Veículo -> Type: " + type +", Price: " + price + ", Marca: " + marca +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", fuel='" + fuel + '\'' +
                ", codeFipe='" + codeFipe + '\'' +
                ", monthReference='" + monthReference + '\'' +
                ", siglaCombustivel='" + siglaCombustivel + '\'' +
                '}';
    }
}
