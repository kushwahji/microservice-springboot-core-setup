/**
 * 
 */
package com.hcl.ms.core.exception;


import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String errorDetails;
	private String errorMoreInfo;
}
