package org.citronix.citronix.web.vm.mapper;

import org.citronix.citronix.domain.Field;
import org.citronix.citronix.web.vm.FieldResponseVM;
import org.citronix.citronix.web.vm.FieldVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {FarmMapper.class})
public interface FieldMapper {
    FieldMapper INSTANCE = Mappers.getMapper(FieldMapper.class);

    @Mapping(source = "farmId", target = "farm.id")
    Field toField(FieldVM fieldVM);

    FieldVM toFieldVM(Field field);

    FieldResponseVM toFieldResponseVM(Field field);
}
