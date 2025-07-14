/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.Objects;

public class Gasto {
    private int id;
    private String descricao;
    private double valor;
    private LocalDate data;

    public Gasto(int id, String descricao, double valor, LocalDate data) {
        if (id <= 0) throw new IllegalArgumentException("ID deve ser positivo.");
        if (descricao == null || descricao.trim().isEmpty()) throw new IllegalArgumentException("Descrição obrigatória.");
        if (valor < 0) throw new IllegalArgumentException("Valor deve ser positivo.");
        if (data == null) throw new IllegalArgumentException("Data obrigatória.");
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID deve ser positivo.");
        this.id = id;
    }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) throw new IllegalArgumentException("Descrição obrigatória.");
        this.descricao = descricao;
    }

    public double getValor() { return valor; }
    public void setValor(double valor) {
        if (valor < 0) throw new IllegalArgumentException("Valor deve ser positivo.");
        this.valor = valor;
    }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) {
        if (data == null) throw new IllegalArgumentException("Data obrigatória.");
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gasto)) return false;
        Gasto gasto = (Gasto) o;
        return id == gasto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + " - " + descricao + " | R$" + valor + " | " + data;
    }
}