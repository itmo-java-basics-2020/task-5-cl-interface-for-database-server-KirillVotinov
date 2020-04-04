package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.logic.Database;

import static ru.andrey.kvstorage.console.DatabaseCommandResult.CommonDatabaseCommandResult;

public class CreateDatabaseCommand implements DatabaseCommand {
    private final ExecutionEnvironment env;
    private final String databaseName;
    private final Database database;

    public ExecutionEnvironment getEnv() {
        return this.env;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public Database getDatabase() {
        return this.database;
    }

    public CreateDatabaseCommand(ExecutionEnvironment env, String databaseName, Database database) {
        this.env = env;
        this.databaseName = databaseName;
        this.database = database;
    }

    @Override
    public DatabaseCommandResult execute() {
        return env.getDatabase(databaseName).map(x ->
                CommonDatabaseCommandResult.error(
                        String.format("Database with name \"%s\" already exists", databaseName)
                )
        ).orElseGet(() -> {
            env.addDatabase(database);
            return CommonDatabaseCommandResult.success(
                    String.format("Database \"%s\" is created successfully", databaseName)
            );
        });
    }
}