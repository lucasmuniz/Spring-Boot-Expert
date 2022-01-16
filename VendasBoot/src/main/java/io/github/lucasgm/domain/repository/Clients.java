package io.github.lucasgm.domain.repository;

import io.github.lucasgm.domain.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clients {

    private static final String INSERT = "INSERT INTO CLIENTE (NOME) VALUES (?) ";
    private static final String SELECT_ALL = "SELECT * FROM CLIENTE ";
    private static final String UPDATE = "UPDATE CLIENTE SET NOME = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM CLIENTE WHERE ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Client save(Client client) {
        jdbcTemplate.update(INSERT, new Object[]{client.getName()});
        return client;
    }

    public Client update(Client client) {
        jdbcTemplate.update(UPDATE, new Object[]{client.getName(), client.getId()});
        return client;
    }

    public void delete(Integer id) {
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    public List<Client> getByName(String name) {
        return jdbcTemplate.query(SELECT_ALL.concat("WHERE NOME LIKE ? "), new Object[]{"%" + name + "%"}, getClientRowMapper());
    }

    public List<Client> getAll() {
        return jdbcTemplate.query(SELECT_ALL, getClientRowMapper());
    }

    private RowMapper<Client> getClientRowMapper() {
        return new RowMapper<Client>() {
            @Override
            public Client mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Client(resultSet.getInt("id"), resultSet.getString("nome"));
            }
        };
    }

}
