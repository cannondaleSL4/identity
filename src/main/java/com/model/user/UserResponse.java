package com.model.user;

import com.model.response.OperationResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by dima on 18.02.18.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserResponse extends OperationResponse {
    private User user = new User();
}
