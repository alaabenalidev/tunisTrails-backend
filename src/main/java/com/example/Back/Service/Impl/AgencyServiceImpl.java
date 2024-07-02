package com.example.Back.Service.Impl;

import com.example.Back.Entity.Agency;
import com.example.Back.Service.AgencyService;
import com.example.Back.repository.AgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {

    @Autowired
    private AgencyRepository agencyRepository;

    @Override
    public void addAgency(Agency agency) {
        agencyRepository.save(agency);
    }

    @Override
    public List<Agency> getAgencies() {
        return agencyRepository.findAll();
    }

    @Override
    public Agency getAgency(Integer id) {
        Agency agency = agencyRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid agency id" + id));

        return agency;
    }

    @Override
    public void updateAgency(Integer id, Agency agency) {
        // Check whether the agency is in the database or not
        agencyRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid agency id" + id));

        agency.setIdAgency(id);
        agencyRepository.save(agency);
    }

    @Override
    public void deleteAgency(Integer id) {
        // Check whether the agency is in the database or not
        Agency agency = agencyRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid agency id" + id));

        agencyRepository.delete(agency);
    }
}
