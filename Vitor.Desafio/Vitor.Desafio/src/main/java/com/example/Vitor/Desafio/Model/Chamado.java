package com.sos.suporte.model;

import com.example.Vitor.Desafio.Model.Entity;

import javax.persistence.Id;

@Entity
public class Chamado {

    @Id
    private Long id;
    private String titulo;
    private String descricao;
    private String prioridade;

    // Construtores, getters e setters
    public Chamado() {}

    public Chamado(Long id, String titulo, String descricao, String prioridade) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
}
