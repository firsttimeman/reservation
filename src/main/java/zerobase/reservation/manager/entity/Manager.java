package zerobase.reservation.manager.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import zerobase.reservation.BaseEntity;
import zerobase.reservation.MemberType;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Manager extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manId;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String phoneNumber;

    @Email
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    //TODO 시큐리티 구현

}
