package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.UserDto;
import com.pragma.plazoleta.domain.model.User;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-21T18:36:04-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class UserDtoMapperImpl implements UserDtoMapper {

    @Override
    public User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        String name = null;
        String lastName = null;
        String documentId = null;
        String cellPhone = null;
        LocalDate dateBirth = null;
        String email = null;
        String password = null;
        String role = null;

        name = userDto.getName();
        lastName = userDto.getLastName();
        documentId = userDto.getDocumentId();
        cellPhone = userDto.getCellPhone();
        dateBirth = userDto.getDateBirth();
        email = userDto.getEmail();
        password = userDto.getPassword();
        role = userDto.getRole();

        User user = new User( name, lastName, documentId, cellPhone, dateBirth, email, password, role );

        return user;
    }

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setName( user.getName() );
        userDto.setLastName( user.getLastName() );
        userDto.setDocumentId( user.getDocumentId() );
        userDto.setCellPhone( user.getCellPhone() );
        userDto.setDateBirth( user.getDateBirth() );
        userDto.setEmail( user.getEmail() );
        userDto.setPassword( user.getPassword() );
        userDto.setRole( user.getRole() );

        return userDto;
    }
}
