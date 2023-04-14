package ru.ydubovitsky.infinitygrammarspring.security.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class AppUserRegisterRequestDto {

    private String username;
    private String password;
    private String password2;
    private String email;
    private String phone;

}
