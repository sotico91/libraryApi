package com.library.libraryAPI.configuration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DatabaseCleanupTest {

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    @InjectMocks
    private DatabaseCleanup databaseCleanup;

    @Test
    void testCloseDataSource() throws SQLException {
        when(dataSource.getConnection()).thenReturn(connection);

        databaseCleanup.closeDataSource();

        verify(connection, times(1)).close();
    }
}