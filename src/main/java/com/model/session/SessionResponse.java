package com.model.session;

import com.model.response.OperationResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SessionResponse extends OperationResponse {
  //@ApiModelProperty(required = true, value = "")
  private SessionItem item;
}
