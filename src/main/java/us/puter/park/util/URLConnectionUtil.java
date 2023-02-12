package us.puter.park.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Slf4j
public class URLConnectionUtil {

	@Value("${system.url-connection.timeout}")
	private static int timeout;

	public static String connect(String targetUrl) {

		HttpURLConnection httpConn = null;
		HttpsURLConnection httpsConn = null;
		InputStream is = null;
		BufferedReader reader = null;
		StringBuilder sb = null;
		String resultRaw = "no_data";

		try {
			URL url = new URL(targetUrl);

			if ("https".equals(url.getProtocol())) {
				httpsConn = (HttpsURLConnection) url.openConnection();
				httpsConn.setConnectTimeout(timeout);
				httpsConn.setReadTimeout(timeout);
				httpsConn.setRequestMethod("POST");
				httpsConn.setRequestProperty("Content-Type", "application/json");
				httpsConn.setRequestProperty("Accept", "application/json");
				httpsConn.setDoOutput(true);
				httpsConn.setHostnameVerifier(new HostnameVerifier() {
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
				httpsConn.setSSLSocketFactory(context.getSocketFactory());
				httpsConn.connect();

				is = httpsConn.getInputStream();
			} else {
				httpConn = (HttpURLConnection) url.openConnection();
				httpConn.setConnectTimeout(timeout);
				httpConn.setReadTimeout(timeout);
				httpConn.setRequestMethod("POST");
				httpConn.setRequestProperty("Content-Type", "application/json");
				httpConn.setRequestProperty("Accept", "application/json");
				httpConn.setDoOutput(true);
				httpConn.connect();

				is = httpConn.getInputStream();
			}

			// 응답값 처리
			reader = new BufferedReader(new InputStreamReader(is));
			sb = new StringBuilder();

			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			resultRaw = sb.toString();

		} catch (IOException e) {
			log.error("API 호출 중 오류 발생.", e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				log.error("error", e);
			}
			if (httpsConn != null) {
				httpsConn.disconnect();
			}
			if (httpConn != null) {
				httpConn.disconnect();
			}

			return resultRaw;
		}

	}
}
