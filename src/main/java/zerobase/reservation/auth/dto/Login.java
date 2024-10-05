package zerobase.reservation.auth.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Login {

    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;
}
