/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.aprendizaje.pcollection;

import java.util.Arrays;
import java.util.List;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.coders.StringUtf8Coder;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.values.PCollection;

/**
 *
 * @author Miguel Cotrina
 */
public class PCollectionSamples {
    public static void main(String[] args){
        
        //Creacion del Pipeline
        PipelineOptions options = PipelineOptionsFactory.create();
        Pipeline p = Pipeline.create(options);
        
        //Metodo de ingesta en memoria
        final List<String> inputArray = Arrays.asList(
        "To be, or not to be: that is the question: ",
        "Whether 'tis nobler in the mind to suffer ",
        "The slings and arrows of outrageous fortune, ",
        "Or to take arms against a sea of troubles, ");

        PCollection<String> inputMemory = p.apply("Leer datos en memoria",Create.of(inputArray)).setCoder(StringUtf8Coder.of());

        //Metodo de ingesta de origen externa
        PCollection<String> inputExternal = p.apply("Leer cloud storage", TextIO.read().from("gs://some/inputData.txt"));
        

        
        
        
    }
}
