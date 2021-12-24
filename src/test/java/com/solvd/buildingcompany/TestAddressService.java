package com.solvd.buildingcompany;

import com.solvd.buildingcompany.domain.Address;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;
import com.solvd.buildingcompany.service.AddressService;
import com.solvd.buildingcompany.service.impl.AddressServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class TestAddressService extends BeforeAfterAnnotation {

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
        Assert.assertNotNull(testAddress, "Address wasn't created");
    }

    @Test
    public void verifyAddressDeleteTest() throws RetrieveDataException, IOException {
        String city = "Minsk";
        AddressService addressService = new AddressServiceImpl();
        List<Address> addresses = addressService.getAll();
        for (Address address : addresses) {
            addressService.delete(city);
            String currentCity = String.valueOf(addressService.get(address.getCity()));
            Assert.assertNotEquals(currentCity, city);
        }
    }

    @Test
    public void verifyAddressUpdateTest() throws RetrieveDataException {
        Address address1 = new Address();
        Integer apartmentNumber = 2;
        address1.setCity("Moscow");
        address1.setStreet("Kutuzov");
        address1.setHouseNumber("43");
        address1.setApartmentNumber(apartmentNumber);
        addressService.update(address1);
        Address updatedAddress = addressService.get("Moscow");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(updatedAddress.getStreet(), "Kutuzov");
        softAssert.assertEquals(updatedAddress.getHouseNumber(), "43");
        softAssert.assertEquals(updatedAddress.getApartmentNumber(), apartmentNumber);
    }

    @Test
    public void verifyAddressGetByCityTest() throws RetrieveDataException {
        String city = "Moscow";
        Address address = addressService.get(city);
        Assert.assertEquals(address.getCity(), city);
    }

    @Test
    public void verifyAddressCountTest() throws RetrieveDataException {
        List<Address> addresses = addressService.getAll();
        Integer currentAddressCount = addresses.size();
        Integer addressCount = addressService.getCount();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(addresses, "Addresses is null.");
        softAssert.assertEquals(currentAddressCount, addressCount, "The count of addresses isn't equal.");
        softAssert.assertAll();
    }

    @DataProvider(name = "createAddressDataProvider")
    public Object[][] fillAddressFields() {
        Address address2 = new Address("Minsk", "Derision", "22", 5);
        Address address3 = new Address("Barcelona", "Esparteria", "13", 69);
        Address address4 = new Address("London", "Shakespeare", "12", 45);
        Address address5 = new Address("3843", "  ", "gg", 0);
        return new Object[][]{{address2}, {address3}, {address4}, {address5}};
    }

    @Test(dataProvider = "createAddressDataProvider")
    public void verifyAddressCreateDPTest(Address dpAddress) throws RetrieveDataException {
        Address testAddress = addressService.create(dpAddress);
        Long testId = testAddress.getId();
        Assert.assertNotNull(testId, "Address wasn't created");
    }
}
