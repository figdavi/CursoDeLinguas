/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Aula {
    private int id;
    private Turma turma;
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Professor professor; // pode ser null até o início

    public Aula(int id, Turma turma, LocalDate data, LocalTime horaInicio, LocalTime horaFim) {
        this.id = id;
        this.turma = turma;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    // Métodos funcionais
    public void adiarData(LocalDate novaData) {
        if (professor == null) {
            this.data = novaData;
        }
    }

    public void definirProfessor(Professor professor) {
        this.professor = professor;
    }

    public long calcularDuracaoHoras() {
        return java.time.Duration.between(horaInicio, horaFim).toHours();
    }

    // Getters
    public int getId() { return id; }
    public Turma getTurma() { return turma; }
    public LocalDate getData() { return data; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public LocalTime getHoraFim() { return horaFim; }
    public Professor getProfessor() { return professor; }

    // Setters adicionais
    public void setId(int id) { this.id = id; }
    public void setTurma(Turma turma) { this.turma = turma; }
    public void setData(LocalDate data) { this.data = data; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public void setHoraFim(LocalTime horaFim) { this.horaFim = horaFim; }
    public void setProfessor(Professor professor) { this.professor = professor; }
}
