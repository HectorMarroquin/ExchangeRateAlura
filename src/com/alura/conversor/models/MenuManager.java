package com.alura.conversor.models;

import java.util.HashMap;
import java.util.Map;

public class MenuManager {

    private Map<String, ConversionOption> options;

    public MenuManager() {
        this.options = new HashMap<>();
    }

    public void addOption(String key, ConversionOption option){
        options.put(key, option);
    }

    public ConversionOption getOption( String key){
        return options.get(key);
    }

    public String generateMenu(){

        StringBuilder menu = new StringBuilder();
        menu.append("*********************************************************\n");

        for (Map.Entry<String, ConversionOption> entry : options.entrySet()) {
            String key = entry.getKey();
            ConversionOption option = entry.getValue();

            menu.append(key).append(") ")
                    .append(option.getFromCurrency().getName()).append(" =>> ")
                    .append(option.getToCurrency().getName()).append("\n");
        }

        menu.append("X) Salir\n");
        menu.append("Elija una opción válida:\n");
        menu.append("*********************************************************");
        return menu.toString();

    }


}
