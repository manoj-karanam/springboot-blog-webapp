package io.manojlearns.springboot_blog_webapp.service;

import io.manojlearns.springboot_blog_webapp.dto.RegistrationDto;
import io.manojlearns.springboot_blog_webapp.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;


public interface UserService {

    void saveUser(RegistrationDto registrationDto);

    User findByEmail(@NotEmpty(message = "Email should not be empty or null") @Email String email);
}
