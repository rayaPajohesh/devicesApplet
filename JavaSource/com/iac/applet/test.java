package com.iac.applet;


import java.util.ArrayList;
import java.util.List;


public class test {

	
//	static {
//		if(true){
//			try {
//				 String printerHome = "C:\\Windows\\printer\\";
////				System.load(printerHome + "iomemJNI.dll");
////				System.load(printerHome + "prn_adapter_2.0.dll");
////				System.loadLibrary("iomemJNI");
////	            System.loadLibrary("prn_adapter_2.0");
//		      } catch (UnsatisfiedLinkError ex) {
//		      }
//		}
//	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//pos();
		printer();
	}
	private static void pos() {
		String res = POS.commandPOS("742e6a2ca106910101920101a117910102920140a10f8104380000008207046208c2753180be09810400000000820102");//74126a10a106910101920101a106910102920140
		System.out.println(res);
	}
	private static void printer() {
		String header ="4578" + "," + "3" + "," +
                 "PRINTER_HODOO" + "," +
                 "1234abcd" + "," +
                 "SISS TECHNOLOGY INC 601 ACE-TECHNOTOWERM 684 DEUNGCHON-DONG KANGSEO-GU KOR" + "," +
                 "0123456789:<=>:<=>01234567890:<=>:<=>" + "," +
                 "=1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123" + "," +
                 "6395991105024707";
       String detail =
//    	   "Card_Pro_Name" + "," + "100.000ريالي " + "," + "B Nazanin" + "," + "140" + "," + "Bold" + "," + "19" + "," + "20" +  "," +  "#" +
//           "CARDPICTURE" + "," + "" + "," + "Arial" + "," + "150" + "," + "Bold" + "," + "0" + "," + "0" +  "," +"#" +
//           "CARDTEXT" + "," +"تولد مبارک" + "," + "B Nazanin" + "," + "140" + "," + "Bold" + "," + "21" + "," + "12" +  "," +"#" +
           "Customer_Full_Name" + "," +"���� ������ " + "," + "B Nazanin" + "," + "140" + "," + "Bold" + "," + "19" + "," + "20" +  "," +"#" +
           "CVV2" + "," +"cvv2:3589" + "," + "OCR A Extended" + "," + "100" + "," + "Bold" + "," + "7" + "," + "30" +  "," +"#" +
           "ExpiryDate" + "," +"94/07" + "," + "OCR A Extended" + "," + "100" + "," + "Bold" + "," + "40" + "," + "30" +  "," +"#" +
           "Pan1" + "," + "4707" + "," + "Cambria" + "," + "130" + "," + "Bold" + "," + "28" + "," + "16" +  "," +"#" + 
           "Pan2" + "," + "0502" + "," + "Cambria" + "," + "130" + "," + "Bold" + "," + "21" + "," + "16" +  "," +"#" +
           "Pan3" + "," + "9911" + "," + "Cambria" + "," + "130" + "," + "Bold" + "," + "14" + "," + "16" +  "," +"#" +
           "Pan4" + "," + "1234" + "," + "B Nazanin" + "," + "130" + "," + "Bold" + "," + "7" + "," + "16";
      AppletDevices app = new AppletDevices();
   //  app.init();
      String result = app.issueCard(header, detail);
      System.out.println(result);
	}
//	@SuppressWarnings("unused")
	private static  boolean isDataValid(String header, String detail,
			RequestPrintDetail requestPrintDetail) {
		boolean result = false;
		try {
			String[] headerList = header.split(",");
			if (headerList.length == 0)
				return result;
			requestPrintDetail.setPrinterType(headerList[0]);
			requestPrintDetail.setPrinterIP(headerList[1]);
			requestPrintDetail.setPrinterKey(headerList[2]);			
			requestPrintDetail.setTrack_1_Data(headerList[3]);
			requestPrintDetail.setTrack_2_Data(headerList[4]);
			requestPrintDetail.setTrack_3_Data(headerList[5]);
			requestPrintDetail.setPan(headerList[6]);
			String[] detailList = detail.split("#");
			if (detailList.length ==0) 
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
		return result;
	}
}
