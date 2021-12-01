package com.solvd.buildingcompany.service;

import com.solvd.buildingcompany.domain.Address;

import java.util.List;

public interface AddressService {

    Address create(Address address);

    void delete(String city);

    Address createIfNotExists(Long addressId, List<Address> addresses);

    void update(Address address);

}
