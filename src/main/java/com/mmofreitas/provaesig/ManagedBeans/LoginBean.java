/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmofreitas.provaesig.ManagedBeans;

import com.mmofreitas.provaesig.BancoDados.DAO.UsuarioDAO;
import com.mmofreitas.provaesig.BancoDados.Model.Usuario;
import com.mmofreitas.provaesig.Session.SessionManager;
import javax.ejb.SessionContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
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
            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
            String senha = Usuario.senhaToMD5(this.senha);
            System.out.println(senha);
            Usuario usuario = usuarioDAO.validaUsuarioSenha(email, senha);

            if (usuario == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email ou senha incorretos"));
                return "";
            }
            
            SessionManager.getInstance().setAttribute("usuarioLogado", usuario);
            return "/paraLogados/index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().validationFailed();
            e.printStackTrace();
            return "";
        }
    }
}
