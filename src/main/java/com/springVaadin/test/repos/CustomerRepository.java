package com.springVaadin.test.repos;

import com.springVaadin.test.domains.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, BigInteger> {

    List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);

}
