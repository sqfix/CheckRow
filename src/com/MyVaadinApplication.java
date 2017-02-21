package com;

import Connection.ConnectionProperties;
import Connection.SettReader;
import com.vaadin.data.Property;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;

import java.util.Formatter;
import java.util.HashSet;

/**
 * Created by Makc on 19.02.2017.
 */
public class MyVaadinApplication extends UI {
    private GridLayout  layout;
    private HashSet<CheckBox> checkBoxes = new HashSet<>();
    private ConnectionProperties properties = new ConnectionProperties();

    @Override
    public void init(VaadinRequest request) {
        /** ДОСТАЁМ РАЗМЕРНОСТЬ ТАБЛИЦЫ*/
        int [] size = new SettReader().readFile();


        /** СОЗДАЁМ ВИДИМОСТЬ или как его там....*/
        layout = new GridLayout(size[0], size[1]);
        //GridLayout  layout = new GridLayout(2, 2);
        setContent(layout);

        /** КОЛОНКИ ЭТО ВЕРТИКАЛЬ НО ?:??? */
        for(int i = 0; i < layout.getColumns(); i++)
        {
            for(int j = 0; j < layout.getRows(); j++)
            {
                layout.addComponent(createCheckBox(), i,  j);
            }
        }
        /** Инициализируем данными из таблицы*/
        for(CheckBox box : checkBoxes)
        {
            box.setValue(properties.getValueByValue(layout.getComponentArea(box)));
        }
    }

    public Button createButton(){
        Button button = new Button("PUTIN");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                new ConnectionProperties().testDB();
            }
        });
        return button;
    }

    public CheckBox createCheckBox()
    {
        CheckBox checkBox = new CheckBox();
        checkBox.setValue(false);
        checkBox.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                System.out.println("~~~~~~ITS WORK~~~~~~~");
                update(checkBox);
            }
        });
        checkBoxes.add(checkBox);
        return checkBox;
    }

    public void update(CheckBox checkBox)  {
        checkBox.getTabIndex();
        GridLayout.Area area = layout.getComponentArea(checkBox);
        int X = area.getColumn1();
        int Y = area.getRow1();
        /** ЗАПИСЬ В ТАБЛИЦУ*/
        properties.updateValues(X, Y, checkBox.getValue());

        /**Крайние боксы отвечают за установку всех флажков в ряд / колонку*/
        if(checkBox.getValue()) {
            if (!(X == 0 && Y == 0)) {
                if ((X == 0 || Y == 0)) {
                    if (X == 0) {
                        for (CheckBox ch : checkBoxes) {
                            if (layout.getComponentArea(ch).getRow1() == Y)
                                ch.setValue(checkBox.getValue());
                        }
                    } else {
                        for (CheckBox ch : checkBoxes) {
                            if (layout.getComponentArea(ch).getColumn1() == X)
                                ch.setValue(checkBox.getValue());
                        }
                    }
                }
            }
        }

        /**ВЫДЕЛЕНИЕ ВСЕХ ЭЛЕМЕНТОВ*/
        if(X==0&&Y==0){
            for(CheckBox ch : checkBoxes)
            {
                ch.setValue(checkBox.getValue());
            }
        }

       /* /**Если все элементы в ряд выделены - крайний бокс должен быть активен
        boolean allTrueW = true;
        boolean allTrueH = true;
        for(CheckBox ch : checkBoxes)
        {
            if(layout.getComponentArea(ch).getColumn1() == X)
            {
                if(!ch.getValue())
                    allTrueW = false;
            }
            if(layout.getComponentArea(ch).getRow1() == Y)
            {
                if(!ch.getValue())
                    allTrueH = false;
            }
        }*/

        /**Устанавливаем крайним элементам значения allTrueW, allTrueН*/
        /** Если один из чекбоксов в колонке не активен - знач. крайнего = false*/
        if(!checkBox.getValue())
        {
            for(CheckBox ch : checkBoxes)
            {
                if(layout.getComponentArea(ch).getColumn1() == X && layout.getComponentArea(ch).getRow1() == 0)
                    ch.setValue(false);
                if(layout.getComponentArea(ch).getRow1() == Y && layout.getComponentArea(ch).getColumn1() == 0)
                    ch.setValue(false);
            }
        }

        System.out.println(new Formatter().format("row 1: %d\n column 1: %d",
                X, Y));
    }
}