import com.iac.applet.CardObjectDetail;
import com.iac.applet.RequestPrintDetail;

import java.awt.*;
import java.util.StringTokenizer;

/**
 * Created by mavaji on 2/10/2016.
 */
public class SIP30CJava {
    static public boolean isInit = false;
    private static final String printerHome = "C:/Windows/printer/";


    //***************************************************************************************************************************************************************************************
    //HODOO SIP-30 JNI header file of JAVA and DLL.
    public static native String JavaGetDeviceSerialList(int Number);

    public static native int JavaGetDeviceCount();

    public static native int    JavaOpen(int DevNumber);

    public static native int JavaOpenEx(byte DevNumber, String SerialNumber);

    public static native int JavaClose(int pHandle);

    public static native int JavaResetDevice(int pHandle);

    public static native String JavaDirectCmd(int pHandle, String CmdString, int CmdSize);

    public static native String JavaCardShell(int pHandle, String CmdString, int CmdSize);

    public static native int JavaMM(int pHandle, int Position);

    public static native int JavaMAP(int pHandle, int Position);

    public static native int JavaSC(int pHandle, int cmd);

    public static native int JavaME(int pHandle, int Start, int End);

    public static native int JavaCME(int pHandle, int Color, int Start, int End);

    public static native int JavaIntensity(int pHandle, int Color, int Intensity);

    public static native int JavaColorPrinting(int pHandle, int Ribbon, int PrnMode);

    public static native int JavaMonoPrinting(int pHandle, int PrnMode);

    public static native int JavaMonoFastPrinting(int pHandle, int PrnMode);

    public static native int JavaMC(int pHandle);

    public static native int JavaCP(int pHandle);

    public static native int JavaRibbon(int pHandle, int Ribbon);

    public static native int JavaDBF(int pHandle, int ColorMode, int StartX, int StartY, int Width, int Height, int GraphicMode);

    public static native int JavaSSP(int pHandle, int Port, int Reset);

    public static native int JavaLED(int hHandle, int LedColor, int Action);

    public static native int JavaPL(int pHandle, String jTone);

    public static native int JavaWCT(int pHandle, int Silent, int Alram);

    public static native int JavaGraphicLcdDisplay(int pHandle, String jFileName, int iBank);

    public static native int JavaDisplayImageDownload(int pHandle, String jFileName, int bank);

    public static native int JavaLcdAllClear(int pHandle);

    public static native int JavaLcdPutCompleteHan(int pHandle, byte xx, byte yy, byte attr, String jlcddata);

    public static native int JavaLcdPutCompleteHan4Line(int pHandle, char x1, String lcddata1, char x2, String lcddata2, char x3, String lcddata3, char x4, String lcddata4);

    public static native int JavaSendLcdText(int pHandle, int Line, String jlcddata);

    public static native int JavaLcdTextClear(int pHandle, int ClearMode);

    public static native int JavaImageBufferClear(int pHandle, int iType);

    public static native int JavaPrintAllBufferClear(int pHandle);

    public static native int JavaMakeImage2Buffer(int pHandle, int xPos, int yPos, String imagefilename, int Orientation);

    public static native int JavaDownLoadImage2Print(int pHandle, int nColorMode);

    public static native int JavaDirectDownloadImage(int pHandle, String jVarnish_File, int nColorMode);

    public static native int JavaTextToBmpMake(int pHandle, String jcFileName, String jcStr, String jFontName, int FontType, int FontSize);

    public static native int JavaVarnishToBmpMake(int pHandle, String jFileName, int left, int right, int top, int bottom, int Mode);

    public static native int JavaImageDownload(int pHandle, String jFileName, int type);

    public static native int JavaSingleImageDownload(int pHandle, String jFileName, int type, int startpos, int endpos);

    public static native int JavaDownLoadSingleImage2Print(int pHandle, int nColorPanel);

    public static native String JavaMSRead(int pHandle, int iReadLevel, int iTrack, int iDirection);

    public static native int JavaMSWrite(int pHandle, char Quality, char Verify, char iTrack, String sziso1, String sziso2, String sziso3);

