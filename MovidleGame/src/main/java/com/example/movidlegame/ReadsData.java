package com.example.movidlegame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadsData {
    public static List<Film> ReadingData(){
        String path = "imdb_top_250.csv";
        String line = "";
        String[] val = new String[0];
        Film films;
        List<Film> filmList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "ISO-8859-1"));
            br.readLine();

            while ((line = br.readLine()) != null) {
                val = line.split(";");

                String title = val[1];
                int year = Integer.parseInt(val[2]);
                String genre = val[3];
                String director = val[4];
                String origin = val[5];
                String star = val[6];

                films = new Film(title,year,genre,director,origin,star);
                filmList.add(films);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filmList;
    }
}

