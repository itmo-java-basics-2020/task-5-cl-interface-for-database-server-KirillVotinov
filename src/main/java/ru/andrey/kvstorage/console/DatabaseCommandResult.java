package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    static class CommonDatabaseCommandResult implements DatabaseCommandResult {

        private final Optional<String> result;
        private String errorMessage;
        private final DatabaseCommandStatus status;

        private CommonDatabaseCommandResult(String result, DatabaseCommandStatus status) {
            this.result = Optional.ofNullable(result);
            this.status = status;
        }

        private CommonDatabaseCommandResult(String result, DatabaseCommandStatus status, String errorMessage) {
            this(result, status);
            this.errorMessage = errorMessage;
        }

        @Override
        public Optional<String> getResult() {
            return result;
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return status;
        }

        @Override
        public boolean isSuccess() {
            return status.equals(DatabaseCommandStatus.SUCCESS);
        }

        @Override
        public String getErrorMessage() {
            return errorMessage;
        }

        public static CommonDatabaseCommandResult success(String result) {
            return new CommonDatabaseCommandResult(result, DatabaseCommandStatus.SUCCESS);
        }

        public static CommonDatabaseCommandResult error(String message) {
            return new CommonDatabaseCommandResult(null, DatabaseCommandStatus.FAILED, message);
        }
    }
}