    public static native String JavaExpandMSRead(int pHandle, int iReadLevel, int iTrack, int iDirection);

    public static native int JavaExpand_MS_Write(int pHandle, char Quality, char Verify, char iTrack, String sziso1, String sziExpand1, String sziso2, String sziExpand2, String sziso3, String sziExpand3);

    public static native String JavaGetMSLastError(int pHandle);

    public static native String JavaICPowerOn(int pHandle, int cno);

    public static native int JavaICPowerOff(int pHandle, int cno);

    public static native int JavaICContactOn(int pHandle);

    public static native int JavaICContactOff(int pHandle);

    public static native int JavaDeviceDirectSend(int pHandle, String jcmdbuf);

    public static native String JavaSendCommand(int pHandle, String jCmd);

    public static native String JavaGetLastError(int pHandle, int Size);

    public static native int JavaDeviceErrorClear(int pHandle);

    public static native String JavaGetPrinterInfo(int pHandle);

    public static native int JavaDownloadSW(int pHandle, String filename, String address);

    public static native int JavaSISS_FuseFlash(int pHandle, long fromaddr, long toaddr, long size);

    public static native int JavaReset(int pHandle);

    public static native int JavaInitialize(int pHandle);

    public static native String JavaGetDllVersion(int pHandle);

    public static native int JavaWriteMemory(int pHandle, String WriteMem, long WriteAddress, long WriteByte);

    public static native String JavaReadMemory(int pHandle, double StartAddress, long ReadByte);

    public static native int JavaHoDooDeviceControl(int pHandle, String jDeviceName, double NewState);

    public static native int JavaLcdMode(int pHandle, int Mode);

    public static native int JavaDownloadFile(int pHandle, String filename);

    public static native int JavaCardInsert(int pHandle);

    public static native int JavaCardEject(int pHandle, int cmd);

    public static native int JavaGetStatus(int pHandle, String st0, String st1);

    public static native String JavaCheckSIM(int pHandle, int nSlot);

    public static native String JavaCheckFeeder(int pHandle);

    public static native int JavaICEject(int pHandle);

    public static native String JavaRIBCOLOR(int pHandle);

    public static native String JavaRIBCOLORCOUNT(int pHandle);

    public static native int JavaFlipOver(int pHandle);

    public static native int JavaPrintingSpeed(int pHandle, int Speed);

    public static native int JavaPanelPrinting(int pHandle, int Panel, int BufferSide);

    public static native int JavaMakeImage2BufferExt(int pHandle, int xPos, int yPos, int Extwidth, int Extheight, String jimagefilename, int Orientation, double Rop);

    public static native int JavaTextToBmpMakeExt(int pHandle, String jcFileName, String jcStr, String FontName, int FontBold, int FontItalic, int FontUnderline, int FontStrikeOut, int FontSize);

    public static native int JavaTextToBmpMakeExt2(int pHandle, String jcFileName, String jcStr, long TextColor, String jFontName, int FontBold, int FontItalic, int FontUnderline, int FontStrikeOut, int FontSize);

    public static native String JavaGetBridgeVersion(int pHandle);

    public static native String JavaGetLaminationVersion(int pHandle);

    public static native int JavaLaminate(int pHandle, int LaminateSide);

    public static native int JavaPWR(int pHandle, String jCurrentPW);

    public static native int JavaPWA(int pHandle, String jCurrentPW);

    public static native int JavaPWS(int pHandle, String jCurrentPW, String jNewPW);

    public static native int JavaPWL(int pHandle, String jCurrentPW);

    public static native int JavaSuperPWQ(int pHandle, String jCurrentPW);

    public static native int JavaSuperPW(int pHandle);

    public static native String JavaGetMachineSerialNumber(int pHandle);

    public static native String JavaGetTPHSerialNumber(int pHandle);

    public static native int JavaSetTPHResistance(int pHandle, int TPHResistance);

    public static native String JavaGetTPHResistance(int pHandle);

    public static native String JavaGetUsedCount(int pHandle, int Module);

    public static native int JavaTHE(int pHandle, int EarseTemp);

    public static native int JavaTHP(int pHandle, int EndPos, int PrintingTemp);

