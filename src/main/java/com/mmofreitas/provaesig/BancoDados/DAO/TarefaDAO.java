/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmofreitas.provaesig.BancoDados.DAO;

import com.mmofreitas.provaesig.BancoDados.Entities.Tarefa;
import com.mmofreitas.provaesig.BancoDados.Entities.Usuario;
import com.mmofreitas.provaesig.Constantes.Constantes;
import com.mmofreitas.provaesig.ManagedBeans.Resposta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author matheus
 */
public class TarefaDAO {
    private static TarefaDAO instance;
    protected EntityManager entityManager;
          
    public static TarefaDAO getInstance(){
        if (instance == null){
            instance = new TarefaDAO();
        }
                    
        return instance;
    }
    
    private EntityManager getEntityManager() {
        //argumento é o nome da persistence-unit do arquivo persistence.xml
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Hibernate_Postgres");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }
    
    private TarefaDAO() {
        entityManager = getEntityManager();
    }
    
    public void inserirTarefa(Tarefa tarefa)
    {
        try
        {
            entityManager.getTransaction().begin();
            entityManager.persist(tarefa);
            entityManager.getTransaction().commit();
        }
        catch (Exception ex) {
            //obtem excessão quando inserido mesma chave
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }
    
    public void atualizarTarefa(Tarefa tarefa)
    {
        try
        {
            entityManager.getTransaction().begin();
            entityManager.merge(tarefa);
            entityManager.getTransaction().commit();
        }
        catch (Exception ex) {
            //obtem excessão quando inserido mesma chave
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }
    
    public void removerTarefa(Tarefa tarefa)
    {
        try
        {
            entityManager.getTransaction().begin();
            entityManager.remove(tarefa);
            entityManager.getTransaction().commit();
        }
        catch (Exception ex) {
            //obtem excessão quando inserido mesma chave
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }
    
    public List<Tarefa> getAll(Usuario usuario, int opcao)
    {
        if(opcao != Constantes.TODOS)
        {
            return entityManager.createQuery(
                    "SELECT t FROM Tarefa t " +
                    " where t.status = :status AND t.usuario = :usuario " +
                    " order by timestamp desc")
                    .setParameter("usuario", usuario)
                    .setParameter("status", opcao).getResultList();
        }
        else
        {
            return entityManager.createQuery(
                "SELECT t FROM Tarefa t " +
                " WHERE t.usuario = :usuario " +
                " order by timestamp desc")
                .setParameter("usuario", usuario).getResultList();
        }

        //Modificar para <h:outputText value="#{todo[numero da coluna]} quendo utilizamos
        //entityManager.createNativeQuery("select * from tarefas").getResultList();
    }    
}
