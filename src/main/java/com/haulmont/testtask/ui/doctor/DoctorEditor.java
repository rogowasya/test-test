package com.haulmont.testtask.ui.doctor;

import com.haulmont.testtask.model.Doctor;
import com.haulmont.testtask.model.SpecializationEnum;
import com.haulmont.testtask.service.DoctorService;
import com.haulmont.testtask.service.ServiceContainer;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 18.12.17.
 */

public class DoctorEditor extends Window {

    private Doctor doctor;
    private DoctorService service;

    /* Редактируемые поля сущности Doctor*/
    private TextField firstName = new TextField("Имя");
    private TextField lastName = new TextField("Фамилия");
    private TextField patronymic = new TextField("Отчество");
    private ComboBox<SpecializationEnum> specialization =
            new ComboBox<>("Специализация");

    /* Кнопки упавления */
    Button saveBtn = new Button("ОК", VaadinIcons.CHECK_CIRCLE_O);
    Button cancelBtn = new Button("Отменить", VaadinIcons.CLOSE_CIRCLE_O);
    CssLayout actions = new CssLayout(saveBtn, cancelBtn);

    // Объект связывания полей окна с полями сущности Doctor
    Binder<Doctor> binder = new Binder<>(Doctor.class);

    public DoctorEditor(ServiceContainer services, Doctor doctor) {
        super("Добавление/редактирование");
        setHeight("450px");
        setWidth("300px");
        setPositionX(200);
        setPositionY(50);

        this.doctor = doctor!=null ?doctor :new Doctor();
        this.service = services.getDoctorService();

        // Наполнение списка
        specialization.setItems(SpecializationEnum.values());
        specialization.setItemCaptionGenerator(SpecializationEnum::getDescription);

        binder.bindInstanceFields(this);
        binder.setBean(doctor);

        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        saveBtn.setStyleName(ValoTheme.BUTTON_PRIMARY);
        saveBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        VerticalLayout mainLayout = new VerticalLayout(
                firstName, lastName, patronymic,
                specialization, actions);
        setContent(mainLayout);

        saveBtn.addClickListener(this::saveHandler);
        cancelBtn.addClickListener(this::cancelHandler);
    }

    private void cancelHandler(Button.ClickEvent clickEvent) {
        this.close();
    }

    private void saveHandler(Button.ClickEvent clickEvent) {
        service.save(doctor);
        this.close();
    }

}
