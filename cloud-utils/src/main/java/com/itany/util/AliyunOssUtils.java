package com.itany.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.itany.constants.AliyunOssConsts;
import com.itany.constants.AppConsts;
import com.itany.constants.AppExceptionMsgEnum;
import com.itany.exception.AppException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * AliyunOssUtils.
 *
 * @author Thou
 * @date 2022/10/22
 */
public class AliyunOssUtils {

    private static final String BUCKET_NAME = AliyunOssConsts.BUCKET_NAME;
    private static final String ENDPOINT = AliyunOssConsts.ENDPOINT;
    private static final String ACCESS_KEY_ID = AliyunOssConsts.ACCESS_KEY_ID;
    private static final String ACCESS_KEY_SECRET = AliyunOssConsts.ACCESS_KEY_SECRET;

    /**
     * 上传文件到阿里云 OSS
     *
     * @param file -
     * @return java.lang.String
     * @author Thou
     * @date 2022/10/22
     */
    public static String ossUploadFile(MultipartFile file) {
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new AppException(AppExceptionMsgEnum.FILE_UPLOAD_ERROR);
        }

        String filename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        filename = uuid + filename;
        LocalDate localDate = LocalDate.now();
        String datePath = DateTimeFormatter.ofPattern(AppConsts.FORMAT_DATE_UPLOAD).format(localDate);
        filename = datePath + "/" + filename;

        ossClient.putObject(BUCKET_NAME, filename, inputStream);
        ossClient.shutdown();
        return "/" + filename;
    }
}
