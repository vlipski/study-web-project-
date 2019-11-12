package by.lipski.gui.display;


import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class DisplayPower extends Button {

    private static int power;

    public DisplayPower(Composite parent, int style) {
        super(parent, style);
        this.setEnabled(false);
        GridData gridData = new GridData(GridData.CENTER, GridData.CENTER, false, false);
        gridData.horizontalSpan = 3;
        gridData.heightHint = 30;
        gridData.widthHint = 100;
//        gridData.grabExcessHorizontalSpace = true;
        this.setLayoutData(gridData);
        this.setText("0");
        DisplayPower.power = 0;
    }

    public static int getPower() {
        return power;
    }

    public static void setPower(int power) {
        DisplayPower.power = power;
    }

    public void setPowerSynchronize(int power) {
        Display.getDefault().syncExec(() -> setText(String.valueOf(power)));
        DisplayPower.power = power;
    }

    @Override
    protected void checkSubclass() {
    }
}
