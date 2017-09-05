package project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Elvira on 07.03.2017.
 */
@Entity
@Table(name = "country", schema = "db_tsp")
public class CountryEntity {
    private int idCountry;
    private String name;
    private Collection<ActorEntity> actorsByIdCountry;
    private Collection<DirectorEntity> directorsByIdCountry;
    private Collection<FilmEntity> filmsByIdCountry;

    @Id
    @GenericGenerator(name = "num", strategy = "increment")
    @GeneratedValue(generator = "num")
    @Column(name = "id_country", nullable = false)
    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryEntity that = (CountryEntity) o;

        if (idCountry != that.idCountry) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCountry;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actorByCountry")
    public Collection<ActorEntity> getActorsByIdCountry() {
        return actorsByIdCountry;
    }

    public void setActorsByIdCountry(Collection<ActorEntity> actorsByIdCountry) {
        this.actorsByIdCountry = actorsByIdCountry;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "directorByCountry")
    public Collection<DirectorEntity> getDirectorsByIdCountry() {
        return directorsByIdCountry;
    }

    public void setDirectorsByIdCountry(Collection<DirectorEntity> directorsByIdCountry) {
        this.directorsByIdCountry = directorsByIdCountry;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "filmByCountry")
    public Collection<FilmEntity> getFilmsByIdCountry() {
        return filmsByIdCountry;
    }

    public void setFilmsByIdCountry(Collection<FilmEntity> filmsByIdCountry) {
        this.filmsByIdCountry = filmsByIdCountry;
    }
}
