package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import static ru.andrey.kvstorage.console.DatabaseCommandResult.CommonDatabaseCommandResult;

public class CreateTableCommand implements DatabaseCommand {
    private final ExecutionEnvironment env;
    private final String databaseName;
    private final String tableName;

    public CreateTableCommand(ExecutionEnvironment env, String databaseName, String tableName) {
        this.env = env;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    public ExecutionEnvironment getEnv() {
        return this.env;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public String getTableName() {
        return this.tableName;
    }

    @Override
    public DatabaseCommandResult execute() {
        return env.getDatabase(databaseName).map(database -> {
            try {
                database.createTableIfNotExists(tableName);
                return CommonDatabaseCommandResult.success(
                        String.format("Table \"%s\" in database \"%s\" is created successfully", tableName, databaseName)
                );
            } catch (DatabaseException ex) {
                return CommonDatabaseCommandResult.error(ex.getMessage());
            }
        }).orElseGet(() -> CommonDatabaseCommandResult.error(
                String.format("Database \"%s\" doesn't exist", databaseName)
        ));
    }
}