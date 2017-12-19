package com.haulmont.testtask.ui.patient;

import com.haulmont.testtask.model.json.PatientJson;
import com.haulmont.testtask.service.ServiceContainer;
import com.haulmont.testtask.service.util.JsonUtil;
import com.haulmont.testtask.ui.common.FilterControl;
import com.haulmont.testtask.ui.common.GridUtil;
import com.haulmont.testtask.ui.common.LoyoutUtil;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ClickableRenderer;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 13.12.17.
 */

@SpringComponent
@UIScope
public class PatientManager extends VerticalLayout implements GridUtil<PatientJson>, LoyoutUtil {

    private final ServiceContainer services;

    private Grid<PatientJson> grid = new Grid<>(PatientJson.class);

    public PatientManager(ServiceContainer services) {
        this.services = services;
        initLayout();

        FilterControl filter = new FilterControl();
        filter.newClickListener(this::newPatient);
        addComponent(filter);

        grid.setItems(services.getPatientService().findAllPatient());
        grid.setHeight(400, Unit.PIXELS);
        grid.setWidth(730, Unit.PIXELS);
        grid.setColumns("firstname", "lastname", "patronymic", "phone");

        tuningColumn(0, "Имя", 150d, null, null);
        tuningColumn(1, "Фамилия", 150d, null, null);
        tuningColumn(2, "Отчество", 150d, null, null);
        tuningColumn(3, "Телефон", 170d, null, null);

        tuningColumn(4, "", 55d, VaadinIcons.EDIT, this::updateHandler);
        tuningColumn(5, "", 55d, VaadinIcons.TRASH, this::removeHandler);

        addComponent(grid);
    }

    private void newPatient(Button.ClickEvent clickEvent) {
        openEditDialog(new PatientJson());
    }

    private void updateHandler(ClickableRenderer.RendererClickEvent<PatientJson> clickEvent) {
        PatientJson json = clickEvent.getItem();
        openEditDialog(json);
    }
    private void openEditDialog(PatientJson json) {
        PatientEditor editor = new PatientEditor(
                services,
                JsonUtil.fillPatient(services, json)
        );
        editor.center();
        UI.getCurrent().addWindow(editor);
        editor.addCloseListener(e ->
                grid.setItems(services.getPatientService().findAllPatient())
        );
    }

    private void removeHandler(ClickableRenderer.RendererClickEvent<PatientJson> clickEvent){
        services.getPatientService().removeById(clickEvent.getItem().getId());
        grid.setItems(services.getPatientService().findAllPatient());
    }

    @Override
    public Grid<PatientJson> getGrid() {
        return grid;
    }

    @Override
    public AbstractOrderedLayout getLayout() {
        return this;
    }

}
