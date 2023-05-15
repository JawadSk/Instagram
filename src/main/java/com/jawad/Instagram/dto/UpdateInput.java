package com.jawad.Instagram.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInput {

    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Name must start with an uppercase letter")
    @NotEmpty
    private String firstName;
    private String lastName;
    @Min(value = 18, message = "Age must be 18 or greater than 18")
    private Integer age;
    @Email
    @NotEmpty
    private String email;
    @Pattern(regexp = "[0-9]{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;
}
