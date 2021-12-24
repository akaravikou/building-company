package com.solvd.buildingcompany.service;

import com.solvd.buildingcompany.domain.Address;
import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.domain.Client;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;

import java.util.List;

public interface ClientService {

    Client create(Client client, Long companyId) throws RetrieveDataException;

    List<BuildingCompany> get() throws RetrieveDataException;

    Integer getCount() throws RetrieveDataException;

    List<Client> getAll() throws RetrieveDataException;

}
