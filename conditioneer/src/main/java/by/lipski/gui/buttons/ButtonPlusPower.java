package by.lipski.gui.buttons;

import by.lipski.gui.display.DisplayPower;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import static by.lipski.gui.buttons.ButtonOnOff.getOnOff;


public class ButtonPlusPower extends Button {
    private int value;

    public ButtonPlusPower(Composite parent, int style, Button displayPower) {
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
                    value = Integer.parseInt(displayPower.getText());
                    if (value < 10) {
                        value++;
                        displayPower.setText(String.valueOf(value));
                        DisplayPower.setPower(value);
                    }
                }
            }
        });
    }

    @Override
    protected void checkSubclass() {
    }
}
