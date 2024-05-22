package JsonModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    public int ID;

    @JsonProperty("First Name")
    public String FirstName;

    @JsonProperty("Second Name")
    public String SecondName;

    public String Birthday;
    public String[] Email;
    public String City;
}
