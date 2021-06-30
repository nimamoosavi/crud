package com.nicico.cost.crud.domain.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class TreeResVM<I extends Serializable> {
    private I parentId;
}
