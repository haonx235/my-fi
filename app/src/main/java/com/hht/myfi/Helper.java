package com.hht.myfi;

import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by streetguard on 30-Dec-17.
 */

public class Helper
{
    public static List<String> formatDate(List<String> lst)
    {
        String INPUT_FORMAT = "yyyy-MM-dd";
        String OUTPUT_FORMAT = "dd/MM/yyyy";

        List<String> lst_out = new ArrayList<String>();

        SimpleDateFormat sdf = new SimpleDateFormat(INPUT_FORMAT);

        for (String i: lst)
        {
            lst_out.add(formatDate(i));
        }

        return lst_out;
    }

    public static String formatDate(String s)
    {
        String INPUT_FORMAT = "yyyy-MM-dd";
        String OUTPUT_FORMAT = "dd/MM/yyyy";

        SimpleDateFormat sdf = new SimpleDateFormat(INPUT_FORMAT);
        Date d = null;
        try {
            d = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern(OUTPUT_FORMAT);
        String out = sdf.format(d);
        return out;
    }
}
