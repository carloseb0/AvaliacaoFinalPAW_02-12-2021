/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pecascrud.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jeang
 */
public class Util {

    /**
     * Formatar data para dd/MM/yy HH:mm. Ex. 30/01/2015 16:59
     */
    public final String FORMATO_RETORNO_DATAHORA = "dd/MM/yy HH:mm";
    /**
     * Formatar data para dd/MM/yy HH:mm. Ex. 30/01/2015
     */
    public final String FORMATO_RETORNO_DATA = "dd/MM/yy";

    /**
     * Método utilizado para buscar data/hora atual do sistema.
     *
     * @return
     */
    public Timestamp buscarDataHoraAtual() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * Método utilizado para formatar uma data
     *
     * @param data
     * @param formatoRetorno
     * @return dataFormatada
     */
    public String formatarData(Date data, String formatoRetorno) {
        SimpleDateFormat formatas = new SimpleDateFormat(formatoRetorno);
        String dataFormatada = formatas.format(data);
        return dataFormatada;
    }

}
