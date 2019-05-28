/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmofreitas.provaesig.ManagedBeans;

import com.mmofreitas.provaesig.BancoDados.DAO.TarefaDAO;
import com.mmofreitas.provaesig.BancoDados.Model.Tarefa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author matheus
 */
@ManagedBean(name = "resposta")
@ViewScoped
public class Resposta implements Serializable{
    private TarefaDAO tarefaDAO;
    private int opcao;
    private String descricao_tarefa;
    private int status;
    private List<Tarefa> tarefas;
    
    /**
     * Creates a new instance of Resposta
     */
    public Resposta() {
        opcao = TarefaDAO.TODOS;
        tarefaDAO = TarefaDAO.getInstance();
        tarefas = tarefaDAO.getAll(opcao);
        descricao_tarefa = "";
    }

    public List<Tarefa> getTarefas() {
        System.out.println("fala");
        return tarefas;
    }
    
    public void onRowEdit(RowEditEvent event) {
        Tarefa tarefa = (Tarefa) event.getObject();
        System.out.println("Editando: " + String.valueOf(tarefa.getId()) + " - " + tarefa.getDescricao());
        tarefaDAO.atualizarTarefa(tarefa);
    }
    
    public void removeTarefa(Tarefa tarefa)
    {
        tarefaDAO.removerTarefa(tarefa);
        tarefas = tarefaDAO.getAll(opcao);
    }
    
    public void inseretarefa()
    {
        if(!descricao_tarefa.isEmpty())
        {
            Tarefa t = new Tarefa(descricao_tarefa);
            tarefaDAO.inserirTarefa(t);
            tarefas = tarefaDAO.getAll(opcao);
            descricao_tarefa = "";
        }
    }

    public String getDescricao_Tarefa() {
        return descricao_tarefa;
    }

    public void setDescricao_Tarefa(String descricao_tarefa) {
        this.descricao_tarefa = descricao_tarefa;
    }

    public int getStatus() {
        return status;
    }

    public void inverteStatus(Tarefa tarefa) {
        System.out.println("Editando: " + String.valueOf(tarefa.getId()) + " - " + tarefa.getDescricao());
        if(tarefa.getStatus() == Tarefa.ATIVO)
        {
            tarefa.setStatus(Tarefa.FEITO);
        }
        else 
        {
            tarefa.setStatus(Tarefa.ATIVO);
        }
        tarefaDAO.atualizarTarefa(tarefa);
        
        tarefas = tarefaDAO.getAll(opcao);   
    }
    
    public void setOpcao(int opcao)
    {
        System.out.println(opcao);
        this.opcao = opcao;
        tarefas = tarefaDAO.getAll(opcao); 
    }
    
}
