/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Diogo Rolo
 */
@Converter(autoApply = true)
public class ListLinhaMatrizDetalhadaConverter implements AttributeConverter<ListLinhaMatrizDetalhada, String> {

    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(ListLinhaMatrizDetalhada x) {
        String column = "";
        for (LinhaMatrizDetalhada lmd : x.getList()) {
            column += lmd.getId() + SPLIT_CHAR;
        }
        return column;
    }

    @Override
    public ListLinhaMatrizDetalhada convertToEntityAttribute(String y) {
        return new ListLinhaMatrizDetalhada(new ArrayList<LinhaMatrizDetalhada>());
    }

}
