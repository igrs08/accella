package com.accela.test.accelatest.dtoservice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public abstract class BaseDTOService<E extends BaseEntity, D extends BaseDTOObject> {

	@Autowired
	protected ModelMapper modelMapper;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	protected abstract D convertEntityToDto(final E entity) throws DTOException;

	protected abstract E convertDtoToEntity(final D dto) throws DTOException;

	public List<D> convertEntityToDto(final List<? extends E> list) throws DTOException {

		List<D> dto = list.stream().peek(e -> logger.trace(e.toString())).map(e -> {
			try {
				return this.convertEntityToDto(e);
			} catch (Exception e1) {
				logger.error("Faile entity to DTO conversion, msg= {}", e1.getMessage());
				throw e1;
			}
		}).collect(Collectors.toList());

		return dto;
	}

	// Helper functions
	public LocalDate convertSringToDate(final String stringDate) {
		if (StringUtils.isEmpty(stringDate)) {
			throw new IllegalArgumentException("String value of Date can not be empty");
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.parse(stringDate, formatter);

		return localDate;
	}

	public String convertDateToString(final LocalDate date) {
		if (Objects.isNull(date)) {
			throw new IllegalArgumentException("Date value can not be empty or null");
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return date.format(formatter);
	}

}