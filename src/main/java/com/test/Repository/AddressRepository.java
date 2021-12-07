package com.test.Repository;

import com.test.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    public List<Address> getAllByNumber(String numbers);

    public Address getAllByNumberAndCityAndStreet(String number, String city, String street);
}
