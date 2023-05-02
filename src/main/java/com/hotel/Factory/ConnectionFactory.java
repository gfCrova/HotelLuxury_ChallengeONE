package com.hotel.Factory;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.hotel.Services.configService;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
private final DataSource dataSource;
	
	// Inicializando Pool de Conexiones
    public ConnectionFactory() {

		configService load = new configService();
		load.obtenerCofiguracion();

    	var pooledDataSource = new ComboPooledDataSource();
    	pooledDataSource.setJdbcUrl(load.getUrl());
    	pooledDataSource.setUser(load.getUsername());
    	pooledDataSource.setPassword(load.getPassword());
    	
    	this.dataSource = pooledDataSource;
	}

	public Connection recuperateConnection() {
		
        try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }
}
