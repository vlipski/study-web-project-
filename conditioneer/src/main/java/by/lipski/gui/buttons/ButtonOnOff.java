package by.lipski.gui.buttons;



import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;


public class ButtonOnOff extends Button {

    private Color red;
    private Color green;
    private static boolean onOff;
    public ButtonOnOff(Composite parent, int style, Display display) {
        super(parent, style);
        red = new Color(display, 255, 0, 0);
        green = new Color(display, 0, 128, 0);
        this.setText("on/off");
        this.setBackground(red);
        GridData gridData = new GridData(GridData.CENTER, GridData.CENTER, false, false);
        gridData.horizontalSpan = 6;
        gridData.widthHint = 50;
        gridData.grabExcessHorizontalSpace = true;
        this.setLayoutData(gridData);
        this.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {
                if (getBackground().equals(red)) {
                    setBackground(green);
                    onOff = true;
                } else {
                    setBackground(red);
                    onOff = false;
                }
            }
        });
    }


    public static boolean getOnOff() {
        return onOff;
    }

    /*public static void setOnOff(boolean onOff) {
        ButtonOnOff.onOff = onOff;
    }*/

    public  void setOnOffSynchronize(boolean onOff) {
        Display.getDefault().syncExec(() -> {
            if(onOff){
                setBackground(green);
            } else {
                setBackground(red);
            }
        });
        ButtonOnOff.onOff = onOff;
    }

    @Override
    protected void checkSubclass() {
    }
}
