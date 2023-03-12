package com.TVShows.repo;

import com.TVShows.domain.Role;
import com.TVShows.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepoTest {

    @Autowired
    private UserRepo underTests;
    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .username("JohnDoe")
                .email("doe@gmail.com")
                .password("pass")
                .roles(Role.USER)
                .build();
        underTests.save(user);
    }

    @AfterEach
    void tearDown(){
        underTests.deleteAll();
    }

    @Test
    @DisplayName("Should find user by email")
    void shouldFindUserByEmail() {
        // when
        Optional<User> foundUser = underTests.findByEmail(user.getEmail());

        //then
        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
    }

    @Test
    @DisplayName("Should not find user by email")
    void shouldNotFindUserByEmail() {
        // given
        String email = "Janedoe@gmail.com";

        // when
        Optional<User> foundUser = underTests.findByEmail(email);

        //then
        assertFalse(foundUser.isPresent());
    }

}