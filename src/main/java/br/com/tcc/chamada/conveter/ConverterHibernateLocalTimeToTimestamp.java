package br.com.tcc.chamada.conveter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ConverterHibernateLocalTimeToTimestamp implements AttributeConverter<LocalTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalTime locDateTime) {
		if (locDateTime == null)
			return null;
		
		LocalDateTime atDate = locDateTime.atDate(LocalDate.of(1900, 01, 01));
		
		return (locDateTime == null ? null : Timestamp.valueOf(atDate));
	}

	@Override
	public LocalTime convertToEntityAttribute(Timestamp sqlTimestamp) {
		if (sqlTimestamp == null)
			return null;
		
		LocalDateTime localDateTime = sqlTimestamp.toLocalDateTime();
		LocalTime localTime = localDateTime.toLocalTime();
		
		return localTime;

	}
}
