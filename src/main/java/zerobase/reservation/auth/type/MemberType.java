package zerobase.reservation.auth.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberType {

    MEMBER("ROLE_MEMBER"),
    PARTNER("ROLE_PARTNER");


    private final String description;
}
