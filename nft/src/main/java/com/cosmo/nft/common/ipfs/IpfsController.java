package com.cosmo.nft.common.ipfs;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class IpfsController {
	
	private static final Logger logger = LoggerFactory.getLogger(IpfsController.class);
	
	private final IpfsUtil ipfsUtil;
	private final Gson gson;
	
    @PostMapping("/ipfs")
    public String uploadFile(
    		@RequestParam(value="ipfs_file", required=false) List<MultipartFile> multipartFile
    		, HttpServletResponse response) {
    	
    	String strResult = "{ \"result\":\"FAIL\" }";
    	ArrayList<String> testArrayList = new ArrayList<String>();
    	
    	try {
    		String hashValue = "";
    	    if(multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")){
    	        for(MultipartFile file:multipartFile) {
    	        		hashValue = ipfsUtil.saveFile(file);
    	        		testArrayList.add(hashValue);
    	            }
    	        String jsonPlace = gson.toJson(testArrayList);
    	        strResult = "{ \"result\":\"OK\", \"value\":"+jsonPlace+"}";
    	    }
    	    	
    	}catch(Exception e){
    	    e.printStackTrace();
    	    
    	}
    	
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	
    	logger.info(strResult);
    	
    	return strResult;
    }

    @GetMapping("/ipfs/{hash}")
    public ResponseEntity<byte[]> getFile(@PathVariable("hash") String hash) {
        HttpHeaders headers = new HttpHeaders();
        Charset utf8 = Charset.forName("UTF-8");
        MediaType mediaType = new MediaType("application", "json", utf8);
        headers.set("Content-type", MediaType.ALL_VALUE);
        headers.setContentType(mediaType);
        byte[] bytes = ipfsUtil.loadFile(hash);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bytes);

    }
}