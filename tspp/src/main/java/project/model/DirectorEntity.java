package project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Elvira on 07.03.2017.
 */
@Entity
@Table(name = "director", schema = "db_tsp")
public class DirectorEntity {
    private int idDirector;
    private String name;
    private int country;
    private CountryEntity directorByCountry;
    private Collection<FilmEntity> filmsByIdDirector;

    @Id
    @GenericGenerator(name = "num", strategy = "increment")
    @GeneratedValue(generator = "num")
    @Column(name = "id_director", nullable = false)
    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "country", nullable = false)
    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DirectorEntity that = (DirectorEntity) o;

        if (idDirector != that.idDirector) return false;
        if (country != that.country) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDirector;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + country;
        return result;
    }

    @Override
    public String toString() {
        return  name;
    }

    @ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @JoinColumn(name = "country", referencedColumnName = "id_country", nullable = false, insertable = false, updatable = false)
    public CountryEntity getDirectorByCountry() {
        return directorByCountry;
    }

    public void setDirectorByCountry(CountryEntity directorByCountry) {
        this.directorByCountry = directorByCountry;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "filmByDirector")
    public Collection<FilmEntity> getFilmsByIdDirector() {
        return filmsByIdDirector;
    }

    public void setFilmsByIdDirector(Collection<FilmEntity> filmsByIdDirector) {
        this.filmsByIdDirector = filmsByIdDirector;
    }
}
