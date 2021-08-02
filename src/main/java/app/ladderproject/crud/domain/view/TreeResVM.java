package app.ladderproject.crud.domain.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class TreeResVM<I extends Serializable> {
    private I parentId;
}
