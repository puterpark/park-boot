package us.puter.park.service;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import us.puter.park.util.Utility;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

@Service
public class PredictService {

	private static final Logger logger = LoggerFactory.getLogger(PredictService.class);

	@Value("${system.predict.target-url}")
	private String targetUrl;

	@Value("${system.predict.timeout}")
	private int timeout;

	/**
	 * text 값에 따른 문장의 긍정/부정 분석
	 * 
	 * @param jsonMap
	 * @param text
	 */
	public void getMoodByText(Map<String, Object> jsonMap, String text) {

		HttpsURLConnection conn = null;
		OutputStreamWriter osw = null;
		BufferedReader reader = null;
		StringBuilder sb = null;
		boolean mood = false;

		try {
			String predictUrl = Utility.decStringByJasypt(targetUrl);
			URL url = new URL(predictUrl);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setConnectTimeout(timeout);
			conn.setReadTimeout(timeout);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true);
			conn.setHostnameVerifier(new HostnameVerifier() {

				@Override
				public boolean verify(String hostname, SSLSession session) {

					return true;
				}
			});

			// SSL setting
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, new TrustManager[] { new javax.net.ssl.X509TrustManager() {

				@Override
				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

				}

				@Override
				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {

					return null;
				}
			} }, null);

			conn.setSSLSocketFactory(context.getSocketFactory());

			JSONObject obj = new JSONObject();
			obj.put("text", text);
			text = obj.toString();

			osw = new OutputStreamWriter(conn.getOutputStream());
			osw.write(text);
			osw.close();

			conn.connect();

			// 응답값 처리
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			sb = new StringBuilder();

			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			String resultRaw = sb.toString();

			if (resultRaw.indexOf("positive") > -1) {
				mood = true;
			}
		} catch (NoSuchAlgorithmException | KeyManagementException | IOException e) {
			logger.error("문장 감성분석기 API 호출 중 오류 발생.", e);
		} finally {
			try {
				if (reader != null) {
					osw.close();
				}
			} catch (IOException e) {
			}
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
			}
			if (conn != null) {
				conn.disconnect();
			}

			jsonMap.put("mood", mood);
		}
	}
	
}
