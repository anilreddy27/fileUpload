package com.celcom.life.service.impl;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
import org.xhtmlrenderer.extend.FSImage;
import org.xhtmlrenderer.extend.ReplacedElement;
import org.xhtmlrenderer.extend.ReplacedElementFactory;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.pdf.ITextImageElement;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.simple.extend.FormSubmissionListener;
import sun.misc.BASE64Decoder;

import java.io.IOException;

public class ImageReplacedElementFactory implements ReplacedElementFactory {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private final ReplacedElementFactory superFactory;

	public ImageReplacedElementFactory(ReplacedElementFactory superFactory) {
		this.superFactory = superFactory;
	}

	@Override
	public ReplacedElement createReplacedElement(LayoutContext layoutContext, BlockBox blockBox,UserAgentCallback userAgentCallback, int cssWidth, int cssHeight) {
		
		try {
            Element element = blockBox.getElement();
            if (element == null) {
                return null;
            }
            String nodeName = element.getNodeName();
            String src = element.getAttribute("src");
            if (nodeName.equals("img") && !StringUtils.isEmpty(src)) {
                // Cut off "data:"
                String raw = src.substring(5);
                String mime = raw.substring(0, raw.indexOf(';'));
                // looking for MimeTyp image/...
                if (mime.startsWith("image/")) {
                    String base64data = raw.substring(mime.length() + 1);
                    if (base64data.startsWith("base64,")) {
                        // cut off "base64,"
                        String imgData = base64data.substring(7).trim();
                        BASE64Decoder decoder = new BASE64Decoder();
                        byte[] img = decoder.decodeBuffer(imgData);
                        Image image = Image.getInstance(img);
                        scaleToOutputResolution(image, layoutContext.getSharedContext());
                        FSImage fsImage = new ITextFSImage(image);
                        if ((cssWidth != -1) || (cssHeight != -1)) {
                            fsImage.scale(cssWidth, cssHeight);
                        }
                        return new ITextImageElement(fsImage);
                    }
                }
            }
        } catch (IOException | BadElementException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
	}
	 private void scaleToOutputResolution(Image image, SharedContext sharedContext) {
	        float factor = sharedContext.getDotsPerPixel();
	        image.scaleAbsolute(image.getPlainWidth() * factor, image.getPlainHeight() * factor);
	    }
	
	@Override
	public void remove(Element element) {
		superFactory.remove(element);
	}

	@Override
	public void reset() {
		 superFactory.reset();		
	}

	@Override
	public void setFormSubmissionListener(FormSubmissionListener formSubmissionListener) {
		superFactory.setFormSubmissionListener(formSubmissionListener);
	}

}
