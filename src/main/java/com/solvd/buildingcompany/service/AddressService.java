package com.solvd.buildingcompany.service;

import com.solvd.buildingcompany.domain.Address;
import com.solvd.buildingcompany.domain.exception.RetrieveDataException;

import java.util.List;

public interface AddressService {

    Address create(Address address) throws RetrieveDataException;

    void delete(String city) throws RetrieveDataException;

    void update(Address address) throws RetrieveDataException;

    Address get(String city) throws RetrieveDataException;

    List<Address> getAll() throws RetrieveDataException;

}
