package com.example.Back.Service;

import com.example.Back.Entity.Agency;

import java.util.List;

public interface AgencyService {
    void addAgency(Agency agency);

    List<Agency> getAgencies();

    Agency getAgency(Integer id);

    void updateAgency(Integer id, Agency agency);

    void deleteAgency(Integer id);
}
