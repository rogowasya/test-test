package com.haulmont.testtask.ui.doctor;

import com.haulmont.testtask.model.json.DoctorJson;
import com.haulmont.testtask.service.ServiceContainer;
import com.haulmont.testtask.service.util.JsonUtil;
import com.haulmont.testtask.ui.common.FilterControl;
import com.haulmont.testtask.ui.common.GridUtil;
import com.haulmont.testtask.ui.common.LoyoutUtil;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ClickableRenderer;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 13.12.17.
 */

public class DoctorManager extends VerticalLayout implements GridUtil<DoctorJson>, LoyoutUtil {

    private final ServiceContainer services;

    private Grid<DoctorJson> grid;

    public DoctorManager(ServiceContainer services) {
        this.services = services;
        initLayout();

        FilterControl filter = new FilterControl();
        filter.newClickListener(this::newDoctor);
        addComponent(filter);

        grid = new Grid<>(DoctorJson.class);
        grid.setItems(services.getDoctorService().findAllDoctors());
        grid.setHeight(400, Unit.PIXELS);
        grid.setWidth(710, Unit.PIXELS);
        grid.setColumns("firstname", "lastname", "patronymic", "specialization");

        tuningColumn(0, "Имя", 150d, null, null);
        tuningColumn(1, "Фамилия", 150d, null, null);
        tuningColumn(2, "Отчество", 150d, null, null);
        tuningColumn(3, "Специализация", 150d, null, null);

        tuningColumn(4, "", 55d, VaadinIcons.EDIT, this::updateHandler);
        tuningColumn(5, "", 55d, VaadinIcons.TRASH, this::removeHandler);

        addComponent(new Panel(grid));
    }

    private void newDoctor(Button.ClickEvent clickEvent) {
        openEditDialog(new DoctorJson());
    }

    private void updateHandler(ClickableRenderer.RendererClickEvent<DoctorJson> clickEvent) {
        DoctorJson json = clickEvent.getItem();
        openEditDialog(json);
    }

    private void openEditDialog(DoctorJson json) {
        DoctorEditor editor = new DoctorEditor(
                services,
                JsonUtil.fillDoctor(services, json)
        );
        editor.center();
        UI.getCurrent().addWindow(editor);
        editor.addCloseListener(e ->
                grid.setItems(services.getDoctorService().findAllDoctors())
        );
    }

    private void removeHandler(ClickableRenderer.RendererClickEvent<DoctorJson> clickEvent){
        services.getDoctorService().removeById(clickEvent.getItem().getId());
        grid.setItems(services.getDoctorService().findAllDoctors());
    }

    @Override
    public Grid<DoctorJson> getGrid() {
        return grid;
    }

    @Override
    public AbstractOrderedLayout getLayout() {
        return this;
    }
}
