package com.training.spring.bigcorp.repository;

import com.training.spring.bigcorp.model.Captor;
import com.training.spring.bigcorp.model.Site;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class CaptorDaoImpl implements CaptorDao {
    private NamedParameterJdbcTemplate jdbcTemplate;
    public CaptorDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Captor> findBySiteId(String siteId) {
        return null;
    }

    @Override
    public void create(Captor captor) {
        jdbcTemplate.update("INSERT INTO Captor (ID, NAME, site_id) values( :id, :name, :site_id)",
                new MapSqlParameterSource()
                        .addValue("id", captor.getId())
                        .addValue("name", captor.getName())
            .addValue("site_id", captor.getSite().getId()));
}

    @Override
    public Captor findById(String s) {
        try{
            return jdbcTemplate.queryForObject(SELECT_WITH_JOIN + "where c.id = :id",
                    new MapSqlParameterSource().addValue("id", s),this::captorMapper);
        }catch(Exception e){
            return null;
        }
    }

    private static String SELECT_WITH_JOIN =
            "SELECT c.id, c.name, c.site_id, s.name as site_name " +
                    "FROM Captor c inner join Site s on c.site_id = s.id ";
    @Override
    public List<Captor> findAll() {
        return jdbcTemplate.query(SELECT_WITH_JOIN, this::captorMapper);
    }
    private Captor captorMapper(ResultSet rs, int rowNum) throws SQLException {
        Site site = new Site(rs.getString("site_name"));
        site.setId(rs.getString("site_id"));
        Captor captor = new Captor(rs.getString("name"), site);
        captor.setId(rs.getString("id"));
        return captor;
    }

    @Override
    public void update(Captor captor) {
        jdbcTemplate.update("update Captor set name = :name where id =:id",
                new MapSqlParameterSource()
                        .addValue("id", captor.getId())
                        .addValue("name", captor.getName()));
    }

    @Override
    public void deleteById(String s) {
            jdbcTemplate.update("DELETE FROM Captor c" +
                            " WHERE c.id = :id",
                    new MapSqlParameterSource()
                            .addValue("id", s));

    }
}
