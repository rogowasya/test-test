package com.haulmont.testtask.ui.common;

import com.vaadin.ui.AbstractOrderedLayout;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 14.12.17.
 */

public interface LoyoutUtil {

    default void initLayout() {
        getLayout().setWidth("1050");
        getLayout().setHeight("550");
        getLayout().setSizeUndefined(); // Default
        getLayout().setSpacing(false);
        getLayout().setMargin(false);
    }

    AbstractOrderedLayout getLayout();
}
