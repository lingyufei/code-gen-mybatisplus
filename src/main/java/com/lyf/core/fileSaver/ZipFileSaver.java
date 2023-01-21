package com.lyf.core.fileSaver;

import com.lyf.core.model.bo.StringWriterResultBo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
public class ZipFileSaver {
    public static byte[] Save(List<StringWriterResultBo> stringWriterResultBos){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        for (StringWriterResultBo stringWriterResultBo : stringWriterResultBos) {
            //添加到zip
            try {
                //渲染模板
                StringWriter sw = stringWriterResultBo.getStringWriter();
                zip.putNextEntry(new ZipEntry(stringWriterResultBo.getPath()));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
               log.warn("exception write to zip in ZipFileSaver, e: [{}], file: [{}]", e.getMessage(), stringWriterResultBo.getPath());
            }
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
