package com.solvd.buildingcompany.service.impl;

import com.solvd.buildingcompany.domain.Address;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;
import com.solvd.buildingcompany.persistence.AddressRepository;
import com.solvd.buildingcompany.persistence.impl.AddressMapperImpl;
import com.solvd.buildingcompany.persistence.impl.AddressRepositoryImpl;
import com.solvd.buildingcompany.service.AddressService;

import java.io.IOException;

public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    public AddressServiceImpl() throws IOException {
//        this.addressRepository = new AddressRepositoryImpl();
          this.addressRepository = new AddressMapperImpl();
    }

    @Override
    public Address create(Address address) throws RetrieveDataException {
        address.setId(null);
        addressRepository.create(address);
        return address;
    }

    public void delete(String city) throws RetrieveDataException {
        addressRepository.delete(city);
    }

    public void update(Address address) throws RetrieveDataException {
        addressRepository.update(address);
    }
}
