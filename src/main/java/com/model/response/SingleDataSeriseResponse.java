//This is a common http response model for providing data series

package com.model.response;

import com.model.data.SingleSerise;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class SingleDataSeriseResponse extends OperationResponse {
    private List<SingleSerise> items;
}
