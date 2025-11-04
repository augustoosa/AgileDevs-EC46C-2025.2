package controller;

import model.Funcionario;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class GestorDeFerias {

    /**
     * Processa a solicitação de férias para um determinado funcionário.
     * @param funcionario O funcionário que está solicitando as férias.
     * @param dataInicio A data de início das férias.
     * @param dias A quantidade de dias de férias.
     * @return Uma String com a confirmação da solicitação.
     */
    public String solicitarFerias(Funcionario funcionario, Date dataInicio, int dias) {
        // Toda a lógica de negócio, validações e regras ficam aqui.
        // Ex: verificar se o funcionário tem saldo de férias, se a data é permitida, etc.

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataInicio);
        cal.add(Calendar.DAY_OF_MONTH, dias);
        Date dataFim = cal.getTime();

        return "Férias solicitadas para " + funcionario.getNome() +
               " a partir de " + sdf.format(dataInicio) +
               " por " + dias + " dias. Retorno previsto para " + sdf.format(dataFim) + ".";
    }
}