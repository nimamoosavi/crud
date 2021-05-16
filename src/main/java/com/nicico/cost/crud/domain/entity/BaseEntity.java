package com.nicico.cost.crud.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity<I extends Serializable> implements Serializable {
    @Transient
    private static final long serialVersionUID = 64882529036694162L;

    @Id
    private I id;


}
