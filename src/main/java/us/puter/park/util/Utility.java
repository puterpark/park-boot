package us.puter.park.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.puter.park.config.JasyptConfig;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class Utility {

	protected static final Logger logger = LoggerFactory.getLogger(Utility.class);

	private Utility() {

	}

	public static long getTimeMillis(long time) {

		return (time / 1000) * 1000;
	}

	public static long getTimeMillis() {

		return (System.currentTimeMillis() / 1000) * 1000;
	}

	public static boolean getBooleanValue(Object value) {

		if (value instanceof Boolean) {
			return ((Boolean) value).booleanValue();
		} else {
			return false;
		}
	}

	public static int getIntValue(Object value) {

		if (value instanceof Integer) {
			return ((Integer) value).intValue();
		} else {
			return -1;
		}
	}

	public static int getIntValue(String value) {

		return getIntValue(value, -1);
	}

	public static int getIntValue(String value, int defaultvalue) {

		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultvalue;
		}
	}

	public static int getLongValue(Object value) {

		if (value instanceof Long) {
			return ((Long) value).intValue();
		} else {
			return -1;
		}
	}

	public static String getDisplaySize(long size) {

		/*
		 * Byte (바이트)
		 * KB(킬로바이트) 1,024 = 210 Byte
		 * MB(메가바이트) 1,048,576 = 220Byte
		 * GB(기가바이트) 1,073,741,824 = 230 Byte
		 * TB(테라바이트) 1,099,511,627,776 = 240 Byte
		 * PB(페타바이트) 1,125,899,906,842,624 = 250 Byte
		 * EB(엑사바이트) 1,152,921,504,606,846,976 = 260 Byte
		 * ZB(제타바이트) 1,180,591,620,717,411,303,424 = 270Byte
		 * YB(요타바이트) 1,208,925,819,614,629,174,706,176 = 280 Byte
		 * VB(브론토바이트) 1,974,854,256,821,741,841,624,874 = ⅔Byte
		 * RB(락시아바이트) 23,325,832,583,285,328,532,958,329Byte
		 * OB(에르키스틴바이트) 875,456,156,484,613,628,515,618,641,561Byte
		 * QB(큐타바이트) 184,864,186,186,186,486,484,685,315,645,678,945,651Byte
		 * XC(엑스싸인트) 8142E+29 YB
		 */

		int unit = 1024;
		if (size < unit)
			return size + "bytes";
		int exp = (int) (Math.log(size) / Math.log(unit));
		String pre = String.valueOf("KMGTPE".charAt(exp - 1));

		return String.format("%.1f%sB", size / Math.pow(unit, exp), pre);
	}

	public static String getDisplayTime(long time) {

		String formatString = "yyyy.MM.dd HH:mm";
		SimpleDateFormat format = new SimpleDateFormat(formatString);

		return format.format(new Date(time));
	}

	public static String getTimeYYYYMMDDHHMMSS(long time) {

		String formatString = "yyyy.MM.dd HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(formatString);

		return format.format(new Date(time));
	}

	public static String getTimeYYYYMMDD(long time) {

		String formatString = "yyyyMMdd";
		SimpleDateFormat format = new SimpleDateFormat(formatString);

		return format.format(new Date(time));
	}

	public static String getExtension(String fileName) {
		if (fileName == null) {
			return "etc";
		}
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	public static int getRemainingDate(long setDate) {

		if (setDate == 0) {
			return 0;
		}

		return (int) ((setDate - System.currentTimeMillis()) / (24*60*60*1000) + 1);
	}

	public static String getServerIP() {

		String ip = null;

		try {
			boolean isLoopBack = true;
			Enumeration<NetworkInterface> netInterfaceList = NetworkInterface.getNetworkInterfaces();
			while (netInterfaceList.hasMoreElements()) {
				NetworkInterface ni = netInterfaceList.nextElement();
				Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();

				while (inetAddresses.hasMoreElements()) {
					InetAddress ia = inetAddresses.nextElement();
					if (ia.isLoopbackAddress()) {
						continue;
					}

					if (ia.getHostAddress() != null && ia.getHostAddress().indexOf(".") != -1) {
						ip = ia.getHostAddress();
						isLoopBack = false;
						break;
					}
				}

				if (!isLoopBack) {
					break;
				}
			}
		} catch (SocketException e) {
			logger.error(e.getMessage(), e);

			ip = "127.0.0.1";
		}

		return ip;
	}

	/**
	 * 브라우저가 IE 계열인지 확인.
	 *
	 * @param request
	 * @return
	 */
	public static boolean isIE(HttpServletRequest request) {

		String ua = request.getHeader("User-Agent");

		if (ua != null) {
			ua = ua.toLowerCase();
		} else {
			ua = "";
		}

		if (ua.indexOf("msie") > -1 || ua.indexOf("trident") > -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 사용자 접속기기가 모바일인지 확인.
	 *
	 * @param request
	 * @return
	 */
	public static boolean isMobile(HttpServletRequest request) {

		String ua = request.getHeader("User-Agent");

		if (ua != null) {
			ua = ua.toLowerCase();
		} else {
			ua = "";
		}

		boolean mobile1 = ua.matches(".*(iphone|ipod|android|windows ce|blackberry|symbian|windows phone|webos|opera mini|opera mobi|polaris|iemobile|lgtelecom|nokia|sonyericsson).*");
		boolean mobile2 = ua.matches(".*(lg|samsung).*");

		if (mobile1 || mobile2) {
			return true;
		}

		return false;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public static String getRemoteIP(HttpServletRequest request) {

		return request.getRemoteAddr();
	}

	public static byte[] encodeBase64(String text) {

		return Base64.encodeBase64(text.getBytes());
	}

	public static byte[] decodeBase64(String text) {

		return Base64.decodeBase64(text.getBytes());
	}

	/**
	 * length만큼 랜덤 문자열(영문대문자/소문자/숫자) 생성
	 *
	 * @param length
	 * @return
	 */
	public static String randomUri(int length) {

		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int type = random.nextInt(3);
			switch (type) {
				case 0:
					sb.append((char) (random.nextInt(26) + 97));
					break;
				case 1:
					sb.append((char) (random.nextInt(26) + 65));
					break;
				case 2:
					sb.append(random.nextInt(10));
					break;
			}
		}

		return sb.toString();
	}

	/**
	 * Jasypt 암호화
	 * @param plainString
	 * @return 암호화된 값
	 */
	public static String encStringByJasypt(String plainString) {
		JasyptConfig jasyptConfig = new JasyptConfig();
		return jasyptConfig.stringEncryptor().encrypt(plainString);
	}

	/**
	 * Jasypt 복호화
	 * @param encString
	 * @return 복호화된 값
	 */
	public static String decStringByJasypt(String encString) {
		JasyptConfig jasyptConfig = new JasyptConfig();
		return jasyptConfig.stringEncryptor().decrypt(encString);
	}

}
