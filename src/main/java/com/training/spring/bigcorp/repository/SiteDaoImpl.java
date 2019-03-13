package com.training.spring.bigcorp.repository;

import com.training.spring.bigcorp.model.Site;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SiteDaoImpl implements SiteDao {
    private NamedParameterJdbcTemplate jdbcTemplate;
    public SiteDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Site site) {
        jdbcTemplate.update("INSERT INTO Site (ID, NAME) values( :id, :name)",
                new MapSqlParameterSource()
                        .addValue("id", site.getId())
                        .addValue("name", site.getName()));
    }

    @Override
    public Site findById(String s) {
        try{
            return jdbcTemplate.queryForObject(SELECT_WITH_JOIN + "where s.id = :id",
                    new MapSqlParameterSource().addValue("id", s),this::siteMapper);
        }catch(Exception e){
            return null;
        }
    }

    private static String SELECT_WITH_JOIN =
            "SELECT s.id, s.name " +
                    "FROM Site s  ";
    @Override
    public List<Site> findAll() {
        return jdbcTemplate.query(SELECT_WITH_JOIN, this::siteMapper);
    }
    private Site siteMapper(ResultSet rs, int rowNum) throws SQLException {
        Site site = new Site(rs.getString("name"));
        site.setId(rs.getString("id"));;
        return site;
    }

    @Override
    public void update(Site site) {
        jdbcTemplate.update("update SITE set name = :name where id =:id",
                new MapSqlParameterSource()
                        .addValue("id", site.getId())
                        .addValue("name", site.getName()));
    }

    @Override
    public void deleteById(String s) {
        jdbcTemplate.update("DELETE FROM Site c" +
                        " WHERE c.id = :id",
                new MapSqlParameterSource()
                        .addValue("id", s));
    }
}