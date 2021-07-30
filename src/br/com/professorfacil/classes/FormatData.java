package br.com.professorfacil.classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FormatData {

    public String data() {
        
        Calendar c1 = Calendar.getInstance(new Locale("pt-br"));
        int dia = c1.get(Calendar.DAY_OF_MONTH);
        int mes = c1.get(Calendar.MONTH) + 1;
        int ano = c1.get(Calendar.YEAR);

        String dat = ano + "/" + mes + "/" + dia;

        Date uso = new Date(dat);
        uso.setDate(uso.getDate());
        SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");

        dat = formata.format(uso);

        return dat;
    }

}
