package com.example.Back.security.auth;

import lombok.*;
import com.example.Back.security.user.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

  private String name;
  private String email;
  private String address;
  private String phoneNumber;
  private String password;
  private Role role;
}
