package com.malef.medcalcbot.data.service;


import com.malef.medcalcbot.DTO.MedicineDTO;
import com.malef.medcalcbot.data.entity.Medicine;
import com.malef.medcalcbot.data.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    public MedicineDTO findMedicineByName(String title) {
        return medicineRepository.findMedicineByName(title);
    }
}
