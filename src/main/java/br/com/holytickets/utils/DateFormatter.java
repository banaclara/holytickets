package br.com.holytickets.utils;

import br.com.holytickets.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateFormatter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public String convertTimestampToString(Timestamp dateFromDb) {
        LocalDateTime dateFormatted = dateFromDb != null ? dateFromDb.toLocalDateTime() : null;

        assert dateFormatted != null;
        return dateFormatted.format(FORMATTER);
    }

    public LocalDateTime convertStringToLocalDateTime(String dateAsString) {
        if (dateAsString == null || dateAsString.isBlank()) {
            throw new BadRequestException("The date string cannot be null or empty");
        }

        return LocalDateTime.parse(dateAsString, FORMATTER);
    }

    public String convertLocalDateTimeToString(LocalDateTime dateTime) {
        if (dateTime == null) {
            throw new BadRequestException("The LocalDateTime object cannot be null");
        }
        return dateTime.format(FORMATTER);
    }
}
