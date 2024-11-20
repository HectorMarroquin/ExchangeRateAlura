package com.alura.conversor.factory;

import com.alura.conversor.models.ConversionOption;
import com.alura.conversor.models.Currency;
import com.alura.conversor.models.MenuManager;

public class MenuFactory {

    public static MenuManager createMenuManager(){

        MenuManager menuManager = new MenuManager();

        Currency dolar     = new Currency("Dolar","USD");
        Currency argentino = new Currency("Peso Argentino","ARS");
        Currency brasil    = new Currency("Peso Brasileño","BRL");
        Currency colombia  = new Currency("Peso Colombiano","COP");

        ConversionOption first  = new ConversionOption(dolar,argentino);
        ConversionOption second = new ConversionOption(argentino,dolar);
        ConversionOption third  = new ConversionOption(dolar,brasil);
        ConversionOption fourth = new ConversionOption(brasil,dolar);
        ConversionOption fifth  = new ConversionOption(dolar,colombia);
        ConversionOption sixth  = new ConversionOption(colombia,dolar);

        menuManager.addOption("1", first );
        menuManager.addOption("2", second );
        menuManager.addOption("3", third );
        menuManager.addOption("4", fourth  );
        menuManager.addOption("5", fifth );
        menuManager.addOption("6", sixth );
        menuManager.addOption("6", sixth );

        return menuManager;
    }
}
