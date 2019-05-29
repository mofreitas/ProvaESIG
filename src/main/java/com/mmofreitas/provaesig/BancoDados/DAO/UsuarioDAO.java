/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmofreitas.provaesig.BancoDados.DAO;

import com.mmofreitas.provaesig.BancoDados.Model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author matheus
 */
public class UsuarioDAO {
    private static UsuarioDAO instance;
    protected EntityManager entityManager;
          
    public static UsuarioDAO getInstance(){
        if (instance == null){
            instance = new UsuarioDAO();
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
    
    private UsuarioDAO() {
        entityManager = getEntityManager();
    }
    
    public void insereUsuario(Usuario usuario)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(usuario);
        entityManager.getTransaction().commit();
    }
    
    public Usuario validaUsuarioSenha(String email, String senha)
    {
        try {
            email = email.toLowerCase().trim();
            List<Usuario> retorno = entityManager.createQuery(
                    "FROM " + Usuario.class.getName()
                    + " WHERE email = :email AND senha = :senha")
                    .setParameter("email", email)
                    .setParameter("senha", senha)
                    .getResultList();

            if (retorno.size() == 1) {
                Usuario usuario = (Usuario) retorno.get(0);
                return usuario;
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
