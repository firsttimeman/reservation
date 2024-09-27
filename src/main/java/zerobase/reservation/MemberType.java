package zerobase.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberType {

    CUSTOMER("회원"),
    MANAGER("매니져");


    private final String description;
}
