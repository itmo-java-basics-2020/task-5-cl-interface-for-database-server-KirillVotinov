package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.console.DatabaseCommandResult.CommonDatabaseCommandResult;
import ru.andrey.kvstorage.exception.DatabaseException;

public class UpdateKeyCommand implements DatabaseCommand {

    private final ExecutionEnvironment env;
    private final String databaseName;
    private final String tableName;
    private final String key;
    private final String value;

    public UpdateKeyCommand(ExecutionEnvironment currentEnv,
                     String currentDatabaseName,
                     String currentTableName,
                     String currentKey,
                     String currentValue) {
        this.env = currentEnv;
        this.databaseName = currentDatabaseName;
        this.tableName = currentTableName;
        this.key = currentKey;
        this.value = currentValue;
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

    public String getValue() {
        return this.value;
    }

    @Override
    public DatabaseCommandResult execute() {
        return env.getDatabase(databaseName).map(database -> {
            try {
                database.write(tableName, key, value);
                return CommonDatabaseCommandResult.success(
                        String.format("Key \"%s\" has value \"%s\" in table \"%s\" in database \"%s\"",
                                key,
                                value,
                                tableName,
                                databaseName)
                );
            } catch (DatabaseException ex) {
                return CommonDatabaseCommandResult.error(ex.getMessage());
            }
        }).orElseGet(() ->
                CommonDatabaseCommandResult.error(
                        String.format("Database with name \"%s\" doesn't exist", databaseName)
                )
        );
    }
}