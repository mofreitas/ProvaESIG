/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmofreitas.provaesig.BancoDados.Entities;

import com.mmofreitas.provaesig.Constantes.Constantes;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author matheus
 */
@Entity
@Table(name = "tarefas")
public class Tarefa implements Serializable {
    
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
    @ManyToOne(cascade = CascadeType.MERGE)
    private Usuario usuario;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String getIcone()
    {
        if(this.status == Constantes.ATIVO)
        {
            return Constantes.ICONE_ATIVO;
        }
        else
        {
            return Constantes.ICONE_FEITO;
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
