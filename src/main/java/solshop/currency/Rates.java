package solshop.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {

    private String no;
    private String effectiveDate;
    private Double bid;
    private Double ask;

    public Rates(){

    }

}
