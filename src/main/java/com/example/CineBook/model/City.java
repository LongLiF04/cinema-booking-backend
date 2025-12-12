package com.example.CineBook.model;

import com.example.CineBook.model.auditing.AuditingEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cities")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City extends AuditingEntity {
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(length = 10)
    private String code;
}
