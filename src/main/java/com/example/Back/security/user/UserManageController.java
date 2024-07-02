package com.example.Back.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserManageController {

    private final UserService service;

    @PatchMapping
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequest request,
          Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("get-info")
    @PreAuthorize("hasAuthority('agency:read') or hasAuthority('admin:read') or hasAuthority('user:read')")
    public ResponseEntity<User> getInfo() {
        return ResponseEntity.ok(service.getAuthenticatedUser());
    }
}
