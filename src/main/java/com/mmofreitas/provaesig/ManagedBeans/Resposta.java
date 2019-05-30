/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmofreitas.provaesig.ManagedBeans;

import com.mmofreitas.provaesig.BancoDados.DAO.TarefaDAO;
import com.mmofreitas.provaesig.BancoDados.Entities.Tarefa;
import com.mmofreitas.provaesig.BancoDados.Entities.Usuario;
import com.mmofreitas.provaesig.Constantes.Constantes;
import com.mmofreitas.provaesig.Session.SessionManager;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author matheus
 */
@ManagedBean(name = "resposta")
@SessionScoped
public class Resposta implements Serializable{
    private TarefaDAO tarefaDAO;
    private int opcao;
    private String descricao_tarefa;
    private int status;
    private List<Tarefa> tarefas;
    private Usuario usuario;
    
    /**
     * Creates a new instance of Resposta
     */
    public Resposta() {
        opcao = 2;
        tarefaDAO = TarefaDAO.getInstance();
        
        //Obtem usuario da sessionmanager
        usuario = SessionManager.getInstance().getUsuarioLogado();
        tarefas = tarefaDAO.getAll(usuario, opcao);
        descricao_tarefa = "";
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }
    
    public void onRowEdit(RowEditEvent event) {
        Tarefa tarefa = (Tarefa) event.getObject();
        tarefa.setUsuario(usuario);
        System.out.println("Editando: " + String.valueOf(tarefa.getId()) + " - " + tarefa.getDescricao());
        tarefaDAO.atualizarTarefa(tarefa);
    }
    
    public void removeTarefa(Tarefa tarefa)
    {
        System.out.println("Removendo: " + String.valueOf(tarefa.getId()) + " - " + tarefa.getDescricao());
        tarefaDAO.removerTarefa(tarefa);
        tarefas = tarefaDAO.getAll(usuario, opcao);
    }
    
    public void inseretarefa()
    {
        //Apenas insere tarefa se a descrição não tiver vazia
        if(!descricao_tarefa.isEmpty())
        {
            Tarefa t = new Tarefa(descricao_tarefa);            
            t.setUsuario(usuario);
            tarefaDAO.inserirTarefa(t);
            tarefas = tarefaDAO.getAll(usuario, opcao);
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

    //Atualiza status da tarefa
    public void inverteStatus(Tarefa tarefa) {
        System.out.println("Editando: " + String.valueOf(tarefa.getId()) + " - " + tarefa.getDescricao());
        if(tarefa.getStatus() == Constantes.ATIVO)
        {
            tarefa.setStatus(Constantes.FEITO);
        }
        else 
        {
            tarefa.setStatus(Constantes.ATIVO);
        }
        tarefa.setUsuario(usuario);
        tarefaDAO.atualizarTarefa(tarefa);
    }
    
    public void setOpcao(int opcao)
    {
        System.out.println(opcao);
        this.opcao = opcao;
        tarefas = tarefaDAO.getAll(usuario, opcao); 
    }
    
    public String getUsuario()
    {
        //Retorma mensagem de bem vindo do usuário
        try
        {
            String apresentacao = usuario.getNome() + " " + usuario.getUltimo_nome();
            return "Olá, " + apresentacao;
        }
        catch(NullPointerException npe)
        {
            return "";
        }
    }
    
    public String logout()
    {
        //Encerra sessão do usuário
        SessionManager.getInstance().encerrarSessao();
        return "/paraTodos/login.xhtml?faces-redirect=true";
    }
    
}
