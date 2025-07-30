package com.example.springsecurity.Services;

import com.example.springsecurity.Models.DemandeConge;
import com.example.springsecurity.Repository.DemandeCongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeCongeService {

    @Autowired
    private DemandeCongeRepository demandeCongeRepository;

    public DemandeConge createDemande(DemandeConge demande) {
        demande.setStatut("En attente");
        return demandeCongeRepository.save(demande);
    }

    public List<DemandeConge> getAllDemandes() {
        return demandeCongeRepository.findAll();
    }

    public Optional<DemandeConge> getDemandeById(Long id) {
        return demandeCongeRepository.findById(id);
    }

    public Optional<DemandeConge> updateDemande(Long id, DemandeConge updated) {
        return demandeCongeRepository.findById(id).map(demande -> {
            demande.setNom(updated.getNom());
            demande.setPrenom(updated.getPrenom());
            demande.setPoste(updated.getPoste());
            demande.setDateDebut(updated.getDateDebut());
            demande.setDateFin(updated.getDateFin());
            demande.setTypeConge(updated.getTypeConge());
            demande.setJustification(updated.getJustification());
            demande.setStatut(updated.getStatut());
            return demandeCongeRepository.save(demande);
        });
    }

    public boolean deleteDemande(Long id) {
        if (demandeCongeRepository.existsById(id)) {
            demandeCongeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

