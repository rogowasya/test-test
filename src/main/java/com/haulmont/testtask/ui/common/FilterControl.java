package com.haulmont.testtask.ui.common;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.Registration;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 18.12.17.
 */

public class FilterControl extends HorizontalLayout {

    protected Button newBtn = new Button("Добавить", VaadinIcons.FILE_ADD);
    protected CssLayout actions = new CssLayout(newBtn);
    protected HorizontalLayout mainLayout = new HorizontalLayout(actions);

    public FilterControl() {
        super();
        addComponent(newBtn);
        setSpacing(false);
        setHeight("40");

        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        newBtn.setStyleName(ValoTheme.BUTTON_PRIMARY);
        newBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        addComponent(mainLayout);
    }

    public Registration newClickListener(Button.ClickListener listener) {
        return newBtn.addClickListener(listener);
    }

}
