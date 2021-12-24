package com.solvd.buildingcompany;

import com.solvd.buildingcompany.domain.Address;
import com.solvd.buildingcompany.domain.BuildingCompany;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;
import com.solvd.buildingcompany.service.AddressService;
import com.solvd.buildingcompany.service.BuildingCompanyService;
import com.solvd.buildingcompany.service.impl.AddressServiceImpl;
import com.solvd.buildingcompany.service.impl.BuildingCompanyServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestBuildingCompanyService {

    private BuildingCompanyService buildingCompanyService;
    private AddressService addressService;
    private BuildingCompany buildingCompany;

    public TestBuildingCompanyService() throws IOException {
        this.addressService = new AddressServiceImpl();
        this.buildingCompanyService = new BuildingCompanyServiceImpl();
        this.buildingCompany = new BuildingCompany();
    }

    @Test
    public void verifyBuildingCompanyCreateTest() throws RetrieveDataException, IOException {
        Address address2 = new Address();
        address2.setCity("Minsk");
        address2.setStreet("Zmagarou");
        address2.setHouseNumber("2");
        address2.setApartmentNumber(18);
        addressService.create(address2);
        buildingCompany.setAddress(address2);
        buildingCompany.setName("New Company");
        buildingCompanyService.create(buildingCompany);
        Long newCompanyId = buildingCompanyService.getIdByName("New Company");
        Assert.assertNotNull(newCompanyId);
    }

    @Test
    public void verifyBuildingCompanyGetIdByNameTest() throws RetrieveDataException {
        Long companyId = buildingCompanyService.getIdByName("Sweet home");
        Assert.assertNotNull(companyId);
    }
}
