package com.bluefenix.api.Models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.time.LocalDate;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idFila", scope = Fila.class)
@Table(name = "fila")
public class Fila {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFila;

    @Column(name = "data_fila", nullable = false)
    private LocalDate dataFila;

    @Column(name = "numero_consultas", nullable = true)
    private int numeroConsultas = 0;

    @Column(name = "tempo_medio_consulta", nullable = true)
    private LocalTime tempoMedioConsulta;

    @Column(name = "paciente_atual", nullable = true)
    private Long id_paciente_atual;

    // O CascadeType.ALL significa que, quando a fila ser atualizada, as consultas também serão.
    // o orphanRemoval = true significa que, quando uma consulta for removida da lista de consultas de uma fila, ela também será deletada do banco de dados
    @OneToMany(mappedBy = "fila", cascade = CascadeType.ALL, orphanRemoval = true) @Fetch(FetchMode.JOIN)
    private List<Consulta> consultas = new ArrayList<>();

    public Long getIdFila() {
        return idFila;
    }

    public void setIdFila(Long idFila) {
        this.idFila = idFila;
    }

    public LocalDate getDataFila() {
        return dataFila;
    }

    public void setDataFila(LocalDate dataFila) {
        this.dataFila = dataFila;
    }

    public int getNumeroConsultas() {
        return numeroConsultas;
    }

    public void setNumeroConsultas(int numeroConsultas) {
        this.numeroConsultas = numeroConsultas;
    }

    public LocalTime getTempoMedioConsulta() {
        return tempoMedioConsulta;
    }

    public void setTempoMedioConsulta(LocalTime tempoMedioConsulta) {
        this.tempoMedioConsulta = tempoMedioConsulta;
    }

/*     public Set<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(Set<Paciente> pacientes) {
        this.pacientes = pacientes;
    } */

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}