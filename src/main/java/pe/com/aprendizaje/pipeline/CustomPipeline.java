/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.aprendizaje.pipeline;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import pe.com.aprendizaje.pipeline.CustomOptions.customoptions;

/**
 *
 * @author Miguel Cotrina
 */
public class CustomPipeline {
    public static void main(String[] args){
        PipelineOptionsFactory.register(customoptions.class);
        customoptions pipelineOptions = PipelineOptionsFactory.fromArgs(args).withValidation().as(customoptions.class);
        Pipeline p = Pipeline.create(pipelineOptions);
        // ejecutamos el pipeline
        p.run().waitUntilFinish();
    }
}
