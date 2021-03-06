package com.wow.rpc.Utils;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.util.Dictionary;
import org.simpleframework.xml.util.Entry;

public class TestCaseData implements Entry {

	@Attribute(name = "name")
	private final String name;
	@ElementList(inline = true, name = "property")
	private Dictionary<Property> properties;

	public TestCaseData(@Attribute(name = "name") String name,
			@ElementList(inline = true, name = "property") Dictionary<Property> properties) {
		this.name = name;
		this.properties = properties;
	}

	public String getName() {
		return name;
	}

	public String getProperty(String name) {
		return properties.get(name).getValue();
	}

}