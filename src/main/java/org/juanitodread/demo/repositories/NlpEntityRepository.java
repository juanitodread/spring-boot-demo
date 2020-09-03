package org.juanitodread.demo.repositories;

import org.juanitodread.demo.dtos.NlpEntity;
import org.postgresql.util.PGobject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class NlpEntityRepository extends JdbcDaoSupport {
    private final Logger logger = LoggerFactory.getLogger(NlpEntityRepository.class);

    private DataSource dataSource;

    @Autowired
    public NlpEntityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize(){
        setDataSource(this.dataSource);
    }

    public List<NlpEntity> findAll() {
        logger.info("Find all entities");

        String SQL = "SELECT * FROM nlp_entity";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(SQL);

        List<NlpEntity> result = new ArrayList<NlpEntity>();
        for(Map<String, Object> row: rows){
            NlpEntity entity = new NlpEntity();
            entity.setUuid((UUID) row.getOrDefault("id", ""));
            entity.setName((String) row.getOrDefault("name", ""));
            entity.setType((String) row.getOrDefault("type", ""));
            entity.setEnabled((Boolean) row.getOrDefault("enabled", false));
            var json = (PGobject) row.getOrDefault("definition", "");
            entity.setDefinition(json.getValue());
            entity.setCreated((Timestamp) row.getOrDefault("created_time", ""));
            entity.setUpdated((Timestamp) row.getOrDefault("updated_time", ""));

            result.add(entity);
        }

        logger.info("Find all entities completed");
        return result;
    }
}
