package snippet;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;

public class MetadataExtractorStudy01 {
  public static void main(String[] args) throws Exception {
    String imagePath = "D:\\dev_workspace\\java\\hg_project\\wechat-oa-hg\\src\\main\\webapp\\img\\104.jpeg";
    File mFile = new File(imagePath);
    Metadata metadata = ImageMetadataReader.readMetadata(mFile);
    // 获取所有项的信息
    Iterable<Directory> directoryIterable = metadata.getDirectories();
    if (directoryIterable == null) {
      return;
    }

    Iterator<Directory> directoryIterator = directoryIterable.iterator();
    while (directoryIterator.hasNext()) {
      Directory directory = directoryIterator.next();
      Collection<Tag> tags = directory.getTags();
      for (Tag tag : tags) {
        System.out.println("tag:" + tag);
      }
    }

    // 取具体某一项的信息
    Iterable<ExifSubIFDDirectory> exifSubIFDDirectoryIterable = metadata
        .getDirectoriesOfType(ExifSubIFDDirectory.class);
    if (exifSubIFDDirectoryIterable == null) {
      return;
    }
    Iterator<ExifSubIFDDirectory> subIFDDirectoryIterator = exifSubIFDDirectoryIterable.iterator();
    if (subIFDDirectoryIterator != null && subIFDDirectoryIterator.hasNext()) {
      ExifSubIFDDirectory dr = subIFDDirectoryIterator.next();
      if (dr.containsTag(ExifSubIFDDirectory.TAG_USER_COMMENT)) {
        System.out.println(dr.getDescription(ExifSubIFDDirectory.TAG_USER_COMMENT));
      }
    }
  }
}