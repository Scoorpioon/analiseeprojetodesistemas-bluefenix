package com.bluefenix.api.Services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluefenix.api.Models.Consulta;
import com.bluefenix.api.Models.Fila;
import com.bluefenix.api.Repositories.ConsultaRepository;
import com.bluefenix.api.Repositories.FilaRepository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import jakarta.transaction.Transactional;

@Service
public class FilaServices {
    
    @Autowired
    private FilaRepository repositorioFila;

    @Autowired
    private ConsultaRepository repositorioConsulta;

    @Transactional
    public Fila cadastrarFila(Fila dadosRecebidos) {
        dadosRecebidos.setIdFila(null);

        for(Consulta consulta : dadosRecebidos.getConsultas()) {
            if(consulta.getDataConsulta().toLocalDate() != dadosRecebidos.getDataFila()) {
                throw new RuntimeException("Uma das consultas possui uma data divergente da data da fila. Necessário verificar.");
            }
        }

        this.repositorioFila.save(dadosRecebidos);

        return dadosRecebidos;
    }

    @Transactional
    public List<Fila> findAll() {
        return this.repositorioFila.findAll();

    }

    @Transactional
    public Fila encontrarFilaPorData(LocalDate data) {
        Fila filaEncontrada = this.repositorioFila.findByDataFila(data);

        return filaEncontrada;
    }

    @Transactional
    public Fila removerPacienteDaFila(Long idConsulta, Long idFila) {
        System.out.println("Função executada");

        Optional<Fila> filaBuscada = repositorioFila.findById(idFila);
        Optional<Consulta> consultaBuscada = repositorioConsulta.findById(idConsulta);

        System.out.println(" ");

        System.out.println("ID buscado da fila: " + idFila);
        System.out.println("ID buscado da consulta: " + idConsulta);

        System.out.println(" ");
        
        if(filaBuscada.isPresent() && consultaBuscada.isPresent()) {
            
            Fila filaEncontrada = filaBuscada.get();
            Hibernate.initialize(filaEncontrada.getConsultas());
            Consulta consultaEncontrada = consultaBuscada.get();


            filaEncontrada.getConsultas().remove(consultaEncontrada);
            repositorioFila.save(filaEncontrada);

            return filaEncontrada;
        } else {
            if(!filaBuscada.isPresent()) {
                System.out.println("A fila não foi encontrada");
            }

            if(!consultaBuscada.isPresent()) {
                System.out.println("A consulta não foi encontrada");
            }

            throw new RuntimeException("Putz mano, nao deu pra achar alguma das informacoes. Verifica de novo ae");
        }
    }
}