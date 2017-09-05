package project.dao.repository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.interfaces.CountryDao;
import org.springframework.stereotype.Repository;
import project.model.CountryEntity;

/**
 * Created by Elvira on 14.03.2017.
 */
@Repository
@Service
@Transactional
public class CountryImpl extends AbstractImpl implements CountryDao {

    private CountryDao countryDao;

    public CountryImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public CountryImpl() {
    }

    public void addCountry(CountryEntity country) {
        getSession().persist(country);
    }

    public void updateCountry(CountryEntity country) {
        getSession().update(country);
    }

    public CountryEntity getCountryById(Integer idCountry) {
        CountryEntity country = (CountryEntity) getSession().load(CountryEntity.class, new Integer(idCountry));
        return country;
    }

    public List<CountryEntity> listCountries() {
        List<CountryEntity> countryEntityList = getSession().createQuery("from CountryEntity").list();
        return countryEntityList;
    }

    public void deleteCountry(CountryEntity country) {
   //     CountryEntity country = (CountryEntity) getSession().load(CountryEntity.class, new Integer(idCountry));
        if (country != null) {
            getSession().delete(country);
        }

    }
}
