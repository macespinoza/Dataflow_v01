/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.aprendizaje.pipeline;


import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;

/**
 *
 * @author Miguel Cotrina
 */
public class argsPipeline {
        public static void main(String[] args){

        PipelineOptions pipelineOptions = PipelineOptionsFactory.fromArgs(args).withValidation().create();

        Pipeline p = Pipeline.create(pipelineOptions);

        // ejecutamos el pipeline
        p.run().waitUntilFinish();
        
    }
}
