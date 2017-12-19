package com.haulmont.testtask.service.util;

import java.text.SimpleDateFormat;
import java.util.Date; /**
 * Created by FilatovNA(rogowasya@gmail.com) on 18.12.17.
 */

public class DateUtil {

    private static SimpleDateFormat FORMAT_DDMMYYYY = new SimpleDateFormat("dd.MM.yyyy");

    public static String convertDDMMYY(Date created) {
        return FORMAT_DDMMYYYY.format(created);
    }
}
