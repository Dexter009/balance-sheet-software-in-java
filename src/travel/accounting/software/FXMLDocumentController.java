/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travel.accounting.software;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import travel.accounting.database.DatabaseHandler;

/**
 *
 * @author gaj
 */
public class FXMLDocumentController implements Initializable {
    
    PreparedStatement pst; 
    ObservableList<Balance> list = FXCollections.observableArrayList();
    ObservableList<Balance> llist = FXCollections.observableArrayList();
    ObservableList<Balance> lllist = FXCollections.observableArrayList();
    @FXML
    private TableView<Balance> tableView;

    @FXML
    private TableColumn<Balance, String> Bdate;
    @FXML
    private TableColumn<Balance, Number> ttid;
    @FXML
    private TableColumn<Balance, String> Bpart;
    @FXML
    private TableColumn<Balance, Double> Bdeb;
    @FXML
    private TableColumn<Balance, String> Bcre;
    @FXML
    private TableColumn<Balance, Double> Balan;
    @FXML
    private TableColumn<Balance, String> Remarks;
    @FXML
    private TableColumn<Balance, String> ServedBy;
    @FXML
    private TableColumn<Balance, Double> Cnt;
    @FXML
    private TableColumn<Balance, String> frm111;
    @FXML
    private TableColumn<Balance, String> to111;
    @FXML
    private TableColumn<Balance, String> fdate;
    @FXML
    private AnchorPane s1;
    @FXML
    private AnchorPane pp;
    @FXML
    private AnchorPane s2;
    @FXML
    private TextField tid;
    @FXML
    private CheckBox cancell;
    @FXML
    private TextField refund;
    @FXML
    private TextField pn;
    @FXML
    private TextField contact;
    @FXML
    private TextField from;
    @FXML
    private TextField rate1;
    @FXML
    private TextField dest;
    @FXML
    private TextField payment;
    @FXML
    private TextField total;
    @FXML
    private TextField ba;
    @FXML
    private TextField debtotal;
    @FXML
    private TextField bag;
    @FXML
    private TextField arate;
    @FXML
    private TextField rem;
    @FXML
    private Button mt;
    @FXML
    private Button balload;
    
    @FXML
    private Button ct;
    @FXML
    private Button print;
    @FXML
    private Button report;
    @FXML
    private DatePicker dop;
    @FXML
    private DatePicker doi;
    @FXML
    private DatePicker datefrom;
    @FXML
    private DatePicker dateto;
    
DatabaseHandler databaseHandler;

@Override
public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //tid.setText("1");
        
databaseHandler = DatabaseHandler.getInstance();
initCol();
loadData();
//lloadData();
refund.setDisable(true);
total.setDisable(true);
dbexcel exxi = new dbexcel();
exxi.methodd2();
    }

