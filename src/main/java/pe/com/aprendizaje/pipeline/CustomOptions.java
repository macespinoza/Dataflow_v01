/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.aprendizaje.pipeline;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;

/**
 *
 * @author Miguel Cotrina
 */
public class CustomOptions {
    public interface customoptions extends PipelineOptions{
        @Description("Descripcion de el texto 1")
        @Default.String("texto1")
        String getTexto1();
        void setTexto1(String texto1);
        
        @Description("Descripcion de el texto 1")
        @Default.String("texto2")
        String getTexto2();
        void setTexto2(String texto2);
    }
}
