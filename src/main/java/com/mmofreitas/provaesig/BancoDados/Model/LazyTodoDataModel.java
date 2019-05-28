/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmofreitas.provaesig.BancoDados.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author matheus
 */
public class LazyTodoDataModel extends LazyDataModel {
    
    List<Tarefa> tarefas;
    
    public LazyTodoDataModel(List<Tarefa> tarefas)
    {
        this.tarefas = tarefas;
    }
    
    
    
}
