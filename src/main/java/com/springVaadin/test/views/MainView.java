package com.springVaadin.test.views;

import com.springVaadin.test.services.CustomerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route
public class MainView extends VerticalLayout {

    public MainView(CustomerService customerService) {
        add(new Button("Click me", e -> Notification.show("Hello, Spring+Vaadin user!")));

        add(new RouterLink("Customer's List",CustomersView.class));

    }

}
