package com.haulmont.testtask.ui.recipe;

import com.haulmont.testtask.model.json.RecipeJson;
import com.haulmont.testtask.service.ServiceContainer;
import com.haulmont.testtask.service.util.JsonUtil;
import com.haulmont.testtask.ui.common.GridUtil;
import com.haulmont.testtask.ui.common.LoyoutUtil;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ClickableRenderer;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 13.12.17.
 */

public class RecipeManager extends VerticalLayout implements GridUtil<RecipeJson>, LoyoutUtil {

    private final ServiceContainer services;

    private Grid<RecipeJson> grid = new Grid<>(RecipeJson.class);

    public RecipeManager(ServiceContainer services) {
        this.services = services;
        initLayout();

        RecipeFilterControl filter =
                new RecipeFilterControl(services.getPatientService().findAll());
        filter.newClickListener(this::newRecipe);
        filter.applyClickListener(this::applyFilter);
        addComponent(filter);

        grid.setItems(services.getRecipeService().findAllRecipes());
        grid.setHeight(400, Unit.PIXELS);
        grid.setWidth(970, Unit.PIXELS);
        grid.setColumns("description", "patient", "doctor", "created", "priority");

        tuningColumn(0, "Описание", 250d, null, null);
        tuningColumn(1, "Пациент", 150d, null, null);
        tuningColumn(2, "Врач", 150d, null, null);
        tuningColumn(3, "Дата создания", 130d, null, null);
        tuningColumn(4, "Приоритет", 180d, null, null);

        tuningColumn(5, "", 55d, VaadinIcons.EDIT, this::updateHandler);
        tuningColumn(6, "", 55d, VaadinIcons.TRASH, this::removeHandler);

        addComponent(grid);
    }

    private void applyFilter(RecipeFilterControl.ClickEvent clickEvent) {
        grid.setItems(
                services.getRecipeService().findRecipesByFilters(
                        clickEvent.getFilterJson().getDescript(),
                        clickEvent.getFilterJson().getPatient(),
                        clickEvent.getFilterJson().getPriority()
                ));
    }

    private void newRecipe(Button.ClickEvent clickEvent) {
        openEditDialog(new RecipeJson());
    }

    private void updateHandler(ClickableRenderer.RendererClickEvent<RecipeJson> clickEvent) {
        RecipeJson json = clickEvent.getItem();
        openEditDialog(json);
    }
    private void openEditDialog(RecipeJson json) {
        RecipeEditor editor = new RecipeEditor(
                services,
                JsonUtil.fillRecipe(services, json)
        );
        editor.center();
        UI.getCurrent().addWindow(editor);
        editor.addCloseListener(e ->
                grid.setItems(services.getRecipeService().findAllRecipes())
        );
    }

    private void removeHandler(ClickableRenderer.RendererClickEvent<RecipeJson> clickEvent){
        services.getRecipeService().removeById(clickEvent.getItem().getId());
        grid.setItems(services.getRecipeService().findAllRecipes());
    }

    @Override
    public Grid<RecipeJson> getGrid() {
        return grid;
    }

    @Override
    public AbstractOrderedLayout getLayout() {
        return this;
    }

}
