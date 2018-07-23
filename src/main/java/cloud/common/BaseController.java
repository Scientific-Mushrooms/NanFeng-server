package cloud.common;

import cloud.common.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequestMapping(("/api"))
public class BaseController {

    @Autowired
    private UserRepository userRepository;

    public String currentPath = "/home/backend/upload/";

    public Date stringToDate(String paramDate) throws ParseException {
        String realDate = paramDate.substring(1, 20) + ".000+0000";
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date date = sdf.parse(realDate);
        return date;
    }

    public String generateUUID() {

        String result = String.valueOf(UUID.randomUUID());

        return result;
    }

}
