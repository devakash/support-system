package com.yodlee.support.microservice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yodlee.support.microservice.dao.IssueDao;
import com.yodlee.support.microservice.dao.ToolDao;
import com.yodlee.support.microservice.entity.Issue;
import com.yodlee.support.microservice.entity.IssueStatusInfo;
import com.yodlee.support.microservice.entity.Resolution;
import com.yodlee.support.microservice.entity.ToolInfo;
import com.yodlee.support.microservice.exception.RequestAttributeInvalidException;
import com.yodlee.support.microservice.responseutils.SuccessResponeEntity;
import com.yodlee.support.microservice.service.IssueService;

@RestController
public class SupportController {

	@Autowired
	DiscoveryClient client;

	@Autowired
	private IssueService iservice;

	@Autowired
	ToolDao tDao;

	@Autowired
	IssueDao iDao;

	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	@GetMapping("/getIssues/{id}")
	public ToolInfo get(@PathVariable Integer id, @RequestParam(required = true) String userName) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", "");
		uriVariables.put("to", "");

		ToolInfo info = proxy.retrieveExchangeValue("INR", "EUR");
		return new ToolInfo(info.getPort());
	}

	@GetMapping("/getIssue/{id}")
	public ResponseEntity<?> getIssueStatus(@PathVariable Integer id) {

		ToolInfo toolInfo = tDao.getToolInfo(id);
		return new ResponseEntity<>(toolInfo, HttpStatus.OK);
	}

	@GetMapping("/mobile")
	public void em(@RequestParam(required = true) String msg) {
		try {
			String message = String.valueOf(msg);
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("Got the Error--->" + e.getMessage());
		}
	}

	/**
	 * 
	 * @param toolid -id of the tool as defined/generated from Support system micro
	 *               service
	 * @param map    - Map with below keys.
	 *               issueDescription,issueType,reporterName,file<optional>
	 * @param file   -an image file to upload
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/addissue2/{toolid}")
	public ResponseEntity<?> post(@PathVariable Integer toolid, @RequestParam HashMap<String, String> map,
			@RequestPart(required = false, name = "file") MultipartFile file) throws IOException {

		ToolInfo t = tDao.getToolInfo(toolid);
		if (t == null) {
			throw new RequestAttributeInvalidException("tool id " + toolid + " is invalid");
		}

		if (file != null && file.getContentType() != null && !file.getContentType().contains("image")) {
			throw new RequestAttributeInvalidException(
					"unknown file type " + file.getContentType() + " only images are allowed");
		}

		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		Issue issue = mapper.convertValue(map, Issue.class);

		if (issue == null || issue.getIssueDescription() == null || issue.getIssueDescription().trim().length() == 0
				|| issue.getReporterName() == null || issue.getReporterName().trim().isEmpty()) {
			throw new RequestAttributeInvalidException("One or more attribute are missing or have null value");

		}
		issue.setToolInfo(t);
		Integer createIssue = iservice.createIssue(issue, file);
		SuccessResponeEntity response = new SuccessResponeEntity("Issue created with issue id as :::" + createIssue,
				HttpStatus.OK, new Date());
		return ResponseEntity.ok(response);
	}
	
	/**
	 * 
	 * @param toolid -id of the tool as defined/generated from Support system micro
	 *               service
	 * @param map    - Map with below keys.
	 *               issueDescription,issueType,reporterName,file<optional>
	 * @param file   -an image file to upload
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/arc")
	public ResponseEntity<?> posttest(
			@RequestPart(required = true, name = "file") MultipartFile file, HttpServletRequest request) throws IOException {

		InputStream inputStream = file.getInputStream();
		
		FileUtils.copyInputStreamToFile(inputStream, new File("C:\\Users\\jakash\\desktop\\"+file.getOriginalFilename()));
		
		
		return ResponseEntity.ok("success");
		
		
	}

	/**
	 * 
	 * @param toolid -id of the tool as defined/generated from Support system micro
	 *               service
	 * @param map    - Map with below keys.
	 *               issueDescription,issueType,reporterName,file<optional>
	 * @param file   -an image file to upload
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/addissue/{toolid}")
	public ResponseEntity<?> posta(@PathVariable Integer toolid, @RequestBody Issue issue,
			@RequestPart(required = false, name = "file") MultipartFile file) throws IOException {

		ToolInfo t = tDao.getToolInfo(toolid);
		if (t == null) {
			throw new RequestAttributeInvalidException("tool id " + toolid + " is invalid");
		}

		if (file != null && !file.getContentType().contains("image")) {
			throw new RequestAttributeInvalidException(
					"unknown file type " + file.getContentType() + " only images are allowed");
		}

		if (issue == null || issue.getIssueDescription() == null || issue.getIssueDescription().trim().length() == 0
				|| issue.getReporterName() == null || issue.getReporterName().trim().isEmpty()) {
			throw new RequestAttributeInvalidException("One or more attribute are missing or have null value");

		}
		issue.setToolInfo(t);
		Integer createIssue = iservice.createIssue(issue, file);
		SuccessResponeEntity response = new SuccessResponeEntity("Issue created with issue id as :::" + createIssue,
				HttpStatus.OK, new Date());
		return ResponseEntity.ok(response);
	}

	@GetMapping("/getImage/{toolId}/file/{issueid}")
	public ResponseEntity<?> get(HttpServletResponse response, @PathVariable Integer toolId,
			@PathVariable Integer issueid) {

		ToolInfo t = tDao.getToolInfo(toolId);
		if (t == null) {
			throw new RequestAttributeInvalidException("tool id " + toolId + " is invalid");
		}

		if (issueid == null || issueid == 0) {
			throw new RequestAttributeInvalidException("issue id " + issueid + " is invalid");
		}

		byte[] image = iservice.getImage(issueid);
		if (image == null || image.length == 0) {
			throw new RequestAttributeInvalidException("No image for issue id " + issueid);
		}
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
	}
	
	@GetMapping(value = "/downloadps1",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<?> downloadps1() throws FileNotFoundException{
		File file=new File("c:\\Users\\jakash\\Downloads\\download.ps1");
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

	    return ResponseEntity.ok()
	            .contentLength(file.length())
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename()+".ps1" + "\"")
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(resource);
	}

	@PostMapping("/updateissue/{toolid}")
	public ResponseEntity<?> resolution(@RequestParam(required = true) Integer issueid, @PathVariable Integer toolid,
			@RequestParam HashMap<String, String> map, @RequestPart(required = false, name = "file") MultipartFile file)
			throws IOException {

		ToolInfo t = tDao.getToolInfo(toolid);
		if (t == null) {
			throw new RequestAttributeInvalidException("tool id " + toolid + " is invalid");
		}

		Issue i = iDao.getIssue(issueid);
		if (i == null) {
			throw new RequestAttributeInvalidException("issue id " + issueid + " is invalid");
		}

		if (i.getToolInfo().getToolId() != toolid) {
			throw new RequestAttributeInvalidException("Wrong tool id issue id combination::");
		}

		if (i.getStatusInfo().getStatusName().equals("CLOSED") || i.getStatusInfo().getStatusName().equals("FIXED")) {
			throw new RequestAttributeInvalidException("Issue is already closed or fixed");
		}

		IssueStatusInfo statusInfo = iDao.getStatusInfo(Integer.parseInt(map.get("statusId")));

		if (statusInfo != null) {
			i.setStatusInfo(statusInfo);

			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

			Resolution r = mapper.convertValue(map, Resolution.class);
			r.setIssue(i);
			if (file != null) {
				r.setAttachment(file.getBytes());
			}
			return ResponseEntity.ok(iservice.updateResolution(r));
		} else {
			throw new RequestAttributeInvalidException("issue status id is invalid !!");

		}
	}

	@GetMapping("/getIssueStatus")
	public List<IssueStatusInfo> get() {
		return iservice.getIssueStatus();
	}

	@RequestMapping("/getIssueStatus/{appName}")
	public List<ServiceInstance> geta(@PathVariable String appName) {
		return this.client.getInstances(appName);
	}

	@GetMapping("/dummy")
	public ResponseEntity<?> dummy(@RequestHeader("Content-Type") String contentType, HttpServletRequest request)
			throws IOException {
		System.out.println(contentType);
		return ResponseEntity.ok("");

	}

}
