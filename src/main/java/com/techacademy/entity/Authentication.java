package com.techacademy.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {

    public static enum Role {
        一般, 管理者
    }

    @Id
    @Column(length = 20, nullable = false)
    @NotEmpty
    private String code;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @Column(nullable = false)
    private Date validDate;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
}
