package com.pragma.plazoleta.application.handler.implementations;

import com.pragma.plazoleta.application.dto.UserDto;
import com.pragma.plazoleta.application.handler.interfaces.IUserHandler;
import com.pragma.plazoleta.application.mapper.UserDtoMapper;
import com.pragma.plazoleta.domain.api.IEmployeeServicePort;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.model.Employee;
import com.pragma.plazoleta.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandlerImp implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final UserDtoMapper userDtoMapper;
    private final IEmployeeServicePort employeeServicePort;
    private final IRestaurantServicePort restaurantServicePort;

    @Override
    public void saveOwner(UserDto userDto) {
        User user = userDtoMapper.userDtoToUser(userDto);
        user.setRole("PROPIETARIO");
        userServicePort.saveUser(user);
    }

    @Override
    public void saveEmployee(UserDto userDto, String email) {
        User user = userDtoMapper.userDtoToUser(userDto);
        user.setRole("EMPLEADO");
        int userId = userServicePort.saveUser(user).getId();
        int ownerId = userServicePort.getUserIdByEmail(email);
        int restaurantId = restaurantServicePort.getRestaurantByOwnerId(ownerId).getId();
        employeeServicePort.saveEmployee(new Employee(restaurantId, userId, true));
    }

    @Override
    public void saveCustomer(UserDto userDto) {
        User user = userDtoMapper.userDtoToUser(userDto);
        user.setRole("CLIENTE");
        userServicePort.saveUser(user);
    }
}
