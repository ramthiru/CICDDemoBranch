package com.wow.rpc.Utils;

import org.apache.log4j.Logger;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.util.Dictionary;

@Root(name = "testcases")

public class XMLReader {

	@ElementList(entry = "testcase", inline = true)
	private Dictionary<TestCaseData> testcases = new Dictionary<TestCaseData>();
	private static Logger log = Logger.getLogger(XMLReader.class);
	
	public XMLReader(@ElementList(entry = "testcase", inline = true) Dictionary<TestCaseData> testcases) {
		this.testcases = testcases;
	}

	public TestCaseData getResourceByName(String name) {
		log.info("In get resource name is : "+name);
		log.info(testcases.get(name));
		return testcases.get(name);
	}

}