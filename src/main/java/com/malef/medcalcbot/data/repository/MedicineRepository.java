package com.malef.medcalcbot.data.repository;

import com.malef.medcalcbot.DTO.MedicineDTO;
import com.malef.medcalcbot.data.entity.Medicine;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicineRepository extends BaseRepository<Medicine> {

    public List<Medicine> findAll() {
        return em.createQuery("""
                    select c
                    from Medicine c
                    """, Medicine.class)
                .getResultList();
    }

    public MedicineDTO findMedicineByName(String title) {
        return em.createQuery("""
                        select new com.malef.medcalcbot.DTO.MedicineDTO(p.id, p.title,p.childDose,p.doseType)
                        from Medicine p
                        where p.title = :title
                    """,MedicineDTO.class)
                .setParameter("title", title)
                .getSingleResultOrNull();
    }
}
