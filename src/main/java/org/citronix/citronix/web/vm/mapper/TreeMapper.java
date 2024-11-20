package org.citronix.citronix.web.vm.mapper;

import org.citronix.citronix.domain.Tree;
import org.citronix.citronix.web.vm.TreeVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TreeMapper {
    TreeMapper INSTANCE = Mappers.getMapper(TreeMapper.class);

    @Mapping(source = "fieldId", target = "field.id")
     Tree toTree(TreeVM treeVM);

    @Mapping(source = "field.id", target = "fieldId")
     TreeVM toTreeVM(Tree tree);
}
