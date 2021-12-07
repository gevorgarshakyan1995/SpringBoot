package com.test.Repository;

import com.test.Model.Phone;
import com.test.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {

    List<Phone> getAllByModels(String models);
}
