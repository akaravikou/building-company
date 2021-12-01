package com.solvd.buildingcompany.persistence;

import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    void create(Client client, Long companyId);

    Optional<Long> findIdByLastName(String lastName);

    List<BuildingCompany> select();

    Client createIfNotExists(Long clientId, List<Client> clients);

}
