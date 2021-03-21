package com.springVaadin.test.views;

import com.springVaadin.test.domains.Customer;
import com.springVaadin.test.services.CustomerService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "customers")
public class CustomersView extends VerticalLayout {

    private CustomerService customerService;
    private final Grid<Customer> grid;

    public CustomersView(CustomerService customerService) {
        add(new Button("Back to Home",e-> UI.getCurrent().navigate(MainView.class)));
        this.customerService=customerService;
        this.grid = new Grid<>(Customer.class);
        grid.setItems(customerService.getAllCustomers());
        add(grid);
    }

}
