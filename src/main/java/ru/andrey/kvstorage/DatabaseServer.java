package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.DatabaseCommandResult.CommonDatabaseCommandResult;
import ru.andrey.kvstorage.console.listOfDatabaseCommand;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;

import java.util.Arrays;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {
    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        String currentCommandName = null;
        try {
            if (commandText == null) {
                throw new IllegalArgumentException();
            }
            String[] parametrsOfCommand = commandText.split(" ");
            currentCommandName = parametrsOfCommand[0];
            String[] args = Arrays.copyOfRange(parametrsOfCommand, 1, parametrsOfCommand.length);
            return listOfDatabaseCommand.valueOf(currentCommandName).getCommand(env, args).execute();
        } catch (IllegalArgumentException ex) {
            return CommonDatabaseCommandResult.error(
                    String.format("Command \"%s\" doesn't exist", currentCommandName)
            );
        } catch (DatabaseException ex) {
            return CommonDatabaseCommandResult.error(ex.getMessage());
        }
    }
}