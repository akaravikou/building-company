package com.solvd.buildingcompany;

import com.solvd.buildingcompany.domain.Address;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;
import com.solvd.buildingcompany.service.AddressService;
import com.solvd.buildingcompany.service.impl.AddressServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class TestAddressService {

    private AddressService addressService;
    private Address address;


    public TestAddressService() throws IOException {
        this.addressService = new AddressServiceImpl();
        this.address = new Address();
    }

    @BeforeClass
    public void createTestAddress() {
        address.setCity("Minsk");
        address.setStreet("Volha");
        address.setHouseNumber("43");
        address.setApartmentNumber(2);
    }

    @Test
    public void verifyAddressCreateTest() throws RetrieveDataException {
        addressService.create(address);
        Address testAddress = addressService.get(address.getCity());
        Assert.assertNotNull(testAddress, "Address wasn't created" );
    }

    @Test
    public void verifyAddressDeleteTest() throws RetrieveDataException, IOException {
            AddressService addressService = new AddressServiceImpl();
            List<Address>addresses = addressService.getAll();
            for(Address address : addresses){
                addressService.delete("Minsk");
                Address afterDelete = addressService.get(address.getCity());
                Assert.assertEquals(afterDelete, "Minsk");
            }
    }
}
