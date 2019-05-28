/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmofreitas.provaesig.BancoDados.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author matheus
 */

@Entity
@Table(name = "tarefas")
public class Tarefa implements Serializable {
    
    public final static int ATIVO = 0;
    public final static int FEITO = 1;
    public final static String ICONE_ATIVO = "far fa-square";
    public final static String ICONE_FEITO = "far fa-check-square";
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;
    @Column
    private String descricao;
    @Column
    private int status;
    @Column
    @CreationTimestamp
    private LocalDateTime timestamp;

    //tem que ter para funcionar o createQuery
    public Tarefa(){}
    
    public Tarefa(String descricao)
    {
        this.descricao = descricao;
        status = 0;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getIcone()
    {
        if(this.status == ATIVO)
        {
            return ICONE_ATIVO;
        }
        else
        {
            return ICONE_FEITO;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tarefa t = (Tarefa) o;
        return t.id == this.id;
    }
    
    @Override
    public int hashCode()
    {
        return this.id;
    }
}
