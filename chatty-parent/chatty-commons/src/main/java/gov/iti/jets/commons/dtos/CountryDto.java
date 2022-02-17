package gov.iti.jets.commons.dtos;

import gov.iti.jets.commons.util.ValidationUtil;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class CountryDto implements Serializable {

    @NotNull
    private int countryId;

    @NotNull
    private String countryName;

    public CountryDto( int countryId, String countryName ) {
        this.countryId = countryId;
        this.countryName = countryName;

        ValidationUtil.getInstance().validate( this );
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId( int countryId ) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName( String countryName ) {
        this.countryName = countryName;
    }
}
