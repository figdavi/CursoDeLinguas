/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Lingua {
    PORTUGUES("Português"),
    INGLES("Inglês"),
    FRANCES("Francês");

    private final String nome;
    Lingua(String nome) { this.nome = nome; }
    
    public static List<Lingua> fromString(String s) {
        if (s == null || s.isBlank()) return new ArrayList<>();
        return Arrays.stream(s.split(","))
                .map(String::trim)
                .map(str -> Lingua.valueOf(str.toUpperCase()))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() { return nome; }
    
    public static String toString(List<Lingua> linguas) {
        return linguas.stream().map(Enum::name).collect(Collectors.joining(","));
    }
    
    public static String allToString() {
        return Arrays.stream(Lingua.values())
            .map(Lingua::toString)
            .collect(Collectors.joining(", "));
    }
}
