package com.haulmont.testtask.ui.common;

import com.haulmont.testtask.model.json.JsonBase;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ClickableRenderer;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 14.12.17.
 */

public interface GridUtil<T extends JsonBase> {

    Grid<T> getGrid();

    default void tuningColumn(
            int idx, String title, Double width,
            VaadinIcons icon, ClickableRenderer.RendererClickListener<T> listener) {

        Grid.Column<T, ?> column;
        if (icon!=null && listener!=null) {
            final ButtonRenderer<T> renderer = new ButtonRenderer<>(listener);
            renderer.setHtmlContentAllowed(true);
            column = getGrid().addColumn((e) -> icon.getHtml(), renderer);
        }else{
            column = getGrid().getColumns().get(idx);
        }

        column.setCaption(title);
        if (width!=null)
            column.setWidth(width);
    }
}