@FXML
private void checkselected(ActionEvent event){
refund.setDisable(false);
    //s2.setDisable(true);
arate.setDisable(true);
ba.setDisable(true);
payment.setDisable(true);
rate1.setDisable(true);
if(!cancell.isSelected()){
refund.setDisable(true);
arate.setDisable(false);
ba.setDisable(false);
payment.setDisable(false);
rate1.setDisable(false);
}
}
@FXML
private void clear(ActionEvent event) {
        pn.setText("");
        payment.setText("");
        ba.setText("");
        arate.setText("");    
        rate1.setText("");
        rem.setText("");
((TextField)doi.getEditor()).setText("");
((TextField)dop.getEditor()).setText("");
contact.setText("");
        //String tranid = tid.getText();
from.setText("");
dest.setText("");
bag.setText("");
refund.setText("");}    
    
    @FXML
    private void addBalance(ActionEvent event) {
        String passengername = pn.getText();
        String passengerpay = payment.getText();
        String brokeragent = ba.getText();
        String brokerrate = arate.getText();    
        String passengerrate = rate1.getText();
        String remarks = rem.getText();
        String di = ((TextField)doi.getEditor()).getText();
        String dp = ((TextField)dop.getEditor()).getText();
        String phone = contact.getText();
        String tranid = tid.getText();
        String start = from.getText();
        String to = dest.getText();
        String bagn = bag.getText();
        String ref = refund.getText();

            String qu = "INSERT INTO BALANCE(pname,ppay,ba,br,pr,rem,pnh,source,destiny,di,dp,bag,refu)"+ "VALUES ("
                    + "'" +	passengername + "',"
                    + "'" +	passengerpay + "',"
                    + "'" +	brokeragent + "',"
                    + "'" +	brokerrate + "',"
                    + "'" +	passengerrate + "',"
                    + "'" +	remarks + "',"
                    + "'" +	phone + "',"
                    + "'" +	start + "',"
                    + "'" +	to + "',"
                    + "'" +	di + "',"
                    + "'"+	dp + "',"
                    + "'"+	bagn + "',"
                    + "'"+	ref + "'"
                + ")";
            String qu1 = "INSERT INTO BALANCE1(transidd,pnamee,ppayy,baa,brr,prr,remm,pnhh,sourcee,destinyy,dii,dpp,bagg,refuu)"+ "VALUES ("
                    + "'" +	tranid + "',"
                    + "'" +	passengername + "',"
                    + "'" +	passengerpay + "',"
                    + "'" +	brokeragent + "',"
                    + "'" +	brokerrate + "',"
                    + "'" +	passengerrate + "',"
                    + "'" +	remarks + "',"
                    + "'" +	phone + "',"
                    + "'" +	start + "',"
                    + "'" +	to + "',"
                    + "'" +	di + "',"
                    + "'"+	dp + "',"
                    + "'"+	bagn + "',"
                    + "'"+	ref + "'"
                + ")";
            if (databaseHandler.execAction(qu) && databaseHandler.execAction(qu1)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Success");
            alert.showAndWait();
        } else //Error
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed");
            alert.showAndWait();
        }  
        llist.clear();

        
    initCol();
    loadData();
