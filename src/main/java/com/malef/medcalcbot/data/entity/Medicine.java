package com.malef.medcalcbot.data.entity;


import com.malef.medcalcbot.DTO.DoseType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table (name = "medicines")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "child_dose", nullable = false)
    private String childDose;

    @Column(name = "dose_type")
    @Enumerated(EnumType.STRING)
    private DoseType doseType;
}

