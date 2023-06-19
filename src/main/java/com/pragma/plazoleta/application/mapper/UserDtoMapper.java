package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.UserDto;
import com.pragma.plazoleta.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {
    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
}
