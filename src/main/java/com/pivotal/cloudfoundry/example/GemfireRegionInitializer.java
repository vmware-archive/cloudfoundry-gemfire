package com.pivotal.cloudfoundry.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.function.execution.GemfireOnServersFunctionTemplate;
import org.springframework.stereotype.Component;

@Component
public class GemfireRegionInitializer implements InitializingBean {
	
	private static Logger LOG = LoggerFactory.getLogger(GemfireRegionInitializer.class);	
	
	@Autowired private GemfireOnServersFunctionTemplate _functionTemplate;

	@Override
	public void afterPropertiesSet() throws Exception {
		if(LOG.isDebugEnabled()) {
			LOG.debug("Verifying if test region needs to be created");
		}		
		try {
			_functionTemplate.execute("provision", "test");
			LOG.info("Test region created");
		} catch(Exception ex) {
			LOG.error("Region probably already exists, exception caught...");
			LOG.error("Error caught: " + ex.getMessage());
		}
		
	}

}
