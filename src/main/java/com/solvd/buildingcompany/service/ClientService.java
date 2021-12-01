package com.solvd.buildingcompany.service;

import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.domain.Client;

import java.util.List;

public interface ClientService {

    Client create(Client client, Long companyId);

    List<BuildingCompany> select();

    Client createIfNotExists(Long clientId, List<Client> clients);

}
