package org.citronix.citronix.web.vm.mapper;

import org.citronix.citronix.domain.Harvest;
import org.citronix.citronix.domain.Sale;
import org.citronix.citronix.web.vm.SaleVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    SaleMapper INSTANCE = Mappers.getMapper(SaleMapper.class);

    @Mapping(target = "income", expression = "java(sale.getIncome())")
    @Mapping(target = "harvestId", source = "harvest.id")
    SaleVM toSaleVM(Sale sale);

    @Mapping(target = "harvest", source = "harvestId", qualifiedByName = "harvestIdToHarvest")
    Sale toSale(SaleVM saleVM);



    @Named("harvestIdToHarvest")
    default Harvest harvestIdToHarvest(Long harvestId) {
        return Harvest.builder().id(harvestId).build();
    }
}
