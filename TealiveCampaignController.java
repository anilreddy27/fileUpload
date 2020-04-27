package com.celcom.life.controller;

import com.celcom.life.request.TealiveCampaignRequest;
import com.celcom.life.response.friendsandfamily.FriendsAndFamilyListResponse;
import com.celcom.life.response.tealiveCampaign.TealiveCampaignListResponse;
import com.celcom.life.response.tealiveCampaign.TealiveCampaignResponse;
import com.celcom.life.service.TealiveCampaignService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.MediaType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static com.celcom.life.common.config.Constants.HttpCodes.*;
import static com.celcom.life.config.Constants.RequestMappings.TEALIVE_CAMPAIGN;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = TEALIVE_CAMPAIGN, produces = {APPLICATION_JSON_VALUE})
@Validated
@Api(value = TEALIVE_CAMPAIGN)
@Slf4j
public class TealiveCampaignController {

    @Autowired
    private TealiveCampaignService tealiveCampaignService;


    @ApiOperation(value = "Test")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_200_CODE, message = HTTP_200_MESSAGE, response = TealiveCampaignResponse.class),
            @ApiResponse(code = HTTP_400_CODE, message = HTTP_400_MESSAGE),
            @ApiResponse(code = HTTP_401_CODE, message = HTTP_401_MESSAGE),
            @ApiResponse(code = HTTP_403_CODE, message = HTTP_403_MESSAGE),
            @ApiResponse(code = HTTP_404_CODE, message = HTTP_404_MESSAGE),
            @ApiResponse(code = HTTP_429_CODE, message = HTTP_429_MESSAGE),
            @ApiResponse(code = HTTP_500_CODE, message = HTTP_500_MESSAGE)})
    @GetMapping(value = "/testPdf", produces = APPLICATION_JSON_VALUE)
    public void testPdf(HttpServletResponse httpResponse) throws IOException {
        JsonNode respNode = null;
        Map<String, String> map=new HashMap<>();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        tealiveCampaignService.testPdf(baos);
        byte[] b = baos.toByteArray();
        httpResponse.setContentLength(b.length);
        httpResponse.setContentType(MediaType.PDF.toString());
        httpResponse.getOutputStream().write(b);
        OutputStream
                os
                = new FileOutputStream("D:\\AC483\\Downloads\\Documents-Company\\AddTableExample.pdf");

        // Starts writing the bytes in it
        os.write(b);
        os.close();
        map.put("status", "success");
        respNode = new ObjectMapper().convertValue(map, JsonNode.class);
    }
}
