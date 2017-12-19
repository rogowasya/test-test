package com.haulmont.testtask.ui;

import com.haulmont.testtask.service.ServiceContainer;
import com.haulmont.testtask.ui.doctor.DoctorManager;
import com.haulmont.testtask.ui.patient.PatientManager;
import com.haulmont.testtask.ui.recipe.RecipeManager;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 13.12.17.
 */

@SpringComponent
@UIScope
public class TabSheetMenu extends HorizontalLayout{

    private final DoctorManager doctor;

    private final PatientManager patient;

    private final RecipeManager recipe;

    private TabSheet tabsheet = new TabSheet();

    @Autowired
    public TabSheetMenu(ServiceContainer services) {
        this.doctor = new DoctorManager(services);
        this.patient = new PatientManager(services);
        this.recipe = new RecipeManager(services);
        this.addComponent(tabsheet);
        createTab("Врачи", "image/doctor30.png", doctor);
        createTab("Пациенты", "image/patient.png", patient);
        createTab("Рецепты", "image/recipe.png", recipe);
    }

    private void createTab(String name, String imagePath, AbstractOrderedLayout component) {
        VerticalLayout tab = new VerticalLayout();
        tab.addComponent(component);
        tabsheet.addSelectedTabChangeListener(event -> {
            event.getTabSheet().getSelectedTab().markAsDirtyRecursive();
        });
        tabsheet.addTab(tab, name, new ThemeResource(imagePath));
    }

    public TabSheet getTabsheet() {
        return tabsheet;
    }
}
