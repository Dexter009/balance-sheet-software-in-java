/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travel.accounting.software;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.collections.ObservableList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 *
 * @author gaj
 */
public class excell {
public void methodd1(ObservableList list1,ObservableList list, File file){  
try {

    System.out.println("super list is"+ list1);
    

                     HSSFWorkbook workbook = new HSSFWorkbook();
                     HSSFSheet worksheet = workbook.createSheet("POI Worksheet");
                      HSSFRow row1 = worksheet.createRow((short) 0);
                       HSSFCell cellH1 = row1.createCell((short) 3);
                     cellH1.setCellValue("ZD TOURS BALANCE SHEET");
                     HSSFCellStyle cellStyle1 = workbook.createCellStyle();
                     HSSFFont txtFont = (HSSFFont)workbook.createFont();
txtFont.setFontName("Arial");
txtFont.setFontHeightInPoints((short)18);
cellStyle1.setFont(txtFont);
//cellStyle1.setFillForegroundColor(HSSFColor.GOLD.index);
                    // cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                     //cellStyle1.setWrapText(true);
                     cellH1.setCellStyle(cellStyle1);
                      
// index from 0,0... cell A1 is cell(0,0)
                     HSSFRow row5 = worksheet.createRow((short) 5);
                            
                     HSSFCell cellA1 = row5.createCell((short) 2);
                     cellA1.setCellValue("DATE");
                     HSSFCellStyle cellStyle = workbook.createCellStyle();
                     cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
                     cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                     cellA1.setCellStyle(cellStyle);

                     HSSFCell cellB1 = row5.createCell((short) 3);
                     cellB1.setCellValue("Particulars");
                     cellStyle = workbook.createCellStyle();
                     cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
                     cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                     cellB1.setCellStyle(cellStyle);

                     HSSFCell cellC1 = row5.createCell((short) 4);
                     cellC1.setCellValue("DEBIT");
                     cellStyle = workbook.createCellStyle();
                     cellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
                     cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                     cellC1.setCellStyle(cellStyle);
                                

                     HSSFCell cellD1 = row5.createCell((short) 5);
                     cellD1.setCellValue("CREDIT");
                     cellStyle = workbook.createCellStyle();
                     cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
                     cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                     cellD1.setCellStyle(cellStyle);
                     
                     HSSFCell cellE1 = row5.createCell((short) 6);
                     cellE1.setCellValue("BALANCE");
                     cellStyle = workbook.createCellStyle();
                     cellStyle.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                     cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                     cellE1.setCellStyle(cellStyle);
                     try{
                     for(int i=0;i<list.size();i++){
                         CellStyle cs = workbook.createCellStyle();
                               cs.setWrapText(true);
                         Double totalValue = new Double(0);
                         Double item1 = ((Balance) list1.get(i)).getPrate();
                         Double item2 = ((Balance) list1.get(i)).getArate();
                         Double item3 = ((Balance) list1.get(i)).getReff();
                         String ii,ii1 ;
                         if(item1 == null){ii = "";
                          }else{ii =String.valueOf(item1);}
                         String iii ;
                         if(item2 == null){iii = "";}else{iii = String.valueOf(item2);}
                         String iiiv;
                         if(item3 == null){iiiv = "";}else{iiiv = String.valueOf(item3);}
                         HSSFRow row6 = worksheet.createRow((short) 6+i);
                         String par1 =  ((Balance) list1.get(i)).getPar2();
                         if(par1 == null){par1 = "";}else{par1 = ((Balance) list1.get(i)).getPar2(); }
                         row6.setHeightInPoints((2*worksheet.getDefaultRowHeightInPoints()));
                          
                         worksheet.autoSizeColumn(3);
                    HSSFCell  cc1= row6.createCell(2);
                    HSSFCell  cc2= row6.createCell(3);
                    HSSFCell  cc3= row6.createCell(4);
                    HSSFCell  cc4= row6.createCell(5);
                    HSSFCell  cc5= row6.createCell(6);
                    cc1.setCellStyle(cs);
                    cc2.setCellStyle(cs);
                    cc3.setCellStyle(cs);
                    cc4.setCellStyle(cs);
                    cc5.setCellStyle(cs);
                    
                    //cc5.setCellFormula("SUM(G7+Double.valueOf(E)"+su+"+F)");
                    //cc5.setCellFormula(form);
                      cc1.setCellValue(((Balance) list1.get(i)).getDate1() + "\012");
                      cc2.setCellValue(((Balance) list1.get(i)).getPar1() + "\012\t" + par1 );
                      cc3.setCellValue(ii+"\012");
                      cc4.setCellValue(iiiv + "\012" + iii);
                    for(int j = 0; j <= i;j++)
                            {
                      totalValue =totalValue + ((Balance) list1.get(j)).getBal();}
                      cc5.setCellValue(totalValue);
                     
                     }
                     }
                     catch(NullPointerException e)
                             {  
                             e.printStackTrace();
                             }
            HSSFRow row3 = worksheet.createRow((short) list1.size()+5);
            row3.createCell(3).setCellValue("Total");
            int r1= row3.getRowNum();
            System.out.print("r1 = "+r1);
             int sss=list.size()-1+5;
             int sss1=list.size()-1+6;
             int sss2=list1.size()-1+6;
             String we = workbook.getSheetAt(0).getRow(sss1).getCell(6).toString();
             row3.createCell(4).setCellFormula("SUM(C2:C"+sss+")");
             row3.createCell(5).setCellFormula("SUM(D2:D"+sss+")");
             //row3.createCell(6).setCellFormula("SUM(E"+sss + ")");
             row3.createCell(6).setCellValue(we);
             
    FileOutputStream fileOut = new FileOutputStream(file);
             workbook.write(fileOut);
                     fileOut.flush();
                     fileOut.close();
                 } catch (FileNotFoundException e1) {
                     e1.printStackTrace();
                 } catch (IOException e2) {
                     e2.printStackTrace();
                 }    

}

public void methodd2(ObservableList list1, File file){  
try {
   
    System.out.println("super list is"+ list1);
    System.out.println("super list size is"+ list1.size());

                     HSSFWorkbook workbook = new HSSFWorkbook();
                     HSSFSheet worksheet = workbook.createSheet("POI Worksheet");
                      HSSFRow row1 = worksheet.createRow((short) 0);
                       HSSFCell cellH1 = row1.createCell((short) 3);
                     cellH1.setCellValue("ZD TOURS BALANCE SHEET");
                     HSSFCellStyle cellStyle1 = workbook.createCellStyle();
                     HSSFFont txtFont = (HSSFFont)workbook.createFont();
txtFont.setFontName("Arial");
txtFont.setFontHeightInPoints((short)18);
cellStyle1.setFont(txtFont);
//cellStyle1.setFillForegroundColor(HSSFColor.GOLD.index);
                    // cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                     //cellStyle1.setWrapText(true);
                     cellH1.setCellStyle(cellStyle1);
                      
// index from 0,0... cell A1 is cell(0,0)
                     HSSFRow row5 = worksheet.createRow((short) 5);
                            
                     HSSFCell cellA1 = row5.createCell((short) 2);
                     cellA1.setCellValue("DATE");
                     HSSFCellStyle cellStyle = workbook.createCellStyle();
                     cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
                     cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                     cellA1.setCellStyle(cellStyle);

                     HSSFCell cellB1 = row5.createCell((short) 3);
                     cellB1.setCellValue("Particulars");
                     cellStyle = workbook.createCellStyle();
                     cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
                     cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                     cellB1.setCellStyle(cellStyle);

                     HSSFCell cellC1 = row5.createCell((short) 4);
                     cellC1.setCellValue("DEBIT");
                     cellStyle = workbook.createCellStyle();
                     cellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
                     cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                     cellC1.setCellStyle(cellStyle);
                                

                     HSSFCell cellD1 = row5.createCell((short) 5);
                     cellD1.setCellValue("CREDIT");
                     cellStyle = workbook.createCellStyle();
                     cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
                     cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                     cellD1.setCellStyle(cellStyle);
                     
                     HSSFCell cellE1 = row5.createCell((short) 6);
                     cellE1.setCellValue("BALANCE");
                     cellStyle = workbook.createCellStyle();
                     cellStyle.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
                     cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                     cellE1.setCellStyle(cellStyle);
                     try{
                     for(int i=0;i<list1.size();i++){
                         CellStyle cs = workbook.createCellStyle();
                               cs.setWrapText(true);
                         Double totalValue = new Double(0);
                         Double item1 = ((Balance) list1.get(i)).getPrate();
                         Double item2 = ((Balance) list1.get(i)).getArate();
                         Double item3 = ((Balance) list1.get(i)).getReff();
                         String ii,ii1 ;
                         if(item1 == null){ii = "";
                          }else{ii =String.valueOf(item1);}
                         String iii ;
                         if(item2 == null){iii = "";}else{iii = String.valueOf(item2);}
                         String iiiv;
                         if(item3 == null){iiiv = "";}else{iiiv = String.valueOf(item3);}
                         HSSFRow row6 = worksheet.createRow((short) 6+i);
                         String par1 =  ((Balance) list1.get(i)).getPar2();
                         if(par1 == null){par1 = "";}else{par1 = ((Balance) list1.get(i)).getPar2(); }
                         row6.setHeightInPoints((2*worksheet.getDefaultRowHeightInPoints()));
                          
                         worksheet.autoSizeColumn(3);
                    HSSFCell  cc1= row6.createCell(2);
                    HSSFCell  cc2= row6.createCell(3);
                    HSSFCell  cc3= row6.createCell(4);
                    HSSFCell  cc4= row6.createCell(5);
                    HSSFCell  cc5= row6.createCell(6);
                    cc1.setCellStyle(cs);
                    cc2.setCellStyle(cs);
                    cc3.setCellStyle(cs);
                    cc4.setCellStyle(cs);
                    cc5.setCellStyle(cs);
                    
                    //cc5.setCellFormula("SUM(G7+Double.valueOf(E)"+su+"+F)");
                    //cc5.setCellFormula(form);
                      cc1.setCellValue(((Balance) list1.get(i)).getDate1() + "\012");
                      cc2.setCellValue(((Balance) list1.get(i)).getPar1() + "\012\t" + par1 );
                      cc3.setCellValue(ii+"\012");
                      cc4.setCellValue(iiiv + "\012" + iii);
                    for(int j = 0; j <= i;j++)
                            {
                      totalValue =totalValue + ((Balance) list1.get(j)).getBal();}
                      cc5.setCellValue(totalValue);
                     
                     }
                     }
                     catch(NullPointerException e)
                             {  
                             e.printStackTrace();
                             }
            HSSFRow row3 = worksheet.createRow((short) list1.size()+6);
            row3.createCell(3).setCellValue("Total");
            int r1= row3.getRowNum();
            System.out.print("r1 = "+r1);
             
             int sss2=list1.size()-1+6;
             String we = workbook.getSheetAt(0).getRow(sss2).getCell(6).toString();
             row3.createCell(4).setCellFormula("SUM(C2:C"+sss2+")");
             row3.createCell(5).setCellFormula("SUM(D2:D"+sss2+")");
             //row3.createCell(6).setCellFormula("SUM(E"+sss + ")");
             row3.createCell(6).setCellValue(we);
             
    FileOutputStream fileOut = new FileOutputStream(file);
             workbook.write(fileOut);
                     fileOut.flush();
                     fileOut.close();
                 } catch (FileNotFoundException e1) {
                     e1.printStackTrace();
                 } catch (IOException e2) {
                     e2.printStackTrace();
                 }    

}


}