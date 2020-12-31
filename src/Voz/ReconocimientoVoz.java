package Voz;

import calculadoravoz.*;
import java.io.*;
import javax.swing.*;
import java.util.Locale;
import javax.speech.Central;
import javax.speech.EngineModeDesc;
import javax.speech.recognition.*;

public class ReconocimientoVoz extends ResultAdapter implements Runnable {

    Calculadora calculadora;
    static Recognizer reconocedorVoz;
    String palabra;

    public ReconocimientoVoz(Calculadora calculadora) {
        this.calculadora = calculadora;
    }
    @Override
    public void resultAccepted(ResultEvent re) {
        try {
            Result res = (Result) (re.getSource());
            ResultToken tokens[] = res.getBestTokens();

            for (int i = 0; i < tokens.length; i++) {
                palabra = tokens[i].getSpokenText();

                if (palabra.equals("uno")) {
                    this.calculadora.escribirEnPantalla("1");
                }
                if (palabra.equals("dos")) {
                    this.calculadora.escribirEnPantalla("2");
                }
                if (palabra.equals("tres")) {
                    this.calculadora.escribirEnPantalla("3");
                }
                if (palabra.equals("cuatro")) {
                    this.calculadora.escribirEnPantalla("4");
                }
                if (palabra.equals("cinco")) {
                    this.calculadora.escribirEnPantalla("5");
                }
                if (palabra.equals("seis")) {
                    this.calculadora.escribirEnPantalla("6");
                }
                if (palabra.equals("siete")) {
                    this.calculadora.escribirEnPantalla("7");
                }
                if (palabra.equals("ocho")) {
                    this.calculadora.escribirEnPantalla("8");
                }
                if (palabra.equals("nueve")) {
                    this.calculadora.escribirEnPantalla("9");
                }
                if (palabra.equals("cero")) {
                    this.calculadora.escribirEnPantalla("0");
                }
                if (palabra.equals("mas")) {
                    this.calculadora.undirBoton((byte) 1);
                }
                if (palabra.equals("menos")) {
                    this.calculadora.undirBoton((byte) 2);
                }
                if (palabra.equals("dividido")) {
                    this.calculadora.undirBoton((byte) 4);
                }
                if (palabra.equals("por")) {
                    this.calculadora.undirBoton((byte) 3);
                }
                if (palabra.equals("igual")) {
                    this.calculadora.undirIgual();
                }
                if (palabra.equals("borrar")) {
                    this.calculadora.borrarTodo();
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Carga el archivo ("c:/SimpleGrammarES2.txt") y para poder empezar un
     * proceso de comparacion gramatical y hacer los procesesos de
     * reconocimiento
     *
     * Importante: Se debe establecer un archivo ("c:/SimpleGrammarES2.txt"),
     * con un conjunto de expresiones gramaticales(Palabras) para poder generar
     * un proceso correcto de reconocimiento
     */
    public void reconocerVoz() {
        try {

            EngineModeDesc rec = new EngineModeDesc(Locale.ROOT);
            reconocedorVoz = Central.createRecognizer(rec);   
                        
            reconocedorVoz.allocate();
            
            FileReader gramatica = new FileReader("libreria\\diccionario.txt");

            RuleGrammar reglaGramatica = reconocedorVoz.loadJSGF(gramatica);
            reglaGramatica.setEnabled(true);
            
            reconocedorVoz.addResultListener(new ReconocimientoVoz(calculadora));
            reconocedorVoz.commitChanges();
            reconocedorVoz.requestFocus();
        } catch (Exception e) {
            System.out.println("Error en " + e.toString());
        }
    }
    @Override
    public void run() {
        this.reconocerVoz();
    }

}
