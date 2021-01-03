package com.sachin.stockservice.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;

@Component
public class YahooAPIUtil {

	HttpClient client = HttpClientBuilder.create().build();
	HttpClientContext context = HttpClientContext.create();
	Logger logger = Logger.getLogger(YahooAPIUtil.class);

	public YahooAPIUtil() {
		CookieStore cookieStore = (CookieStore) new BasicCookieStore();
		client = HttpClientBuilder.create().build();
		context = HttpClientContext.create();
		context.setCookieStore(cookieStore);
	}

	public String getPage(String symbol) {
		String rtn = null;
		String url = String.format("https://finance.yahoo.com/quote/%s/history?p=%s", symbol, symbol);
		
		HttpGet request = new HttpGet(url);
		logger.debug(url);

		request.addHeader("User-Agent",
				"Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.2.13) Gecko/20101206 Ubuntu/10.10 (maverick) Firefox/3.6.13");
		try {
			HttpResponse response = client.execute(request, context);
			logger.debug("Response Code : " + response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rtn = result.toString();
			HttpClientUtils.closeQuietly(response);
		} catch (Exception ex) {
			logger.debug("Exception");
			logger.debug(ex);
		}
		logger.debug("returning from getPage");
		return rtn;
	}

	public List<String> splitPageData(String page) {
		// Return the page as a list of string using } to split the page
		return Arrays.asList(page.split("}"));
	}

	public String findCrumb(List<String> lines) {
		String crumb = "";
		String rtn = "";
		for (String l : lines) {
			if (l.indexOf("CrumbStore") > -1) {
				rtn = l;
				break;
			}
		}
		// ,"CrumbStore":{"crumb":"OKSUqghoLs8"
		if (rtn != null && !rtn.isEmpty()) {
			String[] vals = rtn.split(":"); // get third item
			crumb = vals[2].replace("\"", ""); // strip quotes
			crumb = StringEscapeUtils.unescapeJava(crumb); // unescape escaped values (particularly, \u002f
		}
		return crumb;
	}

	public String getCrumb(String symbol) {
		return findCrumb(splitPageData(getPage(symbol)));
	}

	public File downloadData(String symbol, long startDate, long endDate, String crumb) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String directoryName = String.format("E:/Sachin/Data/stockdata/%s/%s", symbol, df.format(new Date()));
		String filename = String.format("E:/Sachin/Data/stockdata/%s/%s/%s.csv", symbol, df.format(new Date()), symbol);
		File directory = new File(directoryName);
		directory.mkdirs();
		File file = new File(filename);
		
		String url = String.format(
				"https://query1.finance.yahoo.com/v7/finance/download/%s?period1=%s&period2=%s&interval=1d&events=history&crumb=%s",
				symbol, startDate, endDate, crumb);
		
		HttpGet request = new HttpGet(url);
		logger.debug(url);

		request.addHeader("User-Agent",
				"Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.2.13) Gecko/20101206 Ubuntu/10.10 (maverick) Firefox/3.6.13");
		try {
			HttpResponse response = client.execute(request, context);
			logger.debug("Response Code : " + response.getStatusLine().getStatusCode());
			HttpEntity entity = response.getEntity();

			String reasonPhrase = response.getStatusLine().getReasonPhrase();
			int statusCode = response.getStatusLine().getStatusCode();

			logger.debug(String.format("statusCode: %d", statusCode));
			logger.debug(String.format("reasonPhrase: %s", reasonPhrase));

			if (entity != null) {
				BufferedInputStream bis = new BufferedInputStream(entity.getContent());
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
				int inByte;
				while ((inByte = bis.read()) != -1)
					bos.write(inByte);
				bis.close();
				bos.close();
			}
			HttpClientUtils.closeQuietly(response);

		} catch (Exception ex) {
			logger.debug("Exception");
			logger.debug(ex);
		}
		
		return file;
	}

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(YahooAPIUtil.class);
		YahooAPIUtil c = new YahooAPIUtil();

		String symbol = "ARVIND.NS";

		String crumb = c.getCrumb(symbol);
		if (crumb != null && !crumb.isEmpty()) {
			logger.debug(String.format("Downloading data to %s", symbol));
			logger.debug("Crumb: " + crumb);
			long startDate = Instant.now().minus(5, ChronoUnit.DAYS).getEpochSecond();
			long endDate = Instant.now().getEpochSecond();
			long currentTime = System.currentTimeMillis();
			
			
			c.downloadData(symbol, startDate, endDate, crumb);
			logger.debug("startDate--> "+startDate+" endDate--> "+endDate+" currentTime--> "+currentTime);
		} else {
			logger.debug(String.format("Error retreiving data for %s", symbol));
		}

	}

}
