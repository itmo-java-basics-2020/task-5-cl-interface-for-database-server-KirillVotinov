package ru.andrey.kvstorage.logic;

import ru.andrey.kvstorage.exception.DatabaseException;

public class ExampleOfDatabase implements Database{
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void createTableIfNotExists(String tableName) throws DatabaseException {

    }

    @Override
    public void createTableIfNotExists(String tableName, int segmentSizeInBytes) throws DatabaseException {

    }

    @Override
    public void write(String tableName, String objectKey, String objectValue) throws DatabaseException {

    }

    @Override
    public String read(String tableName, String objectKey) throws DatabaseException {
        return null;
    }
}