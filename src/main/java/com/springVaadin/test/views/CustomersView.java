package com.springVaadin.test.views;

import com.springVaadin.test.domains.Customer;
import com.springVaadin.test.services.CustomerService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "customers")
public class CustomersView extends VerticalLayout {

    private CustomerService customerService;
    private final Grid<Customer> grid;
    private final CustomerEditor editor;

    public CustomersView(CustomerService customerService, CustomerEditor editor) {
        add(new Button("Back to Home",e-> UI.getCurrent().navigate(MainView.class)));
        this.customerService=customerService;
        this.grid = new Grid<>(Customer.class);
        this.editor=editor;

        // build layout
        TextField textFilter = new TextField();
        textFilter.setPlaceholder("Filter by last name");

        Button addNewBtn = new Button("New customer", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(textFilter, addNewBtn);

        grid.setHeight("300px");
        grid.setColumns("id", "firstName", "lastName");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        textFilter.setValueChangeMode(ValueChangeMode.EAGER);
        textFilter.addValueChangeListener(e -> grid.setItems(listCustomers(e.getValue())));

        // Connect selected Customer to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editCustomer(e.getValue());
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editor.editCustomer(new Customer("", "")));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            grid.setItems(listCustomers(textFilter.getValue()));
        });

        //instantiate list
        grid.setItems(listCustomers(""));

        add(actions, grid, editor);
    }

    private List<Customer> listCustomers(String textFilter){
            if(textFilter.isBlank()){
                return customerService.getAllCustomers();
            }else{
                return customerService.getCustomerByLastNameWithIgnoreCase(textFilter);
            }
    }

}
