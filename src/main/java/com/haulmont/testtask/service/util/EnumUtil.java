package com.haulmont.testtask.service.util;

import com.haulmont.testtask.model.PriorityEnum;
import com.haulmont.testtask.model.SpecializationEnum;

import java.util.Arrays;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 18.12.17.
 */

public class EnumUtil {

    public static PriorityEnum priorityByDesc(String priority) {
        return Arrays.stream(PriorityEnum.values())
                .filter(p->p.getDescription().equals(priority))
                .findFirst().get();
    }

    public static SpecializationEnum specializationByDesc(String specialization) {
        return Arrays.stream(SpecializationEnum.values())
                .filter(p->p.getDescription().equals(specialization))
                .findFirst().get();
    }

}
