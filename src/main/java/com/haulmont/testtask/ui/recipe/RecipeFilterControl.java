package com.haulmont.testtask.ui.recipe;

import com.haulmont.testtask.model.Patient;
import com.haulmont.testtask.model.PriorityEnum;
import com.haulmont.testtask.model.json.RecipeFilterJson;
import com.haulmont.testtask.ui.common.FilterControl;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.Registration;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 18.12.17.
 */

public class RecipeFilterControl extends FilterControl {

    private TextField descript = new TextField();
    private ComboBox<Patient> patient = new ComboBox<>();
    private ComboBox<PriorityEnum> priority = new ComboBox<>();

    Button applyBtn = new Button("Применить", VaadinIcons.FILTER);

    public RecipeFilterControl(List<Patient> patients) {
        super();

        patient.setItems(patients);
        patient.setItemCaptionGenerator(Patient::getFio);

        priority.setItems(PriorityEnum.values());
        priority.setItemCaptionGenerator(PriorityEnum::getDescription);

        actions.addComponent(applyBtn);
        mainLayout.addComponents(descript, patient, priority);

        applyBtn.addClickListener(this::applyBtnHandler);
    }

    public void applyClick(){
        if (isEnabled()) {
            fireClick();
        }
    }
    protected void fireClick() {
        RecipeFilterJson json = new RecipeFilterJson();
        json.setDescript(descript.getValue());
        json.setPatient(patient.getValue());
        json.setPriority(priority.getValue());
        fireEvent( new ClickEvent(this, json));
    }

    private void applyBtnHandler(Button.ClickEvent clickEvent) {
        applyClick();
    }

    public static class ClickEvent extends Button.ClickEvent {

        private RecipeFilterJson filterJson;

        public ClickEvent(Component source, RecipeFilterJson filterJson) {
            super(source);
            this.filterJson = filterJson;
        }

        public RecipeFilterJson getFilterJson() {
            return filterJson;
        }
    }

    @FunctionalInterface
    public interface ClickListener extends Serializable{
        Method APPLY_FILTER_METHOD = ReflectTools
                .findMethod(RecipeFilterControl.ClickListener.class,
                        "applyFilter",
                        RecipeFilterControl.ClickEvent.class);

        void applyFilter(RecipeFilterControl.ClickEvent event);
    }

    public Registration applyClickListener(RecipeFilterControl.ClickListener listener) {
        return addListener(RecipeFilterControl.ClickEvent.class, listener,
                ClickListener.APPLY_FILTER_METHOD);
    }

}
