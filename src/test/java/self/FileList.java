package self;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileList {
  @Test
  public void list()throws IOException {
    Path dir=Paths.get("h:","download");
    //*.{rar,zip}，看jdk文档**在win上用不成
    try(DirectoryStream directoryStream=Files.newDirectoryStream(dir,"*.{zip,rar}")){
      directoryStream.forEach(System.out::println);
    }
  }
}
