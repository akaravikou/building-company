package com.solvd.buildingcompany.persistence;

import com.solvd.buildingcompany.domain.Address;

import java.util.List;

public interface AddressRepository {

    void create(Address address);

    void delete(String city);

    Address createIfNotExists(Long addressId, List<Address> addresses);

    void update(Address address);

}