    public static native int JavaMSReadAmplitude(int pHandle, int iTrack, int iDirection);

    public static native boolean JavaIsOpen();

    public static native int JavaTCPIPClose(int mConnect);

    public static native int JavaTCPIPOpen(String m_iSaveIPAddress);

    public static native int JavaTextCodeToPrinter(int pHandle, int BufferSide, int FontDirection, int xPos, int yPos, int FontSize, int FontSpacing, String StringData);

    public static native int JavaTextCodeToPrinterExt(int pHandle, int BufferSide, int FontDirection,
                                                      int xPos, int yPos, int FontSize, int FontSpacing,
                                                      int reserved7, int reserved8, int reserved9, int reserved10, int reserved11,
                                                      int reserved12, int reserved13, int reserved14, int reserved15, int reserved16, String StringData);

    public static native int JavaSetSaveRibbon(int pHandle, int SaveValue);

    public static native int JavaSet3DESKEY(int pHandle, String Key);

    public static native String JavaMSRead3DES(int pHandle, int iReadLevel, int iTrack, int iDirection);

    public static native int JavaMSWrite3DES(int pHandle, String Quality, String Verify, String iTrack, String jsziso1, String jsziso2, String jsziso3);

    public static native int JavaImageDownload3DES(int pHandle, String FileName, int type);

    public static native int JavaDownLoadImage2Print3DES(int pHandle, int nColorMode);

    public static native int JavaSingleImageDownload3DES(int pHandle, String FileName, int type, int startpos, int endpos);

    public static native int JavaDownLoadSingleImage2Print3DES(int pHandle, int nColorPanel);

    public static native int JavaShiftHopper(int pHandle, int SetHopper);

    public static native int JavaDownloadMultiFunction(int pHandle, String filename, char port, int totlen);

    public static native String JavaGetHopperSensor(int pHandle);

    public static native int JavaGetMobileCheck(int pHandle);

    public static native int JavaMakeText2Buffer(int pHandle, int x, int y, String jcFileName, String jcStr, String jFontName, int FontSize, int lfEscapement, int lfItalic, int lfUnderline, int lfWeight, int lfStrikeOut);

    public static native int JavaMakeText2BufferExt(int pHandle, int x, int y, String cFileName, String cStr, String FontName, long TextColor, int FontSize, int lfEscapement, int lfItalic, int lfUnderline, int lfWeight, int lfStrikeOut);

    public static native int JavaBufferImage2Print(int pHandle, int nColorMode, String nBufferImageName);

    public static native String JavaMSReadMulti(int pHandle, int iReadLevel, int iTrack, int iDirection);

    public static native int JavaMSWriteMulti(int pHandle, char Quality, char Verify, char iTrack, String sziso1, String sziso2, String sziso3);

    public static native String JavaGetVersion(int pHandle);

    public static native int JavaRibbonRemainReset(int pHandle);

    public static native int JavaTextOut(int pHandle, int x, int y, String cStr, String FontName, double TextColor, int FontSize, int lfEscapement, int lfItalic, int lfUnderline, int lfWeight, int lfStrikeOut);

    public static native int JavaImageOut(int pHandle, int xPos, int yPos, int Extwidth, int Extheight, String imagefilename, int Orientation, double Rop);

    public static native int JavaDownloadMonoImage(int pHandle);
    //***************************************************************************************************************************************************************************************

    static {
        try {
            System.loadLibrary("SIP30CJava");
            isInit = true;
        } catch (UnsatisfiedLinkError ule) {
            isInit = false;
        }
    }

    public static String Process(RequestPrintDetail requestPrintDetail) throws Exception {
        String result = "";

        int ret = 0;
        ret = MS_WriteReadTest(requestPrintDetail);

        if (ret < 2) {
            Print(requestPrintDetail);
        }

        return result;
    }

