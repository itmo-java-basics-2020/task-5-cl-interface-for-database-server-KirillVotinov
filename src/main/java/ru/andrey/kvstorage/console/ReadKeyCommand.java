package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.console.DatabaseCommandResult.CommonDatabaseCommandResult;

public class ReadKeyCommand implements DatabaseCommand {
    private final ExecutionEnvironment env;
    private final String databaseName;
    private final String tableName;
    private final String key;

    public ReadKeyCommand(ExecutionEnvironment env,
                   String databaseName,
                   String tableName,
                   String key) {
        this.env = env;
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
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

    public String getKey() {
        return this.key;
    }

    @Override
    public DatabaseCommandResult execute() {
        return env.getDatabase(databaseName).map(database -> {
                    try {
                        return CommonDatabaseCommandResult.success(database.read(tableName, key));
                    } catch (DatabaseException e) {
                        return CommonDatabaseCommandResult.error(e.getMessage());
                    }
                }
        ).orElseGet(() -> CommonDatabaseCommandResult.error(
                String.format("Database with name \"%s\" doesn't exist", databaseName)
                )
        );
    }
}