package io.manojlearns.springboot_blog_webapp.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegistrationDto {


    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty(message = "Email should not be empty or null")
    @Email
    private String email;
    @NotEmpty(message = "password should not be empty")
    private String password;
}
