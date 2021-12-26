/**
 * 
 */
package com.hcl.demo.request;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.hcl.demo.entity.Address;
import com.hcl.demo.entity.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author santosh.kushwah
 *
 */
@Data
public class CustomerRequest {

	private Customer customer;

	/*@Schema(name = "custName", type = "String", format = "String", description = "The customer Name value", required = true, example = "Santosh Kushwah")
	@NotBlank
	private String custName;

	@Schema(name = "email", type = "String", format = "String", description = "The email value", required = true, example = "santosh.kushwah@hcl.com")
	@Email
	private String email;

	@Schema(name = "phone", type = "String", format = "String", description = "The phone value", required = true, example = "9752572357")
	@NotBlank
	private String phone;
	
	@Schema(name = "age", type = "Integer", format = "Integer", description = "The age value", required = true, example = "30")
	@Min(18)
	private int age;
	
	
	@Schema(name = "created", type = "Date", format = "Date", description = "The created value", required = true, example = "2021-12-24")
	private Date created;

	@Schema(name = "updated", type = "Date", format = "Date", description = "The updated value", required = true, example = "2021-12-24")
	private Date updated;

	@Schema(name = "status", type = "boolean", format = "boolean", description = "The status value", required = true, example = "true")
	private boolean status;

	private List<Address> address;
*/
}
