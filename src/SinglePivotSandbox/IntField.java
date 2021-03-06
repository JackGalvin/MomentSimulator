package SinglePivotSandbox;
import javafx.beans.property.*;
import javafx.beans.value.*;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.*;

    // IntField subClass/extenstion of textfield taken from (https://gist.github.com/jewelsea/1962045)

   // helper text field subclass which restricts text input to a given range of natural int numbers
   // and exposes the current numeric int value of the edit box as a value property.
class IntField extends TextField {
    final private IntegerProperty value;
    final private double minValue; //changed data type int>double
    final private double maxValue; //changed data type int>double

    // expose an integer value property for the text field.
    public double  getValue()                 { return value.getValue(); } //changed data type int>double
    public void setValue(double newValue)     { value.setValue(newValue); }
    public IntegerProperty valueProperty() { return value; }
    
    IntField(double minValue, double maxValue, int initialValue) { //changed data type int>double (initial value stays an int)
    if (minValue > maxValue) 
        throw new IllegalArgumentException(
        "IntField min value " + minValue + " greater than max value " + maxValue
        );
    if (maxValue < minValue) 
        throw new IllegalArgumentException(
        "IntField max value " + minValue + " less than min value " + maxValue
        );
    if (!((minValue <= initialValue) && (initialValue <= maxValue))) 
        throw new IllegalArgumentException(
        "IntField initialValue " + initialValue + " not between " + minValue + " and " + maxValue
        );

    // initialize the field values.
    this.minValue = minValue;
    this.maxValue = maxValue;
    value = new SimpleIntegerProperty(initialValue); 
    setText(initialValue + "");

    final IntField intField = this;

    // make sure the value property is clamped to the required range
    // and update the field's text to be in sync with the value.
    value.addListener(new ChangeListener<Number>() {
        @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
        if (newValue == null) {
            intField.setText("");
        } else {
            if (newValue.intValue() < intField.minValue) {
            value.setValue(intField.minValue);
            return;
            }

            if (newValue.intValue() > intField.maxValue) {
            value.setValue(intField.maxValue);
            return;
            }

            if (newValue.intValue() == 0 && (textProperty().get() == null || "".equals(textProperty().get()))) {
            // no action required, text property is already blank, we don't need to set it to 0.
            } else {
            intField.setText(newValue.toString());
            }
        }
        }
    });

    // restrict key input to numerals.
    this.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
        @Override public void handle(KeyEvent keyEvent) {
        if (!"0123456789".contains(keyEvent.getCharacter())) {
            keyEvent.consume();
        }
        }
    });
    
    // ensure any entered values lie inside the required range.
    this.textProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
        if (newValue == null || "".equals(newValue)) {
            value.setValue(0);
            return;
        }

        final double intValue = Integer.parseInt(newValue); //changed data type int>double

        if (intField.minValue > intValue || intValue > intField.maxValue) {
            textProperty().setValue(oldValue);
        }
        
        value.set(Integer.parseInt(textProperty().get()));
        }
    });
    }
}