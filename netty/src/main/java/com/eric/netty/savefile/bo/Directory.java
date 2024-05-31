package com.eric.netty.savefile.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author eric
 * @date 5/26/2024
 */
@Data
public class Directory implements Serializable {

    private static final long serialVersionUID = -1L;

    private String name;

    private boolean directory;

    public Directory(String name, boolean directory) {
        this.name = name;
        this.directory = directory;
    }
}
