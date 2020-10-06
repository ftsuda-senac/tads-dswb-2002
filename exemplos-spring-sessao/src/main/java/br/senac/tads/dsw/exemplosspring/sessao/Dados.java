/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring.sessao;

import java.time.LocalDate;

public class Dados {

    private String nome;

    private int numero;

    private LocalDate data;

    private boolean destaque;

    public Dados() {

    }

    public Dados(String nome, int numero, LocalDate data, boolean destaque) {
        this.nome = nome;
        this.numero = numero;
        this.data = data;
        this.destaque = destaque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isDestaque() {
        return destaque;
    }

    public void setDestaque(boolean destaque) {
        this.destaque = destaque;
    }
    
    public boolean isPar() {
        int resto = this.numero % 2;
        return (resto == 0);
    }

}
