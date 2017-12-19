package com.haulmont.testtask.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@SuppressWarnings("serial")
@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Autowired
    private TabSheetMenu menu;

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("1050");
        layout.setHeight("550");
        layout.setMargin(true);
        layout.addComponent(menu);
        setContent(layout);
    }


}