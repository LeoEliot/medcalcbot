package com.malef.medcalcbot.DTO;


import lombok.*;

@Data
public class MedicineDTO {

    private final Long id;

    private final String title;

    private final String childDose;

    private final DoseType doseType;

}
