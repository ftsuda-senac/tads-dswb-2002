/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring;

import java.time.LocalDateTime;

public class Dados {

    private String nome;

    private int numero;

    private LocalDateTime dataHora;

    private boolean destaque;

    public Dados() {

    }

    public Dados(String nome, int numero, LocalDateTime dataHora, boolean destaque) {
        this.nome = nome;
        this.numero = numero;
        this.dataHora = dataHora;
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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
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
