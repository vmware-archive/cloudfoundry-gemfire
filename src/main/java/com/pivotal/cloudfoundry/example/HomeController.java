package com.pivotal.cloudfoundry.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static Logger LOG = LoggerFactory.getLogger(HomeController.class);	
	
	@Autowired private GemfireTemplate _template;
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object get(@PathVariable String id) {
		LOG.info("Getting value for key [" + id + "] from Gemfire");
		return _template.get(id);
	}
	
	@RequestMapping(value = "/put/{id}/{value}", method = RequestMethod.GET)
	@ResponseBody
	public void put(@PathVariable String id, @PathVariable String value) {
		LOG.info("Putting value [" + value + "]] for key [" + id + "] from Gemfire");
		_template.put(id,  value);
	}
	
}
