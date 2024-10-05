package com.window_system_ai.demo.utilities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public final class DateUtils {

    public static final String DATETIME_RESPONSE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSZ";

    public static String getCurrentDateInResponseFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_RESPONSE_FORMAT);
        return sdf.format(new Date());
    }

    public static long getRemainingDaysFromTwoDates(final LocalDate startDate, final LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public static LocalDate convertToLocalDateFormat(final String maturityDate) {
        DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("[MM/dd/yyyy][MM/dd/yy]");
        return LocalDate.parse(maturityDate, localDateFormatter);
    }


}
