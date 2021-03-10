/*
 * eGov Property Registry System.
 * APIs for Property Registry module. This provide APIs for create new property, update existing property, search existing property. 
 *
 * OpenAPI spec version: 1.0.0
 * Contact: contact@egovernments.org
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Contract class to receive request. Array of Property items  are used in case of create . Where as single Property item is used for update
 */
public class PropertyRequest {
  @JsonProperty("RequestInfo")
  private RequestInfo requestInfo = null;

  @JsonProperty("Property")
  private Property property = null;

public RequestInfo getRequestInfo() {
	return requestInfo;
}

public void setRequestInfo(RequestInfo requestInfo) {
	this.requestInfo = requestInfo;
}

public Property getProperty() {
	return property;
}

public void setProperty(Property property) {
	this.property = property;
}

  

}