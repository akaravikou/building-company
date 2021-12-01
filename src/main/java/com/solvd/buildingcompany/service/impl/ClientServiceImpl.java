package com.solvd.buildingcompany.service.impl;

import com.solvd.buildingcompany.domain.Address;
import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.domain.Client;
import com.solvd.buildingcompany.persistence.ClientRepository;
import com.solvd.buildingcompany.persistence.impl.ClientRepositoryImpl;
import com.solvd.buildingcompany.service.AddressService;

import com.solvd.buildingcompany.service.BuildingCompanyService;
import com.solvd.buildingcompany.service.ClientService;

import java.io.IOException;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final AddressService addressService;
    private final BuildingCompanyService buildingCompanyService;

    public ClientServiceImpl() throws IOException {
        this.clientRepository = new ClientRepositoryImpl();
        this.addressService = new AddressServiceImpl();
        this.buildingCompanyService = new BuildingCompanyServiceImpl();
    }

    @Override
    public Client create(Client client,Long companyId) {
        client.setId(null);
        clientRepository.create(client,companyId);

        if (client.getAddress() != null) {
            Address address = addressService.create(client.getAddress());
            client.setAddress(address);
        }
        return client;
    }

    @Override
    public List<BuildingCompany> select() {
        return clientRepository.select();
    }

    public Client createIfNotExists(Long clientId, List<Client> clients) {
        return clientRepository.createIfNotExists(clientId, clients);
    }
}
