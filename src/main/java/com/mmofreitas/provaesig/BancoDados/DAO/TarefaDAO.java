/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmofreitas.provaesig.BancoDados.DAO;

import com.mmofreitas.provaesig.BancoDados.Model.Tarefa;
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
    public final static int TODOS = 2;    
    public final static int FEITOS = 1;    
    public final static int ATIVOS = 0;
          
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
    
    public void inserirTarefa(Tarefa tarefa){
        entityManager.getTransaction().begin( );
        entityManager.persist(tarefa);
        entityManager.getTransaction().commit();
    }
    
    public void atualizarTarefa(Tarefa tarefa)
    {
        entityManager.getTransaction().begin();
        entityManager.merge(tarefa);
        entityManager.getTransaction().commit();
    }
    
    public void removerTarefa(Tarefa tarefa)
    {
        entityManager.getTransaction().begin();
        //cliente = entityManager.find(Cliente.class, cliente.getId());
        entityManager.remove(tarefa);
        entityManager.getTransaction().commit();
    }
    
    public List<Tarefa> getAll(int opcao)
    {
        switch(opcao)
        {
            case ATIVOS:
                return entityManager.createQuery("from " + Tarefa.class.getName() + 
                        " where status = " + String.valueOf(opcao) +
                        " order by timestamp desc").getResultList();
            case FEITOS:
                return entityManager.createQuery("from " + Tarefa.class.getName() + 
                        " where status = " + String.valueOf(opcao) +
                        " order by timestamp desc").getResultList();
            case TODOS:
            default:
                return entityManager.createQuery("from " + Tarefa.class.getName() +
                    " order by timestamp desc").getResultList();
        }
        
        //Modificar para <h:outputText value="#{todo[numero da coluna]}
        //eturn entityManager.createNativeQuery("select * from tarefas").getResultList();
    }
    
    public List<Tarefa> getFinalizadas()
    {
        return null;
    }
    
}
