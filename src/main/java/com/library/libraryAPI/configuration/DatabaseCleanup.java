package com.library.libraryAPI.configuration;

import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
@AllArgsConstructor
public class DatabaseCleanup {

    private final DataSource dataSource;

    @PreDestroy
    public void closeDataSource() throws SQLException {
        if (dataSource != null) {
            dataSource.getConnection().close();
        }
    }

}
