package com.example.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/execute-sql")
    public List<Map<String, Object>> executeSql(@RequestBody SqlRequest request) {
        return jdbcTemplate.queryForList(request.getQuery());
    }

    @GetMapping("/get-all-info")
    public List<Map<String, Object>> getAllInfo() {
        return jdbcTemplate.queryForList("SELECT * FROM info_table");
    }

    @PostMapping("/add-description")
    public void addDescription(@RequestBody String description) {
        jdbcTemplate.update("INSERT INTO info_table (description) VALUES (?)", description);
    }

    @PostMapping("/delete-by-id")
    public void deleteById(@RequestBody Long id) {
        jdbcTemplate.update("DELETE FROM info_table WHERE id = ?", id);
    }

}