package com.mmofreitas.provaesig.Session;

import com.mmofreitas.provaesig.BancoDados.Model.Usuario;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
    
public class SessionManager {
     
    private static SessionManager instance;
     
    public static SessionManager getInstance(){
         if (instance == null){
             instance = new SessionManager();
         }
          
         return instance;
    }
     
    private SessionManager(){
          
    }
     
    private ExternalContext currentExternalContext(){
         if (FacesContext.getCurrentInstance() == null){
             throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
         }else{
             return FacesContext.getCurrentInstance().getExternalContext();
         }
    }
     
    public Usuario getUsuarioLogado(){
         return (Usuario) getAttribute("usuarioLogado");
    }
     
    public void setUsuarioLogado(Usuario usuario){
         setAttribute("usuarioLogado", usuario);
    }
     
    public void encerrarSessao(){   
         currentExternalContext().invalidateSession();
    }
     
    public Object getAttribute(String nome){
         return currentExternalContext().getSessionMap().get(nome);
    }
     
    public void setAttribute(String nome, Object valor){
         currentExternalContext().getSessionMap().put(nome, valor);
    }
     
}