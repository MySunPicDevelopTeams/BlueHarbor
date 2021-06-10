package com.mysunpic.app.common;

import lombok.Data;

@Data
public class BaseResponse {
    /**
     * status code default 20000
     */
    private String statusCode;
    /**
     * status message default ""
     */
    private String statusMsg;

}
