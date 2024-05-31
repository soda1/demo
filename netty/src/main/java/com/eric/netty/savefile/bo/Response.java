package com.eric.netty.savefile.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author eric
 * @date 6/1/2024
 */
@Data
public class Response implements Serializable {

    private String currentDirectory;

    List<Directory> directories;

    public Response(String currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public Response(List<Directory> directories) {
        this.directories = directories;
    }
}
