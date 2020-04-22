package com.yodlee.support.microservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

public class tes {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		File adminhtml = new File("adminemail.html");
		FileUtils.copyInputStreamToFile(ResourceUtils.getURL("classpath:adminemail.html").openStream(), adminhtml);
		FileUtils.readFileToString(adminhtml);
	}
}
