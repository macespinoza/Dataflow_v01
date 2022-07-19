/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.aprendizaje.pipeline;
import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptionsFactory;

/**
 *
 * @author Miguel Cotrina
 */
public class AsPipeline {
        public static void main(String[] args){

        DataflowPipelineOptions pipelineOptions=PipelineOptionsFactory.as(DataflowPipelineOptions.class); 
        pipelineOptions.setProject("nombre-proyecto");

        Pipeline p = Pipeline.create(pipelineOptions);

        // ejecutamos el pipeline
        p.run().waitUntilFinish();
        
    }
}
