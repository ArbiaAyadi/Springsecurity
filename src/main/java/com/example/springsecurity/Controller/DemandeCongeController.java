package com.example.springsecurity.Controller;

import com.example.springsecurity.Models.DemandeConge;
import com.example.springsecurity.Services.DemandeCongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demandes")
public class DemandeCongeController {

    @Autowired
    private DemandeCongeService demandeCongeService;

    @PostMapping
    public ResponseEntity<DemandeConge> createDemande(@RequestBody DemandeConge demande) {
        DemandeConge saved = demandeCongeService.createDemande(demande);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<DemandeConge>> getAllDemandes() {
        return ResponseEntity.ok(demandeCongeService.getAllDemandes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandeConge> getDemandeById(@PathVariable Long id) {
        return demandeCongeService.getDemandeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemandeConge> updateDemande(@PathVariable Long id, @RequestBody DemandeConge updated) {
        return demandeCongeService.updateDemande(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemande(@PathVariable Long id) {
        if (demandeCongeService.deleteDemande(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
