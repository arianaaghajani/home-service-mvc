package ir.ariana.home_service_mvc.validation;


import ir.ariana.home_service_mvc.exception.WrongImageInputException;
import org.springframework.stereotype.Component;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class TakeAndCheckImage {
    public byte[] specialistImage(String path) {
        byte[] image = new byte[0];
        File inputImage = new File(path);
        String mimetype = new MimetypesFileTypeMap().getContentType(inputImage);
        String type = mimetype.split("/")[0];
        if (type.equals("image")) {
            try {
                image = Files.readAllBytes(inputImage.toPath());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return image;
        } else
            throw new WrongImageInputException("this file is not jpg");
    }

    public void saveSpecialistImageToHDD(byte[] image, String firstName, String lastName) {
        String path = "C:\\\\Users\\\\ASUS\\\\Downloads\\\\20240705_102701_2003944260.jpg" + firstName + " " + lastName;
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(image);
        } catch (IOException e) {
            throw new WrongImageInputException("can not save image");
        }
    }
}
