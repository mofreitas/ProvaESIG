/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmofreitas.provaesig.ManagedBeans;

import com.mmofreitas.provaesig.BancoDados.DAO.UsuarioDAO;
import com.mmofreitas.provaesig.BancoDados.Entities.Usuario;
import com.mmofreitas.provaesig.Session.SessionManager;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author matheus
 */
@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {
    private String email;
    private String senha;
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    } 
    
    public String validaEmailSenha()
    {
        try {
            //Obtem senha em MD5 e verifica se entrada existe no banco
            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
            String senha = Usuario.senhaToMD5(this.senha); 
            Usuario usuario = usuarioDAO.validaUsuarioSenha(email.trim(), senha);

            //Se usuário não existe, é mandada uma mensagem para o h:messages.
            if (usuario == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email ou senha incorretos"));
                return "";
            }
            
            //Se existe, sessão é criada com informações do usuário e ele é redirecionado para página do app
            SessionManager.getInstance().setAttribute("usuarioLogado", usuario);
            return "/paraLogados/index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro ao efetuar login"));
            e.printStackTrace();
            return "";
        }
    }
}
