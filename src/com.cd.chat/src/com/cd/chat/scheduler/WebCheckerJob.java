package com.cd.chat.scheduler;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cd.chat.service.impl.ChatManager;
import com.cd.chat.service.impl.WebPageChecker;

public class WebCheckerJob extends QuartzJobBean {
	
	protected Log log = LogFactory.getLog(getClass());
	
	@SuppressWarnings("rawtypes")
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		Map dataMap = context.getJobDetail().getJobDataMap();
		ChatManager chatManager = (ChatManager) dataMap.get("chatManager");
		WebPageChecker webPageChecker = (WebPageChecker) dataMap.get("webPageChecker");
		String spec = (String) dataMap.get("spec");
		
		log.info("begin check");
		if (webPageChecker.check(spec)) {
			log.info(">> FOUND");
			chatManager.sendMessage("sembiring.adi@gmail.com", "Found");
		} else {
			chatManager.sendMessage("sembiring.adi@gmail.com", "not found yet");
			log.info("NOT FOUND");
		}
	}

}
