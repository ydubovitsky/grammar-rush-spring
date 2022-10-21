package ru.ydubovitsky.grammarrushspring.security.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthResponse {

    private String jwttoken;
    private String username;
    private String firstName;
    private String lastName;
    private String about;

}
