package com.example.CineBook.model;

import com.example.CineBook.model.auditing.AuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SysPermission extends AuditingEntity {

    @Column(unique = true, length = 100, nullable = false)
    private String permission;

    @Column(length = 100, nullable = false)
    private String code;

    @Column(length = 500, nullable = false)
    private String description;

    @Column(length = 100, nullable = false)
    private String systemFlag;
}
