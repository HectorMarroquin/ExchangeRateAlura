package com.alura.conversor.main;

import com.alura.conversor.exceptions.ConversionServiceException;
import com.alura.conversor.factory.MenuFactory;
import com.alura.conversor.models.ConversionOption;
import com.alura.conversor.models.ConversionResult;
import com.alura.conversor.models.Currency;
import com.alura.conversor.models.MenuManager;
import com.alura.conversor.services.CurrencyConversionService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MenuManager menuManager = MenuFactory.createMenuManager();

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

                if (!scanner.hasNextDouble()) {
                    System.out.println("Por favor, ingrese un número válido.");
                    scanner.nextLine(); // Consumir la entrada no válida
                    continue; // Regresar al inicio del bucle
                }
                try {
                    // Leer la cantidad como double
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consumir el carácter de nueva línea sobrante

                    // Validar opción seleccionada
                    ConversionOption selectedOption = menuManager.getOption(choice);

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

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue; // Volver al menú
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
