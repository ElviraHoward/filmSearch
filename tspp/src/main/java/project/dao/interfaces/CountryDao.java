package project.dao.interfaces;

import project.model.CountryEntity;

import java.util.List;

/**
 * Created by Elvira on 12.03.2017.
 */
public interface CountryDao {

    public void addCountry(CountryEntity country);
    public void updateCountry(CountryEntity country);
    public CountryEntity getCountryById(Integer idCountry);
    public List<CountryEntity> listCountries();
    public void deleteCountry(CountryEntity country);

}
