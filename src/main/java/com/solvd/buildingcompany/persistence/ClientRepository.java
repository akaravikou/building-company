package com.solvd.buildingcompany.persistence;

import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.domain.Client;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    void create(Client client, Long companyId) throws RetrieveDataException;

    Optional<Long> findIdByLastName(String lastName) throws RetrieveDataException;

    List<BuildingCompany> get() throws RetrieveDataException;

}
