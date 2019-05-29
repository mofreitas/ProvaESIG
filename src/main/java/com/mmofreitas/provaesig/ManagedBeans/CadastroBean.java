/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmofreitas.provaesig.ManagedBeans;

import com.mmofreitas.provaesig.BancoDados.DAO.UsuarioDAO;
import com.mmofreitas.provaesig.BancoDados.Model.Usuario;
import com.mmofreitas.provaesig.Session.SessionManager;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author matheus
 */
@ManagedBean(name = "cadastroBean")
@RequestScoped
public class CadastroBean {
    private String email;
    private String nome;
    private String ultimo_nome;
    private String senha;
    private String repet_senha;
    
    /**
     * Creates a new instance of CadastroBean
     */
    public CadastroBean() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUltimo_nome() {
        return ultimo_nome;
    }

    public void setUltimo_nome(String ultimo_nome) {
        this.ultimo_nome = ultimo_nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRepet_senha() {
        return repet_senha;
    }

    public void setRepet_senha(String repet_senha) {
        this.repet_senha = repet_senha;
    }
    
    public String cadastraUsuario()
    {
        String senhaMD5 = Usuario.senhaToMD5(senha);
        UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
        Usuario usuario = new Usuario(email, nome, ultimo_nome, senhaMD5);
        usuarioDAO.insereUsuario(usuario);
        SessionManager.getInstance().setAttribute("usuarioLogado", usuario);
        return "/paraLogados/index.xhtml?faces-redirect=true";
    }
    
    //Formas de validação
    //http://incepttechnologies.blogspot.com/p/validation-in-jsf.html
    public void validateEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String msg = "Email inválido";
        //Email regex from https://emailregex.com/
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
       
        
        if(!value.toString().matches(regex))
        {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
    }
    
    public void validateSenha1(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String msg = "Senha deve ter pelo menos 6 caracteres";
        System.out.println(nome);
        
        if(value.toString().length() < 6)
        {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
    }
    
    //Para validar em conjuntro com outros input
    //https://stackoverflow.com/questions/7608145/how-to-get-the-value-of-another-component-in-a-custom-validator
    public void validateSenha2(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String msg = "Ambas as senhas inseridas devem ser iguais";
        UIInput startComponent = (UIInput) component.getAttributes().get("senha");
        String senha = startComponent.getValue().toString();
        String senha_confirma = value.toString();
        
        if(!senha_confirma.equals(senha))
        {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
    }
    
}
