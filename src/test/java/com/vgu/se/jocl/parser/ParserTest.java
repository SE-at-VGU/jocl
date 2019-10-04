/**************************************************************************
Copyright 2019 Vietnamese-German-University

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

@author: thian
***************************************************************************/

package com.vgu.se.jocl.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.vgu.se.jocl.expressions.OclExp;
import com.vgu.se.jocl.parser.simple.SimpleParser;
import com.vgu.se.jocl.utils.UMLContextUtils;

public class ParserTest {

    private static JSONArray plainUMLContext;
    private static String oclExpStr;

    private static Map<Integer, Integer> testInt = new HashMap<>();

    static {
        String s = "[" + "{\"class\" : \"Car\","
                + "\"attributes\" : [{\"name\" : \"color\", \"type\" : \"String\"}]"
                + "}," + "{\"class\" : \"Person\","
                + "\"attributes\" : [{\"name\" : \"name\", \"type\" : \"String\"}]"
                + "}," + "{\"association\" : \"Ownership\","
                + "\"ends\" : [\"owners\", \"ownedCars\"],"
                + "\"classes\" : [\"Car\", \"Person\"]" + "}" + "]";

        try {
            plainUMLContext = (JSONArray) new JSONParser().parse(s);
            oclExpStr = "Car.allInstances() -> forAll(c|c.owners -> select(p|p.name='Hoang')->forAll(c|c.color = 'blue')) -> collect(d|d->forAll(d2|d2))";
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ParseException {

        ArrayList<String> parenthesesArray = new ArrayList<String>();
        SimpleParser parser = new SimpleParser();
        OclExp ocl = parser.parse(oclExpStr, plainUMLContext);
        System.out.println(ocl);

//        String s = "c|c";
//        String spacePatt = "^(.*)\\|(.*)$";
//        System.out.println("Matches : " + s.matches(spacePatt)+ "\nOriginal : \n" + s 
//                + "\nTrim : \n" + s.replaceAll("^(\\s)*|(\\s)*$", ""));
//        Matcher m = Pattern.compile(spacePatt).matcher(s);
//        if (m.find()) {
//            System.out.println("Original : \n" + s 
//                    + "\nTrim : \n" + s.replaceAll(spacePatt, ""));
//        }
    }

}
