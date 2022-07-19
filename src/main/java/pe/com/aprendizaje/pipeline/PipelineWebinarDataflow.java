/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.aprendizaje.pipeline;

import java.util.Arrays;
import java.util.List;
import org.apache.beam.runners.dataflow.DataflowRunner;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import pe.com.aprendizaje.optionsExtends.OptionWebinarbase;

/**
 *
 * @author Miguel Cotrina
 */
public class PipelineWebinarDataflow {
    
    static class ComputeWordLengthFn extends DoFn<String, String> {
        @DoFn.ProcessElement
        public void processElement(@DoFn.Element String word, DoFn.OutputReceiver<String> out) {
          // Use OutputReceiver.output para trasmitir los elementos de salida
          out.output( Integer.toString( word.length()));
        }
    }
    
    public static void main(String[] args){
        OptionWebinarbase.optionwebinarbase pipelineOptions =
                PipelineOptionsFactory.fromArgs(args).as(OptionWebinarbase.optionwebinarbase.class);
        run(pipelineOptions);
        
    }
    private static PipelineResult run(OptionWebinarbase.optionwebinarbase options) {
        Pipeline pipeline = Pipeline.create(options);
        PCollection<String> inputGCE = pipeline.apply("Lectura en GCS", TextIO.read().from(options.getInput()));
        
        /* Procesamos los dato */
        PCollection<String> outputMemory = inputGCE.apply("Contador", ParDo.of(new PipelineWebinarDataflow.ComputeWordLengthFn()));
                
        /* eneramos elementos de salida */
        outputMemory.apply("Escritura en GCS", TextIO.write().to(options.getOutput()).withSuffix(".csv").withoutSharding()); 
        // withoutSharding Generar un archivo unico

        return pipeline.run();
        
        /*
        Genera plantilla
        mvn clean compile
        mvn compile exec:java -Dexec.mainClass=pe.com.aprendizaje.pipeline.PipelineWebinarDataflow -Dexec.cleanupDaemonThreads=false -Dexec.args=" --project=ci-datalake-dev --region=us-west4 --stagingLocation=gs://labdataflow-356804-config/temp_staging/staging --tempLocation=gs://labdataflow-356804-config/temp_template --templateLocation=gs://labdataflow-356804-config/templates/labtemplatev1.json --runner=DataflowRunner"
        Ejecuta plantilla
        gcloud dataflow jobs run labpractico --gcs-location gs://labdataflow-356804-config/templates/labtemplatev1.json --region us-west4 --parameters input=gs://labdataflow-356804-input/origen.csv,output=gs://labdataflow-356804-output/total
        
        */
    }
        
}
