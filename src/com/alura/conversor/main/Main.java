package com.alura.conversor.main;

import com.alura.conversor.exceptions.ConversionServiceException;
import com.alura.conversor.models.ConversionOption;
import com.alura.conversor.models.ConversionResult;
import com.alura.conversor.models.Currency;
import com.alura.conversor.models.MenuManager;
import com.alura.conversor.services.CurrencyConversionService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MenuManager menuManager = new MenuManager();

        Currency dolar     = new Currency("Dolar","USD");
        Currency argentino = new Currency("Peso Argentino","ARSS");
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

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        CurrencyConversionService currencyConversionService = new CurrencyConversionService();

        while (running) {
            System.out.println(menuManager.generateMenu());
            String choice = scanner.nextLine(); // Leer la opción del menú

            if (choice.equalsIgnoreCase("X")) {
                System.out.println("Saliendo...");
                running = false;
            } else {
                System.out.println("Ingrese la cantidad: ");
                try {
                    // Leer la cantidad como double
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consumir el carácter de nueva línea sobrante

                    // Validar opción seleccionada
                    ConversionOption selectedOption = menuManager.getOption(choice);
                    if (selectedOption == null) {
                        System.out.println("Opción no válida. Por favor, seleccione una opción del menú.");
                        continue; // Volver al inicio del bucle
                    }

                    String codeOrigin = "[" + selectedOption.getFromCurrency().getCode() + "]";
                    String codeFinal = "[" + selectedOption.getToCurrency().getCode() + "]";

                    // Realizar la conversión
                    ConversionResult conversionResult = currencyConversionService.getExchangeRate(
                            selectedOption.getFromCurrency().getCode(),
                            selectedOption.getToCurrency().getCode(),
                            amount
                    );
                    System.out.printf("El valor %.2f %s corresponde a %.2f %s.%n",
                            amount, codeOrigin, conversionResult.getAmountConverted(), codeFinal);

                } catch (ConversionServiceException e) {
                    System.out.println("Error al realizar la conversión: " + e.getMessage());
                    System.out.println("Por favor intente más tarde.");
                } catch (Exception e) {
                    System.out.println("Error inesperado: " + e.getMessage());
                }
            }
        }


    }
}
