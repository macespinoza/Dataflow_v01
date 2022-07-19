/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.aprendizaje.pipeline;

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.Pipeline;

import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;

/**
 *
 * @author Miguel Cotrina
 * @version Apache Beam 2.38 | Esta versión quedará obsoleta a partir del 20 de abril de 2023.
 * Canalizacion basica
 */
public class SimplePipeline {
    public static void main(String[] args){
        
    // Definimos las opciones de pipeline
    PipelineOptions options = PipelineOptionsFactory.create();
    
    // Definimos las opciones desde la linea de comandos por argumentos
    // En este caso nos limitamos a las metodos de forma nativa
    // https://beam.apache.org/releases/javadoc/2.1.0/org/apache/beam/sdk/options/PipelineOptions.html
    //PipelineOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().create();
    // Creamos el pipeline
    Pipeline p = Pipeline.create(options);
    
    // ejecutamos el pipeline
    p.run().waitUntilFinish();
        
    }

}
