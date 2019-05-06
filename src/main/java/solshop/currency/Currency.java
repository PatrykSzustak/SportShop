package solshop.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Currency {
    private String table;
    private String currency;
    private String code;
    private List<Rates> rates;
   public Currency(){

   }
}
