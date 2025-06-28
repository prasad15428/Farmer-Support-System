package com.project.Farmer.Support.System.Exception;

public class CropDetailsNotFoundInDataBase extends RuntimeException {
    public CropDetailsNotFoundInDataBase(String message) {
        super(message);
    }
}
