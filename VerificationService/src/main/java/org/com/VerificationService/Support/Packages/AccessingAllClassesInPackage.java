package org.com.VerificationService.Support.Packages;

//reference: https://www.baeldung.com/java-find-all-classes-in-package

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * note: packageName should be, for instance, org.com. ... .YourPackageName
 */
public class AccessingAllClassesInPackage
{
    public List<Class> findAllClassOfAPackage(String packageName)
    {
        /**
         * In this method, we're initiating the SubTypesScanner class and fetching all subtypes of the Object class.
         * Through this approach, we get more granularity when fetching the classes.
         */
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        List<Class> listOfClasses = reflections.getSubTypesOf(Object.class).stream().collect(Collectors.toList());
        return listOfClasses;
    }

    public List<String> findAllClassNameOfAPackage(String packageName)
    {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        List<Class> listOfClasses = reflections.getSubTypesOf(Object.class).stream().collect(Collectors.toList());
        List<String> result = new ArrayList<>();
        for(int i=0; i < listOfClasses.size(); i++)
        {
            //provide full path of the package's name
            result.add(listOfClasses.get(i).getName());
        }
        return result;
    }
}
