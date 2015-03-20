package org.openntf.xpagescaptcha;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;


public class Captcha implements Producer {

  private DefaultKaptcha producer;


  public Captcha(HashMap<String, String> configMap) {
    producer = new DefaultKaptcha();
    
    Config mapConfig = new Config(convertToConfigMap(configMap));
    producer.setConfig(mapConfig);

  }

  // here we paste all config properties
  private static Properties convertToConfigMap(HashMap<String, String> configMap) {

	    Properties configProperties = new Properties();

	    for (String strConfigname : configMap.keySet()) {
	      configProperties.put(strConfigname, configMap.get(strConfigname));
	    }

	    return configProperties;

	  }
  
  
  // add the image to the xpages resource streams
  public void getImageInResponse(ServletOutputStream writer, String str_Text){

	try {
		BufferedImage imgKaptcha = createImage(str_Text);
		ImageIO.setUseCache(false);
		ImageIO.write(imgKaptcha, "jpg", writer);
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }
  
  
  
  // creates image
  public BufferedImage createImage(String text) {
	  try{
		  return producer.createImage(text);
	  }catch(Exception e){
		 e.printStackTrace(); 
	  }
	  return null;
  }
  
  // creates random text
  public String createText() {
    return producer.createText();
  }


}
