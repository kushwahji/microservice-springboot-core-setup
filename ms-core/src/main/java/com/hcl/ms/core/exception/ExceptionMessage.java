/**
 * 
 */
package com.hcl.ms.core.exception;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {
	private Integer status;
	private HttpStatus errorType;
	private String errorDetail;
	private Object errorMoreInfo;
}
