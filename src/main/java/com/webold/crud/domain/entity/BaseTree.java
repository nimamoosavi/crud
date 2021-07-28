package com.webold.crud.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Setter
@Getter
public class BaseTree<T> implements Serializable {

    @Transient
    private static final long serialVersionUID = 6488252903669024162L;

    @ManyToOne
    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "PARENT_ID", updatable = false, insertable = false)
    private T parent;

    @Column(name = "PARENT_ID")
    private Long parentId;
}
