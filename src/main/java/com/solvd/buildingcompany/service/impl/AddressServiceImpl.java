package com.solvd.buildingcompany.service.impl;

import com.solvd.buildingcompany.domain.Address;
import com.solvd.buildingcompany.persistence.AddressRepository;
import com.solvd.buildingcompany.persistence.impl.AddressRepositoryImpl;
import com.solvd.buildingcompany.service.AddressService;

import java.io.IOException;
import java.util.List;

public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    public AddressServiceImpl() throws IOException {
        this.addressRepository = new AddressRepositoryImpl();
    }


    @Override
    public Address create(Address address) {
        address.setId(null);
        addressRepository.create(address);
        return address;
    }

    public void delete(String city) {
        addressRepository.delete(city);
    }

    public Address createIfNotExists(Long addressId, List<Address> addresses){
       return addressRepository.createIfNotExists(addressId, addresses);
    }

    public void update(Address address) {
        addressRepository.update(address);
    }
}
