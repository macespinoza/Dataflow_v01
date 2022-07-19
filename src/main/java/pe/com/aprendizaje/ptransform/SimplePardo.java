/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.aprendizaje.ptransform;

import java.util.Arrays;
import java.util.List;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.coders.StringUtf8Coder;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;

/**
 *
 * @author Miguel Cotrina
 */
public class SimplePardo {
    
    static class ComputeWordLengthFn extends DoFn<String, String> {
        @ProcessElement
        public void processElement(@Element String word, OutputReceiver<String> out) {
          // Use OutputReceiver.output para trasmitir los elementos de salida
          out.output( Integer.toString( word.length()));
        }
    }
    
    public static void main(String[] args){
        
        /* ----------------------------------------------------------------------------------------- */        
        //Creacion del Pipeline
        PipelineOptions options = PipelineOptionsFactory.create();
        Pipeline p = Pipeline.create(options);

        /* ----------------------------------------------------------------------------------------- */ 
        //Variable de cadena de texto
        final String inputString =  "Cultivo una rosa blanca\n" +
                                    "En Junio como en Enero,\n" +
                                    "Para el amigo sincero,\n" +
                                    "Que me da su mano franca.\n" +
                                    " \n" +
                                    "Y para el cruel que me arranca\n" +
                                    "El corazón con que vivo,\n" +
                                    "Cardo ni ortiga cultivo\n" +
                                    "cultivo una rosa blanca.";
        
        /* ----------------------------------------------------------------------------------------- */ 
        //Metodo 01
        PCollection<String> inputMemory = p.apply("Leer datos en memoria",Create.of(inputString)).setCoder(StringUtf8Coder.of());
        PCollection<String> outputMemory = inputMemory.apply("Contar numero de palabras", ParDo.of(new ComputeWordLengthFn()));
        outputMemory.apply("Escribir Salida", TextIO.write().to("salida01").withSuffix(".csv").withoutSharding()); 
        // withoutSharding Generar un archivo unico
        
        //Metodo 02 concatenado
        p.apply("Leer datos en memoria",Create.of(inputString)).setCoder(StringUtf8Coder.of())
         .apply("Contar numero de letras", ParDo.of(new ComputeWordLengthFn()))
         .apply("Escribir Salida", TextIO.write().to("salida02").withSuffix(".csv").withoutSharding());
        
        //Metodo 03 Lightweight DoFns
        p.apply("Leer datos en memoria",Create.of(inputString)).setCoder(StringUtf8Coder.of())
         .apply("Contar numero de letras", ParDo.of(  new DoFn<String, String>() {    // a DoFn Instancia ligera
            @ProcessElement
            public void processElement(@Element String word, OutputReceiver<String> out) {
              out.output(Integer.toString(word.length()));
            }
          }))
         .apply("Escribir Salida", TextIO.write().to("salida03").withSuffix(".csv").withoutSharding());
        
        /* ----------------------------------------------------------------------------------------- */ 
        //Ejecucion del pipeline
        p.run().waitUntilFinish();
        
    }
}
