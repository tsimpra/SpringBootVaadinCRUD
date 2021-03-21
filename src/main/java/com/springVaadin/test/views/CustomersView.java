package com.springVaadin.test.views;

import com.springVaadin.test.domains.Customer;
import com.springVaadin.test.services.CustomerService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "customers")
public class CustomersView extends VerticalLayout {

    private CustomerService customerService;
    private final Grid<Customer> grid;

    public CustomersView(CustomerService customerService) {
        add(new Button("Back to Home",e-> UI.getCurrent().navigate(MainView.class)));
        this.customerService=customerService;
        this.grid = new Grid<>(Customer.class);
        TextField textFilter = new TextField();
        textFilter.setPlaceholder("Filter by last name");
        textFilter.setValueChangeMode(ValueChangeMode.EAGER);
        textFilter.addValueChangeListener(e -> grid.setItems(listCustomers(e.getValue())));
        grid.setItems(listCustomers(""));
        add(textFilter,grid);
    }

    private List<Customer> listCustomers(String textFilter){
            if(textFilter.isBlank()){
                return customerService.getAllCustomers();
            }else{
                return customerService.getCustomerByLastNameWithIgnoreCase(textFilter);
            }
    }

}
