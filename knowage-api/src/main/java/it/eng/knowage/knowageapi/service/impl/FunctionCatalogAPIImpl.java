/*
 * Knowage, Open Source Business Intelligence suite
 * Copyright (C) 2021 Engineering Ingegneria Informatica S.p.A.

 * Knowage is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * Knowage is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.

 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.eng.knowage.knowageapi.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.UnaryOperator;

import org.springframework.beans.factory.annotation.Autowired;

import it.eng.knowage.boot.error.InvalidHtmlPayloadException;
import it.eng.knowage.boot.error.KnowageBusinessException;
import it.eng.knowage.boot.error.KnowageRuntimeException;
import it.eng.knowage.boot.filter.XSSUtils;
import it.eng.knowage.knowageapi.dao.dto.SbiCatalogFunction;
import it.eng.knowage.knowageapi.mapper.FunctionCompleteDTO2SbiCatalogFunction;
import it.eng.knowage.knowageapi.mapper.SbiFunctionCatalog2FunctionCompleteDTO;
import it.eng.knowage.knowageapi.mapper.SbiFunctionCatalog2FunctionDTO;
import it.eng.knowage.knowageapi.repository.SbiCatalogFunctionRepository;
import it.eng.knowage.knowageapi.resource.dto.FunctionCompleteDTO;
import it.eng.knowage.knowageapi.resource.dto.FunctionDTO;
import it.eng.knowage.knowageapi.service.FunctionCatalogAPI;

/**
 * @author Marco Libanori
 */
public class FunctionCatalogAPIImpl implements FunctionCatalogAPI {

	private static final SbiFunctionCatalog2FunctionDTO TO_FUNCTION_DTO = new SbiFunctionCatalog2FunctionDTO();
	private static final SbiFunctionCatalog2FunctionCompleteDTO TO_FUNCTION_COMPLETE_DTO = new SbiFunctionCatalog2FunctionCompleteDTO();
	private static final FunctionCompleteDTO2SbiCatalogFunction TO_SBI_CATALOG_FUNCTION = new FunctionCompleteDTO2SbiCatalogFunction();

	private XSSUtils xssUtils = new XSSUtils();
	private final UnaryOperator<FunctionCompleteDTO> sanitizeFunctionCompleteDTO = e -> {
		String description = e.getDescription();
		String benchmark = e.getBenchmark();

		description = xssUtils.sanitize(description);
		benchmark = xssUtils.sanitize(benchmark);

		e.setDescription(description);
		e.setBenchmark(benchmark);

		return e;
	};

	@Autowired
	private SbiCatalogFunctionRepository repository;

	@Override
	public List<FunctionDTO> find(String searchStr) {
		return repository.findAll(searchStr)
				.stream()
				.map(TO_FUNCTION_DTO)
				.collect(toList());
	}

	@Override
	public List<FunctionCompleteDTO> findComplete(String searchStr) {
		return repository.findAll(searchStr)
				.stream()
				.map(TO_FUNCTION_COMPLETE_DTO)
				.map(sanitizeFunctionCompleteDTO)
				.collect(toList());
	}

	@Override
	public FunctionCompleteDTO get(UUID id) {
		return Optional.ofNullable(repository.find(id.toString())).map(TO_FUNCTION_COMPLETE_DTO)
				.orElseThrow(() -> new KnowageRuntimeException("Function with id " + id + " not found"));
	}

	@Override
	public FunctionCompleteDTO create(FunctionCompleteDTO function) {

		String description = function.getDescription();
		String benchmark = function.getBenchmark();

		xssUtils.isSafe(description);
		xssUtils.isSafe(benchmark);

		description = xssUtils.sanitize(description);
		benchmark = xssUtils.sanitize(benchmark);

		function.setDescription(description);
		function.setBenchmark(benchmark);

		SbiCatalogFunction beFunction = Optional.ofNullable(function)
				.map(TO_SBI_CATALOG_FUNCTION)
				.orElseThrow(() -> new KnowageRuntimeException("Function cannot be null"));

		return Optional.ofNullable(repository.create(beFunction))
				.map(TO_FUNCTION_COMPLETE_DTO)
				.get();
	}

	@Override
	public FunctionCompleteDTO update(FunctionCompleteDTO function) {

		String description = function.getDescription();
		String benchmark = function.getBenchmark();

		description = sanitizeXSS(description);
		benchmark = sanitizeXSS(benchmark);

		function.setDescription(description);
		function.setBenchmark(benchmark);

		SbiCatalogFunction beFunction = Optional.ofNullable(function)
				.map(TO_SBI_CATALOG_FUNCTION)
				.orElseThrow(() -> new KnowageRuntimeException("Function cannot be null"));

		return Optional.ofNullable(repository.update(beFunction))
				.map(TO_FUNCTION_COMPLETE_DTO)
				.get();
	}

	@Override
	public void delete(UUID id) throws KnowageBusinessException {
		repository.delete(id.toString());
	}

	private String sanitizeXSS(String input) {
		boolean isSafe = xssUtils.isSafe(input);

		if (!isSafe) {
			throw new InvalidHtmlPayloadException(input);
		}

		return xssUtils.sanitize(input);
	}

}
