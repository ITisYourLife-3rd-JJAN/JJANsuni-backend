package com.kb.jjan.global.common;

import com.sun.istack.NotNull;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    private LocalDateTime createAt;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "is_deleted", columnDefinition = "NUMBER(1)")
    private boolean isDeleted;

    public boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void changeDeleted() {
        this.isDeleted = !this.isDeleted;
    }
}
