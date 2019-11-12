package by.lipski.gui.display;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class DisplayTemperature extends Button {

    private static int temperature;

    public DisplayTemperature(Composite parent, int style) {
        super(parent, style);
        this.setEnabled(false);
        GridData gridData = new GridData(GridData.CENTER, GridData.CENTER, false, false);
        gridData.horizontalSpan = 3;
        gridData.heightHint = 30;
        gridData.widthHint = 100;
//        gridData.grabExcessHorizontalSpace = true;
        this.setLayoutData(gridData);
        this.setText("22");
    }

    public static int getTemperature() {
        return temperature;
    }

    public static void setTemperature(int temperature) {
        DisplayTemperature.temperature = temperature;
    }

    public void setTemperatureSynchronize(int temperature) {
        Display.getDefault().syncExec(() -> setText(String.valueOf(temperature)));
        DisplayTemperature.temperature = temperature;
    }

    @Override
    protected void checkSubclass() {
    }
}
