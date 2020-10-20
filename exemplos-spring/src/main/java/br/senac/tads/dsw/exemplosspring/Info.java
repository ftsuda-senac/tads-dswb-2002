/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring;

import java.time.LocalDate;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author fedts
 */
public class Info {
    
    @NotBlank(message = "{form.nome.error.notBlank}")
    private String nome;

    @NotNull(message = "{form.dataNascimento.error.notNull}")
    @Past(message = "{form.dataNascimento.error.past}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // ISO-8601
    private LocalDate dataNascimento;

    @Min(value = 1, message = "{form.numero.error.min}")
    @Max(value = 99, message = "{form.numero.error.max}")
    private int numero;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
}