    //**********************************************************************************************************************************
    public static void Print(RequestPrintDetail requestPrintDetail) {
        int handle = JavaOpen(0);

        System.out.println("HODOO SIP-30 Printing Test for JAVA %d/%d \n");

        if (handle != 0) {
            //Move to MS read station(card loading...)
            JavaMM(handle, 5);  //5: MM_START_PRINT_POSITION

            JavaPrintAllBufferClear(handle);

//			JavaIntensity(handle,0,5); //Yellow
//			JavaIntensity(handle,1,5); //Magenta
//			JavaIntensity(handle,2,5); //Cyan
            JavaIntensity(handle, 3, 5); //Black

            System.out.println("Send Intensity Value");

            //----------------------------------------------------------------------------------------------------
            System.out.println(String.format("Printing start...\n"));


            String tempFilename = "d:\\hodootemp\\~Temp.bmp";

            for (CardObjectDetail list : requestPrintDetail.getCardObjectDetail()) {
                String fieldValue = list.getFieldValue().trim();
                int lineTextYpos = list.getColNum();
                int lineTextXpos = list.getRowNum();

                Font objFont = new Font(list.getFontName(), Font.BOLD, list.getFontSize() * 120 / 300)
                        .deriveFont((float) (list.getFontSize() * 120 / 300.));
                JavaTextOut(handle, lineTextXpos, lineTextYpos, fieldValue, list.getFontName(),
                        0, list.getFontSize(), -900, 0, 0, 700, 0);
//                if ((lineTextXpos != 0) && (lineTextYpos != 0) && (crClr4 != 0x00000000)){
//                    if (JavaTextToBmpMakeExt2(handle, tempFilename, list.getFieldValue(), 0,
//                            list.getFontName(), 1, 0, 0, 0, list.getFontSize()) == 1) {
//                        JavaMakeImage2Buffer(handle, lineTextXpos, lineTextYpos, tempFilename, 0);
//                    }
//                }
            }

            JavaRibbon(handle, 4); //set mono ribbon(DM_RIBBON_Kr)
            JavaDownloadMonoImage(handle);
            JavaPanelPrinting(handle, 3, 0);

            JavaMM(handle, 4);  //4: MM_STACKER_STATION

            JavaClose(handle);
            System.out.println(String.format("Ending JNI system"));
        } else {
            System.out.println(String.format("JavaOpen(0) is Fail\n"));
        }
    }

