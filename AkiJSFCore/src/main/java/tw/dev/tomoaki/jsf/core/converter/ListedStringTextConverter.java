package tw.dev.tomoaki.jsf.core.converter;

/**
 *
 * @author tomoaki
 */

import java.io.Serializable;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "tw.dev.tomoaki.jsf.core.converter.ListedStringTextConverter", managed = true)
public class ListedStringTextConverter extends ListedDataTextConverter<String> implements Serializable {

    @Override
    protected String obtainDataText(String data) {
        return data;
    }
    
}
