package com.iac.applet;

import java.util.Calendar;

import com.tosan.serialport.*;
import com.tosan.serialport.Protocol.POSMessage;
import com.tosan.serialport.Protocol.TosanPOSMessage;

public class POS {

	private static final String SpliterError = ";";
	private static final String Success = "00";
	private static final String POSError = "6666";

	public static String getPhysicalId() {
		String res = "";
		System.out.println(getCurrentDateTime() + "  INFO  " + "getPhysicalId  <ENTER>");
		try {
			res = sendToPOS("74126a10a106910101920101a106910102920140");
		} catch (Throwable ex) {
			res = ex.getMessage();
		}
		System.out.println(getCurrentDateTime() + "  INFO  " + "getPhysicalId : " + res +	" <EXIT>");
		return res;
	}

	public static String commandPOS(String command) {
		String res = "";
		System.out.println(getCurrentDateTime() + "  INFO  " + "commandPOS  <ENTER>");
		try {
			res = sendToPOS(command);
		} catch (Throwable ex) {
			res = ex.getMessage();
		}
		System.out.println(getCurrentDateTime() + "  INFO  " + "commandPOS : " + res +	" <EXIT>");
		return res;
	}

	public static String sendToPOS(String command) throws Throwable {
		String res = "";
		try {
			TosanPOSMessage requestMessage = new TosanPOSMessage();
			TosanPOSMessage responseMessage = new TosanPOSMessage();
			Object byaPOSRequestData = null;
			byaPOSRequestData = Utility.hexStringToByteArray(command);
			requestMessage.setData((byte[]) byaPOSRequestData);
			PortConfig portConfig = SerialPortHelper.getPortConfig();
			PortResult portResult = SerialPortHelper.SendAndReceive(
					(POSMessage) requestMessage, (POSMessage) responseMessage,
					(PortConfig) portConfig);

			if (portResult.getStatus() == Status.SUCCESS) {
				res = Success + SpliterError
						+ byteArrayToHexString(responseMessage.getData());
			} else {
				throw new Exception(portResult.getStatus().toString());
			}
		} catch (Exception ex) {
			res = POSError + SpliterError + ex.getMessage();
		}
		return res;
	}

	public static String byteArrayToHexString(byte[] a) {
		if (a == null)
			return "";
		StringBuilder sb = new StringBuilder(a.length * 2);
		for (byte b : a)
			sb.append(String.format("%02x", b & 0xff));
		return sb.toString();
	}

	public static String byteArrayToString(byte[] a) {
		String res = "";
		for (byte b : a)
			res += b;
		return res;
	}
	
	private static String getCurrentDateTime() {
		Calendar calendar = Calendar.getInstance();

		String timeValue = "" + calendar.get(Calendar.YEAR) + "-"
				+ formatRecordNumber((calendar.get(Calendar.MONTH) + 1), 2)
				+ "-"
				+ formatRecordNumber(calendar.get(Calendar.DAY_OF_MONTH), 2)
				+ " "
				+ formatRecordNumber(calendar.get(Calendar.HOUR_OF_DAY), 2)
				+ ":" + formatRecordNumber(calendar.get(Calendar.MINUTE), 2)
				+ ":" + formatRecordNumber(calendar.get(Calendar.SECOND), 2);

		calendar = null;
		return timeValue;

	}
	
	private static String formatRecordNumber(int iRecordNumber, int iRequiredSize) {
		String strRecordNumber = new Integer(iRecordNumber).toString();
		while (strRecordNumber.length() < iRequiredSize) {
			strRecordNumber = "0" + strRecordNumber;
		}
		return strRecordNumber;
	}
	
	public static void main(String[] args) {
		System.out.println(110);
	}
}