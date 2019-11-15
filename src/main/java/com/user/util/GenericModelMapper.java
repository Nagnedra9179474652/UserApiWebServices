package com.user.util;

import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class GenericModelMapper {

	private static ModelMapper modelMapper;

	static {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public static <T> T modelConverter(Object obj, Class<T> des) {

		T result = null;

		try {
			result=modelMapper.map(obj, des);
		} catch (IllegalArgumentException | MappingException e) {
			e.printStackTrace();
		}
		return result;
	}

}
