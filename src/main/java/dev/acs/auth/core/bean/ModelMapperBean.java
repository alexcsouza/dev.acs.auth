package dev.acs.auth.core.bean;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperBean {
	
	private ModelMapper modelMapper;
	
	@PostConstruct
	public void init() {
		modelMapper = new ModelMapper();
	}
	
	
	public ModelMapper getModelMapper() {
		return modelMapper;
	}
	
	
	
}
