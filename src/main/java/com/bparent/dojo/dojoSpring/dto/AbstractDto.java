package com.bparent.dojo.dojoSpring.dto;

import org.modelmapper.ModelMapper;

public abstract class AbstractDto<E> {

    public AbstractDto toDto(E entity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, this.getClass());
    }

}
