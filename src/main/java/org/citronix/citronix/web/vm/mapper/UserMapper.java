package org.citronix.citronix.web.vm.mapper;

import org.citronix.citronix.domain.AppUser;
import org.citronix.citronix.web.vm.LoginVM;
import org.citronix.citronix.web.vm.RegisterVM;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    RegisterVM toUserVM(AppUser appUser);

    AppUser toUser(RegisterVM registerVM);

    AppUser toUser(LoginVM loginVM);

    LoginVM toLoginVM(AppUser appUser);
}
