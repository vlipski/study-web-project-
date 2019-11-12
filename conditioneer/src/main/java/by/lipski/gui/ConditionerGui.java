package by.lipski.gui;

import by.lipski.gui.buttons.*;
import by.lipski.gui.display.DisplayPower;
import by.lipski.gui.display.DisplayTemperature;
import by.lipski.socket.SocketIn;
import by.lipski.socket.SocketOut;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class ConditionerGui {
    private static Display display = null;
    private static Shell shell = null;
    private ButtonOnOff buttonOnOff;
    private DisplayTemperature displayTemperature;
    private DisplayPower displayPower;


    private ConditionerGui() {
    }

    // необходим для выполнения тестов
    public ConditionerGui(Display display, Shell shell) {
        ConditionerGui.display = display;
        ConditionerGui.shell = shell;
    }

    public static void main(String[] args) {
        display = new Display();
        shell = new Shell(display);
        ConditionerGui conditionerGui = new ConditionerGui();
        conditionerGui.createGUI();
        shell.pack();
        //открывает графическое окно
        shell.open();
        //запускает поток отправки сигнала с данными о работе кондиционера
        Thread threadOut = new SocketOut(conditionerGui);
        threadOut.start();
        Thread threadIn = new  SocketIn(conditionerGui);
        threadIn.start();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    //создает графическое окно с кнопочками
    public void createGUI() {
        shell.setSize(300, 300);
        shell.setText("CONDITIONER0001");
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 6;
        shell.setLayout(gridLayout);

        Label temperature = new Label(shell, SWT.NONE);
        temperature.setText("TEMPERATURE");
        GridData gridData = new GridData(GridData.CENTER, GridData.CENTER, false, false);
        gridData.horizontalSpan = 3;
        gridData.grabExcessHorizontalSpace = true;
        temperature.setLayoutData(gridData);

        Label power = new Label(shell, SWT.NONE);
        power.setText("POWER");
        gridData = new GridData(GridData.CENTER, GridData.CENTER, false, false);
        gridData.horizontalSpan = 3;
        gridData.grabExcessHorizontalSpace = true;
        power.setLayoutData(gridData);

        displayTemperature = new DisplayTemperature(shell, SWT.BUTTON2);
        displayPower = new DisplayPower(shell, SWT.BUTTON2);
        new ButtonPlusTemperature(shell, SWT.PUSH, displayTemperature);
        new ButtonPlusPower(shell, SWT.PUSH, displayPower);
        new ButtonMinusTemperature(shell, SWT.PUSH, displayTemperature);
        new ButtonMinusPower(shell, SWT.PUSH, displayPower);
        buttonOnOff = new ButtonOnOff(shell, SWT.TOGGLE, display);

    }

    public ButtonOnOff getButtonOnOff() {
        return buttonOnOff;
    }

   /* public void setButtonOnOff(ButtonOnOff buttonOnOff) {
        this.buttonOnOff = buttonOnOff;
    }*/

    public DisplayTemperature getDisplayTemperature() {
        return displayTemperature;
    }

    /*public void setDisplayTemperature(DisplayTemperature displayTemperature) {
        this.displayTemperature = displayTemperature;
    }*/

    public DisplayPower getDisplayPower() {
        return displayPower;
    }
    /*public void setDisplayPower(DisplayPower displayPower) {
        this.displayPower = displayPower;
    }*/
}
