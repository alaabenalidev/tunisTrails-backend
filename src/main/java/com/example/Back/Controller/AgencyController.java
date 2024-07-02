package com.example.Back.Controller;

import com.example.Back.Entity.Agency;
import com.example.Back.Service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/Agency")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('agency:create')")
    public String addAgency(@RequestBody Agency agency){
        agencyService.addAgency(agency);
        return "success add agency";
    }

    @GetMapping
    public List<Agency> getAgencies() {
        return agencyService.getAgencies();
    }

    @GetMapping("/get")
    public Agency getAgency(@RequestParam Integer id){
        return agencyService.getAgency(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('agency:update')")
    public ResponseEntity<Void> updateAgency(@PathVariable Integer id, @RequestBody Agency agency){
        agencyService.updateAgency(id, agency);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('agency:delete')")
    public ResponseEntity<Void> deleteAgency(@PathVariable Integer id){
        agencyService.deleteAgency(id);
        return ResponseEntity.noContent().build();
    }
}
