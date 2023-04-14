package ru.ydubovitsky.infinitygrammarspring.security.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsernameAndPasswordAuthRequest {

    private String username;
    private String password;

}
