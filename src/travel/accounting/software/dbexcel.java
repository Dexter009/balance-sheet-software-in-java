/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travel.accounting.software;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import travel.accounting.database.DatabaseHandler;

/**
 *
 * @author gaj
 */
public class dbexcel {
public void methodd2(){
 try{
 
      FileOutputStream fileOut = new FileOutputStream("User_details.xls");
                     HSSFWorkbook wb = new HSSFWorkbook();
                     HSSFSheet sheet = wb.createSheet("User_details");
     

                HSSFRow header = sheet.createRow(0);

                header.createCell(0).setCellValue("Date");

                header.createCell(1).setCellValue("Transaction ID");

                header.createCell(2).setCellValue("Particulars");

                header.createCell(3).setCellValue("Debit");

                header.createCell(4).setCellValue("Credit");

                header.createCell(5).setCellValue("Balance");
DatabaseHandler handler = DatabaseHandler.getInstance();
        String quer = "SELECT * FROM BALANCE";
        ResultSet rs = handler.execQuery(quer);
        int index = 1;
        try {
            while (rs.next()) {
HSSFRow row = sheet.createRow(index);
HSSFCellStyle cs = wb.createCellStyle();
cs.setWrapText(true);
row.setHeightInPoints(2*sheet.getDefaultRowHeightInPoints());
                         sheet.autoSizeColumn(2);
                    row.createCell(0).setCellValue(rs.getString("dp") + "\012" + "");

                    row.createCell(1).setCellValue(rs.getString("transid"));

            HSSFCell cc3= row.createCell(2);
                    cc3.setCellValue(rs.getString("pname") + "\012" + rs.getString("ba"));
                    cc3.setCellStyle(cs);
                      
                    row.createCell(3).setCellValue(rs.getString("pr") + "\012");
                HSSFCell cc4= row.createCell(4);
                    cc4.setCellValue(rs.getString("br") + "\012" + rs.getString("refu"));
                    cc4.setCellStyle(cs);
                   HSSFCell cc5 = row.createCell(5);
                  // cc5.setCellFormula(quer);
                   if(index == 1){
                   
                   }
                   index++;                   

                
}} catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }    



                wb.write(fileOut);
                fileOut.flush();
                fileOut.close();
                System.out.println("File created successfully");
}catch (FileNotFoundException e1) {
                     e1.printStackTrace();
                 } 
 catch (IOException e2) {
                     e2.printStackTrace();
                 }    
}



}