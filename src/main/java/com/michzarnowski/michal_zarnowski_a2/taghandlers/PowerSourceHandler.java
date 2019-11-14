

package com.michzarnowski.michal_zarnowski_a2.taghandlers;

import com.michzarnowski.michal_zarnowski_a2.model.PowerSource;
import com.michzarnowski.michal_zarnowski_a2.model.PowerSourceDb;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class PowerSourceHandler extends SimpleTagSupport {
    
    @Override
    public void doTag()throws JspException {
        
        //Get JSP writer object
        JspWriter out = getJspContext().getOut();
        //Get all available power sources from PowerSourceDb class
        ArrayList<PowerSource> powerSources = PowerSourceDb.getPowerSources();
        //Format string for selectable options
        String optionFormat = "<option value='%s'>%s</option>";
        
        try {
            out.println("<select>");

            //Loop through list of power sources and print as options
            for(PowerSource source : powerSources){
                String sourceDesc = source.getDescription();
                out.println(String.format(optionFormat, sourceDesc, sourceDesc));
            }
            
            out.println("</select>");
        } catch (IOException ex) {
            System.out.println(ex);
        } 
    }

}
