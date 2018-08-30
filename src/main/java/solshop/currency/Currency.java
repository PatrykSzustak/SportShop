package solshop.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.client.RestTemplate;

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
