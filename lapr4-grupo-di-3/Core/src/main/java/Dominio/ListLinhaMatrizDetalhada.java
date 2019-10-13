/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Diogo Rolo
 */
public class ListLinhaMatrizDetalhada implements Serializable {

    private List<LinhaMatrizDetalhada> list;

    public ListLinhaMatrizDetalhada(List<LinhaMatrizDetalhada> list) {
        this.list = list;
    }

    public ListLinhaMatrizDetalhada() {
        this.list = new ArrayList<>();
    }

    public List<LinhaMatrizDetalhada> getList() {
        return list;
    }

    public void setList(List<LinhaMatrizDetalhada> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ListLinhaMatrizDetalhada other = (ListLinhaMatrizDetalhada) obj;
        if (!Objects.equals(this.list, other.list)) {
            return false;
        }
        return true;
    }
    
}
