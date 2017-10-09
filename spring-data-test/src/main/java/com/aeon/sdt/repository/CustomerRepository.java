package com.aeon.sdt.repository;

import com.aeon.sdt.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by roshane on 3/12/2017.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