    public static int MS_WriteReadTest(RequestPrintDetail requestPrintDetail) {
        int x = 0;
        int RetryCnt = 2;
        int errorTrackCnt = 0;
        int errorTrack1Cnt = 0;
        int errorTrack2Cnt = 0;
        int errorTrack3Cnt = 0;

        int handle = JavaOpen(0);


        if (handle != 0) {
            boolean selectedIC = false;
            boolean selectedMono_K = true;
            boolean selectedMono_Kr = false;
            boolean selectedColor_YMCKO = false;

            if (selectedIC) {
                //Pass
            } else {
                JavaMM(handle, 3);  //3: MM_START_MSR_POSITION
            }

            for (x = 0; x < RetryCnt; x++) {
                String strData = "";
                //----------------------------------------------------------------------------------------------------
                // Write
                //----------------------------------------------------------------------------------------------------
                String txtT1 = requestPrintDetail.getTrack_1_Data();
                String txtT2 = requestPrintDetail.getTrack_2_Data();
                String txtT3 = requestPrintDetail.getTrack_3_Data();

                int ret = 0;

                System.out.println("HICO Encoding....");
                ret = JavaMSWrite(handle, 'H', (char) 0, (char) 4, (String) txtT1, (String) txtT2, (String) txtT3);

                //char[] charArray = strData.toCharArray(); //Char[]로 변환
                //sendMsg(String.format("Read MS Write = %d\n" ,ret));

                if (ret == 1) {
                    System.out.println("MS Write Success");
                    System.out.println("MS Read Success and JavaMSRead()\n");
                } else {
                    System.out.println("MS Write Fail");
                    System.out.println(String.format("MS Read Fail for Track 1,2,3 and GetMSLastError() = %s\n", JavaGetMSLastError(handle)));

                    int count = 1;
                    String str = JavaGetMSLastError(handle);
                    StringTokenizer st = new StringTokenizer(str, "|");

                    while (st.hasMoreTokens()) {
                        if (count == 1) {
                            System.out.println(String.format("MS Track 1 Error = 0x%x\n", Integer.parseInt(st.nextToken())));
                        } else if (count == 2) {
                            System.out.println(String.format("MS Track 2 Error = 0x%x\n", Integer.parseInt(st.nextToken())));
                        } else if (count == 3) {
                            System.out.println(String.format("MS Track 3 Error = 0x%x\n", Integer.parseInt(st.nextToken())));
                        }

                        count++;
                    }    //while(st.hasMoreTokens())
                }


                //----------------------------------------------------------------------------------------------------
                // Read
                //----------------------------------------------------------------------------------------------------
                System.out.println(String.format("MS Write Track 1,2,3 \n"));


                strData = JavaMSRead(handle,1,4,1);

                char[] charArray = strData.toCharArray(); //Char[]로 변환
                //sendMsg(String.format("Read MS Data = %s\n" ,strData));

                if ('1' == charArray[0]) {
                    String readTrack1 = "";
                    String readTrack2 = "";
                    String readTrack3 = "";

                    System.out.println(String.format("MS Read Success and JavaMSRead()\n"));

                    StringTokenizer st = new StringTokenizer(strData, "|");

                    //Status data Pass
                    st.hasMoreTokens();
                    st.nextToken();

                    //get track 1,2,3 data.
                    st.hasMoreTokens();
                    readTrack1 = st.nextToken();
                    System.out.println(String.format("MS Track 1 = %s\n", readTrack1));

                    st.hasMoreTokens();
                    readTrack2 = st.nextToken();
                    System.out.println(String.format("MS Track 2 = %s\n", readTrack2));

                    st.hasMoreTokens();
                    readTrack3 = st.nextToken();
                    System.out.println(String.format("MS Track 3 = %s\n", readTrack3));


                    //get original data.
                    txtT1 = requestPrintDetail.getTrack_1_Data();
                    txtT2 = requestPrintDetail.getTrack_2_Data();
                    txtT3 = requestPrintDetail.getTrack_3_Data();


                    //Compare original and read data.
                    if (!txtT1.equals(readTrack1)) {
                        errorTrack1Cnt++;
                    }

                    if (!txtT2.equals(readTrack2)) {
                        errorTrack2Cnt++;
                    }

                    if (!txtT3.equals(readTrack3)) {
                        errorTrack3Cnt++;
                    }


                    if ((!txtT1.equals(readTrack1)) || (!txtT2.equals(readTrack2)) || (!txtT3.equals(readTrack3))) {
                        errorTrackCnt++;
                    } else {
                        break;
                    }
                } else {
                    System.out.println("MS Read Error");
                    System.out.println(String.format("MS Read Fail for Track 1,2,3 and GetMSLastError() = %s\n", JavaGetMSLastError(handle)));

                    int count = 1;
                    String str = JavaGetMSLastError(handle);
                    StringTokenizer st = new StringTokenizer(str, "|");

                    while (st.hasMoreTokens()) {
                        if (count == 1) {
                            System.out.println(String.format("MS Track 1 = 0x%x\n", Integer.parseInt(st.nextToken())));
                        } else if (count == 2) {
                            System.out.println(String.format("MS Track 2 = 0x%x\n", Integer.parseInt(st.nextToken())));
                        } else if (count == 3) {
                            System.out.println(String.format("MS Track 3 = 0x%x\n", Integer.parseInt(st.nextToken())));
                        }

                        count++;
                    }//while(st.hasMoreTokens())
                }
            } //for(int x = 0; x < RetryCnt; x++)


            if (selectedMono_K || selectedMono_Kr || selectedColor_YMCKO) {
                //If MS result is error then eject a card to stacker and job finish
                if (x >= 2) {
                    JavaMM(handle, 4);  //4: MM_STACKER_STATION
                }
            } else {
                //If only MS then eject a card to stacker and job finish
                JavaMM(handle, 4);  //4: MM_STACKER_STATION
            }

            JavaClose(handle);
            System.out.println(String.format("JavaClose()\n"));
            System.out.println(String.format("Ending JNI system"));

            System.out.println("Close MS test");

        } else {
            System.out.println(String.format("JavaOpen(0) is Fail\n"));

        }

        //return error count
        return x;
    }
}
