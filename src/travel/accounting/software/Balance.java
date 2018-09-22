/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travel.accounting.software;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author gaj
 */
public class Balance {
private final SimpleStringProperty date1 = new SimpleStringProperty();
private final SimpleStringProperty date2 = new SimpleStringProperty();
private final SimpleStringProperty contactt = new SimpleStringProperty();
private final SimpleStringProperty frm11 = new SimpleStringProperty();
private final SimpleStringProperty to11 = new SimpleStringProperty();
//private SimpleObjectProperty date2;
private final StringProperty par1 = new SimpleStringProperty();
private final StringProperty par2 = new SimpleStringProperty();
private final SimpleStringProperty bagn = new SimpleStringProperty();
private final SimpleStringProperty remar = new SimpleStringProperty();
private final ObjectProperty<Double> prate = new SimpleObjectProperty<>();
private final ObjectProperty<Double> arate = new SimpleObjectProperty<>();
private final ObjectProperty<Double> reff = new SimpleObjectProperty<>();
private final SimpleStringProperty tidd = new SimpleStringProperty();;
//private final SimpleStrlingProperty baal;
public ReadOnlyObjectWrapper<Double> bal = new ReadOnlyObjectWrapper<>();

 
public Balance(String date1,String date2, String par1, String par2, Double prate, Double arate, Double reff, String tidd, String bagn, String remar,String contactt, String frm11,String to11){
setDate1(date1);
setDate2(date2);
setPar1(par1);
setPar2(par2);
setPrate(prate);
setArate(arate);
setReff(reff);
setTidd(tidd);
setBagn(bagn);
setRemar(remar);
setContactt(contactt);
setFrm11(frm11);
setTo11(to11);
bal.bind(Bindings.createObjectBinding(()->{
    
    if (prateProperty().get() == null
                        && arateProperty().get() == null && reffProperty().get() !=null) {
                    return 0.0 - reffProperty().get();

    
 }
  if (prateProperty().get() == null
                        && arateProperty().get() == null && reffProperty().get()==null) {
                    return 0.0;

    
 }  
  if (prateProperty().get() == null
                        && reffProperty().get()==null && arateProperty().get()!= null ) {
                    return 0.0 - arateProperty().get();

    
 }  
                 return  prateProperty().get() - arateProperty().get();
 
            },reffProperty(), prateProperty(), arateProperty()));       


}
 public ReadOnlyObjectProperty<Double> balProperty() {
            return this.bal.getReadOnlyProperty();
        }

        public final java.lang.Double getBal() {
            return this.balProperty().get();
        }

public final StringProperty par1Property() {
            return this.par1;
        }

        public final String getPar1() {
            return this.par1Property().get();
        }

        public final void setPar1(final String par1) {
            this.par1Property().set(par1);
        }

public final StringProperty par2Property() {
            return this.par2;
        }

        public final String getPar2() {
            return this.par2Property().get();
        }

        public final void setPar2(final String par2) {
            this.par2Property().set(par2);
        }


public ObjectProperty<Double> prateProperty() {
            return this.prate;
        }

        public Double getPrate() {
            return this.prateProperty().get();
        }

        public final void setPrate(final Double prate) {
            this.prateProperty().set(prate);
        }
        public ObjectProperty<Double> arateProperty() {
            return this.arate;
        }

        public final Double getArate() {
            return this.arateProperty().get();
        }

        public final void setArate(final Double arate) {
            this.arateProperty().set(arate);
        }

public ObjectProperty<Double> reffProperty() {
            return this.reff;
        }

        public final Double getReff() {
            return this.reffProperty().get();
        }

        public final void setReff(final Double reff) {
            this.reffProperty().set(reff);
        }

public final SimpleStringProperty date1Property() {
            return this.date1;
        }

        public final String getDate1() {
            return this.date1Property().get();
        }

        public final void setDate1(final String date1) {
            this.date1Property().set(date1);
        }
        public final SimpleStringProperty date2Property() {
            return this.date2;
        }

        public final String getDate2() {
            return this.date2Property().get();
        }

        public final void setDate2(final String date2) {
            this.date1Property().set(date2);
        }
public final StringProperty tiddProperty() {
            return this.tidd;
        }

        public final String getTidd() {
            return this.tiddProperty().get();
        }

        public final void setTidd(final String tidd) {
            this.tiddProperty().set(tidd);
        }
public final SimpleStringProperty bagnProperty() {
            return this.bagn;
        }

        public final String getBagn() {
            return this.bagnProperty().get();
        }

        public final void setBagn(final String bagn) {
            this.bagnProperty().set(bagn);
        }
        public final SimpleStringProperty remarProperty() {
            return this.remar;
        }

        public final String getRemar() {
            return this.remarProperty().get();
        }

        public final void setRemar(final String remar) {
            this.remarProperty().set(remar);
        }
public final SimpleStringProperty contacttProperty() {
            return this.contactt;
        }

        public final String getContactt() {
            return this.contacttProperty().get();
        }

        public final void setContactt(final String contactt) {
            this.contacttProperty().set(contactt);
        }
public final SimpleStringProperty frm11Property() {
            return this.frm11;
        }

        public final String getFrm11() {
            return this.frm11Property().get();
        }

        public final void setFrm11(final String frm11) {
            this.frm11Property().set(frm11);
        }
public final SimpleStringProperty to11Property() {
            return this.to11;
        }

        public final String getTo11() {
            return this.to11Property().get();
        }

        public final void setTo11(final String to11) {
            this.to11Property().set(to11);
        }        
}
