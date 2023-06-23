package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.model.User;
import com.pragma.plazoleta.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserUseCaseTest {
    private UserUseCase userUseCase;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userUseCase = new UserUseCase(userPersistencePort);
    }

    @Test
    void testGetUserById() {
        // Mocking
        int userId = 1;
        User expectedUser = new User("John","Doe","11234","31203948", LocalDate.of(1955,01,01),"email@emal.com","password","NULL");
        expectedUser.setName("Test User");

        when(userPersistencePort.getUserById(userId)).thenReturn(expectedUser);

        // Test
        User actualUser = userUseCase.getUserById(userId);

        // Verification
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testGetUserByEmail() {
        // Mocking
        String email = "test@example.com";
        User expectedUser = new User("John","Doe","11234","31203948", LocalDate.of(1955,01,01),"email@emal.com","password","NULL");
        expectedUser.setEmail(email);

        when(userPersistencePort.getUserByEmail(email)).thenReturn(expectedUser);

        // Test
        User actualUser = userUseCase.getUserByEmail(email);

        // Verification
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testSaveUser() {
        // Mocking
        User userToSave = new User("John","Doe","11234","31203948", LocalDate.of(1955,01,01),"email@emal.com","password","NULL");
        userToSave.setName("Test User");

        // Test
        userUseCase.saveUser(userToSave);

        // Verification
        verify(userPersistencePort).saveUser(userToSave);
    }
}

