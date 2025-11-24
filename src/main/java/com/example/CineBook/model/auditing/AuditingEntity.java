package com.example.CineBook.model.auditing;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Data
@AllArgsConstructor
public abstract class AuditingEntity implements SoftDeletable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Instant createTime;
    private UUID createBy;
    private Instant updateTime;
    private UUID updateBy;
    private Boolean isDelete = false;
    private Instant deleteTime;
    private UUID deleteBy;

    @Override
    public void setDeleted(Boolean deleted) {
        this.isDelete = deleted;
    }

    @Override
    public Boolean getIsDelete() {
        return this.isDelete;
    }


}

/// Kiến thức học được
