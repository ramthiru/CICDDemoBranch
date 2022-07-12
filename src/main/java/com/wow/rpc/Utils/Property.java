package com.wow.rpc.Utils;

import org.apache.log4j.Logger;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.util.Entry;

public class Property implements Entry {

	@Attribute(name = "name")
	private String name;
	@Attribute(name = "value")
	private String value;
	private static Logger log = Logger.getLogger(Property.class);
	
	public Property(@Attribute(name = "name") String name, @Attribute(name = "value") String value) {
		log.info("In Property.java - constructor "+" Name : "+name+"Value : "+value);
		this.name = name;
		this.value = value;
	}

	public String getName() {
		log.info("In Property.java Class : getName()");
		return name;
	}

	public String getValue() {		
		log.info("In Property.java Class : getValue()");
		return value;
	}

	
}
