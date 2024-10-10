package se331.lab.rest.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import jakarta.servlet.ServletException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Component
public class CloudStorageHelper {
    private static Storage storage = null;
    static {
        InputStream serviceAccount =
                null;
        try {
            serviceAccount = new ClassPathResource("key.json").getInputStream();
                storage = StorageOptions.newBuilder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setProjectId("imageupload-8d680")
                        .build().getService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // End init
    public String uploadFile(MultipartFile filepart, final String bucketname) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dtString = sdf.format(new Date());
        final String fileName = dtString + "-" + filepart.getOriginalFilename();
        InputStream is = filepart.getInputStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] readBuf = new byte[4096];
        while (is.available() > 0) {
            int bytesRead = is.read(readBuf);
            os.write(readBuf, 0, bytesRead);
        }
        // Convert ByteArrayOutputStream into Bute[]
        BlobInfo blobInfo =
                storage.create(
                        BlobInfo
                                .newBuilder(bucketname, fileName)
                        // Modify access list to allow all user with link to read file
                                .setAcl(new
                                        ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                                .setContentType(filepart.getContentType())
                                .build(),
                        os.toByteArray());
        return blobInfo.getMediaLink();
    }
        public String getImageUrl(MultipartFile file, String bucket) throws IOException, ServletException {
            final String fileName = file.getOriginalFilename();
            // Check extension of file
            if (fileName != null && !fileName.isEmpty() && fileName.contains(".")) {
                final String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
                String[] allowedExt = {"jpg", "jpeg", "png", "gif"};
                for (String s : allowedExt) {
                    if (extension.equals(s)) {
                        return uploadFile(file, bucket);
                    }
                }
                throw new ServletException("file must be image");
            }
            return null;
        }
}
