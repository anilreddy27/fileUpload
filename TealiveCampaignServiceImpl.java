package com.celcom.life.service.impl;

import com.celcom.life.common.security.logger.SecureLogger;
import com.celcom.life.domain.TealiveCampaign;
import com.celcom.life.domain.TealiveCampaignVoucherStatus;
import com.celcom.life.provider.InternalServiceProvider;
import com.celcom.life.provider.request.TealiveNotificationRequest;
import com.celcom.life.repository.TealiveCampaignRepository;
import com.celcom.life.request.TealiveCampaignRequest;
import com.celcom.life.response.tealiveCampaign.TealiveCampaignListResponse;
import com.celcom.life.response.tealiveCampaign.TealiveCampaignResponse;
import com.celcom.life.service.TealiveCampaignService;
import com.celcom.life.util.ErrorCode;
import com.celcom.life.util.ImageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author AnilReddy
 */
@Service
public class TealiveCampaignServiceImpl implements TealiveCampaignService {

 

    private Configuration cfg;

    private String basePackagePath = "/";

    @PostConstruct
    public void init() {
        TemplateLoader loader = new ClassTemplateLoader(getClass(), basePackagePath);
        cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setTemplateLoader(loader);
        cfg.setDefaultEncoding("UTF-8");
    }

   
   
    @Override
    public Object testPdf(ByteArrayOutputStream os) {
        try {
            Writer writer = new StringWriter();
            Template template = cfg.getTemplate("test.ftl");
             Map<String,String> map =new HashMap<>();
            map.put("federatedCount","0");
            map.put("s3Status","Complaint");
            template.process(map, writer);
            DocumentBuilder builder = null;
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builder = builderFactory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(writer.toString().getBytes("UTF-8")));

            ITextRenderer renderer = new ITextRenderer();

            renderer.getSharedContext().setReplacedElementFactory(
                    new ImageReplacedElementFactory(renderer.getSharedContext().getReplacedElementFactory()));
            renderer.setDocument(doc, null);

            renderer.layout();
            renderer.createPDF(os);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

   
}
