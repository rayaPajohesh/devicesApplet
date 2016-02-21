package com.iac.applet;

import java.applet.Applet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AppletDevices extends Applet {

	private static final long serialVersionUID = 1L;
	private final String PRINTER_EVOLIS = "1";
	private final String PRINTER_MABNA = "2";
	private final String PRINTER_HODOO = "3";
	private final String SUCCESS = "00";
	private final String errorInternalApplet = "9999";
	private final String errorInternalDevice = "8888";
	private final String errorEvolis = "7777";
	private String errorCode;

	private RequestPrintDetail requestPrintDetail;

	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	public void stopApplet() {
		super.stop();

	}

	public void destroyApplet() {
		super.destroy();
	}

	public String getPhysicalId(){
		return POS.getPhysicalId();
	}
	
	public String commandPOS(String command){
		return POS.commandPOS(command);		
	}
	
	public String issueCard(String requestHeader, String requestDetail) {

		try {

			System.out.println(getCurrentDateTime() + "  INFO  "
					+ "issueCard()  <ENTER>");

			requestPrintDetail = new RequestPrintDetail();
			boolean isValidData = isDataValid(requestHeader, requestDetail,
					requestPrintDetail);
			System.out.println(getCurrentDateTime() + "  INFO  "
					+ "Request No : " + requestPrintDetail.getRequestNo());
			/*System.out.println(getCurrentDateTime() + "  INFO  "
					+ "*****requestHeader :  " + requestHeader);
			System.out.println(getCurrentDateTime() + "  INFO  "
					+ "*****requestDetail :  " + requestDetail);*/
			if (!isValidData)
				return errorInternalApplet;
			if (requestPrintDetail.getPrinterType().equalsIgnoreCase(PRINTER_MABNA)) {
				Mabna smart = new Mabna();
				errorCode = smart.Process(requestPrintDetail);
			} else if (requestPrintDetail.getPrinterType().equalsIgnoreCase(PRINTER_EVOLIS)) {
				Class evolisClass = Class.forName("Evolis");
				Method processMethod = evolisClass.getMethod("Process", 
						new Class[]{RequestPrintDetail.class});
				errorCode = (String)processMethod.invoke(null, requestPrintDetail);
			} else if (requestPrintDetail.getPrinterType().equalsIgnoreCase(PRINTER_HODOO)){
				//Hodoo pci30c = new Hodoo();                            //************Hodoo printer to Develop******************
//                Hodoo hodoo = new Hodoo();
//				errorCode = hodoo.Process(requestPrintDetail);
                Class hodooClass = Class.forName("SIP30CJava");
                Method processMethod = hodooClass.getMethod("Process",
                        new Class[]{RequestPrintDetail.class});
                errorCode = (String)processMethod.invoke(null, requestPrintDetail);
			} else 
				errorCode = errorInternalDevice;
			if (errorCode==null||errorCode.equals(""))
				errorCode = SUCCESS;
		}

		catch (Exception e) {
			e.printStackTrace();
			errorCode = e.getMessage();
		}
		System.out.println(getCurrentDateTime() + "  INFO  "
				+ "issueCard()  <EXIT>");
		return errorCode;
	}

	private boolean isEmpty(Object str) {

		if (str == null || ((String) str).length() == 0) {
			return true;

		}
		if ((String) str.toString().trim() == null) {

			return true;

		}

		else {

			return false;
		}

	}

	private boolean isDataValid(String header, String detail,
			RequestPrintDetail requestPrintDetail) {
		boolean result = false;
		System.out.println(getCurrentDateTime() + "  INFO  "
				+ "isDataValid()  <ENTER>");
		try {
			String[] headerList = header.split(",");
			if (headerList.length == 0)
				return result;
			if (isEmpty(headerList[0]))
				return result;
			requestPrintDetail.setRequestNo(headerList[0]);
			requestPrintDetail.setPrinterType(headerList[1]);
			requestPrintDetail.setPrinterIP(headerList[2]);
			requestPrintDetail.setPrinterKey(headerList[3]);
			requestPrintDetail.setTrack_1_Data(headerList[4]);
			requestPrintDetail.setTrack_2_Data(headerList[5]);
			requestPrintDetail.setTrack_3_Data(headerList[6]);
			requestPrintDetail.setPan(headerList[7]);
			String[] detailList = detail.split("#");
			if (detailList.length == 0)
				return result;
			String[] rowDetailList;
			CardObjectDetail cod;
			List<CardObjectDetail> lstRowDetail = new ArrayList<CardObjectDetail>();
			for (int i = 0; i < detailList.length; i++) {
				cod = new CardObjectDetail();
				rowDetailList = detailList[i].split(",");
				cod.setFieldName(rowDetailList[0]);
				cod.setFieldValue(rowDetailList[1]);
				cod.setFontName(rowDetailList[2]);
				cod.setFontSize(Integer.parseInt(rowDetailList[3]));
				cod.setFontStyle(rowDetailList[4]);
				cod.setColNum(Integer.parseInt(rowDetailList[5]));
				cod.setRowNum(Integer.parseInt(rowDetailList[6]));
				lstRowDetail.add(cod);

			}
			requestPrintDetail.setCardObjectDetail(lstRowDetail);
			result = true;

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		System.out.println(getCurrentDateTime() + "  INFO  "
				+ "isDataValid()  <EXIT>");
		return result;
	}

	private String getCurrentDateTime() {
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

	private static String formatRecordNumber(int iRecordNumber,
			int iRequiredSize) {
		String strRecordNumber = new Integer(iRecordNumber).toString();

		while (strRecordNumber.length() < iRequiredSize) {
			strRecordNumber = "0" + strRecordNumber;

		}

		return strRecordNumber;

	}
}