//    lloadData();
    }
        private void initCol() {
        Bdate.setCellValueFactory(new PropertyValueFactory<>("date2"));
        fdate.setCellValueFactory(new PropertyValueFactory<>("date1"));
        Remarks.setCellValueFactory(new PropertyValueFactory<>("remar"));
        ServedBy.setCellValueFactory(new PropertyValueFactory<>("bagn"));
        Cnt.setCellValueFactory(new PropertyValueFactory<>("contactt"));
        frm111.setCellValueFactory(new PropertyValueFactory<>("frm11"));
        to111.setCellValueFactory(new PropertyValueFactory<>("to11"));
        ttid.setCellValueFactory(new PropertyValueFactory<>("tidd"));
        Bpart.setCellValueFactory((TableColumn.CellDataFeatures<Balance, String> b) -> new SimpleStringProperty(b.getValue().getPar1()
                + "\n\t " + b.getValue().getPar2()));
        
        Bdeb.setCellValueFactory(new PropertyValueFactory<>("prate"));
//     Bcre.setCellValueFactory(new PropertyValueFactory<>("refu" +"\n" + "agrte"));
   Bcre.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Balance, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Balance, String> b) {
 String n3,n4;
                Double n1= b.getValue().getReff();

 if(n1 == null){ n3 = "";}else{n3 = b.getValue().getReff().toString();}
  Double n2 = b.getValue().getArate();
 if(n2 == null){ n4 = "";}else{n4 = b.getValue().getArate().toString(); }
  return new SimpleStringProperty(""+n3
                        + "\n " + n4);}
              
            
        });
 
 Balan.setCellValueFactory((TableColumn.CellDataFeatures<Balance, Double> b) -> new SimpleObjectProperty (b.getValue().getBal()));
        Balan.setCellFactory((TableColumn<Balance, Double> b) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    
                    if (this.getTableRow() != null && item != null) {
                             int current = this.getTableRow().getIndex();
                            int pre = this.getTableRow().getIndex() - 1;
                            if(current == 0) {
                                pre = current;
                            }
                  
                            Double totalValue = new Double(0);
             
                            for(int i = 0; i <= current;i++)
                            {
                                  totalValue =totalValue + tableView.getItems().get(i).getBal();
                                
                             System.out.println(totalValue);
                            }     

                            setText(String.valueOf(totalValue));


//                            setText(this.getTableRow().getItem().toString());
                      } else {
                            setText("");
                        }
                                   }};
        });
       
        }
        
        private void loadData() {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String qu = "SELECT * FROM BALANCE";
        ResultSet rs = handler.execQuery(qu);
        try {
            while (rs.next()) {
              
                String date1 = rs.getString("dp");
                String date2 = rs.getString("di");
                String par1 = rs.getString("pname");
                String par2 = rs.getString("ba");
                String prate = rs.getString("pr");
                String agrate = rs.getString("br");
                String reff = rs.getString("refu");
                String bagen = rs.getString("bag");
                String remar = rs.getString("rem");
                String contactt = rs.getString("pnh");
                String frm11 = rs.getString("source");
                String to11 = rs.getString("destiny");
                int tiidd = rs.getInt("transid");
                String ttidd = Integer.toString(tiidd);
                int tidd = tiidd + 1;
                System.out.print("tidd ="+tidd);
                String tiddd = Integer.toString(tidd);
                tid.setText(tiddd);
                if("".equals(date1)){date1 = date2;}
               Double d1,d2,d3;
                if("".equals(prate)){d1 = null;}
                else{d1 = Double.valueOf(prate);}
                if("".equals(agrate)){d2 = null;}else{d2 =Double.valueOf(agrate);}
                if("".equals(reff)){d3 = null;}else{d3 = Double.valueOf(reff);}
                
                list.add(new Balance(date1,date2, par1,par2 ,d1, d2,d3,ttidd,bagen,remar,contactt,frm11,to11));
                System.out.print("list is"+list);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView.setItems(new LineItemListWithTotal(list));

    
        }

        private void lloadData() {
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String qu = "SELECT * FROM BALANCE1";//,BALANCE";
        ResultSet rs = handler.execQuery(qu);
        
        try {
            while (rs.next()) {
              
                String date1 = rs.getString("dpp");
                String date2 = rs.getString("dii");
                String par1 = rs.getString("pnamee");
                String par2 = rs.getString("baa");
                String prate = rs.getString("prr");
                String agrate = rs.getString("brr");
                String reff = rs.getString("refuu");
                String bagen = rs.getString("bagg");
                String remar = rs.getString("remm");
                String contactt = rs.getString("pnhh");
                String frm11 = rs.getString("sourcee");
                String to11 = rs.getString("destinyy");
                String ttidd = rs.getString("transidd");
                //String ttidd = Integer.toString(tiidd);
               /* int tidd = tiidd + 1;
                System.out.print("tidd ="+tidd);
                String tiddd = Integer.toString(tidd);*/
                //String ttidd = tid.getText();
               Double d1,d2,d3;
                if("".equals(prate)){d1 = null;}
                else{d1 = Double.valueOf(prate);}
                if("".equals(agrate)){d2 = null;}else{d2 =Double.valueOf(agrate);}
                if("".equals(reff)){d3 = null;}else{d3 = Double.valueOf(reff);}
                
                llist.add(new Balance(date1,date2,par1,par2 ,d1, d2,d3,ttidd,bagen,remar,contactt,frm11,to11));
                System.out.print("list is"+list);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView.setItems(new LineItemListWithTotal(llist));
        }
        @FXML
        private void llloadData(ActionEvent event) {
           list.clear();
           lllist.clear();
        String datefromm = ((TextField)datefrom.getEditor()).getText();
        String datetoo = ((TextField)dateto.getEditor()).getText();
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String qu = "SELECT * FROM BALANCE WHERE dp BETWEEN'" + datefromm + "'AND'" + datetoo+"'";
        ResultSet rs = handler.execQuery(qu);
        try {

            while (rs.next()) {
              
                String date1 = rs.getString("dp");
                String date2 = rs.getString("di");
                String par1 = rs.getString("pname");
                String par2 = rs.getString("ba");
                String prate = rs.getString("pr");
                String agrate = rs.getString("br");
                String reff = rs.getString("refu");
                String bagen = rs.getString("bag");
                String remar = rs.getString("rem");
                String contactt = rs.getString("pnh");
                String frm11 = rs.getString("source");
                String to11 = rs.getString("destiny");
                int tiidd = rs.getInt("transid");
                String ttidd = Integer.toString(tiidd);
                /*int tidd = tiidd + 1;
                System.out.print("tidd ="+tidd);
                String tiddd = Integer.toString(tidd);
                tid.setText(tiddd);*/
               Double d1,d2,d3;
                if("".equals(prate)){d1 = null;}
                else{d1 = Double.valueOf(prate);}
                if("".equals(agrate)){d2 = null;}else{d2 =Double.valueOf(agrate);}
                if("".equals(reff)){d3 = null;}else{d3 = Double.valueOf(reff);}
                
                lllist.add(new Balance(date1,date2, par1,par2 ,d1, d2,d3,ttidd,bagen,remar,contactt,frm11,to11));
                System.out.print("list is"+lllist);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView.setItems(new LineItemListWithTotal(lllist));
         ObservableList list1 = FXCollections.observableArrayList();    
    list1 = tableView.getItems();
FileChooser fileChooser = new FileChooser();

              //Set extension filter
              FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("excel files (*.xls)", "*.xls");
              fileChooser.getExtensionFilters().add(extFilter);

              //Show save file dialog
              File file = fileChooser.showSaveDialog(tableView.getScene().getWindow());

              if(file != null){
    excell ex = new excell();
ex.methodd1(list1,lllist,file);
                  System.out.println("hi");
                  
              }
        
        }
        @FXML
    private void generateSalesReceipt(ActionEvent event) throws JRException {
        String reportLocale = "src/PassengerReceipt.jrxml";
        //Put parameters in hashmap

        HashMap params = new HashMap();
        params.put("_pname", pn.getText());
        params.put("_from", from.getText());
        params.put("_to", dest.getText());
        if(!rate1.getText().isEmpty()){
        params.put("_rate", rate1.getText());}
        if(!payment.getText().isEmpty()){
        params.put("_pay",payment.getText());}
        if(!refund.getText().isEmpty()){
        params.put("_refun", refund.getText());}
        params.put("_fd", ((TextField)dop.getEditor()).getText());
        params.put("_phn", contact.getText());
        params.put("_rem", rem.getText());        
        params.put("_bag", bag.getText());        
        params.put("_tid", tid.getText());        
        



InputStream is = this.getClass().getResourceAsStream("/view/PassengerReceipt.jrxml");
if(is == null){
Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Success");
            alert.showAndWait();
}

// File file =   new java.io.File(reportLocale);  
  JasperDesign jpd = JRXmlLoader.load(is);
JasperReport jr = JasperCompileManager.compileReport(jpd);
  //URL url=FXMLDocumentController.class.getClassLoader().getResource("PassengerReceipt.jrxml");
//System.out.Println(""+url);
  JasperPrint jp = JasperFillManager.fillReport(jr, params, new JREmptyDataSource());
        //JasperExportManager.exportReportToPdfStream(jp);
        JasperViewer.viewReport(jp, false);

    }

@FXML
private void Print(ActionEvent event){

 Printer printer = Printer.getDefaultPrinter(); //get the default printer
    javafx.print.PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT); //create a pagelayout.  I used Paper.NA_LETTER for a standard 8.5 x 11 in page.
    PrinterJob job = PrinterJob.createPrinterJob();//create a printer job

    if(job.showPrintDialog(tableView.getScene().getWindow()))// this is very useful it allows you to save the file as a pdf instead using all of your printer's paper. A dialog box pops up, allowing you to change the "name" option from your default printer to Adobe pdf. 
    {
        double pagePrintableWidth = pageLayout.getPrintableWidth(); //this should be 8.5 inches for this page layout.
        double pagePrintableHeight = pageLayout.getPrintableHeight();// this should be 11 inches for this page layout.


        tableView.prefHeightProperty().bind(Bindings.size(tableView.getItems()).multiply(35));// If your cells' rows are variable size you add the .multiply and play with the input value until your output is close to what you want. If your cells' rows are the same height, I think you can use .multiply(1). This changes the height of your tableView to show all rows in the table.
        tableView.minHeightProperty().bind(tableView.prefHeightProperty());//You can probably play with this to see if it's really needed.  Comment it out to find out.
        tableView.maxHeightProperty().bind(tableView.prefHeightProperty());//You can probably play with this to see if it' really needed.  Comment it out to find out.

        double scaleX = pagePrintableWidth / tableView.getBoundsInParent().getWidth();//scaling down so that the printing width fits within the paper's width bound.
        double scaleY = scaleX; //scaling the height using the same scale as the width. This allows the writing and the images to maintain their scale, or not look skewed.
        double localScale = scaleX; //not really needed since everything is scaled down at the same ratio. scaleX is used thoughout the program to scale the print out.

        double numberOfPages = Math.ceil((tableView.getPrefHeight() * localScale) / pagePrintableHeight);//used to figure out the number of pages that will be printed.



        //System.out.println("pref Height: " + tblvMain.getPrefHeight());
        //System.out.println("number of pages: " + numberOfPages);


        tableView.getTransforms().add(new Scale(scaleX, (scaleY)));//scales the printing. Allowing the width to say within the papers width, and scales the height to do away with skewed letters and images.
        tableView.getTransforms().add(new Translate(0, 0));// starts the first print at the top left corner of the image that needs to be printed

        //Since the height of what needs to be printed is longer than the paper's heights we use gridTransfrom to only select the part to be printed for a given page.
        Translate gridTransform = new Translate();
        tableView.getTransforms().add(gridTransform);

        //now we loop though the image that needs to be printed and we only print a subimage of the full image.
        //for example: In the first loop we only pint the printable image from the top down to the height of a standard piece of paper. Then we print starting from were the last printed page ended down to the height of the next page. This happens until all of the pages are printed. 
        // first page prints from 0 height to -11 inches height, Second page prints from -11 inches height to -22 inches height, etc. 
        for(int i = 0; i < numberOfPages; i++)
        {
            gridTransform.setY(-i * (pagePrintableHeight / localScale));
            job.printPage(pageLayout, tableView);
        }

        job.endJob();//finally end the printing job.

}
}
@FXML
private void Something(ActionEvent event){
    total.setDisable(false);
    FilteredList<Balance> filteredData = new FilteredList<>(list, p -> true);    
    // 2. Set the filter Predicate whenever the filter changes.
		total.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (person.getPar1().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} else if (person.getPar2().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Balance> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableView.setItems(sortedData);
    
}
@FXML
private void Refresh(ActionEvent event){
    list.clear();
    //llist.clear();
    //lllist.clear();
    total.setText("");
    total.setDisable(true);
    initCol();
    loadData();        
tableView.setItems(new LineItemListWithTotal(list));
datefrom.getEditor().clear();
dateto.getEditor().clear();

}

@FXML
private void excelling(ActionEvent event){
ObservableList list1 = FXCollections.observableArrayList();    
    list1 = tableView.getItems();
    System.out.println("list1 is"+ list1);
    if(total.getText()!=""){
    FileChooser fileChooser = new FileChooser();

              //Set extension filter
              FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("excel files (*.xls)", "*.xls");
              fileChooser.getExtensionFilters().add(extFilter);

              //Show save file dialog
              File file = fileChooser.showSaveDialog(tableView.getScene().getWindow());

              if(file != null){
    excell ex = new excell();
ex.methodd2(list1,file);
                  System.out.println("ola");
                  
              }
    
    
    }else{
FileChooser fileChooser = new FileChooser();

              //Set extension filter
              FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("excel files (*.xls)", "*.xls");
              fileChooser.getExtensionFilters().add(extFilter);

              //Show save file dialog
              File file = fileChooser.showSaveDialog(tableView.getScene().getWindow());

              if(file != null){
    excell ex = new excell();
ex.methodd1(list1,list,file);
                  System.out.println("hi");
                  
              }
DatabaseHandler handler = DatabaseHandler.getInstance();
        String qu2 = "DROP TABLE BALANCE1";
             handler.execAction(qu2);
}}
}
