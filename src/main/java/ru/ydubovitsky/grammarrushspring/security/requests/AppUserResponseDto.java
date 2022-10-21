package ru.ydubovitsky.grammarrushspring.security.requests;

import lombok.*;

import javax.persistence.Column;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserResponseDto {

    private Integer id;
    private String username;
    private Set<String> roles;
    private String jwttoken;
}
