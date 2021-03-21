package com.springVaadin.test.services;

import com.springVaadin.test.domains.Customer;
import com.springVaadin.test.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return Optional.of(customerRepository.findAll()).orElse(new ArrayList<Customer>());
    }

    public Customer getCustomerById(BigInteger id){
        return customerRepository.findById(id).orElse(null);
    }

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer){
        Customer original = customerRepository.findById(customer.getId()).orElse(null);
        if(original!=null){
            original.setFirstName(customer.getFirstName());
            original.setLastName(customer.getLastName());
            return customerRepository.save(original);
        }
        return null;
    }

    public boolean deleteCustomer(Customer customer){
        Customer original = customerRepository.findById(customer.getId()).orElse(null);
        if(original!=null){
            customerRepository.delete(original);
            return true;
        }
        return false;
    }

    public List<Customer> getCustomerByLastNameWithIgnoreCase(String textFilter) {
        return Optional.of(customerRepository.findByLastNameStartsWithIgnoreCase(textFilter)).orElse(new ArrayList<>());
    }
}
