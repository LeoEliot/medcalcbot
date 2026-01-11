package com.malef.medcalcbot.data.entity;


import com.malef.medcalcbot.DTO.CalcType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table (name = "medicines")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "child", nullable = false)
    private String child;

    @Column(name = "type")
    private @Enumerated(EnumType.STRING)
    CalcType type;
}

