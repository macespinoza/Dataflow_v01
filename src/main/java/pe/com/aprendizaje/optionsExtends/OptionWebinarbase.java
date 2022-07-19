/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.aprendizaje.optionsExtends;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.ValueProvider;

/**
 *
 * @author Miguel Cotrina
 */
public class OptionWebinarbase {
    public interface optionwebinarbase extends PipelineOptions {
        
        @Description("Input Cloud storage. ")
        @Default.String("gs://my-bucket/input.csv")
        ValueProvider<String> getInput();
        void setInput(ValueProvider<String> gcinput);
        
        @Description("Output Cloud storage. ")
        @Default.String("gs://my-bucket/output.csv")
        ValueProvider<String> getOutput();
        void setOutput(ValueProvider<String> gcoutput);

    }
}

