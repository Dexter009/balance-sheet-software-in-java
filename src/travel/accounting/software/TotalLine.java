/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travel.accounting.software;

import java.util.stream.Collectors;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;

/**
 *
 * @author gaj
 */
public class TotalLine extends Balance {
//private final ReadOnlyObjectWrapper<Double> bal = new ReadOnlyObjectWrapper<>(); 

    public TotalLine(ObservableList<? extends Balance> items) {
        super(null, null,"Total", "",null,null,null,null,null,null,null,null,null);

             /*   bal.bind(Bindings.createObjectBinding(() -> 
                items.stream().collect(Collectors.summingDouble(Balance::getBal)), items));
*/
    }

    @Override
    public ReadOnlyObjectProperty<Double> balProperty() {
        return bal;
    }
@Override
 public Double getPrate() {
            return prateProperty().get();
        }

}
