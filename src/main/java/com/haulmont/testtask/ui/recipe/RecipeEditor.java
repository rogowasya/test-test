package com.haulmont.testtask.ui.recipe;

import com.haulmont.testtask.model.*;
import com.haulmont.testtask.service.ServiceContainer;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 18.12.17.
 */

public class RecipeEditor extends Window {

    private Recipe recipe;
    private ServiceContainer services;

    /* Редактируемые поля сущности Doctor*/
    private TextField description = new TextField("Описание");
    private ComboBox<Patient> patient = new ComboBox<>("Пациент");
    private ComboBox<Doctor> doctor = new ComboBox<>("Доктор");
    private ComboBox<PriorityEnum> priority = new ComboBox<>("Приоритет");

    /* Кнопки упавления */
    Button saveBtn = new Button("ОК", VaadinIcons.CHECK_CIRCLE_O);
    Button cancelBtn = new Button("Отменить", VaadinIcons.CLOSE_CIRCLE_O);
    CssLayout actions = new CssLayout(saveBtn, cancelBtn);

    // Объект связывания полей окна с полями сущности Doctor
    Binder<Recipe> binder = new Binder<>(Recipe.class);

    public RecipeEditor(ServiceContainer services, Recipe recipe) {
        super("Добавление/редактирование");
        setHeight("450px");
        setWidth("300px");
        setPositionX(200);
        setPositionY(50);

        this.recipe = recipe!=null ?recipe :new Recipe();
        this.services = services;

        patient.setItems(services.getPatientService().findAll());
        patient.setItemCaptionGenerator(Patient::getFio);

        doctor.setItems(services.getDoctorService().findAll());
        doctor.setItemCaptionGenerator(Doctor::getFio);

        priority.setItems(PriorityEnum.values());
        priority.setItemCaptionGenerator(PriorityEnum::getDescription);

        binder.bindInstanceFields(this);
        binder.setBean(recipe);

        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        saveBtn.setStyleName(ValoTheme.BUTTON_PRIMARY);
        saveBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        VerticalLayout mainLayout = new VerticalLayout(
                description, patient, doctor,
                priority, actions);
        setContent(mainLayout);

        saveBtn.addClickListener(this::saveHandler);
        cancelBtn.addClickListener(this::cancelHandler);
    }

    private void cancelHandler(Button.ClickEvent clickEvent) {
        this.close();
    }

    private void saveHandler(Button.ClickEvent clickEvent) {
        services.getRecipeService().save(recipe);
        this.close();
    }
}
