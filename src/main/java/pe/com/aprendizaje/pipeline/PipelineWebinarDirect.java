/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.aprendizaje.pipeline;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.coders.StringUtf8Coder;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import pe.com.aprendizaje.ptransform.SimplePardo;

/**
 *
 * @author Miguel Cotrina
 */
public class PipelineWebinarDirect {
    
    static class ComputeWordLengthFn extends DoFn<String, String> {
        @DoFn.ProcessElement
        public void processElement(@DoFn.Element String word, DoFn.OutputReceiver<String> out) {
          // Use OutputReceiver.output para trasmitir los elementos de salida
          out.output( Integer.toString( word.length()));
        }
    }
    
    public static void main(String[] args){
        
        /* ----------------------------------------------------------------------------------------- */        
        //Creacion del Pipeline
        PipelineOptions options = PipelineOptionsFactory.create();
        Pipeline p = Pipeline.create(options);
        
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
        //Pipeline
        
        /* Tomamos los datos */
        
        PCollection<String> inputMemory = 
                p.apply("Leer datos en memoria",Create.of(inputString)).setCoder(StringUtf8Coder.of());
        
        /* Procesamos los dato */
        PCollection<String> outputMemory = 
                inputMemory.apply("Contar numero de palabras", ParDo.of(new PipelineWebinarDirect.ComputeWordLengthFn()));
       
        /* eneramos elementos de salida */
        outputMemory.apply("Escribir Salida", TextIO.write().to("salida_lab180722").withSuffix(".csv").withoutSharding()); 
        // withoutSharding Generar un archivo unico
        
        //Ejecucion de flujo
        p.run().waitUntilFinish();
        /*
        Para ejecutar esto debemos seguir ubicarnos en el directorio de trabajo
        mvn clean compile
        mvn compile exec:java -Dexec.mainClass=pe.com.aprendizaje.pipeline.PipelineWebinarDirect -Dexec.cleanupDaemonThreads=false -Dexec.args="--runner=DirectRunner"
        */
        
    }
}
