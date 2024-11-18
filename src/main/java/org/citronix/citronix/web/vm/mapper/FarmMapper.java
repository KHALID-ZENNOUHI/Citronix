package org.citronix.citronix.web.vm.mapper;

import org.citronix.citronix.domain.Farm;
import org.citronix.citronix.web.vm.FarmVM;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FarmMapper {

    FarmMapper INSTANCE = Mappers.getMapper(FarmMapper.class);

    Farm toFarm(FarmVM farmVM);

    FarmVM toFarmVM(Farm farm);

}
