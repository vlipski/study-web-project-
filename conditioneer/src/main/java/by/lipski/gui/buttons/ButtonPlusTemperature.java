package by.lipski.gui.buttons;

import by.lipski.gui.display.DisplayTemperature;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import static by.lipski.gui.buttons.ButtonOnOff.getOnOff;


public class ButtonPlusTemperature extends Button {

    private int value;

    public ButtonPlusTemperature(Composite parent, int style, Button displayTemperature) {
        super(parent, style);
        this.setText("+");
        GridData gridData = new GridData(GridData.CENTER, GridData.CENTER, false, false);
        gridData.horizontalSpan = 3;
        gridData.widthHint = 50;
        gridData.grabExcessHorizontalSpace = true;
        this.setLayoutData(gridData);
        this.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {
                if (getOnOff()) {
                    value = Integer.parseInt(displayTemperature.getText());
                    if (value < 35) {
                        value++;
                        displayTemperature.setText(String.valueOf(value));
                        DisplayTemperature.setTemperature(value);
                    }
                }
            }
        });
    }

    @Override
    protected void checkSubclass() {
    }
}
