package com.solvd.buildingcompany.service.impl;

import com.solvd.buildingcompany.domain.Address;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;
import com.solvd.buildingcompany.persistence.AddressRepository;
import com.solvd.buildingcompany.persistence.impl.AddressMapperImpl;
import com.solvd.buildingcompany.persistence.impl.AddressRepositoryImpl;
import com.solvd.buildingcompany.service.AddressService;

import java.io.IOException;
import java.util.List;

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

    public Address get(String city) throws RetrieveDataException {
        Address address = addressRepository.get(city);
        if(address == null) {
            throw new RetrieveDataException("No object with this value");
        }
        return address;
    }

    @Override
    public List<Address> getAll() throws RetrieveDataException {
        List<Address> addresses = addressRepository.getAll();
        if(addresses == null) {
            throw new RetrieveDataException("No objects");
        }
        return addresses;
    }

    @Override
    public Integer getCount() throws RetrieveDataException {
        return addressRepository.getCount();
    }
}
