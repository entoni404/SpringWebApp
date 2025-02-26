package org.spring_web.springwebapp.controller;

import org.spring_web.springwebapp.model.entity.UserDetails;
import org.spring_web.springwebapp.model.resource.UserDetailsResource;
import org.spring_web.springwebapp.service.UserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userdetails")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public ResponseEntity<List<UserDetailsResource>> getAllUserDetails() {
        return ResponseEntity.ok(userDetailsService.getAllUserDetails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsResource> getUserDetailsById(@PathVariable Long id) {
        return ResponseEntity.ok(userDetailsService.getUserDetailsById(id));
    }

    @PostMapping
    public ResponseEntity<UserDetailsResource> saveUserDetails(@RequestBody UserDetails userDetails) {
        return ResponseEntity.ok(userDetailsService.saveUserDetails(userDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserDetails(@PathVariable Long id) {
        userDetailsService.deleteUserDetails(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserDetails(@PathVariable Long id, @RequestBody UserDetails updatedDetails) {
        try {
            return ResponseEntity.ok(userDetailsService.updateUserDetails(id, updatedDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
