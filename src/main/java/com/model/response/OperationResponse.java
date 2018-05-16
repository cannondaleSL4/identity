package com.model.response;

import lombok.Data;

/**
 * Created by dima on 18.02.18.
 */
@Data
public class OperationResponse {
    public enum ResponseStatusEnum {SUCCESS,ERROR,WARNING,NO_ACCESS};
    private ResponseStatusEnum  operationStatus;
    private String  operationMessage;
}
