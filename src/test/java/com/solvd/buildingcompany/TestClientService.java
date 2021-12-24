package com.solvd.buildingcompany;

import com.solvd.buildingcompany.domain.Address;
import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.domain.Client;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;
import com.solvd.buildingcompany.service.AddressService;
import com.solvd.buildingcompany.service.BuildingCompanyService;
import com.solvd.buildingcompany.service.ClientService;
import com.solvd.buildingcompany.service.impl.AddressServiceImpl;
import com.solvd.buildingcompany.service.impl.BuildingCompanyServiceImpl;
import com.solvd.buildingcompany.service.impl.ClientServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class TestClientService {

    private AddressService addressService;
    private BuildingCompanyService buildingCompanyService;
    private ClientService clientService;
    private BuildingCompany buildingCompany;
    private Client client;

    public TestClientService() throws IOException {
        this.addressService = new AddressServiceImpl();
        this.buildingCompanyService = new BuildingCompanyServiceImpl();
        this.clientService = new ClientServiceImpl();
        this.buildingCompany = new BuildingCompany();
        this.client = new Client();
    }

    @Test
    public void verifyClientCreateTest() throws RetrieveDataException, IOException {
        Address address3 = new Address();
        address3.setCity("Amsterdam");
        address3.setStreet("Ajax");
        address3.setHouseNumber("13");
        address3.setApartmentNumber(69);
        addressService.create(address3);
        buildingCompany.setAddress(address3);
        buildingCompany.setName("Ace");
        buildingCompanyService.create(buildingCompany);
        Long newCompanyId = buildingCompanyService.getIdByName("Ace");
        client.setAddress(address3);
        client.setFirstName("Guy");
        client.setLastName("Martin");
        client = clientService.create(client, newCompanyId);
        Assert.assertNotNull(client);
    }

    @Test
    public void verifyClientCountTest() throws RetrieveDataException {
        List<Client> clients = clientService.getAll();
        Integer currentClientCount = clients.size();
        Integer clientCount = clientService.getCount();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(clients, "Addresses is null.");
        softAssert.assertEquals(currentClientCount, clientCount, "The count of addresses isn't equal.");
        softAssert.assertAll();
    }
}
