package rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mapper.Mapper;
import rest.helpers.RequestDataParser;
import rest.helpers.RequestDataParserImpl;
import rest.helpers.ResponsePacker;
import rest.helpers.ResponsePackerImpl;
import service.DomainService;

public abstract class GenericRest<T extends DomainService<?, ?>> {
	protected static final Logger logger = LoggerFactory.getLogger(GenericRest.class);
	protected final T service;
	protected final Mapper mainMapper;
	protected final RequestDataParser requestParser;
	protected final ResponsePacker responsePacker;

	public GenericRest(T domainService, Mapper mapper) {
		service = domainService;
		mainMapper = mapper;
		requestParser = new RequestDataParserImpl();
		responsePacker = new ResponsePackerImpl();
	}
}
