package ru.qrnpr.qrcore.fileutils;

import java.io.InputStream;

/**
 * Created by vadim on 23.12.14.
 */
public interface IFileSave {
    public String saveFile(InputStream fileInputStream, String fileName);
}
