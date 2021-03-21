package com.springVaadin.test.views;

import com.springVaadin.test.domains.Customer;
import com.springVaadin.test.services.CustomerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    private CustomerService customerService;
    private final Grid<Customer> grid;

    public MainView(CustomerService customerService) {
        add(new Button("Click me", e -> Notification.show("Hello, Spring+Vaadin user!")));

        this.customerService=customerService;
        this.grid = new Grid<>(Customer.class);
        grid.setItems(customerService.getAllCustomers());
        add(grid);
    }

}
