package com.example.ordered_food.repository;

import com.example.ordered_food.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository  extends JpaRepository<Address,Long> {

}
